package com.example.coffeerewards

import 	java.util.UUID

//This class will be used when multiple users are supported
class User (
   _name : String
) {
   val id : UUID = UUID.randomUUID()
   var points : Int = 0
   val name : String = _name
}