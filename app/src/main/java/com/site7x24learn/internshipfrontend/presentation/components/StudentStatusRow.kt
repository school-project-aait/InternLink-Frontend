package com.site7x24learn.internshipfrontend.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.site7x24learn.internshipfrontend.domain.models.application.StudentStatus

// Updated StudentStatusRow
@Composable
fun StudentStatusRow(
    student: StudentStatus,
    onStatusChange: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(student.name, Modifier.weight(1f))
            Text(student.email, Modifier.weight(1f))
            Box(Modifier.weight(1f)) {  // Status column now contains the dropdown
                StatusDropdown(
                    selectedStatus = student.status,
                    onStatusChange = onStatusChange
                )
            }
        }
        HorizontalDivider()
    }
}


//
//@Composable
//fun StudentStatusRow(
//    student: StudentStatus,
//    onStatusChange: (String) -> Unit
//) {
//    Column {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(student.name, modifier = Modifier.weight(1f))
//            Text(student.email, modifier = Modifier.weight(1f))
//            Text(student.status, modifier = Modifier.weight(1f))
//            Box(modifier = Modifier.weight(1f)) {
//                StatusDropdown(
//                    selectedStatus = student.status,
//                    onStatusChange = onStatusChange
//                )
//            }
//        }
//        HorizontalDivider()
//    }
//}

//@Composable
//fun StudentStatusRow(
//    student: StudentStatus,
//    onStatusChange: (String) -> Unit
//) {
//    Column {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(student.name, modifier = Modifier.weight(1f))
//            Text(student.email, modifier = Modifier.weight(1f))
//            Text(student.status, modifier = Modifier.weight(1f))
//            Box(modifier = Modifier.weight(1f)) {
//                StatusDropdown(
//                    selectedStatus = student.status,
//                    onStatusChange = onStatusChange
//                )
//            }
//        }
//        HorizontalDivider()
//    }
//}
