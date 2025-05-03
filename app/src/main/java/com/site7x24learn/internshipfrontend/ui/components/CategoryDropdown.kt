package com.site7x24learn.internshipfrontend.ui.components


import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.site7x24learn.internshipfrontend.data.model.Category


//meow

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoryDropdown(
//    categories: List<Category>,
//    selectedCategory: String,  // Store ID as String in form state
//    onCategorySelected: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded },
//        modifier = modifier
//    ) {
//        // Text field shows category name while storing ID internally
//        OutlinedTextField(
//            value = categories.find { it.category_id.toString() == selectedCategory }?.category_name ?: "",
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Select Category") },
//            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
//            modifier = Modifier
//                .menuAnchor()
//                .fillMaxWidth()
//        )
//
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            // Show actual categories from API instead of hardcoded list
//            categories.forEach { category ->
//                DropdownMenuItem(
//                    text = { Text(category.category_name) },
//                    onClick = {
//                        // Store category ID instead of name
//                        onCategorySelected(category.category_id.toString())
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}
//





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    categories: List<Category>,
    selectedCategory: String,  // this is the ID as a string
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    // Show category name (not ID) in the text field
    val selectedCategoryName = categories.find { it.category_id.toString() == selectedCategory }?.category_name ?: ""

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedCategoryName,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Category") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.category_name) },
                    onClick = {
                        onCategorySelected(category.category_id.toString()) // Return ID
                        expanded = false
                    }
                )
            }
        }
    }
}

















//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoryDropdown(
//    categories: List<Category>, // ✅ Passed from ViewModel
//    selectedCategory: String,   // This is category ID (e.g., "1")
//    onCategorySelected: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var expanded by remember { mutableStateOf(false) }
//    val items=categories.map { it.category_name }
//
//    // ✅ Get the category name corresponding to the selected ID
//    val selectedCategoryName = categories.find { it.category_id.toString() == selectedCategory }?.category_name ?: ""
//
//    // ✅ Wrap the dropdown components
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded }, // Toggle when field is clicked
//        modifier = modifier
//    ) {
//        // ✅ TextField shows the category name, not ID
//        OutlinedTextField(
//            value = selectedCategoryName,
//            onValueChange = {}, // Read-only field
//            readOnly = true,
//            label = { Text("Select Category") },
//            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
//            modifier = Modifier
//                .fillMaxWidth() // Fill the available width
//                .menuAnchor()   // Correct placement of menu
//        )
//
//        // ✅ Dropdown with category names
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            categories.forEach { category ->
//                DropdownMenuItem(
//                    text = { Text(category.category_name) },
//                    onClick = {
//                        onCategorySelected(category.category_id.toString()) // Store ID
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}
//
//
//

















//
//
////2nd
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoryDropdown(
//    categories: List<Category>, // ✅ Receive dynamic categories
//    selectedCategory: String,
//    onCategorySelected: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    val selectedCategoryName =
//        categories.find { it.category_id.toString() == selectedCategory }?.category_name ?: ""
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded },
//        modifier = modifier
//    ) {
//        OutlinedTextField(
//            value = selectedCategory,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Select Category") },
//            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//            modifier = Modifier
//                .menuAnchor()
//                .fillMaxWidth()
//        )
//
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            // ✅ Dynamically loop through categories
//            categories.forEach { category ->
//                DropdownMenuItem(
//                    text = { Text(category.category_name) },
//                    onClick = {
//                        onCategorySelected(category.category_id.toString()) // You store ID
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}
//



















//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoryDropdown(
//    selectedCategory: String,
//    onCategorySelected: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val options = listOf(
//        "Information Technology",
//        "Creative Art & Design",
//        "Social & Humanities",
//        "Sales & Marketing"
//    )
//
//    var expanded by remember { mutableStateOf(false) }
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded },
//        modifier = modifier
//    ) {
//        OutlinedTextField(
//            value = selectedCategory,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Select Category") },
//            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//            modifier = Modifier
//                .menuAnchor()
//                .fillMaxWidth()
//        )
//
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            options.forEach { selectionOption ->
//                DropdownMenuItem(
//                    text = { Text(selectionOption) },
//                    onClick = {
//                        onCategorySelected(selectionOption)
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}