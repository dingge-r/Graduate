<template>
  <div class="app-container">
    <div class="filter-container">
      <!-- <el-form>
        <el-form-item>
          <el-button
            type="primary"
            icon="plus"
            @click="showCreate()"
            v-if="hasPerm('article:add')"
            >添加</el-button
          >
        </el-form-item>
      </el-form> -->
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
        prop="phone"
        label="联系电话"
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
      <el-table-column
        align="center"
        prop="jianli(resume)"
        label="简历"
        style="width: 60px"
      >
        <template slot-scope="scope">
         <div v-if="scope.row.resume!=null">
          <el-link
            :href="scope.row.resume"
            icon="el-icon-view"
            target="_blank"
            type="primary"
            >在线预览</el-link
          >
           </div>
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
        this.totalCount = data.totalCount;
      });
    },
    jianli() {
      return;
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
  },
};
</script>
