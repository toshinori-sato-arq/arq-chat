package jp.androbo.quick.chat.domain.model.event.error

import javax.inject.Singleton

@Singleton
class SimpleErrorMessageGenerator extends ErrorMessageGenerator {
  override def generate(e: ErrorEvent): String = e match {
    case ErrorEvent.DuplicatedEmailAddress => "そのメールアドレスは使用できません"
    case ErrorEvent.InvalidEmailAddress => "そのメールアドレスは使用できません"
    case ErrorEvent.LoginError => "メールアドレスまたはパスワードが正しくありません"
  }
}
