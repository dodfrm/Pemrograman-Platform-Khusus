package com.polstat.perpustakaan

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.perpustakaan.ui.theme.PerpustakaanTheme

class MainActivity : ComponentActivity() {
    private val myActivityTag = "lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerpustakaanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        name = "Politeknik Statistika - STIS",
                        modifier = Modifier.padding(innerPadding),
                        onExit = { finish() }
                    )
                }
            }
        }
        Log.i(myActivityTag, "onCreate State")
    }

    override fun onStart() {
        super.onStart()
        Log.i(myActivityTag, "onStart State")
    }

    override fun onResume() {
        super.onResume()
        Log.i(myActivityTag, "onResume State")
    }

    override fun onPause() {
        super.onPause()
        Log.i(myActivityTag, "onPause State")
    }

    override fun onStop() {
        super.onStop()
        Log.i(myActivityTag, "onStop State")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(myActivityTag, "onDestroy State")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(myActivityTag, "onRestart State")
    }
}

@Composable
fun MainContent(name: String, modifier: Modifier = Modifier, onExit: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting(name)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onExit) {
            Text(text = "Keluar")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Selamat Datang di Perpustakaan $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PerpustakaanTheme {
        MainContent(name = "Android", onExit = {})
    }
}
