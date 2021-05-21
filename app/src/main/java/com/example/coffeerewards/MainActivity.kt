package com.example.coffeerewards

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

// TODO: Make these values user-settable
// The amount of points needed to redeem a reward
const val redeemablePoints : Int = 10

// The amount of points awarded per dollar spent on product
const val pointsPerDollar : Int = 5

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    // Declare all the component variables
    lateinit var textPurchaseAmount: EditText
    lateinit var textRedeemablePoints: TextView
    lateinit var buttonAddPurchase: Button
    lateinit var buttonRedeemPoints: Button
    lateinit var spinnerSelectUser: Spinner
    lateinit var currentUser: User

    // For now, manually entering users
    // TODO: Load this data in from DB
    var listOfUsers: MutableList<User> = mutableListOf(
        User("Caroline Seiber"),
        User("Sinjin Seiber"),
        User("Alex Smith")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeSpinner(listOfUsers)
        initializeViews()
    }

    // When a new item is selected, update the current user and available redeemable points
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        currentUser = listOfUsers[position]
        textPurchaseAmount.setText("0")
        initializeUserPoints()
    }

    // This function is required, but I am automatically setting the first user to be selected, so it is not needed
    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    // Set up the user spinner, automatically choosing the first user in the list
    private fun initializeSpinner(listOfUsers: MutableList<User>) {
        spinnerSelectUser = findViewById(R.id.spinner_select_user)
        spinnerSelectUser.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val arrAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfUsers)

        // Set layout to use when the list of choices appear
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner
        spinnerSelectUser.setSelection(0)
        spinnerSelectUser.setAdapter(arrAdapter)
    }

    // Set all the components to their views
    private fun initializeViews() {
        textPurchaseAmount = findViewById(R.id.text_purchase_amount)
        textRedeemablePoints = findViewById(R.id.text_user_points)
        buttonAddPurchase = findViewById(R.id.button_add_purchase)
        buttonRedeemPoints = findViewById(R.id.button_redeem)

        // Set the on click listeners
        buttonAddPurchase.setOnClickListener(addPurchaseListener)
        buttonRedeemPoints.setOnClickListener(redeemPointsListener)

        initializeUserPoints()
        textPurchaseAmount.setText("0")
    }

    // Get the points from the current user and set the proper fields
    private fun initializeUserPoints() {
        // Set initial values for user points
        currentUser = spinnerSelectUser.selectedItem as User

        val userPoints = currentUser.getPoints()
        textRedeemablePoints.setText(userPoints.toString())

        checkRedeemablePoints(userPoints)
    }

    // When the Add Purchase button is clicked, get the value of the purchase amount
    // and add the appropriate points to the redeemable points
    private val addPurchaseListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button_add_purchase -> {
                val purchaseAmount: CharSequence = textPurchaseAmount.getText()
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
    private val redeemPointsListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button_redeem -> {
                if (buttonRedeemPoints.isClickable) {
                    updateRedeemablePoints(-1 * redeemablePoints)
                }
            }
        }
    }

    // Update the amount of redeemable points available
    private fun updateRedeemablePoints(points: Int) {
        // Only update the redeemable points if there is an actual change
        if (abs(points) > 0) {
            // Get the current amount of points already held by the user
            val currentPointsValue: CharSequence = textRedeemablePoints.getText()
            val currentPoints: Int = currentPointsValue.toString().toInt()

            // Update the point value
            val totalPoints = currentPoints + points
            currentUser.setPoints(totalPoints)
            textRedeemablePoints.setText(currentUser.getPoints().toString())

            checkRedeemablePoints(totalPoints)
        }
    }

    // Accepts the points a current user has, then updates the 'Redeem Rewards' button to enable or disable
    private fun checkRedeemablePoints(points: Int) {
        // If the new number of points is enough to activate rewards, activate the 'Redeem Rewards' button
        if (points >= redeemablePoints) {
            buttonRedeemPoints.enable()
        } else {
            buttonRedeemPoints.disable()
        }
    }

    // Disables the 'Redeem Rewards' button if not enough points are available
    private fun View.disable() {
        setAlpha(.5f);
        setClickable(false);
    }

    // Enables the 'Redeem Rewards' button if enough points are available
    private fun View.enable() {
        setAlpha(1f);
        setClickable(true);
    }
}