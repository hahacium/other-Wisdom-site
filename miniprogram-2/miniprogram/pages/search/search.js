var WxSearch = require('../../wxSearchView/wxSearchView.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    serverUrl: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    // 2 搜索栏初始化
    var that = this;
    wx.request({
      url: app.serverUrl + "/search",
      method: "POST",
      success: function (res) {
        var searchList = res.data.data;
        console.log(res);
        WxSearch.init(
          that,  // 本页面一个引用
          [], // 热点搜索推荐，[]表示不使用
          searchList,// 搜索匹配，[]表示不使用
          that.mySearchFunction, // 提供一个搜索回调函数
          that.myGobackFunction //提供一个返回回调函数
        );
      }
    })
  },
  // 3 转发函数，固定部分，直接拷贝即可
  wxSearchInput: WxSearch.wxSearchInput,  // 输入变化时的操作
  wxSearchKeyTap: WxSearch.wxSearchKeyTap,  // 点击提示或者关键字、历史记录时的操作
  wxSearchDeleteAll: WxSearch.wxSearchDeleteAll, // 删除所有的历史记录
  wxSearchConfirm: WxSearch.wxSearchConfirm,  // 搜索函数
  wxSearchClear: WxSearch.wxSearchClear,  // 清空函数

  // 4 搜索回调函数  
  mySearchFunction: function (value) {
    console.log(value)
    // do your job here
    // 示例：跳转
    wx.showLoading({
      title: '请等待...',
    })
   
    wx.request({
      url: app.serverUrl + "/getSearch?name=" +value ,
      method: 'POST',
      // data: {
      //   name: value
      // },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(e) {
        var a = JSON.parse(e.data.data)
        console.log(a);
        var b = JSON.stringify(a);
        wx.hideLoading();
         wx.navigateTo({
           url: '../show/show?search=' + b/*e.data.data*/
         })
      }
    })
  },

  // 5 返回回调函数
  myGobackFunction: function () {
    //回跳到上一层
    wx.navigateBack()
  },
})
