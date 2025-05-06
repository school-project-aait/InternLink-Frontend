package com.site7x24learn.internshipfrontend.presentation.components




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.site7x24learn.internshipfrontend.domain.models.internships.Internship

@Composable
fun InternshipCard(
    internship: Internship,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Shape definitions
    val cardShape = RoundedCornerShape(12.dp)
    val statusShape = RoundedCornerShape(4.dp)

    // Parse requirements from description
    val requirements = internship.description?.split("\n")
        ?.filter { it.trim().startsWith("-") || it.trim().startsWith("•") }
        ?.map { it.trim().removePrefix("-").removePrefix("•").trim() }
        ?: emptyList()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = cardShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title and Category Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = internship.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "[${internship.categoryName}]",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Company and Status Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Company: ${internship.companyName}",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                // Status Indicator
                Box(
                    modifier = Modifier
                        .clip(statusShape)
                        .background(
                            color = when (internship.status.lowercase()) {
                                "open" -> Color(0xFF4CAF50).copy(alpha = 0.2f)
                                "closed" -> Color(0xFFF44336).copy(alpha = 0.2f)
                                else -> Color(0xFF9E9E9E).copy(alpha = 0.2f)
                            }
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = internship.status.replaceFirstChar { it.uppercase() },
                        color = when (internship.status.lowercase()) {
                            "open" -> Color(0xFF2E7D32)
                            "closed" -> Color(0xFFC62828)
                            else -> Color(0xFF616161)
                        },
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Requirements Section
            if (requirements.isNotEmpty()) {
                Text(
                    text = "Requirements:",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    requirements.take(3).forEach { req ->
                        Text(
                            text = "• $req",
                            modifier = Modifier.padding(vertical = 2.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    if (requirements.size > 3) {
                        Text(
                            text = "+ ${requirements.size - 3} more...",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            // Deadline and Active Status Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Deadline: ${internship.deadline}",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Active Indicator
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (internship.isActive) Color(0xFF4CAF50)
                                else Color(0xFF9E9E9E)
                            )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (internship.isActive) "Active" else "Inactive",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}