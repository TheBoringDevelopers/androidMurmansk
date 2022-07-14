package com.theboringdevelopers.smartmurmansk.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.theboringdevelopers.smartmurmansk.R
import com.theboringdevelopers.smartmurmansk.activity.MainActivity
import com.theboringdevelopers.smartmurmansk.data.model.ErrorInfo
import okhttp3.ResponseBody

fun showBottomNavBar(activity: Activity?) = changeVisibility(activity, shouldShow = true)
fun hideBottomNavBar(activity: Activity?) = changeVisibility(activity, shouldShow = false)

fun changeVisibility(activity: Activity?, shouldShow: Boolean) {
    val bottomNavigationView = (activity as? MainActivity)?.binding?.navView
    bottomNavigationView?.visibility = if (shouldShow) View.VISIBLE else View.GONE
}

fun ResponseBody.toErrorInfo(): ErrorInfo {
    val gson = Gson()
    return gson.fromJson(this.string(), ErrorInfo::class.java)
}

/**
 * Скрыть клавиатуру
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Скрыть клавиатуру
 */
fun Activity.hideKeyboard() {
    var view: View? = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Взять фокус и показать клавиатуру
 */
fun View.requestFocusShowKeyboard() {
    this.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.showErrorSnackBar(message: String?) {
    if (this.currentFocus != null) {
        // убрать клавиатуру
        val inputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
    val snackBar = Snackbar.make(
        this.window.decorView.findViewById(android.R.id.content),
        message ?: this.getString(R.string.unknown_error),
        Snackbar.LENGTH_INDEFINITE
    )
    snackBar.setActionTextColor(this.resources.getColor(R.color.blue))
    snackBar.setAction("OK") { }
    val textView =
        snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(this.resources.getColor(R.color.white))
    textView.maxLines = 10
    snackBar.show()
}

/**
 * Информационное сообщение
 */
fun Activity.showInfoSnackBar(message: String) {
    if (this.currentFocus != null) {
        // убрать клавиатуру
        val inputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
    val snackBar = Snackbar.make(
        this.window.decorView.findViewById(android.R.id.content), message,
        Snackbar.LENGTH_LONG
    )
    val textView =
        snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(this.resources.getColor(R.color.white))
    textView.maxLines = 10
    snackBar.show()
}