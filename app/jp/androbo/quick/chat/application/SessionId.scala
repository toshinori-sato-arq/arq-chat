package jp.androbo.quick.chat.application

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class SessionId(value: String) extends AnyVal

object SessionId {
  implicit val format: Format[SessionId] = new Format[SessionId] {
    override def reads(json: JsValue): JsResult[SessionId] = json.validate[String].map(SessionId.apply)
    override def writes(o: SessionId): JsValue = JsString(o.value)
  }
}
