package com.dubornogolpo.dubornogolpo2

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkValidity(Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID))
    }

    private fun checkValidity(uuid: String)
    {
        FirebaseFirestore.getInstance().collection("users")
            .get()
            .addOnSuccessListener {
                var flag: Int = 0
                for ( doc in it!! )
                {
                    if ( doc.get("id") == uuid )
                    {
                        flag = 1
                        break
                    }
                }
                if ( flag == 0 ) {
                    Toast.makeText(this, "Not registered. Please scan a valid barcode and register.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ScannerActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                else if ( flag == 1 )
                {
                    val intent = Intent(this, HomeScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
    }
}
