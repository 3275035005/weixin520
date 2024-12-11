var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    data: {
        courseList:[],
        current:0,
        categoryList:[
          {
            id: 0,
            title:'全部'
          }
        ],
    },
    onLoad: function () {
        this.getCategoryList();
        this.getCourseByCategoryId('');
     },

     // 根据分类查询所有预约课程
     getCourseByCategoryId(id){
        call.getData('wx/getCourse?categoryId='+id , this.onSuccessCourseAll, this.onFaiCourseAll);
     },

     onSuccessCourseAll(res) {
      if(res.code == 20000){
        this.setData({
          courseList:res.data.row
      })
    } 
    },
  
    onFaiCourseAll() {
      help.show("网络请求失败");
    },


    // 查询所有课程类别
    getCategoryList(){
      call.getData('wx/getCategory' , this.onSuccessCategoryAll, this.onFaiCategoryAll);
    },
    onSuccessCategoryAll(res) {
      if(res.code == 20000){
        this.setData({
          categoryList:this.data.categoryList.concat(res.data.row)
      })
      } 
    },
    onFaiCategoryAll() {
      help.show("网络请求失败");
    },

    cash_tab__change(e){
        let size = e.target.dataset.index

        this.setData({
          current : size
      })
        if(size == 0){
          this.getCourseByCategoryId('');
        }else{
          this.getCourseByCategoryId(this.data.categoryList[size].id);
        }

    }

})