package com.example.mystore.dialog

import android.app.AlertDialog
import android.content.Context

object DialogHelper {

    fun showErrorDialog(context: Context, title: String, message: String, listener: (Int) -> Unit) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Retry") { dialog, _ ->
                listener.invoke(0)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                listener.invoke(1)
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setCancelable(false)
            .show()
    }
}