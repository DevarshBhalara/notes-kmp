package com.bdcode.noteappkmm.domain.note

import com.bdcode.noteappkmm.presentation.BabyBlueHex
import com.bdcode.noteappkmm.presentation.LightGreenHex
import com.bdcode.noteappkmm.presentation.RedOrangeHex
import com.bdcode.noteappkmm.presentation.RedPinkHex
import com.bdcode.noteappkmm.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val colors = listOf(
                RedOrangeHex,
                RedPinkHex,
                BabyBlueHex,
                VioletHex,
                LightGreenHex
        )

        fun generateRandomColor() = colors.random()
    }
}
