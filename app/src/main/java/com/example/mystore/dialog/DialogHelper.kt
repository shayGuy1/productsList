package com.example.mystore.dialog

import android.app.AlertDialog
import android.content.Context

object DialogHelper {
    enum class ResultCode {
        Retry, Cancel
    }
    fun showErrorDialog(context: Context, title: String, message: String, listener: (ResultCode) -> Unit) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Retry") { dialog, _ ->
                listener.invoke(ResultCode.Retry)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                listener.invoke(ResultCode.Cancel)
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setCancelable(false)
            .show()
    }
}