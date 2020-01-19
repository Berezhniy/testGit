package ru.startandroid.testgit.utils.extention

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View?.hideKeyboardWithClearFocus() {
    this?.clearFocus()
    val imm=this?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(this?.windowToken, 0)
}

fun View.updateVisibility(isVisible: Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}