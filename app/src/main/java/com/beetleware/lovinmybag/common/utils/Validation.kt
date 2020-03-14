package com.beetleware.lovinmybag.common.utils

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import com.beetleware.lovinmybag.R
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import java.util.regex.Pattern

object Validation {

    /**
     * Use this method to validate the email.
     *
     * @param email the String that you want to validate.
     * @return true if its correct email, false otherwise.
     */
    fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email.trim { it <= ' ' }).matches()


    /**
     * Use this method to validate that confirmPassword matches password.
     *
     * @param pass the password string.
     * @param confirmPass the confirm password string.
     * @return true if confirm password matches the password, false otherwise.
     */
    fun isPasswordsMatches(pass: String, confirmPass: String) = pass == confirmPass


    /**
     * Use this method to validate that birthDate is in the allowed range (16 to 80 years old)
     *
     * @param year birthDate year.
     * @param month birthDate month.
     * @param day birthDate day.
     * @return true if the birthDate in the allowed range, false otherwise.
     */
    fun isBirthDateValid(year: String, month: String, day: String): Boolean {

        return when {
            Integer.valueOf(year) < Calendar.getInstance().get(Calendar.YEAR) - 80 -> false
            Integer.valueOf(year) < Calendar.getInstance().get(Calendar.YEAR) - 16 -> true
            Integer.valueOf(year) > Calendar.getInstance().get(Calendar.YEAR) - 16 -> false

            Integer.valueOf(month) < Calendar.getInstance().get(Calendar.MONTH) -> true
            Integer.valueOf(month) > Calendar.getInstance().get(Calendar.MONTH) -> false

            else -> Integer.valueOf(day) < Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        }
    }

    /**
     * Use this method to validate that activation code isn't less than 5 digits.
     *
     * @param code the activation code.
     * @return true if the code length is 5, false otherwise.
     */
    fun isActivationCodeValid(code: String) = code.length == 5

    /**
     * use this method to validate that the number in correct range
     */
    fun isRangeValid(str: String, rangeStart: Int, rangeEnd: Int) = Integer.valueOf(str) in rangeStart..rangeEnd

    /**
     * use this method to validate that the social account text is empty or a valid url that contain
     * the specific word passed in type
     *
     * @param url the text to be validated
     * @param type if you want to make this url for specific website pass it here (ex: facebook)
     * @return {@code true} if the text is empty or valid url that contains the type,
     * {@code false} otherwise
     */
    fun isUrlValid(url: String, type: String = ""): Boolean {

        if (url.isEmpty()) return true
        if (!url.toLowerCase().contains(type.toLowerCase())) return false
        if (Patterns.WEB_URL.matcher(url).matches()) return true

        return false
    }


    /**
     * use this method to validate the phone number
     * @param phone String the phone number to be validated
     * @return Boolean tru if phone is valid, false otherwise
     */
    fun isValidPhone(phone: String) = Patterns.PHONE.matcher(phone.trim { it <= ' ' }).matches()

    /**
     * use this number to validate the egyptian phone number that starts with(+20,0020,020,01)
     * @param phone String the phone number to be validated
     * @return Boolean tru if phone is valid, false otherwise
     */
    fun isValidPhoneEgypt(phone: String): Boolean {

        var isValid = false

        val matcher = Pattern.compile("(020|\\+20|0020)([0-9]{10})").matcher(phone)
        val matcher1 = Pattern.compile("^(01)([0-9]{9})$").matcher(phone)

        if (matcher.matches() || matcher1.matches())
            isValid = true

        return isValid
    }


    /**
     * use this method to validate that password is 8 length at least and contain numbers
     * and arabic or english letters
     * @param password String the password to be validated
     * @return Boolean true if password is valid
     */
    fun isValidComplexPassword(password: String) =
        Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z\\u0621-\\u064A]).{8,}$").matcher(password).matches()

    /**
     * use this method to validate that name contains only arabic letters, english letters or space
     * @param name String the name to be validate
     * @return Boolean tru if name is valid
     */
    fun isValidName(name: String) : Boolean {
        val parts = name.split(" ")

        return  parts.size >= 3
    }

    /**
     * use this method to validate that promo code contains only numbers and letters and its length is 6
     * @param promoCode String the promo code to be validate
     * @return Boolean tru if promoCode is valid
     */
    fun isValidPromoCode(promoCode: String) = promoCode.matches("^[a-zA-Z0-9]{6}".toRegex())

    fun validateEmptyField(context: Context, editText: EditText): Boolean {
        val value = editText.text.toString().trim { it <= ' ' }
        if (value.isEmpty()) {
            var hint = ""
            if (editText.parent is TextInputLayout) {
                val parent = editText.parent as TextInputLayout
                hint = parent.hint!!.toString()
            } else if (editText.parent.parent is TextInputLayout) {
                val parent = editText.parent.parent as TextInputLayout
                hint = parent.hint!!.toString()
            } else {
                hint = editText.hint.toString()
            }
            editText.requestFocus()
            editText.error = context.getString(R.string.empty_error_msg) + " " + hint
            return false
        } else {
            editText.error = null
            return true
        }
    }
}