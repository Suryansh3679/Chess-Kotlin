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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainView(){
    val chessViewModel : MainViewModel = viewModel()
    ChessBoard(chessViewModel)
}

@Composable
fun ChessBoard(viewModel: MainViewModel) {
    // Create 2D list of chess pieces (8x8 board)
    val chessBoard = viewModel.chessBoard

    // Keep track of selected position (row, column)
    val selectedPosition =viewModel.selectedPosition

    LazyVerticalGrid(
        columns = GridCells.Fixed(8),
        verticalArrangement = Arrangement.Center
    ) {
        items(64) { index ->
            val row = index / 8
            val col = index % 8
            val piece = chessBoard[row][col]

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        if ((row + col) % 2 == 0) colorResource(id = R.color.light_green)
                        else colorResource(id = R.color.light_white)
                    )
                    .border(
                        width = if (selectedPosition.value == Pair(row, col)) 2.dp else 0.dp,
                        color = if (selectedPosition.value == Pair(
                                row,
                                col
                            )
                        ) Color.Blue else Color.Transparent
                    )
                    .draggablePiece(row, col, chessBoard)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                viewModel.handleTap(row, col)
                            }
                        )
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
    row: Int,
    col: Int,
    board:SnapshotStateList<SnapshotStateList<ChessPiece>>
): Modifier = this.pointerInput(Unit) {
    detectDragGestures { change, dragAmount ->
        change.consume()
        val piece = board[row][col]

        if (piece != ChessPiece.Empty) {
            // Calculate new position based on drag amount
            // This is a simplified example - you might want to implement more sophisticated drag logic
            val newRow = (row + dragAmount.y.toInt()/20 )
            val newCol = (col + dragAmount.x.toInt() / 20)

            if (newRow != row || newCol != col) {
                board[newRow][newCol] = piece
                board[row][col] = ChessPiece.Empty
            }
        }
    }
}
