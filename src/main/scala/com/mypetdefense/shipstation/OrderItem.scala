package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

case class OrderItem(
  orderItemId: Int,
  lineItemKey: Option[String] = None,
  sku: Option[String] = None,
  name: Option[String] = None,
  imageUrl: Option[String] = None,
  weight: Option[Weight] = None,
  quantity: Int,
  unitPrice: Option[Double] = None,
  taxAmount: Option[Double] = None,
  shippingAmount: Option[Double] = None,
  warehouseLocation: Option[String] = None,
  options: Option[List[ItemOption]] = None,
  productId: Option[Int] = None,
  fulfillmentSku: Option[String] = None,
  adjustment: Option[Boolean] = None,
  upc: Option[String] = None,
  createDate: Option[String] = None,
  modifyDate: Option[String] = None,
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}

object OrderItem extends Gettable[OrderItem] {
  def baseResourceCalculator(req: Req) = req / "products"
}
