package com.bendrisstarek.rabobankassessment.util

/**
 * this class represents an exception's utils
 */
object ExceptionUtils {
    /**
     * this function foramts an exception
     * @param exception
     * @return
     */
    @JvmStatic
    fun format(exception: Throwable?): String {
        var exceptionStr = ""
        val message = getThrowableMessage(exception)
        val cause = getThrowableCause(exception)
        exceptionStr += "MessageDb[$message]\n"
        exceptionStr += "Cause[$cause]"
        return exceptionStr
    }

    /**
     * this function returns the exception's cause
     * @param throwable
     * @return
     */
    @JvmStatic
    fun getThrowableCause(throwable: Throwable?): String {
        var causeStr = ""
        if (throwable != null) {
            var cause = throwable.cause
            while (cause != null) {
                causeStr = cause.toString() + ""
                cause = cause.cause
            }
        }
        return causeStr
    }

    /**
     * this function returns the exception's message
     * @param throwable
     * @return
     */
    @JvmStatic
    fun getThrowableMessage(throwable: Throwable?): String? {
        var messageStr: String? = ""
        if (throwable != null) {
            messageStr = throwable.message
        }
        return messageStr
    }
}