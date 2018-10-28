package jp.androbo.quick.chat.domain.message

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class MessageId(value: String) extends AnyVal

object MessageId {
  implicit val format: Format[MessageId] = new Format[MessageId] {
    override def writes(o: MessageId): JsValue = JsString(o.value)
    override def reads(json: JsValue): JsResult[MessageId] = json.validate[String].map(MessageId.apply)
  }
}
