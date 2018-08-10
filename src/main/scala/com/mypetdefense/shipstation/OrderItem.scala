package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

case class OrderItem(
  orderItemId: Int,
  lineItemKey: Option[String],
  sku: Option[String],
  name: Option[String],
  imageUrl: Option[String],
  weight: Option[Weight],
  quantity: Int,
  unitPrice: Option[Double],
  taxAmount: Option[Double],
  shippingAmount: Option[Double],
  warehouseLocation: Option[String],
  options: List[ItemOption],
  productId: Option[Int],
  fulfillmentSku: Option[String],
  adjustment: Boolean,
  upc: Option[String],
  createDate: String,
  modifyDate: String,
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}
