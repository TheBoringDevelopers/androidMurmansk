package com.theboringdevelopers.smartmurmansk.util.ui

import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.Selection
import android.widget.EditText

/**
 * Вотчер запрещающий стирать +7 и вставлять что-то начинающееся не с +7
 */
class CustomPhoneWatcher(val editText: EditText?) : PhoneNumberFormattingTextWatcher("RU") {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        super.onTextChanged(s, start, before, count)
    }

    override fun afterTextChanged(s: Editable) {
        if (!s.toString().startsWith("+7")) {
            editText?.setText("+7")
            Selection.setSelection(editText?.text, editText?.text?.length!!)
        }
        super.afterTextChanged(s)
    }
}