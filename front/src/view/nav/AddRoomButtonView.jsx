import React from 'react'

export default function AddRoomButtonView(props) {
  return props.sessionId
    ? (
      <span className="navbar-item">
        <button
          className="button"
          onClick={props.enterAddRoomView}>部屋を追加</button>
      </span>
    )
    : <span></span>
}