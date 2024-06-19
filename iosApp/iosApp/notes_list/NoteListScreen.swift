//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Devarsh Bhalara on 18/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    
    private var noteDataSource: NoteDataSource
    @StateObject var viewModel = NoteListViewModel(noteDateSource: nil)
    
    @State var isNoteSelected = false
    @State var selectedNoteId: Int64? = nil
    
    init(noteDateSource: NoteDataSource) {
        self.noteDataSource = noteDateSource
    }
    
    var body: some View {
        VStack {
            
            ZStack {
                
                HideableSearchTextField<NoteDetailScreen>(onSearchToggle: {
                    viewModel.toggleSearchActive()
                }, destinationProvider: {
                    NoteDetailScreen(noteDateSource: noteDataSource, noteId: nil)
                }, isSearchActive: viewModel.isSearchActive,
                searchText: $viewModel.searchText)
                .frame(maxWidth: .infinity, minHeight: 40)
                .padding()
                
                if !viewModel.isSearchActive {
                    Text("All Notes")
                        .font(.title2)
                }
            }
            
            List {
                ForEach(viewModel.filteredNotes, id: \.self.id) { note in
                    Button(action: {
                        selectedNoteId = note.id?.int64Value
                        isNoteSelected = true
                    }) {
                        NoteItem(note: note, onDeleteClick: {
                            viewModel.deleteNoteById(id: note.id?.int64Value)
                        })
                    }
                    .background(
                        
                        NavigationLink(destination: NoteDetailScreen(noteDateSource: noteDataSource, noteId: selectedNoteId), isActive: $isNoteSelected) {
                            EmptyView()
                        }.hidden()
                    )
                }
            }
            .onAppear {
                viewModel.loadNotes()
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
            
        }
        .onAppear {
            viewModel.setNoteDataSource(noteDateSource: noteDataSource)
        }
    }
}

#Preview {
    EmptyView()
}
