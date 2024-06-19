package com.bdcode.noteappkmm.data.note

import com.bdcode.noteappkmm.domain.note.Note
import com.bdcode.noteappkmm.domain.time.DateTimeUtil

class SearchNote {

    fun execute(notes: List<Note>, query: String): List<Note> {
        if(query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }

}