name := "ShipStation-Scala"

version := "0.0.1-SNAPSHOT"

organization := "com.mypetdefense.shipstation"

scalaVersion := "2.11.12"

libraryDependencies ++= {
  val liftVersion = "2.6.3"
  Seq(
    "ch.qos.logback"            %  "logback-classic"      % "1.1.3",
    "net.liftweb"               %% "lift-common"          % liftVersion,
    "net.liftweb"               %% "lift-util"            % liftVersion,
    "net.liftweb"               %% "lift-json"            % liftVersion,
    "net.databinder.dispatch"   %% "dispatch-core"        % "0.11.3",
    "net.databinder.dispatch"   %% "dispatch-lift-json"   % "0.11.3",
    "org.scalatest"             %% "scalatest"            % "2.2.6"        % "test"
  )
}

initialCommands := """
import com.mypetdefense.shipstation._
import scala.concurrent._
import scala.concurrent.duration._
"""

enablePlugins(BuildInfoPlugin)

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)

buildInfoPackage := "com.mypetdefense.shipstation"

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".sonatype")

pomExtra :=
<url>https://github.com/riveramj/shipstation-scala</url>
<licenses>
  <license>
    <name>Apache 2</name>
    <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    <distribution>repo</distribution>
  </license>
</licenses>
<scm>
  <url>https://github.com/riveramj/shipstation-scala.git</url>
  <connection>https://github.com/riveramj/shipstation-scala.git</connection>
</scm>
<developers>
  <developer>
    <id>riveramj</id>
    <name>Mike Rivera</name>
    <email>rivera.mj@gmail.com</email>
  </developer>
</developers>

