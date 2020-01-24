package com.dubornogolpo.dubornogolpo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.dubornogolpo.dubornogolpo2.models.Author
import kotlinx.android.synthetic.main.activity_author_details.*

class AuthorDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_details)

        val author = intent.getParcelableExtra<Author>("author")

        authorName.text = author.name
        authorBirth.text = author.birthplace
        authorSchool.text = author.school
        authorCollege.text = author.college
        authorHobbies.text = author.hobbies
        authorBlood.text = author.bloodGroup
        Picasso.get().load(author.photo).fit().into(authorPhoto)
    }
}
