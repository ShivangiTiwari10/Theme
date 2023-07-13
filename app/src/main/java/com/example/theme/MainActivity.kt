package com.example.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.theme.ui.theme.ThemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    @Composable
    fun App() {

        val theme = remember { mutableStateOf(false) }

        ThemeTheme(theme.value) {

            Column(Modifier.background(MaterialTheme.colors.background)) {
                Text(
                    "Cheezy code",
                    style = MaterialTheme.typography.h1
                )
                Button(onClick = { theme.value = !theme.value }) {

                    Text(text = "Change Theme")
                }
            }
        }

    }
}
