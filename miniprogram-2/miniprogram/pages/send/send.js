// pages/ltjm/ltjm.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    faceUrl: "../../img/index/cx-10.jpg",
    name:'',
    nickname:'',
    username:'',
    phone:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var me = this;
    var serverUrl = app.serverUrl;
    console.log(options.userId);
    //var id = JSON.stringify(options.userId)
    //console.log(id);
    wx.request({
      url: serverUrl + '/label/personalInformation/' + options.userId,
      method: "POST",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function(res) {
        console.log(res.data);
        var faceUrl = "../../img/index/cx-10.jpg";
        if (res.data.faceImage != null && res.data.faceImage != '' && res.data.faceImage != undefined) {
          faceUrl = serverUrl + res.data.faceImage;
        }
        me.setData({
          name: res.data.name,
          nickname: res.data.name,
          username: res.data.username,
          phone: res.data.phone,
          faceUrl: faceUrl
        })
        
      }
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