package com.example.coffeerewards

import 	java.util.UUID

//This class will be used when multiple users are supported
class User (
   _name : String
) {
   private val id : UUID = UUID.randomUUID()
   private var points : Int = 0
   private val name : String = _name

   override fun toString(): String {
      return getName()
   }

   fun getID(): UUID {
      return id
   }

   fun getPoints(): Int {
      return points
   }

   fun setPoints(_points : Int) {
      points = _points
   }

   private fun getName(): String {
      return name
   }
}