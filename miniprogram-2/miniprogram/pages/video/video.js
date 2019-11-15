// pages/video/video.js
var videoUtil = require('../../utils/videoUtil.js')

const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //用于分页的属性
    //总页数
    totalPage: 1,
    //当前页
    page: 1,
    videoList: [],
    screenWidth: 350,
    serverUrl: "",
    searchContent: "",
    screenHeight: 210
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (params) {
    var me = this;
    var screenWidth = wx.getSystemInfoSync().screenWidth;
    var screenHeight = wx.getSystemInfoSync().screenHeight;
    me.setData({
      screenWidth: screenWidth,
      screenHeight: screenHeight
    });
    wx.showLoading({
      title: '请等待，加载中...',
    });

    var searchContent = params.searchValue;
    var isSaveRecord = params.isSaveRecord;
    if (isSaveRecord == null || isSaveRecord == '' || isSaveRecord == undefined) {
      isSaveRecord = 0;
    }
    console.log(searchContent);
    me.setData({
      searchContent: searchContent
    });
    // 获取当前的分页数
    var page = me.data.page;
    me.getAllVideoList(page, isSaveRecord);
  },
  getAllVideoList: function (page, isSaveRecord) {
    var me = this;
    var serverUrl = app.serverUrl;
    var searchContent = me.data.searchContent;
    wx.request({
      url: serverUrl + '/video/showAll/' + page + '/' + isSaveRecord,
      method: "POST",
      data: {
        videoDesc: searchContent
      },
      success: function (res) {
        wx.hideLoading();
        //隐藏导航动画
        wx.hideNavigationBarLoading();
        //停止下拉刷新
        wx.stopPullDownRefresh();
        console.log(res.data);
        // 判断当前页page是否是第一页，如果是第一页，那么设置videoList为空
        if (page === 1) {
          me.setData({
            videoList: []
          });
        }
        var videoList = res.data.data.rows;
        console.log(videoList);
        var newVideoList = me.data.videoList;
        console.log(newVideoList);
        me.setData({
          videoList: newVideoList.concat(videoList),
          page: page,
          totalPage: res.data.data.total,
          serverUrl: serverUrl
        });
      }
    })
  },
  onReachBottom: function () {
    // 页面触底时执行
    var me = this;
    var currentPage = me.data.page;
    var totalPage = me.data.totalPage;
    //判断当前页数跟总页数是否相等，相等无需查询
    if (currentPage === totalPage) {
      wx.showToast({
        title: '已经没有视频了！~~',
        icon: 'none',
        duration: 3000
      })
      return;
    } else {
      var page = currentPage + 1;
      me.getAllVideoList(page, 0);
    }
  },
  onPullDownRefresh: function () {
    //导肮加载动画
    wx.showNavigationBarLoading();
    this.getAllVideoList(1, 0);
  },
  showVideoInfo: function (e) {
    var me = this;
    var videoList = me.data.videoList;
    var arrindex = e.target.dataset.arrindex;
    var videoInfo = JSON.stringify(videoList[arrindex]);
    console.log(videoInfo);
    wx.navigateTo({
      url: '../videoinfo/videoinfo?videoInfo=' + videoInfo
    })
  },
  upload:function() {
    var me = this;
    var userInfo = app.getGlobalUserInfo();
    if(userInfo == null || userInfo == '' || userInfo == undefined) {
      wx.switchTab({
        url: '../mine/mine',
      })
      wx.showToast({
        title: '请登录!',
        icon: 'none',
        duration: 3000
      })
    }else {
      videoUtil.uploadVideo();
    }
  }
})