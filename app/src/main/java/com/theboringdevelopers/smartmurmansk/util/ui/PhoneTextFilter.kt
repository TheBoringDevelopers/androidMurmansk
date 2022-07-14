package com.theboringdevelopers.smartmurmansk.util.ui

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

/**
 * Следит за вводом символов в поле номера телефона
 */
class PhoneTextFilter : InputFilter {

    /**
     * Регулярка
     */
    private val pattern = Pattern.compile("\\+?[0-9 -]*")

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        return if (pattern.matcher(source).matches()) {
            null
        } else {
            ""
        }
    }
}