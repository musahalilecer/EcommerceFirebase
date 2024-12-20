package com.musahalilecer.ecommercefirebase.activity

import AccountScreen
import ShoppingCardScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.musahalilecer.ecommercefirebase.ui.theme.EcommerceFirebaseTheme
import com.musahalilecer.ecommercefirebase.view.*
import com.musahalilecer.ecommercefirebase.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceFirebaseTheme {
                val navController = rememberNavController()
                var showDialog by remember { mutableStateOf(true) }

                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = "E Commerce") }) },
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { paddingValues ->
                    NavigationGraph(navController = navController, viewModel = ProductViewModel(), paddingValues = paddingValues)

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        showDialog = false
                                        navController.navigate("login")
                                    }
                                ) {
                                    Text("Giriş Yap")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = { showDialog = false }
                                ) {
                                    Text("Kayıt Ol")
                                }
                            },
                            title = { Text("Kayıt veya Giriş") },
                            text = { Text("Lütfen devam etmek için kayıt olun veya giriş yapın.") }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 4.dp,

    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("home")},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Black,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Black
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shopping") },
            label = { Text("Shopping") },
            selected = false,
            onClick = { navController.navigate("shopping") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Black,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Black
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
            label = { Text("Account") },
            selected = false,
            onClick = { navController.navigate("account") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Black,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Black
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Add Product") },
            label = { Text("Add") },
            selected = false,
            onClick = { navController.navigate("add_product") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Black,
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Lock, contentDescription = "Login") },
            label = { Text("Login") },
            selected = false,
            onClick = { navController.navigate("login") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = Color.Black,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = Color.Black
            )
        )
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues, viewModel: ProductViewModel) {
    NavHost(navController, startDestination = "home", Modifier.padding(paddingValues)) {
        composable("home") { HomeScreen(navController, viewModel) }
        composable("shopping") { ShoppingCardScreen(navController) }
        composable("account") { AccountScreen() }
        composable("add_product") { ProductAddScreen(viewModel) }
        composable("login") { LoginScreen() }
        composable("buying_operation"){ BuyingOperationScreen() }
    }
}

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProductViewModel) {
    ProductScreen(viewModel, navController)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcommerceFirebaseTheme {
        Greeting("Android")
    }
}
