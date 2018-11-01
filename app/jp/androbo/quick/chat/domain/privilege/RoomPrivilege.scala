package jp.androbo.quick.chat.domain.privilege

import play.api.libs.json._

sealed abstract class RoomPrivilege(val code: String)

object RoomPrivilege {
  case object RoomNameRenamable extends RoomPrivilege("RoomNameRenamable")
  case object RoomDescriptionEditable extends RoomPrivilege("RoomDescriptionEditable")
  case object RoomLeavable extends RoomPrivilege("RoomLeavable")
  case object RoomInvitable extends RoomPrivilege("RoomInvitable")
  case object RoomDeletable extends RoomPrivilege("RoomDeletable")

  object Preset {
    val All: Set[RoomPrivilege] = Set(
      RoomNameRenamable,
      RoomDescriptionEditable,
      RoomLeavable,
      RoomInvitable,
      RoomDeletable,
    )
    val MyPage: Set[RoomPrivilege] = Set(RoomDescriptionEditable)
    val Owner: Set[RoomPrivilege] = All
    val BeInvited: Set[RoomPrivilege] = Set(RoomLeavable)
  }

  def apply(code: String): RoomPrivilege = code match {
    case "RoomNameRenamable" => RoomNameRenamable
    case "RoomDescriptionEditable" => RoomDescriptionEditable
    case "RoomLeavable" => RoomLeavable
    case "RoomInvitable" => RoomInvitable
    case "RoomDeletable" => RoomDeletable
  }

  implicit val format: Format[RoomPrivilege] = new Format[RoomPrivilege] {
    override def writes(o: RoomPrivilege): JsValue = JsString(o.code)

    override def reads(json: JsValue): JsResult[RoomPrivilege] = json.validate[String].flatMap{ s =>
      try {
        JsSuccess(RoomPrivilege(s))
      } catch {
        case _: MatchError => JsError(s"Invalid Privilege [$s]")
      }
    }
  }
}
