var videoUtil = require('../../utils/videoUtil.js')

const app = getApp()

Page({
  data: {
    cover: "cover",
    videoId: "",
    src: "",
    videoInfo: {},
    userLikeVideo: false,
    commentsPage: 1,
    commentsTotalPage: 1,
    commentsList: [],
    serverUrl: app.serverUrl,
    controls: false,
    showBtn: false,
    autoplay: true,
    loop: true,
    enableProgressGesture: false
  },

  videoCtx: {},

  onLoad: function (params) {
    var me = this;
    me.videoCtx = wx.createVideoContext("myVideo", me);
    console.log(params);
    // 获取上一个页面传入的参数
    var videoInfo = JSON.parse(params.videoInfo);
    console.log(videoInfo);
    var height = videoInfo.videoHeight;
    var width = videoInfo.videoWidth;
    var cover = "cover";
    if (width >= height) {
      cover = "";
    }

    me.setData({
      videoId: videoInfo.id,
      src: app.serverUrl + videoInfo.videoPath,
      videoInfo: videoInfo,
      cover: cover
    });

    var serverUrl = app.serverUrl;
    var user = app.getGlobalUserInfo();
    var loginUserId = "";
  },

  onShow: function () {
    var me = this;
    me.videoCtx.play();
  },

  onHide: function () {
    var me = this;
    me.videoCtx.pause();
  },

  showSearch: function () {
    wx.navigateTo({
      url: '../searchVideo/searchVideo',
    })
  },

  upload: function () {
    var me = this;
    var user = app.getGlobalUserInfo();
    var videoInfo = JSON.stringify(me.data.videoInfo);
    var realUrl = '../videoinfo/videoinfo#videoInfo@' + videoInfo;
    if (user == null || user == undefined || user == '') {
      wx.navigateTo({
        url: '../me/me?redirectUrl=' + realUrl,
      })
      wx.showToast({
        title: '请登录!',
        icon: 'none',
        duration: 3000
      })
    } else {
      videoUtil.uploadVideo();
    }
  },

  showIndex: function () {
    wx.switchTab({
      url: '../index/index',
    })
  },

  showMine: function () {
    var user = app.getGlobalUserInfo();

    if (user == null || user == undefined || user == '') {
     wx.switchTab({
       url: '../mine/mine',
     })
    } else {
      wx.switchTab({
        url: '../mine/mine',
      })
    }
  },

 
  shareMe: function () {
    var me = this;
    var user = app.getGlobalUserInfo();
    wx.showActionSheet({
      itemList: ['下载到本地', '举报用户', '分享到朋友圈', '分享到QQ空间', '分享到微博'],
      success: function (res) {
        console.log(res.tapIndex);
        if (res.tapIndex == 0) {
          // 下载
          wx.showLoading({
            title: '下载中...',
          })

          wx.downloadFile({
            url: app.serverUrl + me.data.videoInfo.videoPath,
            success: function (res) {
              // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容
              if (res.statusCode === 200) {
                console.log(res.tempFilePath);
                wx.saveVideoToPhotosAlbum({
                  filePath: res.tempFilePath,
                  success: function (res) {
                    console.log(res.errMsg)
                    wx.hideLoading();
                    wx.showToast({
                      title: '视频保存成功',
                      duration: 2000
                    })
                  }
                })
              }
            }
          })
        } else if (res.tapIndex == 1) {
          // 举报
          var videoInfo = JSON.stringify(me.data.videoInfo);
          var realUrl = '../videoinfo/videoinfo#videoInfo@' + videoInfo;

          if (user == null || user == undefined || user == '') {
            wx.navigateTo({
              url: '../me/me?redirectUrl=' + realUrl,
            })
            wx.showToast({
              title: '请登录!',
              icon: 'none',
              duration: 3000
            })
          } else {
            var publishUserId = me.data.videoInfo.userId;
            var videoId = me.data.videoInfo.id;
            var currentUserId = user.id;
            wx.navigateTo({
              url: '../report/report?videoId=' + videoId + "&publishUserId=" + publishUserId
            })
          }
        } else {
          wx.showToast({
            title: '功能即将开放,敬请期待...',
            icon: 'none'
          })
        }
      }
    })
  },

  onShareAppMessage: function (res) {
    var me = this;
    var videoInfo = me.data.videoInfo;
    return {
      title: '短视频内容分享',
      path: "pages/videoinfo/videoinfo?videoInfo=" + JSON.stringify(videoInfo)
    }
  },

  // commentsPage: 1,
  //   commentsTotalPage: 1,
  //   commentsList: []



  onReachBottom: function () {
    var me = this;
    var currentPage = me.data.commentsPage;
    var totalPage = me.data.commentsTotalPage;
    if (currentPage === totalPage) {
      return;
    }
    var page = currentPage + 1;
    me.getCommentsList(page);
  }
})