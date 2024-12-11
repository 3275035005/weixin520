var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    /**
     * 页面的初始数据
     */
    data: {
      itme:{}
    },  

    onLoad: function (options) {
      const {id} = options;
      this.getInfo(id);
    },
    getInfo(id){
      call.getData('wx/getTeacherById?id='+id , this.onSuccessclassAll, this.onFaiclassAll);
    },
  onSuccessclassAll(res) {
    if(res.code == 20000){
     this.setData({
      item :res.data.row
    })
    console.log(this.data.item);
  }
},
onFaiclassAll() {
  help.show("网络请求失败");
},
})