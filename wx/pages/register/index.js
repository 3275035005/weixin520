var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({
    data: {
        name:'',// 姓名
        phone:'', // 手机号
        username:'', // 账号
        password:'', // 密码
      
        // 班级
        class_Index:0,
        classList:[],
    },
    onLoad: function () {
      this.getClassAll();
    },
	// 获取班级列表
	getClassAll(){
    call.getData('wx/getClass' , this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if(res.code == 20000){
     this.setData({
      classList :res.data.row
    })
  }
},
onFaiclassAll() {
  help.show("网络请求失败");
},
  	// 班级类型
    class_Change(e){
      console.log(e);
      this.setData({
        class_Index : e.detail.value
      })
    },
    // 注册
    register_button(){
     
      console.log(this.data.classList[this.data.class_Index]);
      console.log(this.data.classList[this.data.class_Index].id);
      var myreg = /^(14[0-9]|13[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]19[0-9])\d{8}$$/;
      if(this.data.phone == ""){
        wx.showToast({
          title: '手机号不能为空',
          icon: 'none',
          duration: 1000
        })
        return false;
      }else if(!myreg.test(this.data.phone)){
        wx.showToast({
          title: '请输入正确的手机号',
          icon: 'none',
          duration: 1000
        })
        return false;
      }
      if(this.data.name == ""){
      wx.showToast({
          title: '姓名不能为空',
          icon: 'none',
          duration: 1000
      })
      return false;
      }
      if(this.data.username == ""){
        wx.showToast({
            title: '账号不能为空',
            icon: 'none',
            duration: 1000
        })
        return false;
        }
      if(this.data.password == ""){
        wx.showToast({
            title: '密码不能为空',
            icon: 'none',
            duration: 1000
        })
        return false;
        }
      wx.showLoading({
        title: '注册中...'
      });
      call.request('wx/register',{
        name: this.data.name,
        phone: this.data.phone,
        username:this.data.username,
        password:this.data.password,
        classId:this.data.classList[this.data.class_Index].id
      }, this.onSuccess, this.onFail);
    },

    onSuccess(res) {
    
      wx.hideLoading();
      if(res.code == 20000){
      help.show("注册成功")
      setTimeout(function(){ // 下单成功跳转页面
        wx.reLaunch({
          url: '/pages/login/index'
      })
      },2000)
         
      }else{
          help.show(res.msg)
      }
    },

    onFail() {
      wx.hideLoading();
      help.show("网络请求超时,请稍后再试")
    }
    
})