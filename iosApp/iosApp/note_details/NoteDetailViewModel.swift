//
//  NoteDetailViewModel.swift
//  iosApp
//
//  Created by Devarsh Bhalara on 19/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteDetailScreen {
    
    @MainActor class NoteDetailViewModel: ObservableObject {
        
        private var noteDataSource: NoteDataSource?
        
        private var noteId: Int64? = nil
        @Published var noteTitle = ""
        @Published var noteContent = ""
        @Published private(set) var noteColor = Note.Companion().generateRandomColor()
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNoteIfExists(id: Int64?) {
            
            guard let id = id else {
                return
            }
            
            self.noteId = id
            
            noteDataSource?.getNoteById(id: id, completionHandler: { note, error in
                self.noteTitle = note?.title ?? ""
                self.noteContent = note?.content ?? ""
                self.noteColor = note?.colorHex ?? Note.Companion().generateRandomColor()
            })
            
    
            
        }
        
        func saveNote(onSaved: @escaping () -> Void) {
            
            noteDataSource?.insertNote(note: Note(id: noteId == nil ? nil : KotlinLong(value: noteId!), title: noteTitle, content: noteContent, colorHex: noteColor, created: DateTimeUtil().now()), completionHandler: { error in
                onSaved()
            })
            
        }
        
        func setParamasAndLoadNotes(noteDataSource: NoteDataSource, noteId: Int64?) {
            self.noteDataSource = noteDataSource
            loadNoteIfExists(id: noteId)
        }
        
    }
    
}
