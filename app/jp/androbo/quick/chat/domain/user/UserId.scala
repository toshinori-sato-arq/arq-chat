package jp.androbo.quick.chat.domain.user

import jp.androbo.quick.chat.domain.email.EmailAddress
import jp.androbo.quick.chat.domain.error.ErrorEvent
import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class UserId(email: EmailAddress)

object UserId {
  implicit val format: Format[UserId] = new Format[UserId] {
    override def writes(o: UserId): JsValue = JsString(o.email.value)
    override def reads(json: JsValue): JsResult[UserId] = json.validate[EmailAddress].map(UserId.apply)
  }
}
