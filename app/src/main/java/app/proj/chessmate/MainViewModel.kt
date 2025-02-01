package app.proj.chessmate

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class MainViewModel : ViewModel() {

    private val _chessBoard = mutableStateListOf<SnapshotStateList<ChessPiece>>()
    val chessBoard: SnapshotStateList<SnapshotStateList<ChessPiece>> = _chessBoard
    private val _chance = mutableStateOf(true)
    init {
        initializeBoard()
    }

    private val _selectedPosition = mutableStateOf(Pair(-1, -1))
    val selectedPosition: State<Pair<Int, Int>> = _selectedPosition

    fun handleTap(row: Int, col: Int) {
        val (selectedRow, selectedCol) = _selectedPosition.value

        if (selectedRow == -1 && selectedCol == -1) {
            if ((_chessBoard[row][col] in whitePieces && _chance.value && _chessBoard[row][col] != ChessPiece.Empty )
                ||
                (_chessBoard[row][col] in blackPieces && !_chance.value && _chessBoard[row][col] != ChessPiece.Empty)) {
                _selectedPosition.value = Pair(row, col)
            }
        } else {
            if (selectedRow != row || selectedCol != col) {
                when (_chessBoard[selectedRow][selectedCol]) {
                    ChessPiece.WPawn -> wPawn(row, col, selectedRow, selectedCol)
                    ChessPiece.WRook -> wRook(row, col, selectedRow, selectedCol)
                    ChessPiece.WKnight -> wKnight(row, col, selectedRow, selectedCol)
                    ChessPiece.WBishop -> wBishop(row, col, selectedRow, selectedCol)
                    ChessPiece.WQueen -> wQueen(row, col, selectedRow, selectedCol)
                    ChessPiece.WKing -> wKing(row, col, selectedRow, selectedCol)
                    ChessPiece.BPawn -> bPawn(row, col, selectedRow, selectedCol)
                    ChessPiece.BRook -> bRook(row, col, selectedRow, selectedCol)
                    ChessPiece.BKnight -> bKnight(row, col, selectedRow, selectedCol)
                    ChessPiece.BBishop -> bBishop(row, col, selectedRow, selectedCol)
                    ChessPiece.BQueen -> bQueen(row, col, selectedRow, selectedCol)
                    ChessPiece.BKing -> bKing(row, col, selectedRow, selectedCol)
                    else -> {}
                }
            }

//            _chessBoard[row][col] = _chessBoard[selectedRow][selectedCol]
//            _chessBoard[selectedRow][selectedCol] = ChessPiece.Empty

            _selectedPosition.value = Pair(-1, -1)
        }
    }

    private fun bKing(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun bQueen(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun bBishop(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun bKnight(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun bRook(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun bPawn(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        if ((row - 2 == selectedRow && col == selectedCol && selectedRow == 1 &&  _chessBoard[row][col] == ChessPiece.Empty)
            || (row - 1 == selectedRow && col == selectedCol &&  _chessBoard[row][col] == ChessPiece.Empty)
            || (row - 1 == selectedRow && col+1 == selectedCol && _chessBoard[row][col] in whitePieces)
            || (row - 1 == selectedRow && col-1 == selectedCol && _chessBoard[row][col] in whitePieces)
        ) {
            movePiece(row,col,selectedRow,selectedCol)
        }
    }

    private fun wKing(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun wQueen(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun wBishop(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun wKnight(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
        TODO("Not yet implemented")
    }

    private fun wRook(row: Int, col: Int, selectedRow: Int, selectedCol: Int) {
//        TODO("Not yet implemented")
    }


    private fun wPawn(row: Int, col : Int, selectedRow : Int, selectedCol: Int){
//        TODO("Not yet implemented")

        if ( (row +2 == selectedRow && col == selectedCol && selectedRow == 6 &&  _chessBoard[row][col] == ChessPiece.Empty)
            || (row + 1 == selectedRow && col == selectedCol && _chessBoard[row][col] == ChessPiece.Empty)
            || (row + 1 == selectedRow && col+1 == selectedCol && _chessBoard[row][col] in blackPieces)
            || (row + 1 == selectedRow && col-1 == selectedCol && _chessBoard[row][col] in blackPieces)
        ) {
           movePiece(row,col,selectedRow,selectedCol)
        }

    }
    private fun movePiece(row: Int, col : Int, selectedRow : Int, selectedCol: Int){
        _chessBoard[row][col] = _chessBoard[selectedRow][selectedCol]
        _chessBoard[selectedRow][selectedCol] = ChessPiece.Empty
        _chance.value = !_chance.value
    }

    private fun initializeBoard(){
        _chessBoard.clear()
        _chessBoard.addAll(
        mutableStateListOf(

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
        )
    }
    fun resetBoard(){
        _selectedPosition.value = Pair(-1, -1)
        _chance.value = true
        initializeBoard()
    }



}