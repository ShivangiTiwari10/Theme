package com.example.theme

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {

    //    Composable should be side effect free
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            App()
        }
    }


}


@Composable
fun App() {
    val counter = remember { mutableStateOf(0) }
    LaunchedEffect(key1 = Unit) {
        delay(2000)

        counter.value = 10
    }
    Counter(counter.value)
}

@Composable
fun Counter(value: Int) {


//    LaunchedEffect fun does not call in Recomposition
//    yee initial composition yaa key change pr hi call hota h

    val  state = rememberUpdatedState(newValue = value)

    LaunchedEffect(key1 = Unit) {

        delay(5000)

        Log.d("CheesyCode", state.value.toString())
    }

    Text(text = value.toString())
}



