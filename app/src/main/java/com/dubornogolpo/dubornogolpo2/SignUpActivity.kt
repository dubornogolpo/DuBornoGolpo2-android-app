package com.dubornogolpo.dubornogolpo2

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.dubornogolpo.dubornogolpo2.models.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val qrId = intent.getStringExtra("QR_ID")
        val uuid = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

        signUpButton.setOnClickListener {
            if ( nameText.text != null || phoneText.text != null )
            {
                signup(qrId, uuid, nameText.text.toString(), phoneText.text.toString())
            }
            else
                Toast.makeText(this, "Please enter all details correctly", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signup(qrId: String, uuid: String, name: String, phone: String)
    {
        val user = User(qrId, name, phone, uuid, "")
        FirebaseFirestore.getInstance().collection("users")
            .get()
            .addOnSuccessListener {
                var flag: Int = 0
                for ( doc in it!! )
                {
                    if ( doc.get("qrid") == qrId && doc.get("id") == null ) //look for proper qrid and check to see if the user has already been populated or not
                    {
                        FirebaseFirestore.getInstance().collection("users").document(qrId)
                            .set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, HomeScreenActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        flag = 1
                        break
                    }
                }
                if ( flag == 0 )
                    Toast.makeText(this, "No matching QR ID found in database. Please try scanning a valid barcode", Toast.LENGTH_SHORT).show()
            }
    }
}