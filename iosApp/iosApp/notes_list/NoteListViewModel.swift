//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Devarsh Bhalara on 18/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteListScreen {
    @MainActor class NoteListViewModel: ObservableObject {
        
        private var noteDateSource: NoteDataSource? = nil
        
        private let searchNote = SearchNote()
        
        private var notes = [Note]()
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchText = "" {
            didSet {
                self.filteredNotes = searchNote.execute(notes: self.notes, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false
        
        init(noteDateSource: NoteDataSource? = nil) {
            self.noteDateSource = noteDateSource
        }
        
        func setNoteDataSource(noteDateSource: NoteDataSource) {
            self.noteDateSource = noteDateSource
          
        }
        
        func loadNotes() {
            noteDateSource?.getAllNotes(completionHandler: { notes, error in
                
                self.notes = notes ?? []
                self.filteredNotes = self.notes
                
            })
        }
        
        func deleteNoteById(id: Int64?) {
            guard let id = id else {
                return
            }
            
            noteDateSource?.deleteNoteById(id: id, completionHandler: { error in
                self.loadNotes()
            })
        }
        
        func toggleSearchActive() {
            isSearchActive = !isSearchActive
            if !isSearchActive {
                searchText = ""
            }
        }
        
    }
}
