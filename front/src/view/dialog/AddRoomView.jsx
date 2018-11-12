import React from 'react'

export default function AddRoomView(props) {
  const app = props.app
  const addRoomDialogClasses = `modal ${app.state.isModeAddRoom ? 'is-active' : ''}`;
  return (
    <div className={addRoomDialogClasses}>
      <div className="modal-background"></div>
      <div className="modal-card">
        <header className="modal-card-head">
          <p className="modal-card-title">部屋の追加</p>
          <button className="delete" aria-label="close" onClick={app.leaveAddRoomView}></button>
        </header>
        <section className="modal-card-body">
          <div className="field">
            <input
              className="input"
              type="text"
              placeholder="部屋の名前"
              value={app.addRoomName}
              onChange={e => app.updateAddRoomName(e.target.value)}
            />
          </div>
          <div className="field">
            <input
              className="input"
              type="text"
              placeholder="部屋の説明"
              value={app.state.addRoomDescription}
              onChange={e => app.updateAddRoomDescription(e.target.value)}
            />
          </div>
        </section>
        <footer className="modal-card-foot">
          <button
            className="button is-success"
            onClick={() => app.addRoom(app.state.addRoomName, app.state.addRoomDescription)}
          >追加</button>
          <button className="button" onClick={app.leaveAddRoomView}>Cancel</button>
        </footer>
      </div>
    </div>
  );
}