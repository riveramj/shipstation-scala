package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

import scala.collection.JavaConversions._

trait ShipStationList[T] extends ShipStationObject {
  val total: Int
  val page: Int
  val pages: Int
  val raw: Option[JValue]
}
