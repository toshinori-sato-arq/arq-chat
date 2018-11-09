package jp.androbo.quick.chat.web.controller

import controllers.Assets
import javax.inject._
import play.api.mvc._

@Singleton
class StaticAssetController @Inject()(
                                       cc: ControllerComponents,
                                       assets: Assets,
                                     ) extends AbstractController(cc) {
  def at(path: String, file: String): Action[AnyContent] = {
    val s = file.split('.')
    val ext = s.last.toLowerCase
    val asset = assets.at(path, file)
    if(s.length > 1) {
      if (ext == "html" || ext == "htm") {
        //TODO: HTMLはキャッシュしないようにする
        asset
      }else{
        asset
      }
    } else {
      val url = if(file.last.equals('/')) file else file + "/"
      assets.at(path, url + "index.html")
    }
  }
}
