package app.proj.chessmate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
//    val viewModel : MainViewModel = viewModel()
//    val score = viewModel.score
//
//    Column (modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//
//    ){
//        Text(score.value.toString(), fontSize = 25.sp)
//        Button(onClick = { viewModel.increaseScore() }) {
//            Text(text = "Increase Score")
//        }
//    }
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

    LazyVerticalGrid(columns = GridCells.Fixed(8)) {
        items(chessBoard.size) { index ->
            val piece = chessBoard[index]

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(if ((index / 8 + index % 8) % 2 == 0) colorResource(id = R.color.light_green) else colorResource(id = R.color.light_white))
                    .draggablePiece(index, chessBoard)
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

@Composable
fun ChessSquare(isDark: Boolean, piece: ChessPiece?) {
    Box(
        modifier = Modifier
            .size(50.dp) // Adjust size for the square
            .background(if (isDark) colorResource(id = R.color.light_green) else colorResource(id = R.color.light_white))
            .border(1.dp, Color.Black)
    ) {
        piece?.let {
            // Display the piece (use an image or icon)
            Image(painter = painterResource(id = it.iconRes), contentDescription = it.name)
        }
    }
}
// Chessboard initialization function
fun createChessBoard(): List<ChessPiece> {
    // Initialize an 8x8 chess board with pieces
    return List(64) { index ->
        when (index) {
            0,7 -> ChessPiece.WRook // Place white rooks
            1, 6 -> ChessPiece.WKnight // Place white knights
            2, 5 -> ChessPiece.WBishop // Place white bishops
            3 -> ChessPiece.WQueen // Place white queen
            4 -> ChessPiece.WKing // Place white king
            in 8..15 -> ChessPiece.WPawn // Place white pawns

            in 48..55 -> ChessPiece.BPawn // Place black pawns
            56 -> ChessPiece.BRook // Place black rooks
            57 -> ChessPiece.BKnight // Place black knights
            58 -> ChessPiece.BBishop // Place black bishops
            59 -> ChessPiece.BQueen // Place black queen
            60 -> ChessPiece.BKing // Place black king
            61 -> ChessPiece.BBishop // Place black bishops
            62 -> ChessPiece.BKnight // Place black knights
            63 -> ChessPiece.BRook // Place black rooks

            else -> ChessPiece.Empty// Empty squares
        }
    }
}
