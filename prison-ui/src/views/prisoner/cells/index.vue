<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="囚犯ID" prop="cellId">
        <el-input
          v-model="queryParams.cellId"
          placeholder="请输入囚犯ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="监区号" prop="blockNumber">
        <el-select v-model="queryParams.blockNumber" placeholder="请选择监区号" clearable>
          <el-option
            v-for="dict in dict.type.ward_zoning"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="牢房号" prop="cellNumber">
        <el-input
          v-model="queryParams.cellNumber"
          placeholder="请输入牢房号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
        round
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['prisoner:cells:add']"
        >新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="success"
          round
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['prisoner:cells:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          round
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['prisoner:cells:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          round
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['prisoner:cells:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cellsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="囚犯ID" align="center" prop="cellId" />
      <el-table-column label="监区号" align="center" prop="blockNumber">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.ward_zoning" :value="scope.row.blockNumber"/>
        </template>
      </el-table-column>
      <el-table-column label="牢房号" align="center" prop="cellNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['prisoner:cells:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['prisoner:cells:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改牢房信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="监区号" prop="blockNumber">
          <el-select v-model="form.blockNumber" placeholder="请选择监区号">
            <el-option
              v-for="dict in dict.type.ward_zoning"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="牢房号" prop="cellNumber">
          <el-input v-model="form.cellNumber" placeholder="请输入牢房号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCells, getCells, delCells, addCells, updateCells } from "@/api/prisoner/cells";

export default {
  name: "Cells",
  dicts: ['ward_zoning'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 牢房信息表格数据
      cellsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cellId: null,
        blockNumber: null,
        cellNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        blockNumber: [
          { required: true, message: "监区号不能为空", trigger: "blur" }
        ],
        cellNumber: [
          { required: true, message: "牢房号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询牢房信息列表 */
    getList() {
      this.loading = true;
      listCells(this.queryParams).then(response => {
        this.cellsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        cellId: null,
        blockNumber: null,
        cellNumber: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.cellId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加牢房信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cellId = row.cellId || this.ids
      getCells(cellId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改牢房信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cellId != null) {
            updateCells(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCells(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const cellIds = row.cellId || this.ids;
      this.$modal.confirm('是否确认删除牢房信息编号为"' + cellIds + '"的数据项？').then(function() {
        return delCells(cellIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('prisoner/cells/export', {
        ...this.queryParams
      }, `cells_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
