package com.dubornogolpo.dubornogolpo2.tools

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.dubornogolpo.dubornogolpo2.StoryView
import com.dubornogolpo.dubornogolpo2.models.Story

class CustomSwipeOutListener(val story: Story, val context: Context):
    CustomViewPagerEndSwipe.OnSwipeOutListener {
    override fun onSwipeOutAtStart(context: Context) {
        AlertDialog.Builder(context)
            .setMessage("আগের গল্পে যেতে চান?")
            .setPositiveButton("হ্যাঁ")
            { dialog, which ->
                val intent = Intent(context, StoryView::class.java)
                intent.putExtra("storyName", story.prev)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(context, intent, null)
            }
            .setNegativeButton("না")
            {
                dialog, which ->
            }
            .create()
            .show()
    }

    override fun onSwipeOutAtEnd(context: Context) {
        AlertDialog.Builder(context)
            .setMessage("পরের গল্পে যেতে চান?")
            .setPositiveButton("হ্যাঁ")
            {
                dialog, which ->
                val intent = Intent(context, StoryView::class.java)
                intent.putExtra("storyName", story.prev)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(context, intent, null)
            }
            .setNegativeButton("না")
            {
                dialog, which ->
            }
            .create()
            .show()
    }
}