// pages/picture/picture.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    jieguo: '全部图纸',
    show: true,
    first: [],
    data: [],
    picture:[],
    src:'',
    serverUrl: app.serverUrl,
    value:''
  },
  search:function(e){
    var me = this;
    if (e.detail.value == '' || e.detail.value == null || e.detail.value == undefined) {
      let page = getCurrentPages().pop();
      if (page == undefined || page == null) {
        return;
      }
      page.onLoad();
    }
    wx.request({
      url: app.serverUrl + '/pic/searchPic/' + e.detail.value,
      method: 'POST',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res)
        me.setData({
          picture: res.data.data,
          jieguo: '搜索结果'
        })
      }
    })
    console.log(e);
  },
  chazhao(){
    
  },
  fh() {
    this.setData({
      show: true
    })
  },
  btn(e) {
    const that = this
    this.setData({show: false, src:''})
    fileList: [e.currentTarget.dataset.img],
      that.setData({
        src: app.serverUrl + e.currentTarget.dataset.img
      })
  },
  sos(e){
    var me = this;
    me.setData({
      jieguo: e.currentTarget.dataset.name
    })
    console.log(e)
    wx.request({
      url: app.serverUrl + '/pic/selectSecond?id=' + e.currentTarget.id,
      method: 'POST',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        var data = res.data.data;
        console.log(data);
        me.setData({
          picture: data,
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var me = this;
    wx.request({
      url: app.serverUrl + '/pic/selectFirst',
      method: 'POST',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function(res) {
        var data = res.data.data;
        console.log(data);
        me.setData({
          data: data
        })
      }
    })

    wx.request({
      url: app.serverUrl + '/pic/selectSecond',
      method: 'POST',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        var data = res.data.data;
        console.log(data);
        me.setData({
          picture: data
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