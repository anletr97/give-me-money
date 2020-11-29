package anlt.dev.momoauto

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat

/**
 * Check permission of main activity
 * @author anlt
 * @param activity
 */
fun checkPermission(activity: MainActivity): Boolean {
    var phoneStateCheck
            = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
    if (phoneStateCheck != PackageManager.PERMISSION_GRANTED) {
        return false
    }
    return true;
}

/**
 * Check required on click
 */
fun validateInput(amt: String, phoneNumber: String, activity: MainActivity): Boolean {
    // Check amount
    // If amount is empty -> toast message and return
    if (amt.isEmpty()) {
            // Toast message
            Toast.makeText(activity, Messages.ERR_MOMO_AMOUNT_IS_EMPTY, Toast.LENGTH_LONG).show()
            return false;
    }
    // Validate phone number
    if (isValidPhoneNumber(phoneNumber, activity)) {
     return true
    }

    return true
}

/**
 * Is valid phone number
 * @param phoneNumber
 * @param activity
 */
fun isValidPhoneNumber(phoneNumber: String, activity: MainActivity): Boolean {
    if (phoneNumber.isNotEmpty()
            && (phoneNumber.length < 10
                || phoneNumber.length > 11)) {
        Toast.makeText(activity, Messages.ERR_MOMO_PHONE_NUMBER_IS_INVALID, Toast.LENGTH_LONG).show()
        return false;
    }
    return true;
}

fun isValidAmt() {
    // TODO checkAmount negative
}

// TODO Check if amount is negative and empty
private fun checkAmount(str: String) {

}
