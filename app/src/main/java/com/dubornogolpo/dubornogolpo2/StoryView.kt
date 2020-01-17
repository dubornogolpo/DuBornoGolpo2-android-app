package com.dubornogolpo.dubornogolpo2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer
import com.dubornogolpo.dubornogolpo2.models.Author
import com.dubornogolpo.dubornogolpo2.models.Story
import com.dubornogolpo.dubornogolpo2.tools.Adapter
import com.dubornogolpo.dubornogolpo2.tools.CustomSwipeOutListener
import kotlinx.android.synthetic.main.activity_story_view.*

class StoryView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_view)

        val name = intent.getStringExtra("storyName")
        FirebaseFirestore.getInstance().collection("stories")
        .whereEqualTo("name", name).get()
            .addOnSuccessListener {
                for ( doc in it!! )
                {
                    val tmp = doc.toObject(Story::class.java)
                    val swipeOutListener =
                        CustomSwipeOutListener(
                            tmp,
                            this
                        )
                    val adapter =
                        Adapter(this, tmp.photos)
                    storyImageViewPager.adapter = adapter
                    storyImageViewPager.setPageTransformer(true, BookFlipPageTransformer())
                    FirebaseFirestore.getInstance().collection("authors")
                    .whereEqualTo("name", tmp.author)
                        .get()
                        .addOnSuccessListener {
                            for ( doc in it!! )
                            {
                                authorName.text = (doc.toObject(Author::class.java)).name
                                Picasso.get().load((doc.toObject(Author::class.java)).photo).fit().into(authorPhoto)
                                authorCardConstraint.setOnClickListener {
                                    val intent = Intent(this, AuthorDetails::class.java)
                                    intent.putExtra("author", doc.toObject(Author::class.java))
                                    startActivity(intent)
                                }
                            }
                        }
                    storyImageViewPager.setOnSwipeOutListener(swipeOutListener)
                }
            }
            .addOnFailureListener {
                Log.d("FetchError", "${it.message}")
            }
    }
}
