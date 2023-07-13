package com.example.theme

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/// LaunchEffect is composable fun. that can not be called under any event (like button click)

//  indipendentaly courotene launch krrne we need scope so we use rememberCoroutineScope()

//** side effect ko run krne we have 1.-launchEffect and 2.- rememberCoroutineScope
class MainActivity : ComponentActivity() {

    //    Composable should be side effect free
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//            LaunchEffectComposable()

            CoroutineScopeComposable()
        }
    }
}


@Composable
fun LaunchEffectComposable() {

    val counter = remember { mutableStateOf(0) }

    var scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {

        Log.d("Counter", "Started")

        try {
            for (i in 1..10) {
                counter.value++
                delay(1000)
            }
        } catch (e: Exception) {

            Log.d("launchEffectComposable", "Exception : ${e.message}")
        }

    }
    var text = "Counter is running ${counter.value}"

    if (counter.value == 10) {
        text = "Counter stopped"
    }
    Text(text = text)
}


//2.
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CoroutineScopeComposable() {

    val counter = remember {
        mutableStateOf(0)
    }

    val scope = rememberCoroutineScope()

    var text = "Counter is running ${counter.value}"

    if (counter.value == 10) {

        text = "Counter stopped"
    }

    Column {
        Text(text = text)

        Button(onClick = {

            scope.launch {

                Log.d("CoroutineScopeComposable", "Started")
                try {
                    for (i in 1..10) {
                        counter.value++
                        delay(1000)
                    }
                } catch (e: Exception) {
                    Log.d("CoroutineScopeComposable", "Exeption:${e.message}")
                }
            }
        }

        ) {
            Text(text = "Start")
        }
    }
}