package app.proj.chessmate

sealed class ChessPiece(val name: String, val iconRes: Int)  {

    object BKing : ChessPiece("BKing", R.drawable.bk)
    object WKing : ChessPiece("WKing", R.drawable.wk)
    object BQueen : ChessPiece("BQueen", R.drawable.bq)
    object WQueen : ChessPiece("WQueen", R.drawable.wqween)
    object WRook : ChessPiece("WRook", R.drawable.wr)
    object BRook : ChessPiece("BRook", R.drawable.br)
    object BKnight : ChessPiece("BKnight", R.drawable.bn)
    object WKnight : ChessPiece("WKnight", R.drawable.wn)
    object BBishop : ChessPiece("BBishop", R.drawable.bb)
    object WBishop : ChessPiece("WBishop", R.drawable.wb)
    object BPawn : ChessPiece("BPawn", R.drawable.bp)
    object WPawn : ChessPiece("WPawn", R.drawable.wp)

    object Empty : ChessPiece("Empty", R.drawable.empty)
}