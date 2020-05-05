package models

import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import scalikejdbc._
import skinny.orm._
import skinny.orm.feature._
import skinny.orm.feature.associations.Association

case class User(id: Int, familyId: Int, familyOwner: Boolean, name: String, insertedAt: DateTime, updatedAt: DateTime)

object User extends SkinnyMapper[User] {
  override lazy val tableName = "users"
  override lazy val defaultAlias = createAlias("u")
  override def extract(rs: WrappedResultSet, n: ResultName[User]): User = new User(
    id = rs.get(n.id),
    familyId = rs.get(n.familyId),
    familyOwner = rs.get(n.familyOwner),
    name = rs.get(n.name),
    insertedAt = rs.get(n.insertedAt),
    updatedAt = rs.get(n.updatedAt))
}
