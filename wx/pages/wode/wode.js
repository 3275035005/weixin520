var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

var userId = '';
Page({

  /**
   * 页面的初始数据
   */
  data: {


    // --- 用户信息 --- //
    userInfo: {},


    // --- 面板 --- //
    user_panel: [
      {
        url: "/pages/appointment_info/index",
        icon: "/images/user_panel_01.svg",
        title: "预约记录"
      }, {
        url: "/pages/score/index",
        icon: "/images/user_panel_02.svg",
        title: "查看成绩"
      }, {
        url: "/pages/updatePassword/index",
        icon: "/images/user_panel_04.svg",
        title: "密码修改"
      }
    ]
  },

  onLoad: function () {
    this.getInit();
  },

  /**
* 获取登录用户信息
*/
  getInit() {
    userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    } else {
      this.getInfo();
    }
  },

  getInfo() {
    call.getData('wx/getUserInfo?id=' + userId, this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        userInfo: res.data.row
      })
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },


  // 退出登录
  out() {
    wx.showModal({
      title: '是否退出登录',
      success(res) {
        if (res.confirm) {
          wx.removeStorageSync('token')
          help.okShow("退出成功");
          setTimeout(function () {
            wx.navigateTo({
              url: '/pages/login/index',
            })
          }, 1000)
        } else if (res.cancel) {

        }
      }
    })



  }



})