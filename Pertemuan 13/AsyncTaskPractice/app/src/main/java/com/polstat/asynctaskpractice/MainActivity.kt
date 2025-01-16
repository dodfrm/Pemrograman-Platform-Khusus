package com.polstat.asynctaskpractice

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    private var imageView: ImageView? = null
    private var button: Button? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.asyncTask)
        imageView = findViewById(R.id.image)

        button?.setOnClickListener {
            val asyncTask = AsyncTaskExample()
            asyncTask.execute("https://stis.ac.id/media/source/up.png")
        }
    }

    private inner class AsyncTaskExample : AsyncTask<String, String, Bitmap?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog?.apply {
                setMessage("Downloading...")
                isIndeterminate = false
                setCancelable(false)
                show()
            }
        }

        override fun doInBackground(vararg strings: String): Bitmap? {
            return try {
                val imageUrl = URL(strings[0])
                val connection = imageUrl.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream: InputStream = connection.inputStream
                val options = BitmapFactory.Options().apply {
                    inPreferredConfig = Bitmap.Config.RGB_565
                }
                BitmapFactory.decodeStream(inputStream, null, options)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)
            progressDialog?.dismiss()
            if (imageView != null && bitmap != null) {
                imageView?.setImageBitmap(bitmap)
            }
        }
    }
}