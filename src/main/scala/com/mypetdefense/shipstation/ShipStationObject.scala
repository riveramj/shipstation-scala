package com.mypetdefense.shipstation

import net.liftweb.common._
import net.liftweb.json._
import net.liftweb.util.Helpers._

import scala.reflect.macros.Context
import scala.reflect.runtime.universe._
import scala.collection.mutable.ListBuffer

/**
 * The common ancestor of any class that represents a ShipStation
 * data structure.
**/
abstract class ShipStationObject {
  /**
   * Return the raw JSON AST representation of the ShipStation
   * data structure this class represents. Use this only if the
   * class representing your data structure doesn't already
   * provide a method for accessing the field you need.
  **/
  def raw: Option[JValue]

  implicit val formats = DefaultFormats

  /**
   * Transform the underlyingData of this ShipStationObject then attempt to extract an
   * instance of the class T from it. This is the general implementation of extracting
   * values from the JSON API response from ShipStation.
   *
   * You should only use this if you're accessing a piece of data from the raw response
   * that we don't support. If you do find need to use this, we'd love it if you opened
   * a pull request!
   *
   * @param transformer The function that transforms the original data into the structure containing the data we want.
   * @return A Full[T] if the extraction was successful, a Failure otherwise.
  **/
  def valueFor[T](transformer: (JValue)=>JValue)(implicit mf: Manifest[T]) =
    tryo(transformer(raw.getOrElse(JNothing)).extract[T](formats, mf)).filterNot(_ == null)

  /**
   * Create a copy of the StripeObject with the raw JValue representation attached.
   *
   * @param raw The raw JValue representation to attach.
  **/
  def withRaw(raw: JValue): ShipStationObject
}
