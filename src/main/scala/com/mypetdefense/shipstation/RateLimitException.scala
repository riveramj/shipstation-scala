package com.mypetdefense.shipstation


class RateLimitException(message: String = null, cause: Throwable = null) extends
  RuntimeException(RateLimitException.defaultMessage(message, cause), cause)

object RateLimitException {
  def defaultMessage(message: String, cause: Throwable) =
    if (message != null) message
    else if (cause != null) cause.toString()
    else null
}
