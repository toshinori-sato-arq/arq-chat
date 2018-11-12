import React from 'react';
import MessageView from './MessageView'

export default function Messages(props) {
  const users = props.users || [];
  const messages = props.messages;
  const messagesStyle = {
    minHeight: "calc(100vh - 136px)",
    maxHeight: "calc(100vh - 136px)",
    overflowY: "scroll",
  }
  return (
    <div style={messagesStyle} id={props.messagesId}>
      {messages.map(m => {
        return <MessageView
          key={m.id}
          message={m}
          user={users.find(u => u.email === m.userId)}
        />
      })}
    </div>
  );
}
