package com.dubornogolpo.dubornogolpo2.recyclerItems

import com.squareup.picasso.Picasso
import com.dubornogolpo.dubornogolpo2.R
import com.dubornogolpo.dubornogolpo2.models.Author
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.author_cardview.view.*

class AuthorItem(val author: Author) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.author_cardview
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.authorNameText.text = author.name
        if (author.photo.isNotEmpty())
        {
            Picasso.get().load(author.photo).fit().into(viewHolder.itemView.profilePhoto)
        }
    }

}