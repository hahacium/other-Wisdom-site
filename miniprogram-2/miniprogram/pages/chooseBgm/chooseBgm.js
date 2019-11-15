const app = getApp()

Page({
    data: {
      serverUrl: "",
      videoParams: {}
    },

    onLoad: function (params) {
      var serverUrl = app.serverUrl;
      var me = this;
      console.log(params);
      me.setData({
        videoParams: params,
        serverUrl: serverUrl
      })

      //debugger;
      // 调用后端
     
    },
    upload: function(e){
      var me = this;
      var desc = e.detail.value.desc;
      console.log("desc:" + desc);
      var duration = me.data.videoParams.duration;
      var tmpHeight = me.data.videoParams.tmpHeight;
      var tmpWidth = me.data.videoParams.tmpWidth;
      var tmpVideoUrl = me.data.videoParams.tmpVideoUrl;
      var tmpCoverUrl = me.data.videoParams.tmpCoverUrl;
      //上传短视频
      wx.showLoading({
        title: '上传中...',
      })
      //var user = app.userInfo;
      var serverUrl = app.serverUrl;
      // fixme 修改原有的全局对象为本地缓存
      var userInfo = app.getGlobalUserInfo();
      wx.uploadFile({
        url: serverUrl + '/video/upload',
        filePath: tmpVideoUrl,
        formData: {
          userId: userInfo.id,
          desc: desc,
          videoSeconds: duration,
          videoWidth: tmpWidth,
          videoHeight: tmpHeight
        },
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
           
            wx.navigateBack({
              delta: 1,
            })
            wx.showToast({
              title: '上传成功,向下滑动刷新!~~',
              icon: 'none',
              duration: 3000
            })
          }
          else if (data.status == 502) {
            wx.showToast({
              title: data.msg,
              duration: 3000,
              icon: "none",
              success: function () {
                wx.removeStorageSync("userInfo");
                wx.switchTab({
                  url: '../mine/mine',
                })
              }
            });
          } 
        }
      })
    }
})

