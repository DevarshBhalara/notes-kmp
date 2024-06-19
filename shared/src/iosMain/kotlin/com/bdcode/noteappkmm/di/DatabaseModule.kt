package com.bdcode.noteappkmm.di

import com.bdcode.noteappkmm.data.local.DatabaseDriverFactory
import com.bdcode.noteappkmm.data.note.SqlDelightNoteDataSource
import com.bdcode.noteappkmm.database.NoteDatabase
import com.bdcode.noteappkmm.domain.note.NoteDataSource

class DatabaseModule {

      val factory by lazy { DatabaseDriverFactory() }
     val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(NoteDatabase(factory.createDriver()))
    }
}