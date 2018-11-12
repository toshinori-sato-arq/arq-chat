import React from 'react';

export default function SignUp(props) {
  const app = props.app;
  const state = app.state;
  return <div className="columns">
    <div className="container card column is-half is-offset-one-quarter">
      <header className="card-header">
        <p className="card-header-title">
          新規ユーザを登録する
        </p>
      </header>
      <div className="field">
        <div className="control has-icons-left has-icons-right">
          <input
            className="input"
            type="text"
            placeholder="Name(名前)"
            value={state.signUpName}
            onChange={e => app.updateSignUpName(e.target.value)}
          />
          <span className="icon is-small is-left">
            <i className="fas fa-user"></i>
          </span>
        </div>
      </div>
      <div className="field">
        <div className="control has-icons-left has-icons-right">
          <input
            className="input"
            type="email"
            placeholder="Email"
            value={state.signUpEMailAddress}
            onChange={e => app.updateSignUpEmail(e.target.value)}
          />
          <span className="icon is-small is-left">
            <i className="fas fa-envelope"></i>
          </span>
        </div>
      </div>
      <div className="field">
        <div className="control has-icons-left">
          <input
            className="input"
            type="password"
            placeholder="Password"
            value={state.signUpPassword}
            onChange={e => app.updateSignUpPassword(e.target.value)}
            onKeyPress={e => {
              if (e.key === "Enter") {
                app.signUp(
                  state.signUpName,
                  state.signUpEMailAddress,
                  state.signUpPassword,
                )
              }
            }}
          />
          <span className="icon is-small is-left">
            <i className="fas fa-lock"></i>
          </span>
        </div>
      </div>
      <div className="field">
        <div className="control">
          <div className="buttons">
            <button
              className="button is-primary"
              onClick={() => {
                app.signUp(
                  state.signUpName,
                  state.signUpEMailAddress,
                  state.signUpPassword,
                )
              }}
            >
              signup
              </button>
            <button
              className="button"
              onClick={() => {
                app.chageSignUpMode(false)
              }}
            >
              キャンセル
                </button>
          </div>
        </div>
      </div>
      <div className="help is-danger">{app.state.signUpError}</div>
    </div>
  </div>
}
