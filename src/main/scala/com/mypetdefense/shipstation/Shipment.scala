package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

import scala.collection.JavaConversions._

case class Shipment(
  shipmentId: Int,
  orderId: Int,
  orderKey: Option[String],
  userId: String,
  orderNumber: String,
  createDate: String,
  shipDate: String,
  shipmentCost: Double,
  insuranceCost: Double,
  trackingNumber: String,
  isReturnLabel: Boolean,
  batchNumber: Option[String],
  carrierCode: String,
  serviceCode: String,
  packageCode: String,
  confirmation: String,
  warehouseId: Option[Int],
  voided: Boolean,
  voidDate: Option[String],
  marketplaceNotified: Boolean,
  notifyErrorMessage: Option[String],
  shipTo: Address,
  weight: Weight,
  dimensions: Option[Dimensions],
  insuranceOptions: Option[InsuranceOptions],
  advancedOptions: Option[AdvancedOptions],
  shipmentItems: Option[List[OrderItem]],
  labelData: Option[String],
  formDate: Option[String],
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}

object Shipment extends Listable[ShipmentList] {
  def baseResourceCalculator(req: Req) = req / "shipments"
}
