import React from 'react'

export default function MessageInputView(props) {
  const messageInputStyle = {
    marginTop: "5px",
  }
  return (
    <div className="field is-grouped" style={messageInputStyle}>
      <div className="field-body">
        <div className="field">
          <p className="control">
            <input
              className="input"
              type="text"
              placeholder="メッセージ入力..."
              value={props.messageInput.text}
              onChange={e => props.updateMessageInput(e.target.value)}
              onKeyPress={e => {
                if (e.key === 'Enter') {
                  props.sendMessage();
                }
              }}
            />
          </p>
        </div>
      </div>
      <p className="control">
        <button
          className="button is-info"
          onClick={props.sendMessage}
        >送信</button>
      </p>
    </div>
  )
}

