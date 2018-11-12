import React, { Component } from 'react';
import NotificationSystem from 'react-notification-system'
import './App.css';
import axios from 'axios'
import ContentView from './view/ContentView'
import Urls from './app/Urls'
import CookieManager from './app/CookieManger'
import NavigationView from './view/nav/NavigationView'
import AddRoomView from './view/dialog/AddRoomView'
import AddUserView from './view/dialog/AddUserView'

axios.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
axios.defaults.withCredentials = true;

function createMessageInputState(text) {
  return {
    text
  };
}

function getMessageInputInitialState() {
  return createMessageInputState("");
}

class App extends Component {
  constructor(props) {
    super(props);

    this._notificationSystem = React.createRef();

    this.state = {
      loginEMailAddress: "",
      loginPassword: "",
      loginError: "",
      signUpName: "",
      signUpEMailAddress: "",
      signUpPassword: "",
      signUpError: "",
      addRoomName: "",
      addRoomDescription: "",
      rooms: [],
      activeRoom: null,
      messages: [],
      messageInput: getMessageInputInitialState(),
      sessionId: CookieManager.loadSessionId(),
      users: [],
      isSignUpMode: false,
      isModeAddRoom: false,
      isModeAddUser: false,
      addUserEMail: "",
    };

    this.fetchInInitialize()

    setInterval(() => {
      if (this.state.sessionId) {
        this.fetch()
      }
    }, 3000)

    this.login = this.login.bind(this);
    this.updateLoginEmail = this.updateLoginEmail.bind(this);
    this.updateLoginPassword = this.updateLoginPassword.bind(this);

    this.logout = this.logout.bind(this);

    this.chageSignUpMode = this.chageSignUpMode.bind(this);
    this.enterSignUp = this.enterSignUp.bind(this);
    this.updateSignUpName = this.updateSignUpName.bind(this);
    this.updateSignUpEmail = this.updateSignUpEmail.bind(this);
    this.updateSignUpPassword = this.updateSignUpPassword.bind(this);

    this.enterAddRoomView = this.enterAddRoomView.bind(this);
    this.leaveAddRoomView = this.leaveAddRoomView.bind(this);
    this.updateAddRoomName = this.updateAddRoomName.bind(this);
    this.updateAddRoomDescription = this.updateAddRoomDescription.bind(this);
    this.addRoom = this.addRoom.bind(this);

    this.updateAddUserEMail = this.updateAddUserEMail.bind(this);
    this.updateMessageInput = this.updateMessageInput.bind(this);
    this.enterAddUserView = this.enterAddUserView.bind(this);
    this.leaveAddUserView = this.leaveAddUserView.bind(this);

    this.changeAcriveRoom = this.changeAcriveRoom.bind(this);

    this.sendMessage = this.sendMessage.bind(this);
  }

  chageSignUpMode(b) {
    this.setState({
      isSignUpMode: b,
    });
  }

  changeAcriveRoom(room) {
    this.setState({
      activeRoom: room,
    })
  }

  enterSignUp() {
    this.setState({
      signUpEMailAddress: "",
      signUpName: "",
      signUpPassword: "",
      signUpError: "",
      isSignUpMode: true,
    });
  }

  enterAddRoomView() {
    this.setState({
      isModeAddRoom: true,
      addRoomName: "",
      addRoomDescription: "",
    })
  }

  leaveAddRoomView() {
    this.setState({
      isModeAddRoom: false,
      addRoomName: "",
      addRoomDescription: "",
    })
  }

  enterAddUserView() {
    this.setState({
      isModeAddUser: true,
      addUserEMail: "",
    })
  }

  leaveAddUserView() {
    this.setState({
      isModeAddUser: false,
      addUserEMail: "",
    })
  }

  fetchInInitialize() {
    this.fetchRooms().then(() => {
      this.fetchMessages()
      this.fetchUsers()
    })
  }

  fetch() {
    this.fetchRooms()
    this.fetchMessages(false)
    this.fetchUsers()
  }

  fetchRooms() {
    return axios.get(Urls.rooms()).then(r => {
      const rooms = r.data.rooms
      const activeRoom = this.state.activeRoom || rooms.find(r => r.name === 'マイページ') || rooms[0]
      this.setState({ rooms, activeRoom })
    }).catch(e => console.error(e))
  }

  addRoom(name, description) {
    return axios.post(Urls.rooms(), { name, description }).then(r => {
      this.setState({
        isModeAddRoom: false,
      });
      this._notificationSystem.current.addNotification({
        message: `部屋の作成をしました`,
        level: 'success'
      });
      this.fetchRooms();
    }).catch(e => {
      this._notificationSystem.current.addNotification({
        message: `部屋の作成に失敗しました`,
        level: 'error'
      });
    })
  }

