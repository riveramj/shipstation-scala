package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

case class Order(
  orderId: Int,
  orderNumber: String,
  orderKey: String,
  orderDate: String,
  createDate: String,
  paymentDate: String,
  shipByDate: String,
  orderStatus: String,
  customerId: String,
  customerUsername: String,
  customerEmail: String,
  billTo: Address,
  shipTo: Address,
  items: List[OrderItem],
  orderTotal: Double,
  amountPaid: Double,
  taxAmount: Double,
  shippingAmount: Double,
  customerNotes: String,
  internalNotes: String,
  gift: Boolean,
  giftMessage: String,
  paymentMethod: String,
  requestedShippingService: String,
  carrierCode: String,
  serviceCode: String,
  packageCode: String,
  confirmation: String,
  shipDate: String,
  holdUntilDate: String,
  weight: Weight,
  dimensions: Dimensions,
  insuranceOptions: InsuranceOptions,
  internationalOptions: InternationalOptions,
  advancedOptions: AdvancedOptions,
  tagIds: List[Int],
  userId: String,
  externallyFulfilled: Boolean,
  externallyFulfilledBy: String,
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}

object Order extends Gettable[Order] {
  def baseResourceCalculator(req: Req) =
    req / "orders"
 }
