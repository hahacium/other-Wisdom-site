// pages/resgin/resgin.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    status: true,    //登录状态
    faceUrl: "../../img/index/cx-10.jpg",
    nickname: "",
    username: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    var me = this;
    var serverUrl = app.serverUrl;
    var userInfo = app.getGlobalUserInfo();
    console.log(userInfo);
    if(userInfo != null && userInfo != '' && userInfo!=undefined) {
      me.setData({
        status: false
      })
    }
    var faceUrl = "../../img/index/cx-10.jpg";
    if (userInfo.faceImage != null && userInfo.faceImage != '' && userInfo.faceImage != undefined) {
      faceUrl = serverUrl + userInfo.faceImage;
    }
    me.setData({
      faceUrl: faceUrl,
      nickname: userInfo.name,
      name: userInfo.username
    })
  },
  //退出登录
  exit: function() {
    var me = this;
    wx.showLoading({
      title: '请等待...',
    })
    //注销，清空缓存
    wx.removeStorageSync("userInfo");
    wx.showToast({
      title: '退出成功',
      duration: 2000,
      icon: 'success'
    })
    me.setData({
      status: true
    })
    wx.hideLoading();
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  btn() {
    wx.navigateTo({
      url: '/pages/me/me'
    })
  },
  //头像上传
  changeFace: function () {
    var me = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['compressed'],
      sourceType: ['album','camera'],
      success: function (res) {
        var tempFilePaths = res.tempFilePaths;
        console.log("tempFilePaths:"+tempFilePaths);
        wx.showLoading({
          title: '上传中...',
        })
        var serverUrl = app.serverUrl;
        var userInfo = app.getGlobalUserInfo();
        wx.uploadFile({
          url: serverUrl + '/user/uploadFace/' + userInfo.id,
          filePath: tempFilePaths[0],
          name: 'file',
          header: {
            'content-type': 'application/json', // 默认值
            'headerUserId': userInfo.id,
            'headerUserToken': userInfo.userToken
          },
          success: function (res) {
            var data = JSON.parse(res.data);
            console.log(data);
            wx.hideLoading();
            if (data.status == 200) {
              wx.showToast({
                title: '上传成功!~~',
                icon: "success"
              });
              var imageUrl = data.data;
              me.setData({
                faceUrl: serverUrl + imageUrl
              });

            } else if (data.status == 500) {
              wx.showToast({
                title: data.msg
              });
            } else if (data.status == 502) {
              wx.showToast({
                title: data.msg,
                duration: 3000,
                icon: "none",
                success: function () {
                  me.setData({
                    status: true
                  })
                  wx.removeStorageSync("userInfo");
                }
              });
            } 
          }
        })
      }
    })
  },
  report: function() {
    wx.navigateTo({
      url: '../feedback/feedback'
    })
  },
  money: function() {
    wx.showToast({
      title: '功能即将开放,敬请期待...',
      icon: 'none'
    })
  },
  ill: function() {
    wx.showToast({
      title: '功能即将开放,敬请期待...',
      icon: 'none'
    })
  },
  check: function() {
    wx.showToast({
      title: '功能即将开放,敬请期待...',
      icon: 'none'
    })
  },
  personalInfo: function() {
    wx.showToast({
      title: '功能即将开放,敬请期待...',
      icon: 'none'
    })
  },
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