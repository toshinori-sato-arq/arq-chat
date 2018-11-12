import React from 'react'
import MainContentView from './MainContentView'
import SignUpView from './SignUpView'
import LoginView from './LoginView'


export default function ContentView(props) {
  //TODO: propsにAppコンポーネント全体を渡しているのを直したい
  const app = props.app;
  const state = app.state;
  return state.sessionId
    ? (<MainContentView app={props.app} messagesId={props.messagesId} />)
    : state.isSignUpMode
      ? (<SignUpView app={props.app} />)
      : (<LoginView app={props.app} />)
    ;
}
