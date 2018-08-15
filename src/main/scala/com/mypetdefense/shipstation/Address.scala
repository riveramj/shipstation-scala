package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

case class Address(
  name: Option[String] = None,
  company: Option[String] = None,
  street1: String,
  street2: Option[String] = None,
  street3: Option[String] = None,
  city: String,
  state: String,
  postalCode: String,
  country: Option[String] = None,
  phone: Option[String] = None,
  residential: Option[Boolean] = None,
  addressVerified: Option[String] = None,
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}
