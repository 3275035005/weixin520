var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';
var courseId = '';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    itme: {},
  },

  onLoad: function (options) {
    const { id } = options;
    courseId = id
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
    call.getData('wx/getCourseById?id=' + courseId + "&userId=" + userId, this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        item: res.data.row
      })
      console.log(this.data.item);
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },

  // 预约课程
  appointmentCourseBtn() {
    call.request('wx/appointmentCourse', {
      courseId: courseId,
      userId: userId
    }, this.onSuccessappointmentCourseBtn, this.onFaiappointmentCourseBtn);
  },
  onSuccessappointmentCourseBtn(res) {
    if (res.code == 20000) {
      help.show("预约课程成功");
      this.getInfo();
    } else {
      help.show(res.message);
    }
  },
  onFaiappointmentCourseBtn() {
    help.show("网络请求失败");
  },

  //取消预约 
  cancelAppointmentCourseBtn() {
    call.request('wx/cancelAppointmentCourse', {
      courseId: courseId,
      userId: userId
    }, this.onSuccesscancelAppointmentCourseBtn, this.onFaicancelAppointmentCourseBtn);
  },
  onSuccesscancelAppointmentCourseBtn(res) {
    if (res.code == 20000) {
      help.show("取消预约成功");
      this.getInfo();
    } else {
      help.show(res.message);
    }
  },
  onFaicancelAppointmentCourseBtn() {
    help.show("网络请求失败");
  },
})