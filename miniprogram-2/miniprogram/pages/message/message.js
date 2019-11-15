// pages/home/home.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user:[],
    sum: 0,
    show: false,
    gly: false,
    aqyh: true,
    title: '打开'
  },
  danger(){
    if(this.data.title=='打开'){
      this.setData({ title:'关闭'})
    }else{
      this.setData({ title: '打开' })
    }
    this.setData({ aqyh: !this.data.aqyh})
  },
  btn: function (e) {
    const db = wx.cloud.database()
    db.collection('dangerous').add({
      data: {
        status: '1',
        name: '213123',
        time: new Date(),
        des: '123123'
      },
      success: function (res) {
        console.log(res)
      }
    })
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
    if (app.getGlobalUserInfo()) {
      //登录
      console.log(app.getGlobalUserInfo().status)
      this.setData({ show: true })
      if (app.getGlobalUserInfo().status == '1') {  //管理员
        this.setData({ gly: true })
        const that = this
        wx.cloud.init({ env: 'a502050312' })
        const db = wx.cloud.database()
        db.collection('dangerous').where({
          status: '1'
        }).watch({
          onChange: function (snapshot) {
            let data = snapshot.docs
            for (let i = 0; i < snapshot.docs.length; i++) {
              data[i].time = snapshot.docs[i].time.getHours() + ':' + snapshot.docs[i].time.getMinutes() + ':' + snapshot.docs[i].time.getSeconds()
            }
            that.setData({ user: data, sum: data.length })
          },
          onError: function (err) {
            console.error('the watch closed because of error', err)
          }
        })
      }
    } else {
      //不登录
      this.setData({ show: false, gly:false})
    }
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