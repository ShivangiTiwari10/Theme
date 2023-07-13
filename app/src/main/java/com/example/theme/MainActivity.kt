package com.example.theme

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


// esse side effects jo only one time excute krrna h
// or kissi condition me launch krrna ho to to wha launched effect use krrte h

class MainActivity : ComponentActivity() {

    //    Composable should be side effect free
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//            exampe 1.
//            sideEffect()

//            exampe 2.
//            ListComposable()

//            example 3.
            Counter()
        }
    }
}

//var count = 1
//@Composable
//fun sideEffect() {
//    count++
//    Text(text = "Cheesy code")
//
//}
//

//@Composable
//fun ListComposable() {
//
//    val categoryState = remember { mutableStateOf(emptyList<String>()) }
//
////    problem1. fetchCategory will be call multiple time
////    and 2. Time consuming
//    categoryState.value = fetchCategory()
//
//
////    define affect handler which will execute one time for managing side effect in controlled way
//// it will excute in curotene scope
//    LaunchedEffect(key1 = Unit) {
//        categoryState.value = fetchCategory()
//    }
//
//    LazyColumn {
//
//        items(categoryState.value) { item ->
//            Text(text = item)
//        }
//    }
//
//}
//
//fun fetchCategory(): List<String> {
//    return listOf("One", "Two", "Three")
//}


@Composable
fun Counter() {

    val count = remember {
        mutableStateOf(0)
    }

    val key = count.value % 3 == 0

    LaunchedEffect(key1 = key) {

        Log.d("Counter", "current count:${count.value}")

    }


    Button(onClick = { count.value++ }) {
        Text(text = "increement")
    }
}

