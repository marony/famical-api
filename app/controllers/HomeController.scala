package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import scalikejdbc._
import skinny.orm._, feature._
import org.joda.time._
import java.util.UUID

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    // テストでユーザー情報を表示
    skinny.DBSettings.initialize()
    val u = models.User.findById(1)
    Ok(views.html.index(u.get.name, u.get.updatedAt))
  }
}
