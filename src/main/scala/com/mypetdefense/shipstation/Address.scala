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

object Address {
  def flattenAddressObject(address: Address) = {

    List(
      address.name.map(n => ("name", n)),
      address.company.map(c => ("company", c)),
      Some(("street1", address.street1)),
      address.street2.map(s => ("street2", s)),
      address.street3.map(s => ("street3", s)),
      Some(("city", address.city)),
      Some(("state", address.state)),
      Some(("postalCode", address.postalCode)),
      address.country.map(c => ("country", c)),
      address.phone.map(p => ("phone", p)),
      address.residential.map(r => ("residential", r.toString)),
      address.addressVerified.map( a => ("addressVerified", a))
    ).flatten.map { case (key, value) =>
      JField(key, JString(value))
    }
  }
}
