package com.musahalilecer.ecommercefirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.musahalilecer.ecommercefirebase.model.Product
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val productCollection = db.collection("products")

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val product = mutableStateOf<List<Product>>(listOf(Product()))



    // Ürün Ekleme
    fun addProduct(product: Product) {
        viewModelScope.launch {
            productCollection.add(product)
        }
    }

    // Ürün Güncelleme
    fun updateProduct(product: Product) {
        viewModelScope.launch {
            productCollection.document(product.id).set(product)
        }
    }

    // Ürün Silme
    fun deleteProduct(productId: String) {
        viewModelScope.launch {
            productCollection.document(productId).delete()
        }
    }

    // Ürünleri Okuma
    fun getProducts(category: String = "", onProductsLoaded: (List<Product>) -> Unit) {
        val query = if (category.isNotEmpty() && category != "All") {
            productCollection.whereEqualTo("category", category).get()
        } else {
            productCollection.get()
        }

        query.addOnSuccessListener { result ->
            val products = result.map { document ->
                document.toObject(Product::class.java).copy(id = document.id)
            }
            onProductsLoaded(products)
        }
    }

    fun setProducts(productList: List<Product>){
        _products.value = productList
    }

}
