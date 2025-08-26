<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="firstName">
        <el-input v-model="queryParams.firstName" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['prisoner:photos:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['prisoner:photos:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['prisoner:photos:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['prisoner:photos:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <div style="margin-top: 30px;;display: flex;flex-wrap: wrap;">
      <div class="block" v-for="(e,index) in photosList" :key="index" style="margin: 5px;border-radius: 16px;background-color: #49094b;padding: 10px;display: flex;flex-direction: column;justify-content: center;align-items: center;width: 160px;">
        <!-- <el-image style="width: 100px; height: 100px" :src="e.inmateImg" ></el-image> -->
        <image-preview :src="e.inmateImg" :width="140" :height="140" />
        <span class="demonstration" style="margin: 14px;color: #18d31c;">{{ e.firstName }}</span>
      </div>
    </div>

    <!-- <el-table v-loading="loading" :data="photosList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名" align="center" prop="firstName" />
      <el-table-column label="照片" align="center" prop="inmateImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.inmateImg" :width="50" :height="50" />
          {{ scope.row.inmateImg }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['prisoner:photos:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['prisoner:photos:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改囚犯照片对话框 -->
    <!-- <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="firstName">
          <el-input v-model="form.firstName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="照片" prop="inmateImg">
          <image-upload v-model="form.inmateImg" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import { listPhotos, getPhotos, delPhotos, addPhotos, updatePhotos } from "@/api/prisoner/photos";
import { listInmates} from "@/api/prisoner/inmates";

export default {
  name: "Photos",
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
      // 囚犯照片表格数据
      photosList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        firstName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        firstName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询囚犯照片列表 */
    getList() {
      this.loading = true;
      listInmates(this.queryParams).then(response => {
        this.photosList = response.rows;
        // console.log(this.photosList);
        this.total = response.total;
        this.loading = false;
      });
      listPhotos(this.queryParams).then(response => {
        // this.photosList = response.rows;
        // this.total = response.total;
        // this.loading = false;
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
        firstName: null,
        inmateImg: null
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
      this.ids = selection.map(item => item.firstName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加囚犯照片";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const firstName = row.firstName || this.ids
      getPhotos(firstName).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改囚犯照片";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.firstName != null) {
            updatePhotos(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPhotos(this.form).then(response => {
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
      const firstNames = row.firstName || this.ids;
      this.$modal.confirm('是否确认删除囚犯照片编号为"' + firstNames + '"的数据项？').then(function() {
        return delPhotos(firstNames);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('prisoner/photos/export', {
        ...this.queryParams
      }, `photos_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
