package anlt.dev.momoauto

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import net.glxn.qrgen.android.QRCode

class MainActivity : AppCompatActivity() {

    /**
     * Money amount
     */
    private var mEditAmount: EditText? = null

    /**
     * Phone number
     */
    private var mEditPhoneNumber: EditText? = null

    /**
     * Button add
     */
    private var mButtonCreate: Button? = null

    /**
     * QRCode
     */
    private var mImagePreview: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditAmount = findViewById<EditText>(R.id.editAmount)
        mEditPhoneNumber = findViewById<EditText>(R.id.editPhoneNumber)
        mImagePreview = findViewById<ImageView>(R.id.imagePreview)
        mButtonCreate = findViewById<Button>(R.id.buttonCreate)

        // When button create on clicked
        (mButtonCreate as Button).setOnClickListener {
            // get String
            var amt = (mEditAmount as EditText).text.toString()
            var phoneNumber = (mEditPhoneNumber as EditText).text.toString()
            // If amount is empty toast message
            if (!validateInput(amt, phoneNumber, this)) {
                hideKeyboard()
                return@setOnClickListener
            }

            if (amt.isNotEmpty() && phoneNumber.isEmpty()) {
                // Get current phone number
                phoneNumber = getCurPhoneNumber()
            }
            if (phoneNumber.isEmpty())
            {
                Toast.makeText(this, "Can not get current phone number please insert a valid one", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // split +84
            phoneNumber = formatPhoneNumber(phoneNumber)
            // Full URL string
            val url = "${Constants.MOMO_URL}/$phoneNumber/$amt"
            (mEditPhoneNumber as EditText).setText(phoneNumber);
            // create a QRCode based on url string
            val bitmap = QRCode.from(url).withSize(640, 640).bitmap()
            (mImagePreview as ImageView).setImageBitmap(bitmap)


            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun getCurPhoneNumber(): String {
        var phoneNumber = "";
        val telephonyManager
        = getSystemService (Context.TELEPHONY_SERVICE) as TelephonyManager
        // Check permission
        if (!checkPermission(this)) {
            phoneNumber
        } else {
            phoneNumber = telephonyManager.line1Number
        }
        return phoneNumber
    }

}