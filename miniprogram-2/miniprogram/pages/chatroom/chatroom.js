const db = wx.cloud.database()
const chatroomCollection = db.collection('chatroom')
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null, // 存储当前用户信息
    textInputValue: '', // 存储输入内容
    chats: [], // 存储聊天记录
    openId: '', // 当前用户openid
    serverUrl: app.serverUrl
  },

  onLoad(options) {
    var me = this;
    var userInfo = app.getGlobalUserInfo();
    if (userInfo == null || userInfo == '' || userInfo == undefined) {
      me.setData({
        userInfo: null
      })
    }else {
      me.setData({
        userInfo: userInfo,
        openId: userInfo.id
      })
    }
    console.log(userInfo)
  },

  async onReady() {
    chatroomCollection.watch({
      onChange: this.onChange.bind(this),
      onError(err){
        console.error(err)
      }
    })
  },

  onChange(snapshot){
    console.log(snapshot);
    // 监听开始时的初始化数据
    if (snapshot.type === 'init') {
      this.setData({
        chats: [
          ...this.data.chats,
          ...[...snapshot.docs].sort((x, y) => x.sendTimeTS - y.sendTimeTS),
        ],
      })
    } else {
      const chats = [...this.data.chats]
      for (const docChange of snapshot.docChanges) {
        // queueType:列表更新类型，表示更新事件对监听列表的影响
        switch (docChange.queueType) {
          // init	初始化列表
          // update	列表中的记录内容有更新，但列表包含的记录不变
          // enqueue	记录进入列表
          // dequeue	记录离开列表
          case 'enqueue': // 记录进入列表
            chats.push(docChange.doc)
            break
        }
      }
      this.setData({
        chats: chats.sort((x, y) => x.sendTimeTS - y.sendTimeTS),
      })
    }
    console.log(this.data.chats);
    console.log(this.data.userInfo);
  },

  onGetUserInfo(e) {
    if (e.detail.userInfo) {
      this.setData({
        userInfo: e.detail.userInfo
      })
    }
  },

  onTextInput(e) {
    this.setData({
      textInputValue: e.detail.value
    })
  },

  onSend() {
    if (!this.data.textInputValue) {
      return
    }
    const doc = {
      id: this.data.userInfo.id,
      avatar: this.data.userInfo.faceImage,
      nickName: this.data.userInfo.name,
      msgText: 'text',
      textContent: this.data.textInputValue,
      sendTime: new Date(),
      sendTimeTS: Date.now(),
    }
    chatroomCollection.add({
      data: doc,
    })
    this.setData({
      textInputValue: '',
    })
  },
})