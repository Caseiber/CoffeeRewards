package com.example.coffeerewards

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val redeemablePoints : Int = 10

const val pointsPerDollar : Int = 5

class MainActivity : AppCompatActivity() {

    // Declare all the component variables
    lateinit var editPurchase: EditText
    lateinit var redeemable: TextView
    lateinit var buttonAddPurchase: Button
    lateinit var buttonRedeemPoints:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set all the components to their variables
        editPurchase  = findViewById(R.id.purchase_amount)
        redeemable = findViewById(R.id.available_points)
        buttonAddPurchase = findViewById(R.id.add_purchase_button)
        buttonRedeemPoints = findViewById(R.id.redeem_button)

        // Set the on click listeners
        buttonAddPurchase.setOnClickListener(addPurchaseListener)
        buttonRedeemPoints.setOnClickListener(redeemPointsListener)

        // Set initial values
        redeemable.setText("0")

        // Grey out and disable the button
        buttonRedeemPoints.setAlpha(.5f)
        buttonRedeemPoints.isClickable = false
    }

    // When the add purchase button is clicked, get the value of the purchase amount
    // and add the appropriate points to the redeemable points
    private val addPurchaseListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.add_purchase_button -> {
                val purchaseAmount : CharSequence = editPurchase.getText()
                val purchasedValue: Int = purchaseAmount.toString().toInt()

                if (purchasedValue > 0) {
                    // Get the current amount of points already held by the user
                    updateRedeemablePoints(purchasedValue / pointsPerDollar)
                }
            }
        }
    }

    // When the 'Redeem Points' button is pressed, remove the proper amount of points from the
    // redeemable points
    private val redeemPointsListener =  View.OnClickListener { view ->
        when (view.getId()) {
            R.id.redeem_button -> {
                if (buttonRedeemPoints.isClickable) {
                    updateRedeemablePoints(-1 * redeemablePoints)
                }
            }
        }
    }

    private fun updateRedeemablePoints(points : Int) {
        // Get the current amount of points already held by the user
        val currentPointsValue : CharSequence = redeemable.getText()
        val currentPoints: Int = currentPointsValue.toString().toInt()

        val totalPoints = currentPoints + points
        redeemable.setText(totalPoints.toString())

        // if the new number of points is enough to activate rewards, activate the 'redeem' button

        if (totalPoints >= redeemablePoints) {
            buttonRedeemPoints.enable()
        } else {
            buttonRedeemPoints.disable()
        }
    }

    fun View.disable() {
        setAlpha(.5f);
        setClickable(false);
    }

    fun View.enable() {
        setAlpha(1f);
        setClickable(true);
    }


//    private val spinner: Spinner = findViewById(R.id.selectUserSpinner)
//    // Create an ArrayAdapter using the string array and a default spinner layout
//    ArrayAdapter.createFromResource(
//        this,
//        R.array.planets_array,
//        android.R.layout.simple_spinner_item
//    ).also { adapter ->
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        // Apply the adapter to the spinner
//        spinner.adapter = adapter
//    }
}