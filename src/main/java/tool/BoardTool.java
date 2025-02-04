package tool;

import java.util.ArrayList;

import board.ChessBoard;
import board.BoardCreater;
import obj.PieceColor;
import obj.Square;
import piece.Piece;

public class BoardTool {

	public static Piece currentPiece;
	public ArrayList<Square> threatenedPieces;
	public ArrayList<Square> threateningPieces;
	public ArrayList<Square> protectedPieces;
	public ArrayList<Square> protectingPieces;
	public ArrayList<Square> validMoves;
	public ArrayList<Square> otherValidMoves;
	public ArrayList<Square> inThreadValidMoves;

	public BoardTool(ChessBoard cBoard) {
		threatenedPieces = new ArrayList<Square>();
		threateningPieces = new ArrayList<Square>();
		validMoves = new ArrayList<Square>();
		otherValidMoves = new ArrayList<Square>();
		inThreadValidMoves = new ArrayList<Square>();
		protectedPieces = new ArrayList<Square>();
		protectingPieces = new ArrayList<Square>();
	}

	public void placePiece(Piece piece) {
		BoardCreater.cBoard.getSquare(piece.square.x, piece.square.y).piece = piece;
	}

	public void move(Piece piece, int newX, int newY) {
		BoardCreater.cBoard.setSquare(piece.square, newX, newY);
	}

	public void setDefaultBoard() {
		// White Pieces
		new piece.Rook(BoardCreater.cBoard.getSquare(0, 0), PieceColor.white);
		new piece.Knight(BoardCreater.cBoard.getSquare(1, 0), PieceColor.white);
		new piece.Bishop(BoardCreater.cBoard.getSquare(2, 0), PieceColor.white);
		new piece.Queen(BoardCreater.cBoard.getSquare(3, 0), PieceColor.white);
		new piece.King(BoardCreater.cBoard.getSquare(4, 0), PieceColor.white);
		new piece.Bishop(BoardCreater.cBoard.getSquare(5, 0), PieceColor.white);
		new piece.Knight(BoardCreater.cBoard.getSquare(6, 0), PieceColor.white);
		new piece.Rook(BoardCreater.cBoard.getSquare(7, 0), PieceColor.white);

		new piece.Pawn(BoardCreater.cBoard.getSquare(0, 1), PieceColor.white);
		new piece.Pawn(BoardCreater.cBoard.getSquare(1, 1), PieceColor.white);
		new piece.Pawn(BoardCreater.cBoard.getSquare(2, 1), PieceColor.white);
		new piece.Pawn(BoardCreater.cBoard.getSquare(3, 1), PieceColor.white);
		new piece.Pawn(BoardCreater.cBoard.getSquare(4, 1), PieceColor.white);
		new piece.Pawn(BoardCreater.cBoard.getSquare(5, 1), PieceColor.white);
		new piece.Pawn(BoardCreater.cBoard.getSquare(6, 1), PieceColor.white);
		new piece.Pawn(BoardCreater.cBoard.getSquare(7, 1), PieceColor.white);

		// Black Pieces
		new piece.Rook(BoardCreater.cBoard.getSquare(0, 7), PieceColor.black);
		new piece.Knight(BoardCreater.cBoard.getSquare(1, 7), PieceColor.black);
		new piece.Bishop(BoardCreater.cBoard.getSquare(2, 7), PieceColor.black);
		new piece.Queen(BoardCreater.cBoard.getSquare(3, 7), PieceColor.black);
		new piece.King(BoardCreater.cBoard.getSquare(4, 7), PieceColor.black);
		new piece.Bishop(BoardCreater.cBoard.getSquare(5, 7), PieceColor.black);
		new piece.Knight(BoardCreater.cBoard.getSquare(6, 7), PieceColor.black);
		new piece.Rook(BoardCreater.cBoard.getSquare(7, 7), PieceColor.black);

		new piece.Pawn(BoardCreater.cBoard.getSquare(0, 6), PieceColor.black);
		new piece.Pawn(BoardCreater.cBoard.getSquare(1, 6), PieceColor.black);
		new piece.Pawn(BoardCreater.cBoard.getSquare(2, 6), PieceColor.black);
		new piece.Pawn(BoardCreater.cBoard.getSquare(3, 6), PieceColor.black);
		new piece.Pawn(BoardCreater.cBoard.getSquare(4, 6), PieceColor.black);
		new piece.Pawn(BoardCreater.cBoard.getSquare(5, 6), PieceColor.black);
		new piece.Pawn(BoardCreater.cBoard.getSquare(6, 6), PieceColor.black);
		new piece.Pawn(BoardCreater.cBoard.getSquare(7, 6), PieceColor.black);
	}

}
