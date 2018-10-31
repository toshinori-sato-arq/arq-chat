package jp.androbo.quick.chat.domain.message

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class MessageText(value: String) extends AnyVal

object MessageText {
  implicit val format: Format[MessageText] = new Format[MessageText] {
    override def writes(o: MessageText): JsValue = JsString(o.value)
    override def reads(json: JsValue): JsResult[MessageText] = json.validate[String].map(MessageText.apply)
  }
}
