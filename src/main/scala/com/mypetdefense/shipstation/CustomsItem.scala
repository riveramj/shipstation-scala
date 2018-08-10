package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

case class CustomsItems(
  customsItemId: String,
  description: String,
  quantity: Double,
  value: Double,
  harmonizedTariffCode: String,
  countryOfOrigin: String,
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}

