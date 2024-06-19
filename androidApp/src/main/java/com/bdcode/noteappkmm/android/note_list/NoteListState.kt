package com.bdcode.noteappkmm.android.note_list

import com.bdcode.noteappkmm.domain.note.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    var isSearchActive: Boolean = false
)