// pages/recom/recom.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    select:0,
    firstLabel: [],
    secondLabel: [],
    serverUrl: ""
  },
  /**
   * 左边按钮切换
   */
  btn(e){
    var me = this;
    me.setData({ select: e.currentTarget.id })
    wx.showLoading({
      title: '请等待...',
    })
    wx.request({
      url: app.serverUrl + "/label/secondLabelManager?id=" + me.data.select,
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success:function(res) {
        wx.hideLoading();
        console.log(res.data.data);
        me.setData({
          secondLabel: res.data.data
        });
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var me = this;
    wx.showLoading({
      title: '请等待...',
    })
    wx.request({
      url: app.serverUrl + "/label/labelManager",
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success: function(res) {
        wx.hideLoading();
        console.log(res.data.data);
        me.setData({
          firstLabel: res.data.data,
          serverUrl: app.serverUrl
        })
      }
    })
    wx.request({
      url: app.serverUrl + "/label/secondLabelManager",
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success: function (res) {
        wx.hideLoading();
        console.log(res.data.data);
        me.setData({
          secondLabel: res.data.data
        })
      }
    })
  },
  showSearch: function () {
    wx.navigateTo({
      url: '../search/search',
    })
  },
  tz: function(e) {
    console.log(e);
    var userId = e.currentTarget.id;
    wx.navigateTo({
      url: '../send/send?userId=' + userId,
    })
  },
  chatroom: function() {
    wx.navigateTo({
      url: '../chatroom/chatroom',
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