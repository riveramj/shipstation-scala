package com.mypetdefense.shipstation

import net.liftweb.json._
import net.liftweb.util.Helpers._

object ShipStationHelpers {
  def camelifyFieldNames(input: JValue): JValue = {
    input.map {
      case JField(fieldName, value) =>
        JField(camelifyMethod(fieldName), value)

      case JArray(contents) =>
        JArray(contents.map(camelifyFieldNames))

      case obj @ JObject(fields) =>
        JObject(fields.map(camelifyFieldNames).asInstanceOf[List[JField]])

      case x =>
        x
    } 
  }

  def fixMultiLevelDecomposing(badJson: String) = {
    badJson.replaceAll("}\"","}").replaceAll("\"\\{","{").replaceAll("\\\\","")
  }
}
