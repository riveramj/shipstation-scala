package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
  import Serialization._
  import Extraction._
import com.mypetdefense.shipstation.ShipStationHelpers._

import org.scalatest._

class OrderSpec extends WordSpec with Matchers {
  implicit val formats = DefaultFormats

  "Order object" should {
    "retrieve correct fields from ShipStation's JSON" in {
      val json = """
        {
          "orderId": 94113592,
          "orderNumber": "TEST-ORDER-API-DOCS",
          "orderKey": "0f6bec18-9-4771-83aa-f392d84f4c74",
          "orderDate": "2015-06-29T08:46:27.0000000",
          "createDate": "2015-07-16T14:00:34.8230000",
          "modifyDate": "2015-09-08T11:03:12.3800000",
          "paymentDate": "2015-06-29T08:46:27.0000000",
          "shipByDate": "2015-07-05T00:00:00.0000000",
          "orderStatus": "awaiting_shipment",
          "customerId": 37701499,
          "customerUsername": "headhoncho@whitehouse.gov",
          "customerEmail": "headhoncho@whitehouse.gov",
          "billTo": {
            "name": "The President",
            "company": null,
            "street1": null,
            "street2": null,
            "street3": null,
            "city": null,
            "state": null,
            "postalCode": null,
            "country": null,
            "phone": null,
            "residential": null,
            "addressVerified": null
          },
          "shipTo": {
            "name": "The President",
            "company": "US Govt",
            "street1": "1600 Pennsylvania Ave",
            "street2": "Oval Office",
            "street3": null,
            "city": "Washington",
            "state": "DC",
            "postalCode": "20500",
            "country": "US",
            "phone": "555-555-5555",
            "residential": false,
            "addressVerified": "Address validation warning"
          },
          "items": [
          {
            "orderItemId": 128836912,
            "lineItemKey": "vd08-MSLbtx",
            "sku": "ABC123",
            "name": "Test item #1",
            "imageUrl": null,
            "weight": {
              "value": 24,
              "units": "ounces"
            },
            "quantity": 2,
            "unitPrice": 99.99,
            "taxAmount": null,
            "shippingAmount": null,
            "warehouseLocation": "Aisle 1, Bin 7",
            "options": [
            {
              "name": "Size",
              "value": "Large"
            }
            ],
            "productId": 7239919,
            "fulfillmentSku": null,
            "adjustment": false,
            "upc": null,
            "createDate": "2015-07-16T14:00:34.823",
            "modifyDate": "2015-07-16T14:00:34.823"
          },
          {
            "orderItemId": 128836913,
            "lineItemKey": null,
            "sku": "DISCOUNT CODE",
            "name": "10% OFF",
            "imageUrl": null,
            "weight": {
              "value": 0,
              "units": "ounces"
            },
            "quantity": 1,
            "unitPrice": -20.55,
            "taxAmount": null,
            "shippingAmount": null,
            "warehouseLocation": null,
            "options": [],
            "productId": null,
            "fulfillmentSku": null,
            "adjustment": true,
            "upc": null,
            "createDate": "2015-07-16T14:00:34.823",
            "modifyDate": "2015-07-16T14:00:34.823"
          }
          ],
          "orderTotal": 194.43,
          "amountPaid": 218.73,
          "taxAmount": 5,
          "shippingAmount": 10,
          "customerNotes": "Thanks for ordering!",
          "internalNotes": "Customer called and would like to upgrade shipping",
          "gift": true,
          "giftMessage": "Thank you!",
          "paymentMethod": "Credit Card",
          "requestedShippingService": "Priority Mail",
          "carrierCode": "fedex",
          "serviceCode": "fedex_home_delivery",
          "packageCode": "package",
          "confirmation": "delivery",
          "shipDate": "2015-07-02",
          "holdUntilDate": null,
          "weight": {
            "value": 48,
            "units": "ounces"
          },
          "dimensions": {
            "units": "inches",
            "length": 7,
            "width": 5,
            "height": 6
          },
          "insuranceOptions": {
            "provider": "carrier",
            "insureShipment": true,
            "insuredValue": 200
          },
          "internationalOptions": {
            "contents": null,
            "customsItems": null,
            "nonDelivery": null
          },
          "advancedOptions": {
            "warehouseId": 24079,
            "nonMachinable": false,
            "saturdayDelivery": false,
            "containsAlcohol": false,
            "mergedOrSplit": false,
            "mergedIds": [],
            "parentId": null,
            "storeId": 26815,
            "customField1": "Custom data that you can add to an order. See Custom Field #2 & #3 for more info!",
            "customField2": "Per UI settings, this information can appear on some carrier's shipping labels. See link below",
            "customField3": "https://help.shipstation.com/hc/en-us/articles/206639957",
            "source": "Webstore",
            "billToParty": null,
            "billToAccount": null,
            "billToPostalCode": null,
            "billToCountryCode": null
          },
          "tagIds": null,
          "userId": null,
          "externallyFulfilled": false,
          "externallyFulfilledBy": null
        }
      """
      val testOrder = camelifyFieldNames(parse(json)).extract[Order]

      testOrder.orderId should equal(94113592)
    }
  }
}
