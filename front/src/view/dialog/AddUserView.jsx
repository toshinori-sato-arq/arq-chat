import React from 'react'

export default function AddUserView(props) {
  const app = props.app
  const addUserDialogClasses = `modal ${app.state.isModeAddUser ? 'is-active' : ''}`
  return (
    <div className={addUserDialogClasses}>
      <div className="modal-background"></div>
      <div className="modal-card">
        <header className="modal-card-head">
          <p className="modal-card-title">メンバーの追加</p>
          <button className="delete" aria-label="close" onClick={app.leaveAddUserView}></button>
        </header>
        <section className="modal-card-body">
          <div className="field">
            <input
              className="input"
              type="text"
              placeholder="追加するメンバーのメールアドレス"
              value={app.state.addUserEMail}
              onChange={e => app.updateAddUserEMail(e.target.value)}
            />
          </div>
        </section>
        <footer className="modal-card-foot">
          <button
            className="button is-success"
            onClick={() => app.addUser(app.state.addUserEMail)}
          >追加</button>
          <button className="button" onClick={app.leaveAddUserView}>Cancel</button>
        </footer>
      </div>
    </div>
  )
}