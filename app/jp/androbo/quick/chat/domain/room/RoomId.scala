package jp.androbo.quick.chat.domain.room

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class RoomId private[room](value: String) extends AnyVal

object RoomId {
  implicit val format: Format[RoomId] = new Format[RoomId] {
    override def writes(o: RoomId): JsValue = JsString(o.value)
    override def reads(json: JsValue): JsResult[RoomId] = json.validate[String].map(RoomId.apply)
  }
}
