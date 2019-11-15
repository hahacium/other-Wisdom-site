// pages/sos/sos.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    search: [],
    serverUrl:'',
  },
  tz(e){
    console.log(e);
    var userId = e.target.id;
    wx.navigateTo({
      url: '../send/send?userId=' + userId,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var me = this;
    console.log(JSON.parse(options.search))
    me.setData({
      search: JSON.parse(options.search),
      serverUrl: app.serverUrl
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})