import React from 'react'

export default function LogoutButtonView(props) {
  return props.sessionId
    ? (
      <span className="navbar-item">
        <button className="button" onClick={props.logout}>ログアウト</button>
      </span>
    )
    : <span></span>
}