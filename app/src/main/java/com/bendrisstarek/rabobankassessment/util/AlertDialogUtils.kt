package com.bendrisstarek.rabobankassessment.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes

/**
 * this class represents alert dialog utils
 */
object AlertDialogUtils {
    /**
     * this function shows a dialog
     * @param context
     * @param message
     */
    fun show(context: Context, message: String?) {
        //showString(context, R.string.empty, message, null)
    }

    /**
     * this function shows a string in a dialog
     * @param context
     * @param title
     * @param message
     * @param listener
     */
    fun showString(context: Context, @StringRes title: Int, message: String?, listener: DialogInterface.OnClickListener?) {
        val builder = AlertDialog.Builder(context)
        //set title alert
        builder.setTitle(title)
        // set message alert
        builder.setMessage(message)
        if (listener != null) {
            //builder.setPositiveButton(R.string.ok, listener)
        } else {
            //builder.setPositiveButton(R.string.ok) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
        }
        // show alert
        builder.create().show()
    }

    /**
     * this fuction shows a dialog
     * @param context
     * @param message
     * @param listener
     * @param cancelable
     */
    fun show(context: Context, @StringRes message: Int, listener: DialogInterface.OnClickListener?, cancelable: Boolean) {
        val builder = AlertDialog.Builder(context)
        // set message alert
        builder.setMessage(message)
        // Sets whether this dialog is canceled when touched outside the window's bounds. or not
        builder.setCancelable(cancelable)
        if (listener != null) {
            //builder.setPositiveButton(R.string.ok, listener)
        } else {
            //builder.setNegativeButton(R.string.ok) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
        }
        // show alert
        builder.create().show()
    }

    /**
     * this function returns a dialog
     * @param context
     * @param message
     * @param positive
     * @param negative
     */
    fun show(context: Context, @StringRes message: Int, positive: DialogInterface.OnClickListener?,
             negative: DialogInterface.OnClickListener?) {
        val builder = AlertDialog.Builder(context)
        // set message alert
        builder.setMessage(message)
        // Sets whether this dialog is canceled when touched outside the window's bounds. or not
        builder.setCancelable(false)
        if (positive != null && negative != null) {
            //builder.setPositiveButton(R.string.dialog_action_yes, positive)
            //builder.setNegativeButton(R.string.dialog_action_no, negative)
        }
        // show alert
        builder.create().show()
    }
}