// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    nav:[
      { name: '每日进度', img: '/img/index/cx-7.jpg' },
      { name: '员工考勤', img: '/img/index/cx-2.jpg' },
      { name: '质量巡检', img: '/img/index/cx-9.jpg' },
      { name: '会议管理', img: '/img/index/cx-3.jpg' },
      { name: '隐蔽工程', img: '/img/index/ybgc.jpg' },
      { name: '工程图纸', img: '/img/index/cx-6.jpg' },
      { name: '施工日志', img: '/img/index/sgrz.jpg' },
      { name: '高危作业', img: '/img/index/cx-8.jpg' },
      { name: '视频监控', img: '/img/index/cx-4.jpg' },
      { name: '项目公司', img: '/img/index/cx-5.jpg' },
    ]
  },
  //标题导航功能点击事件
  btn(e){
    //e.currentTarget.id   对于点击
    /*
    0--每日进度
    1--员工考勤
    2--质量巡检
    3--会议管理
    。。。
    */
    console.log(e.currentTarget.id)
    if (e.currentTarget.id === '1'){
      wx.navigateTo({
        url: '/pages/checkIn/checkIn'
      })
    } else if (e.currentTarget.id === '4') {
      wx.navigateTo({
        url: '/pages/video/video'
      })
    }
    else if (e.currentTarget.id === '2') {
      wx.showToast({
        title: '功能即将开放,敬请期待...',
        icon: 'none'
      })
    }
    else if (e.currentTarget.id === '3') {
      wx.showToast({
        title: '功能即将开放,敬请期待...',
        icon: 'non e'
      })
    }
    else if (e.currentTarget.id === '5') {
      wx.navigateTo({
        url: '/pages/picture/picture'
      })
    }
    else if (e.currentTarget.id === '6') {
      wx.navigateTo({
        url: '/pages/logManagement/logManagement'
      })
    }
    else if (e.currentTarget.id === '7') {
      wx.showToast({
        title: '功能即将开放,敬请期待...',
        icon: 'none'
      })
    }
    else if (e.currentTarget.id === '8') {
      wx.navigateTo({
        url: '/pages/showvideo/showvideo'
      })
    }
    else if (e.currentTarget.id === '9') {
      wx.showToast({
        title: '功能即将开放,敬请期待...',
        icon: 'none'
      })
    }
    else if (e.currentTarget.id === '10') {
      wx.showToast({
        title: '功能即将开放,敬请期待...',
        icon: 'none'
      })
    }
    else if (e.currentTarget.id === '0') {
      wx.showToast({
        title: '功能即将开放,敬请期待...',
        icon: 'none'
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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