  fetchUsers() {
    if (this.state.activeRoom) {
      axios.get(Urls.users(this.state.activeRoom.id)).then(r => {
        this.setState({ users: r.data })
      }).catch(e => console.error(e))
    }
  }

  addUser(email) {
    return axios.post(Urls.users(this.state.activeRoom.id), { userId: email }).then(r => {
      this.setState({
        isModeAddUser: false,
      });
      this._notificationSystem.current.addNotification({
        message: `メンバーを追加をしました`,
        level: 'success'
      });
      this.fetchUsers();
    }).catch(e => {
      this._notificationSystem.current.addNotification({
        message: `メンバーの追加に失敗しました`,
        level: 'error'
      });
    })
  }

  fetchMessages(scrollBottom = true) {
    if (this.state.activeRoom) {
      axios.get(Urls.messages(this.state.activeRoom.id)).then(r => {
        this.setState({ messages: r.data })
        const messages = document.getElementById("messages")
        if (messages && scrollBottom) {
          messages.scroll(0, messages.scrollHeight)
        }
      }).catch(e => console.error(e))
    }
  }

  componentDidUpdate(prevProps, prevState) {
    if (prevState && prevState.activeRoom && this.state.activeRoom) {
      if (prevState.activeRoom.id !== this.state.activeRoom.id) {
        this.fetchUsers();
        this.fetchMessages();
      }
    }
  }

  signUp(name, email, password) {
    name = name.trim();
    email = email.trim();
    password = password.trim();
    const requestBody = { name, email, password };
    axios.post(Urls.signup(), requestBody).then((res) => {
      this.chageSignUpMode(false);
      this._notificationSystem.current.addNotification({
        message: `サインアップに成功しました！`,
        level: 'success'
      });
    }).catch(e => {
      this.setState({
        signUpError: (e && e.response && e.response.data && e.response.data.message) || "エラー"
      })
    })
  }

  login(email, password) {
    email = email.trim();
    if (!email) {
      this.setState({
        loginError: "メールアドレスを入力してください"
      });
      return;
    }
    password = password.trim();
    if (!password) {
      this.setState({
        loginError: "パスワードを入力してください"
      });
      return;
    }

    if (email && password) {
      const requestBody = { email, password }
      axios.post(Urls.login(), requestBody).then(res => {
        const sessionId = res.data.sessionId
        CookieManager.saveSessionId(sessionId)
        this.setState({
          sessionId,
          loginEMailAddress: "",
          loginPassword: "",
          loginError: "",
        });
        this.fetchInInitialize()
      }).catch(e => {
        this.setState({
          loginError: (e && e.response && e.response.data && e.response.data.message) || "エラー"
        })
      })
    }
  }

  logout() {
    axios.delete(Urls.logout()).finally(() => {
      CookieManager.removeSessionId()
      this.setState({
        sessionId: "",
        activeRoom: null,
        messages: [],
        users: [],
      });
    })
  }

  updateLoginEmail(text) {
    this.setState({
      loginEMailAddress: text.trim(),
    });
  }

  updateLoginPassword(text) {
    this.setState({
      loginPassword: text.trim(),
    });
  }

  updateSignUpName(text) {
    this.setState({
      signUpName: text.trim(),
    });
  }

  updateSignUpEmail(text) {
    this.setState({
      signUpEMailAddress: text.trim(),
    });
  }

  updateSignUpPassword(text) {
    this.setState({
      signUpPassword: text.trim(),
    });
  }

  updateAddRoomName(t) {
    this.setState({
      addRoomName: t.trim(),
    })
  }

  updateAddRoomDescription(t) {
    this.setState({
      addRoomDescription: t.trim(),
    })
  }

  updateAddUserEMail(t) {
    this.setState({
      addUserEMail: t.trim(),
    })
  }

  sendMessage() {
    if (this.state.activeRoom) {
      const trimed = this.state.messageInput.text.trim();
      if (trimed) {
        axios.post(Urls.messages(this.state.activeRoom.id), { text: trimed }).then(() => {
          this.fetchMessages()
          this.setState({
            messageInput: getMessageInputInitialState(),
          });
        }).catch(e => {
          this._notificationSystem.current.addNotification({
            message: `メッセージの送信に失敗しました`,
            level: 'error'
          });
        })
      }
    }
  }

  updateMessageInput(text) {
    this.setState({
      messageInput: createMessageInputState(text),
    });
  }

  render() {
    return (
      <div>
        <NavigationView app={this} />
        <AddRoomView app={this} />
        <AddUserView app={this} />
        <ContentView app={this} messagesId="messages" />
        <NotificationSystem ref={this._notificationSystem} />
      </div>
    );
  }
}

export default App;
