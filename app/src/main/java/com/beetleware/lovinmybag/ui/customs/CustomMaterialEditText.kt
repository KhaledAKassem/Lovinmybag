package com.beetleware.lovinmybag.ui.customs

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.common.utils.Validation
import com.rengwuxian.materialedittext.MaterialEditText


class CustomMaterialEditText : MaterialEditText {

    var isRequired = false
    var requiredErrorMsg: String? = null
    var validationType = 0 // 0 is none
    var validationErrorMsg: String? = null
    var mustMatched = -1 //for confirm password


    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style) {
        init(attrs)
    }

    init {
        super.setAccentTypeface(ResourcesCompat.getFont(context, R.font.cairo_semi_bold))
        super.setTypeface(ResourcesCompat.getFont(context, R.font.cairo_semi_bold))
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomMaterialEditText)

        isRequired =
            typedArray.getBoolean(R.styleable.CustomMaterialEditText_required, false)
        requiredErrorMsg =
            typedArray.getString(R.styleable.CustomMaterialEditText_required_error_msg)

        validationType = typedArray.getInt(R.styleable.CustomMaterialEditText_validation_type, 0)
        validationErrorMsg =
            typedArray.getString(R.styleable.CustomMaterialEditText_validation_error_msg)
        mustMatched = typedArray.getResourceId(R.styleable.CustomMaterialEditText_must_match, -1)

        typedArray.recycle()
    }

    fun validateContent(): Boolean {

        if (isRequired && this.text.isNullOrEmpty()) {
            if (requiredErrorMsg == null)
                this.error =
                    "${context.resources.getString(R.string.empty_error_msg)} ${hint ?: "".replace(
                        "*",
                        ""
                    ).toLowerCase().trim()}"
            else
                this.error = requiredErrorMsg

            return false
        } else if (!isRequired && this.text.isNullOrEmpty())
            return true

        when (validationType) {
            1 -> { //email
                if (!Validation.isEmailValid(text?.toString()!!)) {
                    error =
                        validationErrorMsg ?: context.getString(R.string.email_validation_error_msg)
                    return false
                }
            }
            2 -> { //phone
                if (!Validation.isValidPhone(text?.toString()!!)) {
                    error =
                        validationErrorMsg ?: context.getString(R.string.phone_validation_error_msg)
                    return false
                }
            }
            3 -> { // password
                if (text?.toString()?.length!! < 8) {
                    error =
                        validationErrorMsg ?: context.getString(R.string.pass_validation_error_msg)
                    return false
                }
            }
            4 -> { //confirm password
                if (mustMatched == -1) return true

                if (!Validation.isPasswordsMatches(
                        text?.toString()!!,
                        rootView.findViewById<CustomMaterialEditText>(mustMatched).text?.toString()!!
                    )
                ) {
                    error = validationErrorMsg
                        ?: context.getString(R.string.confirm_pass_validation_error_msg)
                    return false
                }
            }

            5 -> { //phone or email
                if (!Validation.isValidPhone(text?.toString()!!) && !Validation.isEmailValid(text?.toString()!!)) {
                    error = validationErrorMsg
                        ?: context.getString(R.string.phone_or_email_validation_error_msg)
                    return false
                }
            }

            6 -> { // egyptian phone
                if (!Validation.isValidPhoneEgypt(text?.toString()!!)) {
                    error =
                        validationErrorMsg ?: context.getString(R.string.phone_validation_error_msg)
                    return false
                }
            }

            7 -> { // egyptian phone or email
                if (!Validation.isValidPhoneEgypt(text?.toString()!!) && !Validation.isEmailValid(
                        text?.toString()!!
                    )
                ) {
                    error = validationErrorMsg
                        ?: context.getString(R.string.phone_or_email_validation_error_msg)
                    return false
                }
            }

            8 -> { // name
                if (!Validation.isValidName(text?.toString()!!) && !Validation.isEmailValid(text?.toString()!!)) {
                    error =
                        validationErrorMsg ?: context.getString(R.string.name_validation_error_msg)
                    return false
                }
            }
        }
        return true
    }

}