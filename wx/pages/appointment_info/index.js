var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';
Page({

  /**
   * 页面的初始数据
   */
  data: {

    // current
    current: 0,

    // cash_tab
    cash_tab: ['全部', '预约成功', '已取消'],

    list: []


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
      this.getCourseAppointmentInfo("");
    }
  },
  getCourseAppointmentInfo(status) {
    call.getData('wx/getCourseAppointmentInfo?id=' + userId + "&status=" + status, this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        list: res.data.row
      })
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },

  // cash_swiper
  swiper(e) {
    this.setData({
      current: e.detail.current
    })
    if (this.data.current == 0) {
      this.getCourseAppointmentInfo('');
    } else {
      this.getCourseAppointmentInfo(this.data.current);
    }
  },

  // cash_tab__change
  cash_tab__change(e) {
    this.setData({
      current: e.target.dataset.index
    })
    if (this.data.current == 0) {
      this.getCourseAppointmentInfo('');
    } else {
      this.getCourseAppointmentInfo(this.data.current);
    }
  }





})