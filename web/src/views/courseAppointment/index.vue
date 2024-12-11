<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="预约学生">
        <el-select v-model="dataVo.userId" placeholder="请选择预约学生">
          <el-option
            v-for="question in userList"
            :key="question.id"
            :label="question.name"
            :value="question.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="预约课程">
        <el-select v-model="dataVo.courseId" placeholder="请选择预约课程">
          <el-option
            v-for="question in courseList"
            :key="question.id"
            :label="question.title"
            :value="question.id"></el-option>
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">重置</el-button>
    </el-form>
    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
      empty-text="暂无数据"
    >

      <el-table-column
        label="序号"
        width="60"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}  <!--序号,算法-->
        </template>
      </el-table-column>
      <el-table-column prop="courseName" label="预约课程名称" />
      <el-table-column prop="userName" label="预约学生姓名" />
      <el-table-column prop="status" label="预约状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '1'" type="primary">预约成功</el-tag>
          <el-tag v-if="scope.row.status === '2'" type="warning">已取消</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="预约时间" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      style="padding: 30px 0; text-align: center;"
      :page-size="limit"
      layout="total, prev, pager, next, jumper"
      :total="total"
      @current-change="getList"
    />
  </div>
</template>
<script>
import { deleteById, pageQuery } from '@/api/courseAppointment'
import { getUserAll } from '@/api/user'
import { getCourseAll } from '@/api/course'
export default {
  data() { // 定义变量和初始值
    return {
      list: [], // 查询之后接口返回集合
      dataVo: {}, // 条件封装对象
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 100, // 总页数
      dialogVisible: false,
      formData: {},
      userList:[],
      courseList:[]
    }
  },
  created() { // 页面渲染之前执行，一般调用method定义方法
    this.getList();
    this.getCourseList();
    this.getUserList();
  },
  methods: { // 创建具体的方法, 定义的方法

    // 查询
    getList(page = 1) {
      this.page = page // 获取用户点击的页码赋值
      pageQuery(this.page, this.limit, this.dataVo)
        .then(response => { // 请求成功
          this.list = response.data.rows.records;
          this.total = response.data.total;
        })
    },
    getUserList(){
      getUserAll().then(res=>{
        this.userList = res.data.row
      })
    },

    getCourseList(){
      getCourseAll().then(res=>{
        this.courseList = res.data.row
      })
    },

    // 删除
    removeDataById(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'

      }).then(() => { // 确定执行的方法
        deleteById(id)
          .then(response => { // 删除成功执行的方法
            if (response.success) {
              this.$message({
                type: 'success',
                message: '删除成功！'
              })
              // 刷新表格
              this.getList()
            } else {
              this.$message({
                type: 'error',
                message: '删除失败！'
              })
            }
          })
      })
    },
    // 清空按按钮执行的方法
    resetData() {
      // 第一步清空条件数据
      this.page = 1;// 当前页
      this.limit = 10; // 每页记录数
      this.dataVo = {};
      this.getList()
    }
  }
}
</script>
