import React from 'react'

export default function SubMenuView(props) {
  const app = props.app;
  const activeRoom = props.activeRoom;
  const rooms = props.rooms.map(room => {
    return <li key={room.id}>
      <a
        className={activeRoom && room.id === activeRoom.id ? "is-active" : ""}
        onClick={() => {
          app.changeAcriveRoom(room)
        }}
      >{room.name}</a>
    </li>
  })
  const users = props.users.map(u => {
    return <li key={u.email}>{u.name}</li>
  })

  const asideStyle = {
    minHeight: "calc(100vh - 69px)",
    marginTop: "10px",
    marginLeft: "5px",
  }

  return (
    <div className="submenu column is-3">
      <aside className="menu box" style={asideStyle}>
        <p className="menu-label">
          チャット部屋一覧
        </p>
        <ul className="menu-list">{rooms}</ul>
        <div className="box">
          <p className="menu-label">
            {activeRoom ? activeRoom.name : ""}のメンバー
          </p>
          <ul className="menu-list">{users}</ul>
        </div>
      </aside>
    </div>
  )
}

