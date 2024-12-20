import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.musahalilecer.ecommercefirebase.model.Product
import com.musahalilecer.ecommercefirebase.viewmodel.ProductViewModel


@Composable
fun ShoppingCardScreen(navHostController: NavHostController) {
    var product by remember { mutableStateOf<Product?>(null) }
    val isLoading = remember { mutableStateOf(true) }


    val context = LocalContext.current

    // Load the product when the screen starts

    LaunchedEffect(Unit) {
        // SharedPreferences'ten veriyi oku
        val sharedPreferences = context.getSharedPreferences("MyProducts", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("productName", null)
        val price = sharedPreferences.getString("productPrice", null)
        val category = sharedPreferences.getString("productCategory", null)
        val image = sharedPreferences.getString("productImage", null)

        // Ürün objesini oluştur
        product = if (name != null && price != null && category != null && image != null) {
            Product(name = name, price = price, category = category, image = image, description = "Description not available", id = "")
        } else {
            null
        }
        isLoading.value = false
    }

    // Display product details or an error message
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading.value) {
            CircularProgressIndicator()
        } else if (product != null) {
            AsyncImage(
                model = product!!.image,
                contentDescription = product!!.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product!!.name, style = MaterialTheme.typography.titleLarge)
            Text(text = product!!.price, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
            Text(text = product!!.category, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product!!.description, style = MaterialTheme.typography.bodyMedium)
        } else {
            Text(text = "Product not found", style = MaterialTheme.typography.titleLarge)
        }
        Button(
            onClick = { navHostController.navigate("buying_operation") }

        ) {
            Text("Buy")
        }
    }
}


