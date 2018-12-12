package com.example.ozan.implicitintents

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.MediaStore



class MainActivity : AppCompatActivity() {

    companion object {
        var TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openWebsite = View.OnClickListener {
            val url = et_website.text.toString()
            val websiteUrl = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, websiteUrl)
            startIntent(intent)
        }

        val openLocation = View.OnClickListener {
            val location = et_location.text.toString()
            val addressUri = Uri.parse("geo:0,0?q=$location")
            val intent = Intent(Intent.ACTION_VIEW, addressUri)
            startIntent(intent)
        }

        val shareText = View.OnClickListener {
            val location = et_share.text.toString()
            val mimeType = "text/plain"

            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this info")
                .setText(location)
                .startChooser()
        }

        btn_website.setOnClickListener(openWebsite)
        btn_location.setOnClickListener(openLocation)
        btn_share.setOnClickListener(shareText)

        take_picture.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

    }

    private fun startIntent(intent: Intent) {
        if (intent.resolveActivity(packageManager) != null) {
            val choser = Intent.createChooser(intent, "title")
            startActivity(choser)
        } else {
            Log.d(TAG, "cant handle intent")
        }
    }
}