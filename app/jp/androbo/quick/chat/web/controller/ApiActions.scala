package jp.androbo.quick.chat.web.controller

import com.google.inject.Inject
import jp.androbo.quick.chat.domain.model.event.error.{ErrorEvent, ErrorMessageGenerator}
import play.api.http.HeaderNames.{CACHE_CONTROL, EXPIRES, PRAGMA}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * ActionFunctionを集めたクラス
  */
class ApiActions @Inject()(
                            parser: BodyParsers.Default,
                            ec: ExecutionContext,
                            errorMessageGenerator: ErrorMessageGenerator,
                          ) {

  def noCache: ActionBuilder[Request, AnyContent] =
    new ActionBuilderImpl(parser)(ec) {
      override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = {
        block(request).map(
          _.withHeaders(
            PRAGMA -> "no-cache",
            CACHE_CONTROL -> "no-cache",
            EXPIRES -> "Thu, 01 Jan 1970 00:00:00 GMT"
          ))
      }
    }

  def error(e: ErrorEvent): JsValue = {
    Json.toJson(ErrorResponse(errorMessageGenerator.generate(e)))
  }
}
