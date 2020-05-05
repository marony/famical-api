package models

import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import scalikejdbc._
import skinny.orm._
import skinny.orm.feature._
import skinny.orm.feature.associations.Association

case class Authentication(userId: Int, sessionId: Option[String], sessionExpireAt: Option[DateTime], loginId: String, password: String, insertedAt: DateTime, updatedAt: DateTime)

object Authentication extends SkinnyMapper[Authentication] {
  override lazy val tableName = "authentications"
  override lazy val defaultAlias = createAlias("a")
  override def extract(rs: WrappedResultSet, n: ResultName[Authentication]): Authentication = new Authentication(
    userId = rs.get(n.userId),
    sessionId = rs.get(n.sessionId),
    sessionExpireAt = rs.get(n.sessionExpireAt),
    loginId = rs.get(n.loginId),
    password = rs.get(n.password),
    insertedAt = rs.get(n.insertedAt),
    updatedAt = rs.get(n.updatedAt))
}
