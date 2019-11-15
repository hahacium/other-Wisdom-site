// pages/register/register.js
var util = require('../../utils/util.js');
var QQMapWX = require('../../utils/qqmap-wx-jssdk.min.js');
var qqmapsdk = new QQMapWX({
  key: '43HBZ-5MRCU-TYFVB-2WCUI-V4IOJ-GPFG3'
});
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ddqd:false,
    month:0,
    year:0,
    day:0,
    liaot: '111',
    disabled: true,
    takeData: '',
    pz: true,
    ani: {},
    title: '请面对摄像头',
    miao: 3,
    src: '/img/cx.png',
    rlsb: true,
    biaoti: '点击签到',
    date:0,
    qdata:[
      '','','','','','','','','','','','','','','','',''
    ],
    sum:0,
    qdatat:0,
    srcoll:0,
    nickname: '',
    time: [],
    faceUrl: '../../img/index/cx-10.jpg'
  },
  btn(){
    var that = this
    var userInfo = app.getGlobalUserInfo();
    if (userInfo == null || userInfo == '' || userInfo == undefined) {
      wx.hideLoading();
      wx.switchTab({
        url: '../mine/mine'
      })
      wx.showToast({
        title: '请登录!',
        icon: 'none',
        duration: 3000
      })
    }else {
      if (that.data.biaoti ==='点击签到'){
      wx.showActionSheet({
        itemList: ['人脸识别', '现场拍照'],
        success: (res) => {
          if (res.tapIndex == 0) {
            const time = setInterval(function () {
              if (that.data.miao == 0) {
                that.search()
                that.setData({ miao: 5 })
                that.setData({ title: '头像校对中' })
                clearInterval(time)
              } else {
                that.setData({ miao: (that.data.miao - 1) })
              }
            }, 1000)
            this.setData({ rlsb: false })
          } else if (res.tapIndex == 1) {
            //拍照界面
            this.setData({ pz: false })
          }
        },
        fail(res) {

        }
      })
      }

    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.start()
    that.ctx = wx.createCameraContext()
    var userInfo = app.getGlobalUserInfo();
    wx.request({
      url: app.serverUrl + '/timer',
      method:'GET',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success:function(res) {
        var string = JSON.stringify(res.data.data);
        var time = JSON.parse(string);
        console.log("RES:" + time)
        that.setData({
          day: time.day,
          month: time.month,
          year: time.year,
        })
        // if (that.data.day == 01) {
        //   that.setData({
        //     qdatat: 0
        //   })
        // }
      }
    })
    wx.request({
      url: app.serverUrl + '/checkStatus/' + userInfo.id,
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success: function(res) {
        console.log("aa:"+res);
        if (res.data.data != null && res.data.data != '' && res.data.data != undefined) {
          that.setData({
            biaoti: '已签到' 
          })
        }
      }
    })
    wx.request({
      url: app.serverUrl + '/checkInRecord/' + userInfo.id,
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success: function(res) {
        console.log(res.data.data);
        if (res.data.data == null || res.data.data == '' || res.data.data == undefined) {
          that.setData({
            qdatat: 0,
            sum: 0
          })
        }else {
          that.setData({
            qdatat: res.data.data.monthTime,
            sum: res.data.data.sum
          })
        }
      }
    })
    wx.request({
      url: app.serverUrl + '/checkInTime/' + userInfo.id,
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success: function(res) {
        console.log(res.data);
        var faceUrl = '../../img/index/cx-10.jpg';
        if(userInfo.faceImage != null && userInfo.faceImage != '' && userInfo.faceImage != undefined) {
          faceUrl = app.serverUrl + userInfo.faceImage
        }
       // for(var i = 0 ; i < res.data.data.length ; i++) {
          // res.data.data[i].finishTime = util.js_date_time(res.data.data[i].finishTime)
          // console.log(util.js_date_time(res.data.data[i].finishTime))
        //}
        that.setData({
          nickname: userInfo.name,
          faceUrl: faceUrl,
          time: res.data.data
        })
      }
    })
    wx.request({
      url: app.serverUrl + '/checkInDay/'+userInfo.id,
      method:'POST',
      header: {
        'content-type': 'application/json', // 默认值
      },
      success: function(res) {
        ///////////////
        console.log(res);
        let qdata = []
        let sy = 0
        for (let i = 0; i < res.data.data.length; i++){
          sy = res.data.data[i].finishTimeStr -1
          qdata[sy] = res.data.data[i].finishTimeStr
        }
        that.setData({ qdata })
        that.setData({ srcoll: qdata[qdata.length-1]*57 })
       // console.log(res);
        /*console.log('----------------------');
        console.log(qdata[22]);
        console.log('----------------------');
        */
      }
    })
    var year = that.data.year;
    var mouth = that.data.month;
    var days;
    if (mouth == 2) {
      days = year % 4 == 0 ? 29 : 28;
    }
    else if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
      days = 31;
    }
    else {
      days = 30;
    }
    this.setData({ date: days })
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

  },
 
  //相机图片截图
  search() {
    var that = this
    that.ctx.takePhoto({
      quality: 'heigh',
      success: (res) => {
        wx.getImageInfo({
          src: res.tempImagePath,
          success: function (res) {
          }
        })
        that.setData({
          src: res.tempImagePath
        })
        setTimeout(()=>{
          console.log(that.data.src);
          var userInfo = app.getGlobalUserInfo();
          var tempImagePath = res.tempImagePath;
          console.log(tempImagePath);
          wx.uploadFile({
            url: app.serverUrl + '/faceTest?userId=' + userInfo.id + '&idCard=' + userInfo.idCard + '&realName=' + userInfo.name,
            method: 'POST',
            filePath: tempImagePath,
            name: 'file',
            header: {
              'content-type': 'application/json', // 默认值
            },
            success: function (res) {
              //var that = this;
              var data1 = JSON.parse(res.data);
              var data = JSON.parse(data1.data);
              console.log(data);
              if (data.Data == null || data.Data == '' || data.Data == undefined) {
                wx.showLoading({
                  title: '验证失败,请重试...',
                })
                wx.hideLoading();
                return;
              }
              if (data.Code == 0) {
                wx.showLoading({
                  title: '人脸验证中...',
                })

                if (Number(data.Data) > 75) {
                  console.log(Number(data.Data));
                  wx.hideLoading();
                  wx.request({
                    url: app.serverUrl + '/checkIn/' + userInfo.id,
                    method: 'GET',
                    header: {
                      'content-type': 'application/json', // 默认值
                    },
                    success: function (res) {
                      //console.log(that,1111111111111111111111111111111)
                      that.setData({
                        biaoti: '已签到',
                        rlsb: true
                      })
                      let page = getCurrentPages().pop();
                      if (page == undefined || page == null) {
                        return;
                      }
                      page.onLoad();
                      wx.showToast({
                        title: '签到成功',
                        icon: 'success',
                        duration: 3000
                      })
                    }
                  })
                }
              } else {
                wx.showLoading({
                  title: '验证失败,请重试...',
                })
                wx.hideLoading();
                wx.navigateBack({

                })
              }
            }
          })
        },5000)

        /*wx.request({
          url: app.serverUrl + '/faceTest?imgUrl=' + that.data.src + '&idCard='+userInfo.idCard+'&realName='+userInfo.name,
          method:'POST',
          header: {
            'content-type': 'application/json', // 默认值
          },
          success:function(res) {
            console.log(res);
          }
        })*/
      }
    })
  },
  //信息认证
  xxrq() {
    var that = this;
    var userInfo = app.getGlobalUserInfo();
    console.log(this.data.src)
    console.log(this.data.liaot);
    wx.showLoading({
      title: '认证中',
    })
    console.log(that.data.takeData.data);
    if (that.data.takeData.data > 0) {
      wx.request({
        url: app.serverUrl + '/checkIn/' + userInfo.id + '?data=' + that.data.takeData.data,
        method: 'GET',
        header: {
          'content-type': 'application/json', // 默认值
        },
        success: function (res) {
          that.setData({
            biaoti: '已签到',
            rlsb: true,
            ddqd:true
          })
          setTimeout(() => {
            that.setData({ pz: true })
          },2000)
          

          let page = getCurrentPages().pop();
          if (page == undefined || page == null) {
            return;
          }
          page.onLoad();
          

          setTimeout(() => {
            wx.showToast({
              title: '签到成功',
              icon: 'success',
              duration: 3000
            })
          }, 2000)
         
        }
      })
    }else {

    }
   
  },
  //位置获取
   getlocalhost() {
    const that = this
    var arradd 
    wx.getLocation({
      highAccuracyExpireTime: 4000,
      type: 'gcj02',
     success(res) {
        qqmapsdk.reverseGeocoder({
          location: {
            latitude: res.latitude,
            longitude: res.longitude
          },
          success:function (res) {
            //获取当前地址成功
            that.setData({
              liaot: res.result.address
            })
          },
          fail: function (res) {
            console.log('获取当前地址失败');
          }
        })
      }
    })
  },
  //相机图片截图
    take () {
    const that = this
    that.ctx.takePhoto({
      quality: 'heigh',
      success:(res) => {
        wx.getImageInfo({
          src: res.tempImagePath,
          success: function (res) {
          }
        })
        that.getlocalhost()
        that.setData({
          src: res.tempImagePath
        })
        setTimeout(()=>{
          console.log(that.data.liaot);
          var userInfo = app.getGlobalUserInfo();
          var tempImagePath = res.tempImagePath;
          console.log(tempImagePath);
          //发送网络请求
          wx.uploadFile({
            url: app.serverUrl + '/faceTest?userId=' + userInfo.id + '&idCard=' + userInfo.idCard + '&realName=' + userInfo.name + '&address=' + that.data.liaot,
            method: 'POST',
            filePath: tempImagePath,
            name: 'file',
            header: {
              'content-type': 'application/json', // 默认值
            },
            success: function (res) {
              console.log(res);
              var data = JSON.parse(res.data);
              that.setData({
                takeData: data
              })
              wx.showToast({
                title: '点击底部签到',
                icon:'none',
                duration:2000
              })
            }
          });
        },600)
        that.setData({ disabled: false })
      }
    })
  },
  //动画效果
  start() {
    const that = this
    const animation = wx.createAnimation({
      duration: 3000,
      timingFunction: 'linear'
    })
    var bz = 2

    setInterval(() => {
      if (bz == 1) {
        that.starttop(animation)
        bz = 2
      } else {
        that.startbottom(animation)
        bz = 1
      }
    }, 3000)
  },
  //动画向上
  starttop(e) {
    const height = (wx.getSystemInfoSync().windowHeight) * 51 / 100
    e.translateY(height).step()
    this.setData({
      ani: e.export()
    })
  },
  //动画向下
  startbottom(e) {
    e.translateY(0).step()
    this.setData({
      ani: e.export()
    })
  }
})