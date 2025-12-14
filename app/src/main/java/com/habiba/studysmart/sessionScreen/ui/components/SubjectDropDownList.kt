package com.habiba.studysmart.sessionScreen.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDropDownList(
    subjects: List<SubjectIdNameDomainModel>,
    selectedSubject: SubjectIdNameDomainModel?,
    onSubjectSelected: (SubjectIdNameDomainModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = true
                ),
            readOnly = true,
            value = selectedSubject?.subjectName ?: "",
            onValueChange = {},
            label = { Text("Select subject") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            subjects.forEach { subject ->
                DropdownMenuItem(
                    text = { Text(subject.subjectName) },
                    onClick = {
                        expanded = false
                        onSubjectSelected(subject)
                    }
                )
            }
        }
    }
}
