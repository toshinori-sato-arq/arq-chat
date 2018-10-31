package jp.androbo.quick.chat.web.controller

import com.google.inject.Inject
import jp.androbo.quick.chat.application.{AuthenticatedUser, SessionId, SessionManager}
import jp.androbo.quick.chat.domain.error.{ErrorEvent, ErrorMessageGenerator}
import play.api.http.HeaderNames.{CACHE_CONTROL, EXPIRES, PRAGMA}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Results._
import play.api.mvc.Security.AuthenticatedRequest
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
 * ActionFunctionを集めたクラス
 */
class ApiActions @Inject()(
                            parser: BodyParsers.Default,
                            errorMessageGenerator: ErrorMessageGenerator,
                            sessionManager: SessionManager,
                            ec: ExecutionContext,
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

  type AuthenticatedRequestType[A] = AuthenticatedRequest[A, AuthenticatedUser]

  def authenticate: ActionBuilder[AuthenticatedRequestType, AnyContent] = noCache.andThen(sessionIdActionRefiner)

  private def sessionIdActionRefiner: ActionRefiner[Request, AuthenticatedRequestType] =
    new ActionRefiner[Request, AuthenticatedRequestType] {
      override protected def refine[A](request: Request[A]): Future[Either[Result, AuthenticatedRequest[A, AuthenticatedUser]]] =
        Future {
          (for {
            sessionId <- request.cookies.get("arq-chat-session-id").map(c => SessionId(c.value))
            user <- sessionManager.findUserBySessionId(sessionId)
          } yield AuthenticatedUser(sessionId, user)).toRight(Unauthorized).map(a => new AuthenticatedRequest(a, request))
        }(executionContext)

      override protected def executionContext: ExecutionContext = ec
    }

  def error(e: ErrorEvent): JsValue = {
    Json.toJson(ErrorResponse(errorMessageGenerator.generate(e)))
  }
}
