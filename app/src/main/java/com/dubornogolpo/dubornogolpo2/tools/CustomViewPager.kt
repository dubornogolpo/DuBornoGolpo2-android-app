package com.dubornogolpo.dubornogolpo2.tools

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class CustomViewPagerEndSwipe(context: Context, attrs: AttributeSet?) :
    ViewPager(context, attrs) {
    private var mStartDragX = 0f
    private var mStartDragY = 0f
    private var callOnce: Boolean = false
    private var mListener: OnSwipeOutListener? = null
    fun setOnSwipeOutListener(listener: OnSwipeOutListener?) {
        mListener = listener
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x
        val y = ev.y
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartDragX = x
                mStartDragY = y
                callOnce = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (mStartDragX + 50f < x && mStartDragY < y  && y < mStartDragY + 100 && currentItem == 0 && !callOnce) {
                    Log.d("touches", "x = $x, mStartDragX = $mStartDragX --> if block")
                    mListener!!.onSwipeOutAtStart(context)
                    callOnce = true
                } else if (mStartDragX > x - 50f && mStartDragY < y  && y < mStartDragY + 100 && currentItem == adapter!!.count - 1 && !callOnce) {
                    Log.d("touches", "x = $x, mStartDragX = $mStartDragX --> else if block")
                    mListener!!.onSwipeOutAtEnd(context)
                    callOnce = true
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    interface OnSwipeOutListener {
        fun onSwipeOutAtStart(context: Context)
        fun onSwipeOutAtEnd(context: Context)
    }
}