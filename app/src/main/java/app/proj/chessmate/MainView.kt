package app.proj.chessmate

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MainView(){
    ChessBoard()
}

@Composable
fun ChessBoard() {
    val chessBoard = remember {
        mutableStateListOf(
            ChessPiece.BRook, ChessPiece.BKnight, ChessPiece.BBishop, ChessPiece.BQueen, ChessPiece.BKing, ChessPiece.BBishop, ChessPiece.BKnight, ChessPiece.BRook,
            ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn,
            ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
            ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
            ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
            ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
            ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn,
            ChessPiece.WRook, ChessPiece.WKnight, ChessPiece.WBishop, ChessPiece.WQueen, ChessPiece.WKing, ChessPiece.WBishop, ChessPiece.WKnight, ChessPiece.WRook
        )
    }
// Get the board state

    LazyVerticalGrid(columns = GridCells.Fixed(8),
        verticalArrangement = Arrangement.Center
        ) {
        items(chessBoard.size) { index ->
            val piece = chessBoard[index]
            val selectedIndex = remember { mutableStateOf(-1) }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        if ((index / 8 + index % 8) % 2 == 0) colorResource(id = R.color.light_green) else colorResource(
                            id = R.color.light_white
                        )
                    )
                    .draggablePiece(index, chessBoard)
                    .pointerInput(Unit) {
                        detectTapGestures (onTap = {
                            Log.d("Current Index", index.toString())
                            handleTap(index, chessBoard, selectedIndex)
                        })
                    }
            ) {
                if (piece != ChessPiece.Empty) {
                    Image(
                        painter = painterResource(id = piece.iconRes),
                        contentDescription = piece.name,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

}

fun Modifier.draggablePiece(
    index: Int,
    board: SnapshotStateList<ChessPiece> // Correct type to handle reactivity
): Modifier = this.pointerInput(Unit) {
    detectDragGestures { change, _ ->
        change.consume() // Consume the gesture
        val piece = board[index]

        if (piece != ChessPiece.Empty) {
            // Example: Update the board when dragging
            board[index]= ChessPiece.Empty // Clear the current position
            board[(index + 8) % 64] = piece // Move the piece to another position (example logic)
        }
    }
}

fun handleTap(
    index: Int,
    board: SnapshotStateList<ChessPiece>,
    selectedIndex: MutableState<Int>
) {
    val selected = selectedIndex.value

    if (selected == -1) {
        // No piece is selected, select this piece if it is not empty
        if (board[index] != ChessPiece.Empty) {
            selectedIndex.value = index
        }
    } else {
        Log.d("Selected Index",selectedIndex.toString())
        // A piece is already selected, move it
        board[index+1] = board[selected] // Move piece to new square
        board[selected] = ChessPiece.Empty // Clear original square
        selectedIndex.value = -1 // Clear selection
    }
}