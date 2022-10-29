package com.xdesign.takehome.common

import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

fun String.setTextColour(
    @ColorInt textColour: Int
) : SpannableString {
    val spannableString = SpannableString(this)
    spannableString.setSpan(ForegroundColorSpan(textColour), 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}

fun String.createSpannableString(spannableString: SpannableString, spaceBetween: Boolean = true) : SpannableStringBuilder {
    val spaceBetweenWords = if (spaceBetween) "\t" else ""
    val spannableStringBuilder = SpannableStringBuilder()
    spannableStringBuilder.append(this + spaceBetweenWords)
    spannableStringBuilder.append(spannableString.ifEmpty { "NA" })
    return spannableStringBuilder
}