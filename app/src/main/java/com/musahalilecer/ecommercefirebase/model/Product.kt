package com.musahalilecer.ecommercefirebase.model

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val image: String = "",  // URL veya dosya adı
    val category: String = "" // Kategori adı
)
