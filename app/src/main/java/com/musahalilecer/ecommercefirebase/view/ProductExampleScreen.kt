package com.musahalilecer.ecommercefirebase.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ProductExpScreen(){
    Box(){
        LazyColumn {
            items(5){
                Card {
                    Text(text = "Product")
                }
            }
        }
    }
}