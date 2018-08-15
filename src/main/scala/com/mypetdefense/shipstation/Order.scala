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
    gift: Boolean = false,
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
    val requiredParams = (
      ("orderNumber" -> orderNumber) ~
      ("orderDate" -> orderDate) ~
      ("orderStatus" -> orderStatus) ~
      ("billTo" -> Address.flattenAddressObject(billTo)) ~
      ("shipTo" -> Address.flattenAddressObject(shipTo))
    )

    val optionalParams = List(
      orderKey.map(("orderKey", _)),
      paymentDate.map(("paymentDate", _)),
      shipByDate.map(("shipByDate", _)),
      customerUsername.map(("customerUsername", _)),
      customerEmail.map(("customerEmail", _)),
      items.map(i => ("items", compact(render(Extraction.decompose(i))))),
      taxAmount.map(t => ("taxAmount", t.toString)),
      shippingAmount.map(s => ("shippingAmount", s.toString)),
      customerNotes.map(("customerNotes", _)),
      internalNotes.map(("internalNotes", _)),
      giftMessage.map(("giftMessage", _)),
      paymentMethod.map(("paymentMethod", _)),
      requestedShippingService.map(("requestedShippingService", _)),
      carrierCode.map(("carrierCode", _)),
      serviceCode.map(("serviceCode", _)),
      packageCode.map(("packageCode", _)),
      confirmation.map(("confirmation", _)),
      shipDate.map(("shipDate", _)),
      weight.map(w => ("weight", compact(render(Extraction.decompose(w))))),
      dimensions.map(d => ("dimensions", compact(render(Extraction.decompose(d))))),
      insuranceOptions.map(i => ("insuranceOptions", compact(render(Extraction.decompose(i))))),
      advancedOptions.map(a => ("advancedOptions", compact(render(Extraction.decompose(a))))),
      weight.map(w => ("weight", compact(render(Extraction.decompose(w))))),
      tagIds.map(t => ("tagIds", compact(render(Extraction.decompose(t)))))
    ).flatten.map { case (key, value) =>
      JField(key, JString(value))
    }

    val params = compact(render(requiredParams))

    val uri = baseResourceCalculator(exec.baseReq <:< Map("Content-Type" -> "application/json")) / "createorder" << params

    exec.executeFor[Order](uri)
  }
}
