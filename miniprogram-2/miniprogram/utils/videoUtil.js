function uploadVideo() {
  var me = this;
  wx.chooseVideo({
    sourceType: ['album', 'camera'],
    camera: 'back',
    success(res) {
      console.log(res)
      var duration = res.duration;
      var tmpHeight = res.height;
      var tmpWidth = res.width;
      var tmpVideoUrl = res.tempFilePath;
      var tmpCoverUrl = res.thumbTempFilePath;
      if (duration < 2) {
        wx.showToast({
          title: '视频长度太短,请上传超过2秒的视频...',
          icon: "none",
          duration: 2000
        });
      } else {
        //打开输入视频描述的页面
        wx.navigateTo({
          url: '../chooseBgm/chooseBgm?duration=' + duration
            + "&tmpHeight=" + tmpHeight
            + "&tmpWidth=" + tmpWidth
            + "&tmpVideoUrl=" + tmpVideoUrl
            + "&tmpCoverUrl=" + tmpCoverUrl,
        })
      }
    }
  })
}
module.exports = {
  uploadVideo: uploadVideo
}
