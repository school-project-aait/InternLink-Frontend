package com.site7x24learn.internshipfrontend.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.site7x24learn.internshipfrontend.domain.models.application.StudentStatus

@Composable
fun StudentStatusRow(
    student: StudentStatus,
    onStatusChange: (String) -> Unit
) {
    val context = LocalContext.current // ✅ Proper context access

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(student.name, Modifier.weight(1f))
            Text(student.email, Modifier.weight(1.2f))
            Text(
                text = "View Resume",
                color = Color.Blue,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(student.resume))
                        context.startActivity(intent)
                    }
            )
            Box(Modifier.weight(1f)) {
                StatusDropdown(
                    selectedStatus = student.status,
                    onStatusChange = onStatusChange
                )
            }
        }
        HorizontalDivider()
    }
}



//import android.content.Intent
//import android.net.Uri
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.site7x24learn.internshipfrontend.domain.models.Application.StudentStatus
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
//
////import com.site7x24learn.internshipfrontend.domain.models.application.StudentStatus
//
//// Updated StudentStatusRow
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
//            Text(student.name, Modifier.weight(1f))
//            Text(student.email, Modifier.weight(1.2f)) // <-- Edited: from 1f to 1.2f
//            Text(
//                text = "View Resume",
//                color = Color.Blue,
//                modifier = Modifier
//                    .weight(1f)
//                    .clickable {
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(student.resume))
//                        context.startActivity(intent)
//                    }
//            )
//            Box(Modifier.weight(1f)) {  // Status column now contains the dropdown
//                StatusDropdown(
//                    selectedStatus = student.status,
//                    onStatusChange = onStatusChange
//                )
//            }
//        }
//        HorizontalDivider()
//    }
//}
//

