import React from 'react'
import SubMenuView from './SubMenuView'
import MessagesView from './message/MessagesView'
import MessagesInputView from './message/MessageInputView'

export default function MainContent(props) {
  const app = props.app;
  const state = app.state;

  const messagesDivisionStyle = {
    marginTop: "22px",
    marginRight: "16px",
    maxHeight: "calc(100vh - 69px)",
    minHeight: "calc(100vh - 69px)",
  }

  const mainContentStyle = {
    "height": "calc(100vh - 52px)",
  }

  return <div className="main columns" style={mainContentStyle}>
    <SubMenuView
      app={app}
      rooms={app.state.rooms}
      activeRoom={app.state.activeRoom}
      users={app.state.users}
    />
    <div className="column box" style={messagesDivisionStyle}>
      <MessagesView
        messages={state.messages}
        users={state.users}
        messagesId={props.messagesId}
      />
      <MessagesInputView
        messageInput={state.messageInput}
        updateMessageInput={app.updateMessageInput}
        sendMessage={app.sendMessage}
      />
    </div>
  </div>
}
