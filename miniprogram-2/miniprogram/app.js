//app.js
App({
  serverUrl: "http://139.159.141.225:8081",
  //全局用户对象
  userInfo: null,
  redisSession: null,

  //设置缓存
  setGlobalUserInfo: function (user) {
    wx.setStorageSync("userInfo", user);
  },

  //获取缓存
  getGlobalUserInfo: function () {
    return wx.getStorageSync("userInfo");
  },

  //清空本地缓存
  clearGlobalUserInfo: function () {
    wx.clearStorageSync("userInfo");
  },

  reportReasonArray: [
    "色情低俗",
    "政治敏感",
    "涉嫌诈骗",
    "辱骂谩骂",
    "广告垃圾",
    "诱导分享",
    "引人不适",
    "过于暴力",
    "违法违纪",
    "其它原因"
  ],
  onLaunch: function () {
    //数据推送
    /*wx.cloud.init({ env: 'a502050312' })
    const db = wx.cloud.database()
    db.collection('dangerous').where({
      status: '1'
    }).watch({
      onChange: function (snapshot) {
        console.log(snapshot.docChanges)//新增记录
        console.log(snapshot.docs)//所以条数   
      },
      onError: function (err) {
        console.error('the watch closed because of error', err)
      }
    })*/
    // ...
    // 等到需要关闭监听的时候调用 close() 方法
    //watcher.close()
    //
    if (!wx.cloud) {
      console.error('请使用 2.2.3 或以上的基础库以使用云能力')
    } else {
      wx.cloud.init({
        // env 参数说明：
        //   env 参数决定接下来小程序发起的云开发调用（wx.cloud.xxx）会默认请求到哪个云环境的资源
        //   此处请填入环境 ID, 环境 ID 可打开云控制台查看
        //   如不填则使用默认环境（第一个创建的环境）
        // env: 'my-env-id',
        traceUser: true,
      })
    }
    this.globalData = {}
  }
})