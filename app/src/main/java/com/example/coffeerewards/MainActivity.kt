package com.example.coffeerewards

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

// The amount of points needed to redeem a reward
const val redeemablePoints : Int = 10

// The amount of points awarded per dollar spent on product
const val pointsPerDollar : Int = 5

class MainActivity : AppCompatActivity() {
    // Declare all the component variables
    lateinit var textPurchaseAmount: EditText
    lateinit var textRedeemablePoints: TextView
    lateinit var buttonAddPurchase: Button
    lateinit var buttonRedeemPoints:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set all the components to their views
        textPurchaseAmount = findViewById(R.id.text_purchase_amount)
        textRedeemablePoints = findViewById(R.id.text_user_points)
        buttonAddPurchase = findViewById(R.id.button_add_purchase)
        buttonRedeemPoints = findViewById(R.id.button_redeem)

        // Set the on click listeners
        buttonAddPurchase.setOnClickListener(addPurchaseListener)
        buttonRedeemPoints.setOnClickListener(redeemPointsListener)

        // Set initial values
        textRedeemablePoints.setText("0")

        // Gray out and disable the button
        buttonRedeemPoints.setAlpha(.5f)
        buttonRedeemPoints.isClickable = false
    }

    // When the Add Purchase button is clicked, get the value of the purchase amount
    // and add the appropriate points to the redeemable points
    private val addPurchaseListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button_add_purchase -> {
                val purchaseAmount : CharSequence = textPurchaseAmount.getText()
                val purchasedValue: Int = purchaseAmount.toString().toInt()

                if (purchasedValue > 0) {
                    // add the points to the amount the user already has
                    updateRedeemablePoints(purchasedValue / pointsPerDollar)
                }
            }
        }
    }

    // When the 'Redeem Rewards' button is pressed, remove the proper amount of points from the
    // redeemable points
    private val redeemPointsListener =  View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button_redeem -> {
                if (buttonRedeemPoints.isClickable) {
                    updateRedeemablePoints(-1 * redeemablePoints)
                }
            }
        }
    }

    // Update the amount of redeemable points available
    private fun updateRedeemablePoints(points : Int) {
        // Only update the redeemable points if there is an actual change
        if (abs(points) > 0) {
            // Get the current amount of points already held by the user
            val currentPointsValue: CharSequence = textRedeemablePoints.getText()
            val currentPoints: Int = currentPointsValue.toString().toInt()

            // Update the point value
            val totalPoints = currentPoints + points
            textRedeemablePoints.setText(totalPoints.toString())

            // If the new number of points is enough to activate rewards, activate the 'Redeem Rewards' button
            if (totalPoints >= redeemablePoints) {
                buttonRedeemPoints.enable()
            } else {
                buttonRedeemPoints.disable()
            }
        }
    }

    // Disables the 'Redeem Rewards' button if not enough points are available
    fun View.disable() {
        setAlpha(.5f);
        setClickable(false);
    }

    // Enables the 'Redeem Rewards' button if enough points are available
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