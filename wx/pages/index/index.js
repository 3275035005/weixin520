var call = require("../../utils/request.js")
var help = require("../../utils/help.js")


Page({
  data: {
    // --- 轮播图 --- //
    bannerList: [],
    // --- 通知 --- //
    noticeList: [],
    // --- 热门课程 --- //
    courseList: []
  },
  onLoad: function () {
    this.getHome();
    this.getInit();

  },
  /**
   * 获取登录用户信息
   */
  getInit() {
    let userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    }
  },
  // 获取班级列表
  getHome() {
    call.getData('wx/getHome', this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        bannerList: res.data.row.banners,
        noticeList: res.data.row.notices,
        courseList: res.data.row.courses
      })
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },




})
