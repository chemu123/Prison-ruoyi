<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="囚犯ID" prop="inmateId">
        <el-input
          v-model="queryParams.inmateId"
          placeholder="请输入囚犯ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="firstName">
        <el-input
          v-model="queryParams.firstName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出生日期" prop="dateOfBirth">
        <el-date-picker clearable
          v-model="queryParams.dateOfBirth"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select v-model="queryParams.gender" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="入狱日期" prop="admissionDate">
        <el-date-picker clearable
          v-model="queryParams.admissionDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择入狱日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.prisoner_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
<!-- 按钮区域 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
        round
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['prisoner:inmates:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        round
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['prisoner:inmates:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        round
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['prisoner:inmates:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        round
          type="primary"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['prisoner:inmates:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inmatesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="囚犯ID" align="center" prop="inmateId" />
      <el-table-column label="照片" align="center" prop="inmateImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.inmateImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" prop="firstName" />
      <el-table-column label="出生日期" align="center" prop="dateOfBirth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateOfBirth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="入狱日期" align="center" prop="admissionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.admissionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计释放日期" align="center" prop="releaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.releaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="犯罪类型" align="center" prop="crime" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.prisoner_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['prisoner:inmates:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['prisoner:inmates:remove']"
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

    <!-- 添加或修改囚犯信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="照片" prop="inmateImg">
          <image-upload v-model="form.inmateImg"/>
        </el-form-item>
        <el-form-item label="姓名" prop="firstName">
          <el-input v-model="form.firstName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="出生日期" prop="dateOfBirth">
          <el-date-picker clearable
            v-model="form.dateOfBirth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入狱日期" prop="admissionDate">
          <el-date-picker clearable
            v-model="form.admissionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入狱日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计释放日期" prop="releaseDate">
          <el-date-picker clearable
            v-model="form.releaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计释放日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="犯罪类型" prop="crime">
          <el-input v-model="form.crime" placeholder="请输入犯罪类型" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.prisoner_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">牢房信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCells">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCells">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="cellsList" :row-class-name="rowCellsIndex" @selection-change="handleCellsSelectionChange" ref="cells">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="监区号" prop="blockNumber" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.blockNumber" placeholder="请选择监区号">
                <el-option
                  v-for="dict in dict.type.ward_zoning"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="牢房号" prop="cellNumber" width="200">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cellNumber" placeholder="请输入牢房号" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInmates, getInmates, delInmates, addInmates, updateInmates } from "@/api/prisoner/inmates";

export default {
  name: "Inmates",
  dicts: ['prisoner_status', 'ward_zoning', 'sys_user_sex'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCells: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 囚犯信息表格数据
      inmatesList: [],
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
        inmateId: null,
        firstName: null,
        dateOfBirth: null,
        gender: null,
        admissionDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        firstName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        dateOfBirth: [
          { required: true, message: "出生日期不能为空", trigger: "blur" }
        ],
        gender: [
          { required: true, message: "性别不能为空", trigger: "change" }
        ],
        admissionDate: [
          { required: true, message: "入狱日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询囚犯信息列表 */
    getList() {
      this.loading = true;
      listInmates(this.queryParams).then(response => {
        this.inmatesList = response.rows;
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
        inmateId: null,
        inmateImg: null,
        firstName: null,
        dateOfBirth: null,
        gender: null,
        admissionDate: null,
        releaseDate: null,
        crime: null,
        status: null
      };
      this.cellsList = [];
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
      this.ids = selection.map(item => item.inmateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加囚犯信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const inmateId = row.inmateId || this.ids
      getInmates(inmateId).then(response => {
        this.form = response.data;
        this.cellsList = response.data.cellsList;
        this.open = true;
        this.title = "修改囚犯信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.cellsList = this.cellsList;
          if (this.form.inmateId != null) {
            updateInmates(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInmates(this.form).then(response => {
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
      const inmateIds = row.inmateId || this.ids;
      this.$modal.confirm('是否确认删除囚犯信息编号为"' + inmateIds + '"的数据项？').then(function() {
        return delInmates(inmateIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 牢房信息序号 */
    rowCellsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 牢房信息添加按钮操作 */
    handleAddCells() {
      let obj = {};
      obj.blockNumber = "";
      obj.cellNumber = "";
      // this.disabled= false;
      // console.log(this.cellsList.length==0);
      if(this.cellsList.length==0){
        this.cellsList.push(obj);
      }
    },
    /** 牢房信息删除按钮操作 */
    handleDeleteCells() {
      if (this.checkedCells.length == 0) {
        this.$modal.msgError("请先选择要删除的牢房信息数据");
      } else {
        const cellsList = this.cellsList;
        const checkedCells = this.checkedCells;
        this.cellsList = cellsList.filter(function(item) {
          return checkedCells.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCellsSelectionChange(selection) {
      this.checkedCells = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('prisoner/inmates/export', {
        ...this.queryParams
      }, `inmates_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
