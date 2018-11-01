package jp.androbo.quick.chat.domain.error

import javax.inject.Singleton

@Singleton
class SimpleErrorMessageGenerator extends ErrorMessageGenerator {
  override def generate(e: ErrorEvent): String = e match {
    case ErrorEvent.DuplicatedEmailAddress => "そのメールアドレスは使用できません"
    case ErrorEvent.InvalidEmailAddress => "そのメールアドレスは使用できません"
    case ErrorEvent.LoginError => "メールアドレスまたはパスワードが正しくありません"
    case ErrorEvent.UnauthorizedOperation => "その操作は許可されていません"
    case ErrorEvent.NotFound => "対象のリソースが見つかりません"
    case ErrorEvent.AlreadyJoined => "すでに参加済みです"
  }
}
