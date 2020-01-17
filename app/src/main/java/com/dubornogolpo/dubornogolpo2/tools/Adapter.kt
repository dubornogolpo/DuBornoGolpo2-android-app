package com.dubornogolpo.dubornogolpo2.tools

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.dubornogolpo.dubornogolpo2.R

class Adapter(val context: Context, val list: List<String>?) : PagerAdapter() {

    var inflater = LayoutInflater.from(context)!!

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }

    override fun getCount(): Int {
        return list!!.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var y_old = 0.0f
        var y_new: Float
        val view = inflater.inflate(R.layout.story_image_cardview, container, false)

        fun hideAuthorCard(view: View) {
            if ( view.alpha != 0f )
            {
                view.animate().translationY(-1f*view.height.toFloat()).alpha(0f).duration = 500
            }
        }
        fun showAuthorCard(view: View)
        {
            if ( view.alpha == 0f)
            {
                view.animate().translationY(0f).alpha(1f).duration = 500
            }
        }

        Picasso.get().load(list!![position]).fit().into(view.findViewById(R.id.storyImage) as ImageView)

        view.findViewById<ImageView>(R.id.storyImage).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    y_old = event.rawY
                    true
                }
                MotionEvent.ACTION_UP -> {
                    y_new = event.rawY
                    val delta = y_new - y_old
                    if ( delta < -200 )
                    {
                        hideAuthorCard(view.rootView.findViewById(R.id.authorCard))
                    }
                    else if ( delta > 200 )
                    {
                        showAuthorCard(view.rootView.findViewById(R.id.authorCard))
                    }
                    true
                }
                else -> true
            }
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}