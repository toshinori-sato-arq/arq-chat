package jp.androbo.quick.chat.domain.room

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class RoomName(value: String) extends AnyVal

object RoomName {
  implicit val format: Format[RoomName] = new Format[RoomName] {
    override def writes(o: RoomName): JsValue = JsString(o.value)
    override def reads(json: JsValue): JsResult[RoomName] = json.validate[String].map(RoomName.apply)
  }
}
