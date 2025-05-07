



@file:OptIn(ExperimentalMaterial3Api::class)
package com.site7x24learn.internshipfrontend.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.site7x24learn.internshipfrontend.domain.models.internships.Category


@Composable
fun CategoryDropdown(
    categories: List<Category>,
    selectedCategoryId: Int?,
    onCategorySelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedCategory = categories.find { it.id == selectedCategoryId }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedCategory?.name ?: "Select a Category",
            onValueChange = {},
            label = { Text("Category") },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onCategorySelected(category.id)
                        expanded = false
                    }
                )
            }
        }
    }
}
