package com.bdcode.noteappkmm.android.note_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bdcode.noteappkmm.domain.note.Note

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadNotes()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("note_detail/-1L")
                },
                backgroundColor = Color.Black,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note",
                    tint = Color.White
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HideableSearchTextField(
                    text = state.searchText,
                    isSearchActive = state.isSearchActive,
                    onTextChange = viewModel::onSearchTextChange,
                    onSearchClick = viewModel::onToggleSearch,
                    onCloseClick = viewModel::onToggleSearch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
                this@Column.AnimatedVisibility(
                    visible = !state.isSearchActive,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "All Notes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                }
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 4.dp,
                content = {
                    items(
                        items = state.notes,
                        key = { it.id!! }
                    ) { note: Note ->
                        NoteItem(
                            note = note,
                            backgroundColor = Color(note.colorHex),
                            onNoteClick = {
                                navController.navigate("note_detail/${note.id}")
                            },
                            onDeleteClick = {
                                viewModel.deleteNoteById(note.id!!)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .animateItemPlacement()
                        )
                    }
                }
            )

//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2),
//                modifier = Modifier.weight(1f)
//            ) {
//                items(
//                    items = state.notes,
//                    key = { it.id!! }
//                ) { note ->
//                    NoteItem(
//                        note = note,
//                        backgroundColor = Color(note.colorHex),
//                        onNoteClick = {
//                            navController.navigate("note_detail/${note.id}")
//                        },
//                        onDeleteClick = {
//                            viewModel.deleteNoteById(note.id!!)
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                            .animateItemPlacement()
//                    )
//                }
//            }

//            LazyColumn(
//                modifier = Modifier.weight(1f)
//            ) {
//                items(
//                    items = state.notes,
//                    key = { it.id!! }
//                    ) { note: Note ->
//
//                }
//            }
        }
    }

}