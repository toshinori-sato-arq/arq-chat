package jp.androbo.quick.chat.domain

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class Password(value: String) extends AnyVal

object Password {
  implicit val format: Format[Password] = new Format[Password] {
    override def writes(o: Password): JsValue = JsString(o.value)
    override def reads(json: JsValue): JsResult[Password] = json.validate[String].map(Password.apply)
  }
}
