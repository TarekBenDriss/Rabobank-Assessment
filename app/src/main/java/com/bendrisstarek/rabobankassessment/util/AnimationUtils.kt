package com.bendrisstarek.rabobankassessment.util

import android.view.animation.AlphaAnimation
import android.widget.ImageView

/**
 * This class is used to declare some functions for the animation of views
 */
object AnimationUtils {
    /**
     * This function will make an animation using the alpha.
     *
     * @param img           the imageView which will be animated
     * @param start         the start value of alpha
     * @param end           the final value of alpha
     * @param duration      the duration of the animation
     * @param startDuration start after some time
     */
    fun setAlphaAnimation(img: ImageView, start: Float, end: Float, duration: Int, startDuration: Int) {
        val animation = AlphaAnimation(start, end)
        animation.duration = duration.toLong()
        animation.startOffset = startDuration.toLong()
        animation.fillAfter = true
        img.startAnimation(animation)
    }
}