package com.example.coffeerewards
//
//import android.os.Bundle
//import android.view.View
//import android.widget.*
//
//class UserSpinner : AdapterView.OnItemSelectedListener {
//    var list_of_items = arrayOf("Item 1", "Item 2", "Item 3")
//
//    fun onCreate(savedInstanceState: Bundle?) {
//        spinner!!.setOnItemSelectedListener(this)
//
//        // Create an ArrayAdapter using a simple spinner layout and languages array
//        val aa = ArrayAdapter(this, R.id.spinner_select_user, list_of_items)
//        // Set layout to use when the list of choices appear
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        // Set Adapter to Spinner
//        spinner!!.setAdapter(aa)
//    }
//
//    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
//        // use position to know the selected item
//    }
//
//    override fun onNothingSelected(arg0: AdapterView<*>) {
//
//    }
//}