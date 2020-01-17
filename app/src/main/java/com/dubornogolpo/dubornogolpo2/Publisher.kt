package com.dubornogolpo.dubornogolpo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer
import com.dubornogolpo.dubornogolpo2.models.ItemImage
import com.dubornogolpo.dubornogolpo2.tools.SimpleAdapter
import kotlinx.android.synthetic.main.activity_publisher.*

class Publisher : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publisher)

        var urls: ArrayList<String> = arrayListOf()

        FirebaseFirestore.getInstance().collection("publisher")
            .get()
            .addOnSuccessListener {
                for ( doc in it!! )
                {
                    val tmp = doc.toObject(ItemImage::class.java)
                    urls.add(tmp.url)
                }
                val adapter = SimpleAdapter(this, urls)
                publisherRecycler.adapter = adapter
                publisherRecycler.setPageTransformer(true, BookFlipPageTransformer())
            }
    }
}
