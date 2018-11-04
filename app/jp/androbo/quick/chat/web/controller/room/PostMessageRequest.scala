package jp.androbo.quick.chat.web.controller.room

import jp.androbo.quick.chat.domain.message.MessageText
import play.api.libs.json.{Format, Json}

case class PostMessageRequest(text: MessageText)

object PostMessageRequest {
  implicit val format: Format[PostMessageRequest] = Json.format[PostMessageRequest]
}