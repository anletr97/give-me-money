package anlt.dev.momoauto

/**
 * Format phone number
 */
fun formatPhoneNumber(str: String): String {
    var retStr = "";
    // If string is not empty
    if (str.isNotEmpty() && str.contains("+84")) {
        // Case: Phone number is from vietnam
        var arrStr = str.split("+84")
        retStr = arrStr[1].toString().padStart(10, '0')
    }
    return retStr;
}