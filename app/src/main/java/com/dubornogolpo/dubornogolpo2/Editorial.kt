package com.dubornogolpo.dubornogolpo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer
import com.dubornogolpo.dubornogolpo2.models.ItemImage
import com.dubornogolpo.dubornogolpo2.tools.SimpleAdapter
import kotlinx.android.synthetic.main.activity_editorial.*

class Editorial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editorial)

        var urls: ArrayList<String> = arrayListOf()

        FirebaseFirestore.getInstance().collection("editorial")
            .get()
            .addOnSuccessListener {
                for ( doc in it!! )
                {
                    val tmp = doc.toObject(ItemImage::class.java)
                    urls.add(tmp.url)
                }
                val adapter = SimpleAdapter(this, urls)
                editorialRecycler.adapter = adapter
                editorialRecycler.setPageTransformer(true, BookFlipPageTransformer())
            }
    }
}
