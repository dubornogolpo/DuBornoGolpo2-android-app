package com.dubornogolpo.dubornogolpo2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_scanner.*

class ScannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        scanButton.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setBeepEnabled(false)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ( resultCode == Activity.RESULT_OK)
        {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if ( result != null )
            {
                if ( result.contents == null )
                    Toast.makeText(this, "Scanning cancelled", Toast.LENGTH_SHORT).show()
                else
                {
                    val intent = Intent(this, SignUpActivity::class.java)
                    intent.putExtra("QR_ID", result.contents.toString())
                    startActivity(intent)
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data)
    }
}
