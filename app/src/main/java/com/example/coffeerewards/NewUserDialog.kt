import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.coffeerewards.R

class NewUserDialog: DialogFragment() {
    lateinit var textUserName : EditText
    private lateinit var buttonSubmitAddUser: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        val view = inflater.inflate(R.layout.add_user_dialog, container, false)
        textUserName = view.findViewById(R.id.text_add_user_name)
        buttonSubmitAddUser = view.findViewById(R.id.btn_submit_add_user)
        buttonSubmitAddUser.setOnClickListener(submitAddUserListener)

        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    // When the 'Add' button is pressed in the add user dialog, dismiss the dialog and add the user to the list
    private val submitAddUserListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.btn_submit_add_user -> {
                // Close the dialog
                this.dismiss()

                // Get the value in the name field and add a new User to the list
                val userName: CharSequence = textUserName.text
                val userNameString : String = userName.toString()

                // In the future, adding the using will be much more complicated, so I am
                // abstracting it to a function
//                addNewUser(userNameString)
            }
        }
    }

    fun getText(): String {
        return textUserName.text.toString()
    }

    fun setText() {

    }
}