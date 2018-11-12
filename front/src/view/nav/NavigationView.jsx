import React from 'react'
import AddRoomButtonView from './AddRoomButtonView'
import AddUserButtonView from './AddUserButtonView'
import LogoutButtonView from './LogoutButtonView'

export default function NavigationView(props) {
  const app = props.app
  return <header>
    <nav className="navbar is-fixed-top">
      <div className="navbar-brand">
        <span className="navbar-item">
          <img src="/img/chat-logo.png" alt="logo" />
        </span>
        <AddRoomButtonView
          sessionId={app.state.sessionId}
          enterAddRoomView={app.enterAddRoomView}
        />
        <AddUserButtonView
          sessionId={app.state.sessionId}
          enterAddUserView={app.enterAddUserView}
          activeRoom={app.state.activeRoom}
        />
        <LogoutButtonView
          sessionId={app.state.sessionId}
          logout={app.logout}
        />
      </div>
    </nav>
  </header>
}