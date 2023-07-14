package com.example.theme

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.view.WindowInsetsAnimation
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


// those side effect where cleanUp required There we use disposableCleanUp before leaving the composition
//    before leaving the composition if u want to cleanUp that code will be written under disposable func.

class MainActivity : ComponentActivity() {

    //    Composable should be side effect free
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            App()
//            MediaCompoable()

            KeyboardComposable()
            TextField(value = "", onValueChange = {})
        }
    }


    @Composable
    fun App() {

        var state = remember {
            mutableStateOf(false)
        }


        DisposableEffect(key1 = state.value) {

            Log.d("Diposable", "Disposable effect started")

            onDispose {
                Log.d("Diposable", "Cleaning Up side effect")
            }
        }
        Button(onClick = { state.value = !state.value }) {
            Text(text = "change state")
        }
    }


    @Composable
    fun MediaCompoable() {

        val context = LocalContext.current

        DisposableEffect(Unit) {

            val mediaPlayer = MediaPlayer.create(context, R.raw.audiomp3)
            mediaPlayer.start()
            onDispose {

                mediaPlayer.stop()
                mediaPlayer.release()

            }
        }

    }


}

@Composable
fun KeyboardComposable() {

    val view = LocalView.current

    DisposableEffect(key1 = Unit) {
        val listner = ViewTreeObserver.OnGlobalLayoutListener {


//        **    insects are rectangle present in screen
//        **   ime is used for keyboard
            val insects = ViewCompat.getRootWindowInsets(view)
            val iskeyBoardVisible = insects!!.isVisible(WindowInsetsCompat.Type.ime())

            Log.d("IsKeyBoardVisible", iskeyBoardVisible.toString())
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listner)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listner)

        }
    }

}
