package com.dubornogolpo.dubornogolpo2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import com.dubornogolpo.dubornogolpo2.models.Author
import com.dubornogolpo.dubornogolpo2.recyclerItems.AuthorItem
import com.dubornogolpo.dubornogolpo2.tools.DatabaseHelper
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_author_browser.*

class AuthorBrowser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_browser)

        val divider = DividerItemDecoration(this,  DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider)?.let { divider.setDrawable(it) }

        val authorAdapter = GroupAdapter<GroupieViewHolder>()
        FirebaseFirestore.getInstance().collection("authors")
            .get()
            .addOnSuccessListener {
                val dbHelper = DatabaseHelper(this)
                for ( doc in it!! )
                {
                    if ( doc != null )
                    {
                        val authortmp = doc.toObject(Author::class.java)
                        authorAdapter.add(
                            AuthorItem(
                                authortmp
                            )
                        )
//                        dbHelper.writeAuthorsToDatabase(authortmp.name, authortmp.birthplace, authortmp.bloodGroup, authortmp.school, authortmp.college, authortmp.hobbies)
                    }
                }
                authorRecycler.adapter = authorAdapter
                authorRecycler.addItemDecoration(divider)
                authorAdapter.setOnItemClickListener { item, view ->
                    item as AuthorItem
                    val intent = Intent(this, AuthorDetails::class.java)
                    intent.putExtra("author", item.author)
                    startActivity(intent)
                }
            }
    }
}
