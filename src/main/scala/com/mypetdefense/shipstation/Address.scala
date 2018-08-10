package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

case class Address(
  name: String,
  company: String,
  street1: String,
  street2: String,
  street3: String,
  city: String,
  state: String,
  postalCode: String,
  country: String,
  phone: String,
  addressVerified: String,
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}
