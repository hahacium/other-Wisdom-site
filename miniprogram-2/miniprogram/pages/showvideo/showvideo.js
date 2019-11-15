// pages/video/video.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    show: false,
    data: []
  },
  web(e) {
    console.log(e)
  },
  btn(e) {
    const video = e.currentTarget.dataset.demo
    const title = e.currentTarget.dataset.name
    const that = this
    if (video.status) {
      wx.showModal({
        title: title,
        content: '是否调用该区域' + video.name + '监控',
        success(res) {
          if (res.confirm) {
            that.view.play()
            that.setData({
              show: true
            })
          } else if (res.cancel) {
          }
        }
      })
    } else {
      wx.showToast({
        title: '该监控维修中',
        image:'../../img/weix.png'
      })
    }
  },
  bf() {
    this.view.stop()
    this.setData({
      show: false
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    const that = this
    this.view = wx.createLivePlayerContext('lol')
    const db = wx.cloud.database()
    db.collection('video').get({
      success: function(res) {
        that.setData({
          data: res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})