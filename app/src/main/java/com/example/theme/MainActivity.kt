package com.example.theme

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


//        ProduceState --
//   *** state ka object produce krega  jiski value asyncronasly update krr payenge

class MainActivity : ComponentActivity() {

    //    Composable should be side effect free
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            counter()
//            Loader()

            Derived()
        }
    }

}

@Composable
fun counter() {


    val state = produceState(initialValue = 0) {
        for (i in 1..10) {
            delay(1000)
            value += 1
        }
    }

    Text(
        text = state.value.toString(),
        style = MaterialTheme.typography.h1
    )
}


@Composable
fun Loader() {

    val degree = produceState(initialValue = 0) {

        while (true) {

            delay(16)
            value = (value + 15) % 360
        }


    }

    Box(contentAlignment = Alignment.Center,
        modifier = androidx.compose.ui.Modifier.fillMaxSize(1f),
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "",
                    modifier = androidx.compose.ui.Modifier
                        .size(60.dp)
                        .rotate(degree.value.toFloat())
                )
                Text(text = "Loading")
            }

        }
    )
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun Derived() {

    val tableOf = remember {
        mutableStateOf(5)
    }
    val index = produceState(initialValue = 1) {


        repeat(9) {
            delay(1000)
            value += 1
        }
    }

    val message = derivedStateOf {
        "${tableOf.value} *${index.value}= ${tableOf.value * index.value}"
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = androidx.compose.ui.Modifier.fillMaxSize(1f)
    ) {
        Text(
            text = message.value,
            style = MaterialTheme.typography.h1
        )
    }
}