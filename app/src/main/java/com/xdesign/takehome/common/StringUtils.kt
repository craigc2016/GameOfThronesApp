package com.xdesign.takehome.common

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

fun String.setTextColour(
    @ColorInt textColour: Int
) : SpannableString {
    val space = "\t"
    val spannableString = SpannableString(space + this)
    spannableString.setSpan(ForegroundColorSpan(textColour), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}