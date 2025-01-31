package nimesh.luhana.finclutech.features.savedrequests

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun SavedRequestsScreen(navController: NavController) {
    val viewModel: SavedRequestsViewModel = viewModel()
    val requests by viewModel.requests.collectAsState()

    LazyColumn {
        items(requests) { request ->
            ListItem(
                headlineContent = { Text("Request ${request.id}") },
                supportingContent = { Text("Amount: ${request.amount} AED") },
                trailingContent = {
                    Button(onClick = { navController.navigate("request_detail/${request.id}") }) {
                        Text("View JSON")
                    }
                }
            )
        }
    }
}