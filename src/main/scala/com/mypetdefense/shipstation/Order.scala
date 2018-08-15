package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import JsonDSL._
  import Extraction._
import net.liftweb.util.Helpers._

import dispatch._, Defaults._

import scala.collection.JavaConversions._

case class Order(
  orderId: Int,
  orderNumber: String,
  orderKey: Option[String] = None,
  orderDate: String,
  createDate: Option[String] = None,
  paymentDate: Option[String] = None,
  shipByDate: Option[String] = None,
  orderStatus: String,
  customerId: Option[String] = None,
  customerUsername: Option[String] = None,
  customerEmail: Option[String] = None,
  billTo: Address,
  shipTo: Address,
  items: Option[List[OrderItem]] = None,
  orderTotal: Option[Double] = None,
  amountPaid: Option[Double] = None,
  taxAmount: Option[Double] = None,
  shippingAmount: Option[Double] = None,
  customerNotes: Option[String] = None,
  internalNotes: Option[String] = None,
  gift: Option[Boolean] = None,
  giftMessage: Option[String] = None,
  paymentMethod: Option[String] = None,
  requestedShippingService: Option[String] = None,
  carrierCode: Option[String] = None,
  serviceCode: Option[String] = None,
  packageCode: Option[String] = None,
  confirmation: Option[String] = None,
  shipDate: Option[String] = None,
  holdUntilDate: Option[String] = None,
  weight: Option[Weight] = None,
  dimensions: Option[Dimensions] = None,
  insuranceOptions: Option[InsuranceOptions] = None,
  internationalOptions: Option[InternationalOptions] = None,
  advancedOptions: Option[AdvancedOptions] = None,
  tagIds: Option[List[Int]] = None,
  userId: Option[String] = None,
  externallyFulfilled: Option[Boolean] = None,
  externallyFulfilledBy: Option[String] = None,
  raw: Option[JValue] = None
) extends ShipStationObject {
  def withRaw(raw: JValue) = this.copy(raw = Some(raw))
}

object Order extends Gettable[Order] {
  def baseResourceCalculator(req: Req) = req / "orders"
  
   def create(
    orderNumber: String,
    orderKey: Option[String] = None,
    orderDate: String,
    paymentDate: Option[String] = None,
    shipByDate: Option[String] = None,
    orderStatus: String,
    customerUsername: Option[String] = None,
    customerEmail: Option[String] = None,
    billTo: Address,
    shipTo: Address,
    items: Option[List[OrderItem]] = None,
    taxAmount: Option[Double] = None,
    shippingAmount: Option[Double] = None,
    customerNotes: Option[String] = None,
    internalNotes: Option[String] = None,
    gift: Option[Boolean] = None,
    giftMessage: Option[String] = None,
    paymentMethod: Option[String] = None,
    requestedShippingService: Option[String] = None,
    carrierCode: Option[String] = None,
    serviceCode: Option[String] = None,
    packageCode: Option[String] = None,
    confirmation: Option[String] = None,
    shipDate: Option[String] = None,
    weight: Option[Weight] = None,
    dimensions: Option[Dimensions] = None,
    insuranceOptions: Option[InsuranceOptions] = None,
    advancedOptions: Option[AdvancedOptions] = None,
    tagIds: Option[List[Int]] = None
  )(implicit exec: ShipStationExecutor): Future[Box[Order]] = {
    val newOrder = Order(
      orderId = -1,
      orderNumber = orderNumber,
      orderKey = orderKey,
      orderDate = orderDate,
      paymentDate = paymentDate,
      shipByDate = shipByDate,
      orderStatus = orderStatus,
      customerUsername = customerUsername,
      customerEmail = customerEmail,
      billTo = billTo,
      shipTo = shipTo,
      items = items,
      taxAmount = taxAmount,
      shippingAmount = shippingAmount,
      customerNotes = customerNotes,
      internalNotes = internalNotes,
      gift = gift,
      giftMessage = giftMessage,
      paymentMethod = paymentMethod,
      requestedShippingService = requestedShippingService,
      carrierCode = carrierCode,
      serviceCode = serviceCode,
      packageCode = packageCode,
      confirmation = confirmation,
      shipDate = shipDate,
      weight = weight,
      dimensions = dimensions,
      insuranceOptions = insuranceOptions,
      advancedOptions = advancedOptions,
      tagIds = tagIds
    )

    val params = compact(render(Extraction.decompose(newOrder)))

    val uri = baseResourceCalculator(exec.baseReq <:< Map("Content-Type" -> "application/json")) / "createorder" << params

    exec.executeFor[Order](uri)
  }
}
