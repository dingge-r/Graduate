<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button
            type="primary"
            icon="plus"
            @click="showCreate()"
            v-if="hasPerm('article:add')"
            >添加</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="list"
      v-loading.body="listLoading"
      element-loading-text="拼命加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="sname"
        label="姓名"
        style="width: 60px"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="sex"
        label="性别"
        style="width: 60px"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="phone"
        label="联系电话"
        style="width: 60px"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="studentnumber"
        label="学号"
        style="width: 60px"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="academy"
        label="分院"
        style="width: 60px"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="classes"
        label="班级"
        style="width: 60px"
      ></el-table-column>
      <!-- <el-table-column
        align="center"
        label="创建时间"
        prop="createTime"
        width="170"
      ></el-table-column> -->

      <el-table-column
        align="center"
        label="管理"
        width="200"
        v-if="hasPerm('article:update')"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="edit"
            @click="showUpdate(scope.$index)"
            >修改</el-button
          >
          <el-button
            type="danger"
            icon="delete"
            @click="removeUser(scope.$index)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        class="small-space"
        :model="tempArticle"
        label-position="left"
        label-width="60px"
        style="width: 300px; margin-left: 50px"
      >
        <el-form-item label="姓名">
          <el-input type="text" v-model="tempArticle.sname"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="tempArticle.sex">
            <el-radio :label="1">女</el-radio>
            <el-radio :label="2">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话">
          <el-input type="text" v-model="tempArticle.phone"></el-input>
        </el-form-item>
        <el-form-item label="学号">
          <el-input type="text" v-model="tempArticle.studentnumber"></el-input>
        </el-form-item>
        <el-form-item label="分院">
          <el-input type="text" v-model="tempArticle.academy"></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input type="text" v-model="tempArticle.classes"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button
          v-if="dialogStatus == 'create'"
          type="success"
          @click="createArticle"
          >创 建</el-button
        >
        <el-button type="primary" v-else @click="updateArticle"
          >修 改</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      totalCount: 0, //分页组件--数据总条数
      list: [], //表格的数据
      listLoading: false, //数据加载等待动画
      listQuery: {
        pageNum: 1, //页码
        pageRow: 10, //每页条数
        name: "",
      },
      dialogStatus: "create",
      dialogFormVisible: false,
      textMap: {
        update: "编辑",
        create: "添加学生信息",
      },
      tempArticle: {
        id: "",
        sname: "",
        sex: 1,
        phone: "",
        studentnumber: "",
        academy: "",
        classes: "",
        resume: "",
      },
      fileList: [
        {
          name: "",
          url: "",
        },
      ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      //查询列表
      if (!this.hasPerm("article:list")) {
        return;
      }
      this.listLoading = true;
      this.api({
        url: "/student/listStudent",
        method: "get",
        params: this.listQuery,
      }).then((data) => {
        this.listLoading = false;
        this.list = data.list;
        for (let i = 0; i < this.list.length; i++) {
          if (this.list[i].sex == 1) {
            this.list[i].sex = "女";
          } else {
            this.list[i].sex = "男";
          }
        }
        this.totalCount = data.totalCount;
      });
    },
    handleSizeChange(val) {
      //改变每页数量
      this.listQuery.pageRow = val;
      this.handleFilter();
    },
    handleCurrentChange(val) {
      //改变页码
      this.listQuery.pageNum = val;
      this.getList();
    },
    handleFilter() {
      //改变了查询条件,从第一页开始查询
      this.listQuery.pageNum = 1;
      this.getList();
    },
    getIndex($index) {
      //表格序号
      return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1;
    },
    showCreate() {
      //显示新增对话框
      this.tempArticle.content = "";
      this.dialogStatus = "create";
      this.dialogFormVisible = true;
    },
    showUpdate($index) {
      //显示修改对话框
      this.tempArticle.id = this.list[$index].id;
      this.tempArticle.sname = this.list[$index].sname;
      if (this.list[$index].sex == "女") {
        this.tempArticle.sex = 1;
      } else {
        this.tempArticle.sex = 2;
      }
      this.tempArticle.phone = this.list[$index].phone;
      this.tempArticle.studentnumber = this.list[$index].studentnumber;
      this.tempArticle.academy = this.list[$index].academy;
      this.tempArticle.classes = this.list[$index].classes;
      this.dialogStatus = "update";
      this.dialogFormVisible = true;
    },
    createArticle() {
      //保存新文章
      this.api({
        url: "/student/addStudent",
        method: "post",
        data: this.tempArticle,
      }).then(() => {
        this.getList();
        this.dialogFormVisible = false;
      });
    },
    updateArticle() {
      //修改文章
      this.api({
        url: "/student/updateStudent",
        method: "post",
        data: this.tempArticle,
      }).then(() => {
        this.getList();
        this.dialogFormVisible = false;
      });
    },
    removeUser($index) {
      let _vue = this;
      this.$confirm("确定删除此学生信息?", "提示", {
        confirmButtonText: "确定",
        showCancelButton: false,
        type: "warning",
      }).then(() => {
        _vue
          .api({
            url: "/student/deleteStudent",
            method: "delete",
            params: {
              id: _vue.list[$index].id,
            },
          })
          .then(() => {
            _vue.getList();
          })
          .catch(() => {
            _vue.$message.error("删除失败");
          });
      });
    },
  },
};
</script>
