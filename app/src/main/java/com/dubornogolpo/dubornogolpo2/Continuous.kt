package com.dubornogolpo.dubornogolpo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import com.dubornogolpo.dubornogolpo2.models.ItemImage
import com.dubornogolpo.dubornogolpo2.recyclerItems.ContinuousRecyclerItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_continuous.*

class Continuous : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continuous)

        val divider = DividerItemDecoration(this,  DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider)?.let { divider.setDrawable(it) }

        val adapter = GroupAdapter<GroupieViewHolder>()

        FirebaseFirestore.getInstance().collection("continuous")
            .get()
            .addOnSuccessListener {
                for ( doc in it!! )
                {
                    adapter.add(
                        ContinuousRecyclerItem(
                            doc.toObject(ItemImage::class.java)
                        )
                    )
                }
                continuousRecycler.adapter = adapter
                continuousRecycler.addItemDecoration(divider)
            }
    }
}
