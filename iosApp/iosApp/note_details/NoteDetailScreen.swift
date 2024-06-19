//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by Devarsh Bhalara on 18/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailScreen: View {
    
    private var noteDateSource: NoteDataSource
    private var noteId: Int64? = nil
    
    @StateObject var viewModel = NoteDetailViewModel(noteDataSource: nil)
    @Environment(\.presentationMode) var presentation
    
    
    init(noteDateSource: NoteDataSource, noteId: Int64? = nil) {
        self.noteDateSource = noteDateSource
        self.noteId = noteId
    }
    
    var body: some View {
        VStack (alignment: .leading) {
            
            TextField ("Enter a Title",  text: $viewModel.noteTitle)
                .font(.title)
            TextField ("Enter a Content",  text: $viewModel.noteContent)
            Spacer()
            
        }.toolbar(content: {
            Button(action: {
                viewModel.saveNote {
                    self.presentation.wrappedValue.dismiss()
                }
            }, label: {
                Image(systemName: "checkmark")
            })
        })
        .padding()
        .background(Color(hex: viewModel.noteColor))
        .onAppear {
            viewModel.setParamasAndLoadNotes(noteDataSource: noteDateSource, noteId: noteId)
        }
    }
}


