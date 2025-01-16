package com.polstat.asynctaskpractice

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {

    private var imageView: ImageView? = null
    private var button: Button? = null
    private var progressDialog: ProgressDialog? = null
    private val executorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.asyncTask)
        imageView = findViewById(R.id.image)

        button?.setOnClickListener {
            downloadImage("https://stis.ac.id/media/source/up.png")
        }
    }

    private fun downloadImage(url: String) {
        progressDialog = ProgressDialog(this).apply {
            setMessage("Downloading...")
            isIndeterminate = false
            setCancelable(false)
            show()
        }

        val future: Future<*>? = executorService.submit {
            try {
                val imageUrl = URL(url)
                val connection = imageUrl.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream: InputStream = connection.inputStream
                val options = BitmapFactory.Options().apply {
                    inPreferredConfig = Bitmap.Config.RGB_565
                }
                val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                runOnUiThread {
                    progressDialog?.dismiss()
                    imageView?.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    progressDialog?.dismiss()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdown()
    }
}
