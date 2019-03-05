# ShipStation

*An easy to use API for Scala.*

This API is designed to make it clean and easy to integrate ShipStation into your webapp.
Not all features are built out yet, but I use it in production with my app so it is stable 
and production ready.

Some things about the library:

* It's built on top of Dispatch, so all methods of the library that talk to Stripe's servers will
  return a Scala `Future`.
* We're built on top of lift-common and lift-json. This is particularly relevant because the subtype
  of our `Futures` is a `Box`, which you can think of as `Option` on steroids. In addition to
  `Full` and `Empty` (which equate to `Some` and `None`), `Box`es also have `Failure` which can
  carry information about a failure condition. It's pretty great.
* The API is subject to change. Plan accordingly.

## Almost Released

The library isn't hosted yet, but it is ready to go. If you want to run it before release, message me and I'll be glad to help.
