name := """famical-api"""
organization := "com.binbo-kodakusan"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(jdbc, specs2 % Test, guice,
    // [Scala と Play flamework 入門 - Qiita](https://qiita.com/umaruskie/items/5c354f3df0943ef071a0)
    "org.skinny-framework" %% "skinny-orm" % "3.1.0",
    "org.postgresql" % "postgresql" % "42.2.12",
    "org.flywaydb" %% "flyway-play" % "6.0.0",

    // 認証・認可
    "jp.t2v" %% "play2-auth" % "play-2.7.x-auth-0.15-RC1",

    "ch.qos.logback" %  "logback-classic" % "1.1.+",
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
    )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.binbo-kodakusan.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.binbo-kodakusan.binders._"
