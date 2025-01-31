package nimesh.luhana.finclutech.features.sendmoney

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nimesh.luhana.finclutech.R
@Composable
fun SendMoneyScreen(
    navController: NavController,
    viewModel: SendMoneyViewModel = hiltViewModel()
) {
    val services by viewModel.services.collectAsState()
    val selectedService by viewModel.selectedService.collectAsState()
    val selectedProvider by viewModel.selectedProvider.collectAsState()
    val showServicesDropdown by viewModel.showServicesDropdown.collectAsState()
    val showProvidersDropdown by viewModel.showProvidersDropdown.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedService?.name ?: "Select Service",
                onValueChange = {},
                label = { Text("Service") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.toggleServicesDropdown(true) }, // Open dropdown
                readOnly = true
            )
        DropdownMenu(
            expanded = showServicesDropdown,
            onDismissRequest = { viewModel.toggleServicesDropdown(false) }
        ) {
            services.forEach { service ->
                DropdownMenuItem(
                    text = { Text(service.name.replace("_"," ")) },
                    onClick = {
                        viewModel.selectService(service)
                        viewModel.toggleServicesDropdown(false)
                    }
                )
            }
        }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedProvider?.name ?: "Select Provider",
                onValueChange = {},
                label = { Text("Provider") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.toggleProvidersDropdown(true) }, // Open dropdown
                readOnly = true
            )
        DropdownMenu(
            expanded = showProvidersDropdown,
            onDismissRequest = { viewModel.toggleProvidersDropdown(false) }
        ) {
            selectedService?.providers?.forEach { service ->
                DropdownMenuItem(
                    text = { Text(service.name.replace("_"," ")) },
                    onClick = {
                        viewModel.selectProviderField(service)
                        viewModel.toggleProvidersDropdown(false)
                    }
                )
            }
        }
        }


        selectedProvider?.let { provider ->
            provider.requiredFields.let { fields ->
                DynamicForm(
                    fields = fields,
                    viewModel = viewModel
                )
            }
        }


        // Submit Button
        Button(
            onClick = {
                if (viewModel.validateForm()) {
                    viewModel.saveRequest()
                    navController.navigate("saved_requests")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.submit))
        }
    }
}