const app = getApp()

Page({
    data: {
        reasonType: "请选择原因",
        reportReasonArray: app.reportReasonArray,
        publishUserId:"",
        videoId:""
    },

    onLoad:function(params) {
      var me = this;
      var videoId = params.videoId;
      var publishUserId = params.publishUserId;

      me.setData({
        publishUserId: publishUserId,
        videoId: videoId
      });
    },

    changeMe:function(e) {
      var me = this;

      var index = e.detail.value;
      var reasonType = app.reportReasonArray[index];

      me.setData({
        reasonType: reasonType
      });
    },

    submitReport:function(e) {
      wx.showToast({
        title: '功能即将开放,敬请期待...',
        icon: 'none'
      })
    }
})
