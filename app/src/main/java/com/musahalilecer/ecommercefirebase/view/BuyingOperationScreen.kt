package com.musahalilecer.ecommercefirebase.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyingOperationScreen() {
    var cardName by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var cardDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Buying Operation",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        content = { paddingValues ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AsyncImage(
                        model = "https://png.pngtree.com/thumb_back/fh260/background/20230630/pngtree-d-rendering-of-mobile-application-for-online-shopping-in-e-commerce-image_3702172.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 16.dp)
                    )

                    TextField(
                        value = cardName,
                        onValueChange = { cardName = it },
                        label = { Text(text = "Card Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    TextField(
                        value = cardNumber,
                        onValueChange = { cardNumber = it },
                        label = { Text(text = "Card Number") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    TextField(
                        value = cardDate,
                        onValueChange = { cardDate = it },
                        label = { Text(text = "Expiration Date") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = { /* Handle payment */ },
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                    ) {
                        Text(text = "Complete Purchase")
                    }
                }
            }
        }
    )
}
