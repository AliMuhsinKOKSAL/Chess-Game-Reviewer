package piece;

import board.ChessBoard;
import board.BoardCreater;
import obj.PieceColor;
import obj.PieceType;
import obj.Square;
import option.OpType;
import option.Option;

public class Pawn extends Piece {

	public static boolean isValidMoveOn = false;
	
	public Pawn(Square square, PieceColor cColor) {
		super(BoardCreater.cBoard.getSquare(square.x, square.y), PieceType.pawn, cColor);
		BoardCreater.cBoard.boardTool.placePiece(this);
	}

	@Override
	public void getMovement() {
		options.clear();
		if (color == PieceColor.white) {
			if (square.y == 1 && BoardCreater.cBoard.getSquare(square.x, square.y + 1).piece == null)
				if (square.y + 2 < ChessBoard.BOARD_SIZE) {
					Option opt = createPawn(BoardCreater.cBoard.getSquare(square.x, square.y + 2), color);
					options.add(opt);
				}
			if(square.y == 7) {
				pawnChange(this, PieceType.queen);
			}
			
			if (square.y + 1 < ChessBoard.BOARD_SIZE) {
				Option opt = createPawn(BoardCreater.cBoard.getSquare(square.x, square.y + 1), color);
				options.add(opt);
			}

			if (square.x + 1 < ChessBoard.BOARD_SIZE && square.y + 1 < ChessBoard.BOARD_SIZE) {
				if (isFullBishop(BoardCreater.cBoard.getSquare(square.x + 1, square.y + 1))) {
					options.add(create(BoardCreater.cBoard.getSquare(square.x + 1, square.y + 1), color));
				}
			}
			if (square.x - 1 >= 0 && square.y + 1 < ChessBoard.BOARD_SIZE) {
				if (isFullBishop(BoardCreater.cBoard.getSquare(square.x - 1, square.y + 1))) {
					options.add(create(BoardCreater.cBoard.getSquare(square.x - 1, square.y + 1), color));
				}
			}
			// left - right+
		} else {
			if (square.y == 6 && BoardCreater.cBoard.getSquare(square.x, square.y - 1).piece == null)
				if (square.y - 2 >= 0) {
					Option opt = createPawn(BoardCreater.cBoard.getSquare(square.x, square.y - 2), color);
					options.add(opt);
				}
			
			if(square.y == 0) {
				pawnChange(this, PieceType.queen);
			}
			if (square.y - 1 >= 0) {
				Option opt = createPawn(BoardCreater.cBoard.getSquare(square.x, square.y - 1), color);
				options.add(opt);
			}
			if (square.x - 1 >= 0 && square.y - 1 >= 0) {
				if (isFullBishop(BoardCreater.cBoard.getSquare(square.x - 1, square.y - 1))) {
					options.add(create(BoardCreater.cBoard.getSquare(square.x - 1, square.y - 1), color));
				}
			}
			if (square.x + 1 < ChessBoard.BOARD_SIZE && square.y - 1 >= 0) {
				if (isFullBishop(BoardCreater.cBoard.getSquare(square.x + 1, square.y - 1))) {
					options.add(create(BoardCreater.cBoard.getSquare(square.x + 1, square.y - 1), color));
				}
			}
		}
	}

	boolean isFullBishop(Square xs) {
		PieceColor px = (color == PieceColor.white) ? PieceColor.black : PieceColor.white;
		if (xs.piece != null) {
			return xs.piece.color == px;
		}else if(isValidMoveOn){
			return true;
		}else {
			return false;
		}
	}

	Option createPawn(Square xsquare, PieceColor pieceColor) {
		OpType xOpType;

		if (xsquare.piece == null) {
			xOpType = OpType.movedTo;
		} else {
			xOpType = OpType.notMovedTo;
		}
		return new Option(xsquare, xOpType);
	}
	
	Piece pawnChange(Piece piece, PieceType type) {
		Piece newPiece = null;
		switch (type) {
		case rook:
			newPiece = new Rook(piece.square, piece.color);
			break;
		case knight:
			newPiece = new Knight(piece.square, piece.color);
			break;
		case bishop:
			newPiece = new Bishop(piece.square, piece.color);
			break;
		case queen:
			newPiece = new Queen(piece.square, piece.color);
			break;
		default:
			throw new NullPointerException("Geçersiz taş tipi");
		}
		
		
		return BoardCreater.cBoard.getSquare(piece.square.x, piece.square.y).piece = newPiece;
	}
}
