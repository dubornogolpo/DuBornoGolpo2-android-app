package com.dubornogolpo.dubornogolpo2.recyclerItems

import com.dubornogolpo.dubornogolpo2.R
import com.dubornogolpo.dubornogolpo2.models.Story
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.story_cardview.view.*

class StoryItem(val story: Story) : Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.story_cardview
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.titleText.text = story.name
        viewHolder.itemView.authorText.text = story.author
    }
}