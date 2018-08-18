package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
import net.liftweb.util._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._
import com.ning.http.client.Response

/**
 * The base class for all singletons that will facilitate communication
 * with ShipStation on behalf of the top-level model objects.
**/
trait ShipStationMeta {
  implicit val formats = DefaultFormats

  /**
   * This function should return the base resource URL for whatever kind
   * of resource this is.
  **/
  def baseResourceCalculator(request: Req): Req

  def metadataProcessor(metadata: Map[String, String]) = {
    metadata.map({
      case (key, value) =>
        (s"metadata[$key]", value)
    })
  }
}

trait Gettable[T <: ShipStationObject] extends ShipStationMeta {
  def get(id: String)(implicit exec: ShipStationExecutor, mf: Manifest[T]): Future[Box[T]] = {
    val getReq = baseResourceCalculator(exec.baseReq) / id
    exec.executeFor[T](getReq)
  }
}


abstract class Listable[Z <: ShipStationList[_]](implicit mf: Manifest[Z]) extends ShipStationMeta {
  def list(implicit exec: ShipStationExecutor): Future[Box[Z]] = {
    exec.executeFor[Z](baseResourceCalculator(exec.baseReq))
  }
}

/*
trait Deleteable extends ShipStationMeta {
  def delete(id: String)(implicit exec: ShipStationExecutor): Future[Box[DeleteResponse]] = {
    val deleteReq = (baseResourceCalculator(exec.baseReq) / id).DELETE
    exec.executeFor[DeleteResponse](deleteReq)
  }
}
*/

/**
 * The base class for all singletons that will facilitate communication
 * with ShipStation on behalf of the child-level model objects.
**/
trait ChildShipStationMeta {
  implicit val formats = DefaultFormats

  /**
   * This function should return the base resource URL for whatever kind
   * of resource this is.
  **/
  def baseResourceCalculator(request: Req, parentId: String): Req

  def metadataProcessor(metadata: Map[String, String]) = {
    metadata.map({
      case (key, value) =>
        (s"metadata[$key]", value)
    })
  }
}

trait ChildGettable[T <: ShipStationObject] extends ChildShipStationMeta {
  def get(parentId: String, id: String)(implicit exec: ShipStationExecutor, mf: Manifest[T]): Future[Box[T]] = {
    val getReq = baseResourceCalculator(exec.baseReq, parentId) / id
    exec.executeFor[T](getReq)
  }
}

abstract class ChildListable[Z <: ShipStationList[_]](implicit mf: Manifest[Z]) extends ChildShipStationMeta {
  def list(parentId: String)(implicit exec: ShipStationExecutor): Future[Box[Z]] = {
    exec.executeFor[Z](baseResourceCalculator(exec.baseReq, parentId: String))
  }
}
/*
trait ChildDeleteable extends ChildShipStationMeta {
  def delete(parentId: String, id: String)(implicit exec: ShipStationExecutor): Future[Box[DeleteResponse]] = {
    val deleteReq = (baseResourceCalculator(exec.baseReq, parentId) / id).DELETE
    exec.executeFor[DeleteResponse](deleteReq)
  }
}

*/
