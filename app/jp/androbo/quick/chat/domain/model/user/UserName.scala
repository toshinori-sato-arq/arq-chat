package jp.androbo.quick.chat.domain.model.user

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class UserName(value: String) extends AnyVal

object UserName {
  implicit val format: Format[UserName] = new Format[UserName] {
    override def writes(o: UserName): JsValue = JsString(o.value)
    override def reads(json: JsValue): JsResult[UserName] = json.validate[String].map(UserName.apply)
  }
}
