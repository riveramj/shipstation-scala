package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
import net.liftweb.util._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._
import com.ning.http.client.Response

import java.util.Base64
import java.nio.charset.StandardCharsets

/**
 * Case class describing a raw response from ShipStation, including
 * HTTP status code and JValue representation of the payload.
**/
case class ShipStationResponse(code: Int, json: JValue)

/**
 * Response transformer for use with dispatch to turn a Response
 * into an instance of ShipStationResponse.
**/
object AsShipStationResponse extends (Response => ShipStationResponse) {
  def apply(res: Response) = {
    ShipStationResponse(res.getStatusCode(), as.lift.Json(res))
  }
}

/**
 * An executor that will talk to ShipStation for you.
**/
class ShipStationExecutor(
  key: String,
  secret: String,
  uri: String = "http://private-anon-c83884431a-shipstation.apiary-mock.com"
) {
  val basicAuthentication = Base64.getEncoder.encodeToString(s"${key}:${secret}".getBytes(StandardCharsets.UTF_8))

  val httpExecutor = new Http()
  val baseReq = url(uri) <:<
  Map("Authorization" -> s"Basic ${basicAuthentication}", "User-Agent" -> ("shipstation-scala/" + BuildInfo.version))
  
  implicit val formats = DefaultFormats

  /**
   * Execute a request against ShipStation and get a {{Full(ShipStationResponse)}} in reponse if things work
   * (e.g. return a 200) and a {{Failure}} otherwise.
   *
   * In general, the primary consumer of this function will be the library itself. External
   * code shouldn't need to use this unless it's implementing functionality not supported by
   * streifen for some reason.
  **/
  def execute(request: Req): Future[Box[ShipStationResponse]] = {
    httpExecutor(request > AsShipStationResponse).either.map {
      case Left(throwable) =>
        Failure("Error occured while talking to shipStation.", Full(throwable), Empty)

      case Right(shipStationResponse) =>
        Full(shipStationResponse)
    }
  }

  protected def handler[T](implicit mf: Manifest[T]): (ShipStationResponse)=>Box[T] = {
    case ShipStationResponse(200, json) =>
      tryo(json.extract[T])

    case ShipStationResponse(code, json) =>
      Failure(s"Unexpected $code") ~> json
  }

  /**
   * Execute a request against ShipStation and get a fully typed object out of the response if it was
   * successful.
   *
   * In general, the primary consumer of this function will be the library itself. External
   * code shouldn't need to use this unless it's implementing functionality not supported by
   * it for some reason.
  **/
  def executeFor[T <: ShipStationObject](request: Req)(implicit mf: Manifest[T]): Future[Box[T]] = {
    execute(request).map { futureBox =>
      for {
        shipStationResponse <- futureBox
        concreteObject <- handler(mf)(shipStationResponse)
      } yield {
        concreteObject
      }
    }
  }
}
