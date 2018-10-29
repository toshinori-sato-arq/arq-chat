package jp.androbo.quick.chat.domain.model.email

import play.api.libs.json._

case class EmailAddress private[email] (value: String) extends AnyVal

object EmailAddress {
  /**
    * 文字列が正しいメールアドレスかどうか検証する
    * @param s 検証する文字列
    * @return
    */
  def isValidEmailAddress(s: String): Boolean = {
    val splits = s.split('@')
    s.count(_ == '@') == 1 && splits.length == 2 && splits.forall(_.nonEmpty)
  }

  implicit val format: Format[EmailAddress] = new Format[EmailAddress] {
    override def writes(o: EmailAddress): JsValue = JsString(o.value)

    override def reads(json: JsValue): JsResult[EmailAddress] = {
      json.validate[String].flatMap(s => {
        if (isValidEmailAddress(s)) {
          JsSuccess(EmailAddress(s))
        } else {
          // TODO: メッセージID
          JsError("Invalid Email address format")
        }
      })
    }
  }
}