name := "Movie Recommendation New"
 
version := "1.0"
 
scalaVersion := "2.10.4"

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "1.6.0", "org.apache.spark" %% "spark-mllib" % "1.6.0", "org.apache.spark" %% "spark-sql" % "1.6.0", "org.apache.spark" %% "spark-hive" % "1.6.0")

resolvers += Resolver.url("bintray-sbt-plugins", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
