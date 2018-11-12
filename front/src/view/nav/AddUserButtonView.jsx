import React from 'react'

export default function AddUserButtonView(props) {
  const canAddUser = props.sessionId
    && props.activeRoom
    && props.activeRoom.privileges
    && props.activeRoom.privileges.includes
    && props.activeRoom.privileges.includes('RoomInvitable')
  return canAddUser
    ? (
      <span className="navbar-item">
        <button
          className="button"
          onClick={props.enterAddUserView}>部屋にメンバーを追加</button>
      </span>
    )
    : <span></span>
}