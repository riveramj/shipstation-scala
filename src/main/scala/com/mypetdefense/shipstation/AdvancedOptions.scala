package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

case class AdvancedOptions(
  warehouseId: Option[Int],
  nonMachinable: Boolean,
  saturdayDelivery: Boolean,
  containsAlcohol: Boolean,
  storeId: Option[Int],
  customField1: Option[String],
  customField2: Option[String],
  customField3: Option[String],
  source: Option[String],
  mergedOrSplit: Boolean,
  mergedIds: List[Int],
  parentId: Option[Int],
  billToParty: Option[String],
  billToAccount: Option[String],
  billToPostalCode: Option[String],
  billToCountryCode: Option[String],
  billToMyOtherAccount: Option[String],
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}

