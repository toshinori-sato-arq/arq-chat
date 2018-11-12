import React from 'react'

export default function LoginView(props) {
  const app = props.app;
  const state = app.state;
  const loginProcess = () => {
    app.login(
      state.loginEMailAddress,
      state.loginPassword,
    )
  };
  return <div className="columns">
    <div className="container card column is-half is-offset-one-quarter">
      <header className="card-header">
        <p className="card-header-title">
          ログインする
            </p>
      </header>
      <div className="field">
        <div className="control has-icons-left has-icons-right">
          <input
            className="input"
            type="email"
            placeholder="Email"
            value={state.loginEMailAddress}
            onChange={e => app.updateLoginEmail(e.target.value)}
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
            value={state.loginPassword}
            onChange={e => app.updateLoginPassword(e.target.value)}
            onKeyPress={e => {
              if (e.key === "Enter") {
                loginProcess();
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
              onClick={loginProcess}
            >
              login
            </button>
            <button
              className="button is-success"
              onClick={() => {
                app.enterSignUp(true)
              }}
            >
              signup
            </button>
          </div>
        </div>
      </div>
      <div className="help is-danger">{app.state.loginError}</div>
    </div>
  </div>
}
