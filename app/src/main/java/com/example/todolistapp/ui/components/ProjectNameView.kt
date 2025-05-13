package com.example.todolistapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.model.Project

@Composable
fun ProjectNameView(
    projects: SnapshotStateList<Project>,
    selectedProject: String,
    onProjectSelected: (String) -> Unit,
    newProjectName: String,
    onNewProjectNameChange: (String) -> Unit,
    onAddProject: () -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    val maxProjNameLength = 30;

    Column(modifier = Modifier.padding(bottom = 15.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Project name",
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .height(55.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFDCE1EF),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onExpandedChange(true) }) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = selectedProject, modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = { onExpandedChange(false) }
            ) {
                projects.forEach { project ->
                    DropdownMenuItem(
                        text = { Text(text = project.name) },
                        onClick = {
                            onProjectSelected(project.name)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 10.dp)) {
            TextField(
                maxLines = 1,
                value = newProjectName,
                onValueChange = {
                    if (it.length <= maxProjNameLength) {
                        onNewProjectNameChange(it)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                    width = 1.dp,
                    color = Color(0xFFDCE1EF),
                    shape = RoundedCornerShape(20.dp)
                ),
                placeholder = { Text("Add new project") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFFFFFFF),
                    unfocusedTextColor = Color(0xFF6E7591),
                    focusedContainerColor = Color(0xFFFFFFFF),
                    focusedTextColor = Color(0xFF6E7591),
                    focusedIndicatorColor = Color(0xFF613BE7)
                ),
            )
            IconButton(onClick = onAddProject) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}