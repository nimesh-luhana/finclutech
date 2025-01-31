package nimesh.luhana.finclutech.features.sendmoney

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import nimesh.luhana.finclutech.data.model.Field
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@Composable
fun DynamicForm(fields: List<Field>, viewModel: SendMoneyViewModel) {
    fields.forEach { field ->
        Spacer(modifier = Modifier.height(20.dp))
        when (field.type) {
            "text", "number" -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = field.name,
                        onValueChange = {},
                        label = { Text( field.name) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    TextField(
                        value =  field.getPlaceholder(),
                        onValueChange = { viewModel.updateField(field.name, it) },
                        label = { Text("en") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            "option" -> {
                var expanded by remember { mutableStateOf(false) }
                val selectedOption = viewModel.formState[field.name] ?: "Select Option"

                Box {
                    TextButton(onClick = { expanded = true }) {
                        Text(selectedOption)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        field.options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option.label) },
                                onClick = {
                                    viewModel.updateField(field.name, option.name)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
