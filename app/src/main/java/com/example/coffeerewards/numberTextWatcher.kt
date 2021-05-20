import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.ParseException

//This class is for entering currency amount while keeping the right format as the user enters values
class NumberTextWatcher(editText: EditText) : TextWatcher {
    private val dfnd: DecimalFormat = DecimalFormat("#,##0.00")
    private val et: EditText = editText
    override fun afterTextChanged(s: Editable) {
        et.removeTextChangedListener(this)
        //After all the text editing, if there is a string to validate - format it
        if (s != null && !s.toString().isEmpty()) {
            try {
                //Take the input string and remove all formatting characters
                val v: String = s.toString()
                    .replace(java.lang.String.valueOf(dfnd.getDecimalFormatSymbols().getGroupingSeparator()), "")
                    .replace("$", "")
                    .replace(java.lang.String.valueOf(dfnd.getDecimalFormatSymbols().getDecimalSeparator()), "")
                //Pass the altered string to a number
                var n: Number = dfnd.parse(v)
                //Get the decimal point correct again
                n = n.toDouble() / 100.0
                //Reformat the text with currency symbols, grouping places etc.
                et.setText(dfnd.format(n))
                //Add the Dollar symbol ($)
                et.setText("$" + et.text.toString())
                //Move the editing cursor back to the right place
                et.setSelection(et.text.length)
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        } else  //if the input field is empty
        {
            et.setText("$0.00")
        }
        et.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

}