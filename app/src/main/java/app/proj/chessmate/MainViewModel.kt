package app.proj.chessmate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class MainViewModel : ViewModel() {

    private val _chessBoard : SnapshotStateList<SnapshotStateList<ChessPiece>> =
        mutableStateListOf(
            // Initialize with 8 rows, each containing 8 pieces
            mutableStateListOf(
                ChessPiece.BRook, ChessPiece.BKnight, ChessPiece.BBishop, ChessPiece.BQueen,
                ChessPiece.BKing, ChessPiece.BBishop, ChessPiece.BKnight, ChessPiece.BRook
            ),
            mutableStateListOf(
                ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn,
                ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn, ChessPiece.BPawn
            ),
            mutableStateListOf(
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty
            ),
            mutableStateListOf(
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty
            ),
            mutableStateListOf(
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty
            ),
            mutableStateListOf(
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty,
                ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty, ChessPiece.Empty
            ),
            mutableStateListOf(
                ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn,
                ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn, ChessPiece.WPawn
            ),
            mutableStateListOf(
                ChessPiece.WRook, ChessPiece.WKnight, ChessPiece.WBishop, ChessPiece.WQueen,
                ChessPiece.WKing, ChessPiece.WBishop, ChessPiece.WKnight, ChessPiece.WRook
            )
        )
    val chessBoard: SnapshotStateList<SnapshotStateList<ChessPiece>> = _chessBoard

        private val _selectedPosition = mutableStateOf(Pair(-1, -1))
        val selectedPosition: State<Pair<Int, Int>> = _selectedPosition

        fun handleTap(row: Int, col: Int) {
            val (selectedRow, selectedCol) = _selectedPosition.value

            if (selectedRow == -1 && selectedCol == -1) {
                if (_chessBoard[row][col] != ChessPiece.Empty) {
                    _selectedPosition.value = Pair(row, col)
                }
            } else {
                if (selectedRow != row || selectedCol != col) {
                    _chessBoard[row][col] = _chessBoard[selectedRow][selectedCol]
                    _chessBoard[selectedRow][selectedCol] = ChessPiece.Empty
                }
                _selectedPosition.value = Pair(-1, -1)
            }
        }

}