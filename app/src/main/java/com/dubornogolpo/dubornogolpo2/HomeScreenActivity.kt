package com.dubornogolpo.dubornogolpo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        contentsButton.setOnClickListener {
            val intent = Intent(this, Contents::class.java)
            startActivity(intent)
        }

        authorsButton.setOnClickListener {
            val intent = Intent(this, AuthorBrowser::class.java)
            startActivity(intent)
        }

        publisherButton.setOnClickListener {
            val intent = Intent(this, Publisher::class.java)
            startActivity(intent)
        }

        editorialButton.setOnClickListener {
            val intent = Intent(this, Editorial::class.java)
            startActivity(intent)
        }

        fullBookButton.setOnClickListener {
            val intent = Intent(this, Continuous::class.java)
            startActivity(intent)
        }

    }
}
