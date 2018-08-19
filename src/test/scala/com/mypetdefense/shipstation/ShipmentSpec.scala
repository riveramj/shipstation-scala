package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import Serialization._
  import Extraction._

import scala.util.{Failure => TryFail, Success => TrySuccess, _}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

import scala.collection.concurrent.TrieMap

import org.scalatest._

class ShipmentSpec extends WordSpec with Matchers {
  implicit val formats = DefaultFormats
  implicit val shipStationExecutor = new ShipStationExecutor("key", "secret")

  "Shipment object" should {
    "retrieve correct fields from ShipStation's JSON" in {
      val json = """
      {
        "shipmentId": 33974374,
        "orderId": 43945660,
        "orderKey": "8061c220f0794a9b92460b8bae6837e4",
        "userId": "123456AB-ab12-3c4d-5e67-89f1abc1defa",
        "orderNumber": "100038-1",
        "createDate": "2014-10-03T06:51:33.6270000",
        "shipDate": "2014-10-03",
        "shipmentCost": 1.93,
        "insuranceCost": 0,
        "trackingNumber": "9400111899561704681189",
        "isReturnLabel": false,
        "batchNumber": "100301",
        "carrierCode": "stamps_com",
        "serviceCode": "usps_first_class_mail",
        "packageCode": "package",
        "confirmation": "delivery",
        "warehouseId": 16079,
        "voided": false,
        "voidDate": null,
        "marketplaceNotified": true,
        "notifyErrorMessage": null,
        "shipTo": {
          "name": "Yoda",
          "company": "",
          "street1": "12223 LOWDEN LN",
          "street2": "",
          "street3": null,
          "city": "MANCHACA",
          "state": "TX",
          "postalCode": "78652-3602",
          "country": "US",
          "phone": "2101235544",
          "residential": null
        },
        "weight": {
          "value": 1,
          "units": "ounces"
        },
        "dimensions": null,
        "insuranceOptions": {
          "provider": null,
          "insureShipment": false,
          "insuredValue": 0
        },
        "advancedOptions": null,
        "shipmentItems": [
        {
          "orderItemId": 56568665,
          "lineItemKey": null,
          "sku": "SQ3785739",
          "name": "Potato Kitten -",
          "imageUrl": null,
          "weight": null,
          "quantity": 1,
          "unitPrice": 1,
          "warehouseLocation": null,
          "options": null,
          "productId": 7565777,
          "fulfillmentSku": null
        }
        ],
        "labelData": null,
        "formData": null
      }"""

      val testShipment = parse(json).extract[Shipment]

      testShipment.shipmentId should equal(33974374)
      testShipment.orderId should equal(43945660)
    }
  }

  "retrieve all shipments from ShipStation mock server" in {
    val shipments = {
      Try(
        Await.result(Shipment.list(), new DurationInt(10).seconds)
      ) match {
        case TrySuccess(Full(shipmentList)) =>
          Full(shipmentList)

        case TrySuccess(shipStationFailure) =>
          shipStationFailure

        case TryFail(throwable: Throwable) =>
          Empty
      }
    }

    shipments.map(_.shipments.size) should equal(Full(2))
    shipments.map(_.shipments.map(_.shipmentId)).openOr(Nil) should equal(List(33974374, 33974373))
  }
}
