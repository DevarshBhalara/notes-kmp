package com.bdcode.noteappkmm.android.note_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bdcode.noteappkmm.domain.note.Note
import com.bdcode.noteappkmm.domain.time.DateTimeUtil

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun NoteItem(
    note: Note,
    backgroundColor: Color,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val formattedDate = remember(note.created) {
        DateTimeUtil.formatNoteDate(note.created)
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable { onNoteClick() }
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete note",
                modifier = Modifier
                    .clickable(MutableInteractionSource(), null) {
                        onDeleteClick()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = note.content, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = formattedDate, color = Color.DarkGray, modifier = Modifier.align(Alignment.End))
    }

}