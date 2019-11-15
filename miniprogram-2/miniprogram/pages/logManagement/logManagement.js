// pages/procurement/procurement.js
const app = getApp()
var QQMapWX = require('../../utils/qqmap-wx-jssdk.min.js');
var qqmapsdk = new QQMapWX({
  key: '43HBZ-5MRCU-TYFVB-2WCUI-V4IOJ-GPFG3'
});
Page({

  /**
   * 页面的初始数据
   */
  data: {
    windpower: 0,
    temp: 0,
    weather: '',
    jindu: 40,
    pz: false,
    sgrz: true,
    title: '添加施工日志',
    text: '未选择',
    status: true,
    option: [],
    src: ['/img/jia.png'],
    fzr: '',
    hsr: '',
    start: '',
    over: '',
    name: '',
    zy: [],
    xgrz: []
  },
  search() {
    const that = this
    that.ctx.takePhoto({
      quality: 'heigh',
      success: (res) => {
        wx.getImageInfo({
          src: res.tempImagePath,
          success: function() {
            let data = that.data.src

            if (that.data.src.length == 2) {
              data[1] = res.tempImagePath
            } else {
              data.unshift(res.tempImagePath)
            }
            that.setData({
              src: data
            })
            //console.log(that.data.src)
          }
        })
      }
    })
    that.setData({
      pz: false
    })

    wx.pageScrollTo({
      scrollTop: 280,
      duration: 0
    })
  },
  tzsg: function(e) {
    const that = this
    if (e.target.dataset.option == '未选择' || e.detail.value.fzr == '' || e.detail.value.cy == '' || e.detail.value.bz == '' || e.target.dataset.src.length == 1) {
      wx.showToast({
        title: '数据不完善',
        icon: 'none',
        duration: 1000
      })
    } else {
      let imgs = []
      if (e.target.dataset.src[e.target.dataset.src.length - 1] == '/img/jia.png') {
        wx.cloud.uploadFile({
          cloudPath: new Date().getTime() + '.png',
          filePath: e.target.dataset.src[0],
          success: res => {
            imgs.push(res.fileID)
          },
          fail: console.error
        })
      } else {
        for (let i = 0; i < e.target.dataset.src.length; i++) {
          wx.cloud.uploadFile({
            cloudPath: new Date().getTime() + '.png',
            filePath: e.target.dataset.src[i],
            success: res => {
              imgs.push(res.fileID)
            },
            fail: console.error
          })
        }
      }
      wx.showLoading({
        title: '上传中',
      })
      setTimeout(() => {
        const hour = (new Date().getHours())
        let todaye = ''
        if (hour > 18 && hour < 24) {
          todaye = '黑夜'
        } else {
          todaye = '白天'
        }
        const db = wx.cloud.database()
        db.collection('xgrz').add({
            data: {
              bz: e.detail.value.bz,
              cy: e.detail.value.cy,
              fj: that.data.windpower,
              fzr: e.detail.value.fzr,
              img: imgs,
              over: new Date(),
              sd: todaye,
              tq: that.data.weather,
              xgxm: e.target.dataset.option
            }
          })
          .then(res => {
            wx.hideLoading()
            const length = parseInt(that.data.jindu) + 1
            that.setData({
              sgrz: true,
              src: ['/img/jia.png'],
              jindu: length
            })
            this.updata(length)
            that.sjxx()
            wx.pageScrollTo({
              scrollTop: 0,
              duration: 0
            })
          })
      }, 2000)
    }
  },
  check() {
    if (this.data.src[this.data.src.length - 1] == '/img/jia.png') {
      this.setData({
        pz: true,
        ppp: true
      })
    }
  },
  option(e) {
    this.setData({
      text: e.currentTarget.id,
      status: true
    })
  },
  select() {
    this.setData({
      status: !this.data.status
    })
  },
  btn() {
    this.setData({
      sgrz: false
    })
  },
  xx() {
    this.setData({
      sgrz: true,
      src: ['/img/jia.png']
    })
  },
  sjxx() {
    const that = this
    this.ctx = wx.createCameraContext()

    wx.cloud.init({})
    //数据查询
    const db = wx.cloud.database()
    db.collection('xmgc').where({
      status: false
    }).get({
      success: function(res) {
        console.log(res)
        const con = res.data[0]
        that.setData({
          fzr: con.fuzere,
          hsr: con.hsr,
          name: con.name,
          zy: con.zysj,
          start: con.start.getFullYear() + '-' + (con.start.getMonth() * 1 + 1) + '-' + con.start.getDate(),
          over: con.over.getFullYear() + '-' + (con.over.getMonth() * 1 + 1) + '-' + con.over.getDate(),
          jindu: con.jindu
        })
      }
    })
    db.collection('xum').get({
      success: function(res) {
        const data = res.data.map(e => {
          return e.option
        })
        that.setData({
          option: data
        })
      }
    })
    db.collection('xgrz').get({
      success: function(res) {
        let data = res.data.map(e => {
          e.over = e.over.getFullYear() + '-' + (e.over.getMonth() * 1 + 1) + '-' + e.over.getDate()
          return e
        })
        data.reverse()
        that.setData({
          xgrz: data
        })
      }
    })
  },
  updata(e) {
    const length = parseInt(e) + ''
    const db = wx.cloud.database({})
    db.collection('xmgc').doc('403078e4-b9dd-4504-9950-1d27f41755dd').update({
      data: {
        jindu: length
      },
      success: function(res) {
        console.log(res)
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.sjxx()
    const that = this
    wx.getLocation({
      highAccuracyExpireTime: 4000,
      type: 'gcj02',
      success(res) {
        qqmapsdk.reverseGeocoder({
          location: {
            latitude: res.latitude,
            longitude: res.longitude
          },
          success: function(res) {
            wx.request({
              url: app.serverUrl + "/weather/" + res.result.address_component.district,
              method: 'POST',
              success: function(res) {
                const data = JSON.parse(res.data.data).result.result
                that.setData({ windpower: data.windpower, temp: data.temp, weather: data.weather})
              }
            })

          },
          fail: function(res) {
            console.log('获取当前地址失败');
          }
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