package info.alirezaahmadi.animatedshop.ui.screen.shoping

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import info.alirezaahmadi.animatedshop.viewModel.MainViewModel

@Composable
fun ShoppingCartScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
) {

    val products by mainViewModel.getAllShoppingItems().collectAsState(emptyList())

    Scaffold(
        bottomBar = {}
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding
        ) {

        }
    }

}