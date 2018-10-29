package jp.androbo.quick.chat.domain.model.room

import play.api.libs.json.{Format, JsResult, JsString, JsValue}

case class RoomDescription(value: String) extends AnyVal

object RoomDescription {
  implicit val format: Format[RoomDescription] = new Format[RoomDescription] {
    override def writes(o: RoomDescription): JsValue = JsString(o.value)
    override def reads(json: JsValue): JsResult[RoomDescription] = json.validate[String].map(RoomDescription.apply)
  }
}
