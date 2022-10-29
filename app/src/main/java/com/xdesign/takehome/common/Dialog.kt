package com.xdesign.takehome.common

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.xdesign.takehome.R

object Dialog {

    fun show(context : Context, title: String, message : String, listener :((DialogInterface) -> Unit)) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)

        builder.setTitle(title).setMessage(message)
        builder.apply {
            setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
                listener(dialog)
            }
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}