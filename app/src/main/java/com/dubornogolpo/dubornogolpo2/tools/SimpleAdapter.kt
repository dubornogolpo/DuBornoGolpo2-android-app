package com.dubornogolpo.dubornogolpo2.tools

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.dubornogolpo.dubornogolpo2.R

class SimpleAdapter(val context: Context, val list: ArrayList<String>?) : PagerAdapter() {

    var inflater = LayoutInflater.from(context)!!

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }

    override fun getCount(): Int {
        return list!!.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = inflater.inflate(R.layout.story_image_cardview, container, false)
        Picasso.get().load(list!![position]).fit().into(view.findViewById(R.id.storyImage) as ImageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}