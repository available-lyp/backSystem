<template>
  <div style="height: 100%">
    <el-container style="height: 100%;">
      <el-aside :width="sideWidth + 'px'"
                style="background-color: rgb(3,38,63); box-shadow: 2px 0 6px rgb(3,38,63); height: 100vh">
        <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden; border-right: 0vh"
                 background-color="rgb(3,38,63)"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 :collapse-transition="false"
                 :collapse="isCollapse"
        >
          <div style="height: 60px; line-height: 60px; text-align: center">
            <img src="../assets/logo.png" alt="" style="width: 30px; position: relative; top: 8px; margin-right: 8px">
            <b style="color: white; cursor: pointer " v-show="LogoTextShow">后台系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>
              <span slot="title" class="font-size">导航一</span>
            </template>
            <el-menu-item-group title="分组一">
              <el-menu-item index="1-1">选项1</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-2">
              <template slot="title">选项2</template>
              <el-menu-item index="1-4-1">选项2-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>
              <span slot="title" class="font-size">导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-1">选项1</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-2">
              <template slot="title">选项2</template>
              <el-menu-item index="2-2-1">选项2-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>
              <span slot="title" class="font-size">导航三</span>
            </template>
            <el-submenu index="3-1">
              <template slot="title">选项一</template>
              <el-menu-item index="3-1">选项3-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style=" font-size: 12px; border-bottom:1px solid #ccc; line-height: 60px; display: flex; box-shadow: 2px 0 6px rgb(1,18,6)">
          <div style="flex: 1; font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
          </div>
          <el-dropdown style="width: 70px; cursor: pointer">
            <span>王小虎</span>
            <i class="el-icon-arrow-down" style="margin-left: 4px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
            <el-breadcrumb-item>活动列表</el-breadcrumb-item>
            <el-breadcrumb-item>活动详情</el-breadcrumb-item>
          </el-breadcrumb>
          <div style="padding: 10px 0">
            <el-input style="width: 140px" placeholder="输入名称" suffix-icon="el-icon-user" v-model="username"></el-input>
            <el-input style="width: 160px" placeholder="输入邮箱" suffix-icon="el-icon-message" class="ml-5"
                      v-model="email"></el-input>
            <el-input style="width: 180px" placeholder="输入地址" suffix-icon="el-icon-location-outline" class="ml-5"
                      v-model="address"></el-input>
            <el-button class="ml-5" type="primary" @click="load">查找</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
          </div>
          <div style="padding: 10px 0">
            <el-button type="primary" @click="handleAdd">添加 <i class="el-icon-plus"></i></el-button>
            <el-popconfirm
                class="ml-5"
                confirm-button-text='确定'
                cancel-button-text='我再想想'
                icon="el-icon-info"
                icon-color="red"
                title="您确定批量删除这些数据吗？"
                @confirm="delBatch">
                <el-button class="mr-5" type="danger" slot="reference" >批量删除 <i class="el-icon-minus"></i></el-button>
            </el-popconfirm>
            <el-button type="danger">导入 <i class="el-icon-bottom"></i></el-button>
            <el-button type="danger">导出 <i class="el-icon-top"></i></el-button>
          </div>

          <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">>
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="id" width="80"></el-table-column>
            <el-table-column prop="username" label="用户" width="100"></el-table-column>
            <el-table-column prop="nickname" label="昵称" width="100"></el-table-column>
            <el-table-column prop="phone" label="电话" width="140"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="150"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column label="操作" width="200" align="center">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">修改 <i class="el-icon-edit"></i></el-button>
                <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确定'
                    cancel-button-text='我再想想'
                    icon="el-icon-info"
                    icon-color="red"
                    title="您确定删除吗？"
                    @confirm="del(scope.row.id)">
                    <el-button type="danger" slot="reference">删除 <i class="el-icon-delete"></i></el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
          <!--分页查询-->
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5,9]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </div>
          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="70px"  size="small">
              <el-form-item label="用户名" >
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="昵称" >
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" >
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电话" >
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" >
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>

export default {
  name: 'HomeView',
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      email: "",
      address: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      msg: "hello 青哥哥",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      LogoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods: {
    //点击收缩按钮触发
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {   //收回
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.LogoTextShow = false
      } else {                 //展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.LogoTextShow = true
      }
    },
    load() {
      //使用axios请求分页查询数据
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      })
          .then(res => {
            console.log(res)
            this.tableData = res.records
            this.total = res.total
          })
      //请求分页查询数据
      // fetch("http://localhost:9090/user/page?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&username="+ this.username)
      //     .then(res => res.json()).then(res => {
      //   console.log(res)
      //   this.tableData=res.data
      //   this.total=res.total
      // })
    },
    save(){
      this.request.post("/user",this.form).then(res =>{
        if (res){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },

    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
    },
    del(id){
      this.request.delete("/user/"+id).then(res => {
        if (res){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)   /* 可以将对象形式的数组变成一个数组，如示  [{1}，{2}，{3}] => {1，2，3} */
      this.request.post("/user/del/batch",ids).then(res => {
        if (res){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum);
      this.pageNum = pageNum
      this.load()
    }


  }
};
</script>
<style>
.headerBg {
  background-color: #eee !important;
}
</style>