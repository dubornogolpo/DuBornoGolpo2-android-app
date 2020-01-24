package com.dubornogolpo.dubornogolpo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import com.dubornogolpo.dubornogolpo2.models.Story
import com.dubornogolpo.dubornogolpo2.recyclerItems.StoryItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_contents.*

class Contents : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents)

        val divider = DividerItemDecoration(this,  DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider)?.let { divider.setDrawable(it) }

        val contentsAdapter = GroupAdapter<GroupieViewHolder>()
        FirebaseFirestore.getInstance().collection("stories")
            .get()
            .addOnSuccessListener {
                for ( doc in it!! )
                {
                    if ( doc != null )
                    {
                        val storytmp = doc.toObject(Story::class.java)
                        contentsAdapter.add(
                            StoryItem(
                                storytmp
                            )
                        )
                    }
                }
                contentsRecycler.adapter = contentsAdapter
                contentsRecycler.addItemDecoration(divider)
                contentsAdapter.setOnItemClickListener { item, view ->
                    item as StoryItem
                    val name = item.story.name
                    val intent = Intent(this, StoryView::class.java)
                    intent.putExtra("storyName", name)
                    startActivity(intent)
                }
            }
    }
}
