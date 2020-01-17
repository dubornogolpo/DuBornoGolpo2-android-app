package com.dubornogolpo.dubornogolpo2.recyclerItems

import com.squareup.picasso.Picasso
import com.dubornogolpo.dubornogolpo2.R
import com.dubornogolpo.dubornogolpo2.models.ItemImage
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.story_image_cardview.view.*


class ContinuousRecyclerItem(val storyImage: ItemImage) : Item<GroupieViewHolder>(){

    override fun getLayout(): Int {
        return R.layout.story_image_cardview
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Picasso.get().load(storyImage.url).fit().into(viewHolder.itemView.storyImage)
    }
}