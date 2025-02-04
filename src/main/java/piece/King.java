package piece;

import board.ChessBoard;

import board.BoardCreater;
import obj.PieceColor;
import obj.PieceType;
import obj.Square;
import option.Option;

public class King extends Piece {

	public King(Square square, PieceColor cColor) {
		super(BoardCreater.cBoard.getSquare(square.x, square.y), PieceType.king, cColor);
		BoardCreater.cBoard.boardTool.placePiece(this);
	}

	@Override
	public void getMovement() {
		options.clear();
		int[][] movei = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };

		for (int[] move : movei) {
			int newx = square.x + move[0];
			int newy = square.y + move[1];
			if (newx >= 0 && newx < ChessBoard.BOARD_SIZE && newy >= 0 && newy < ChessBoard.BOARD_SIZE) {
				Option op = create(BoardCreater.cBoard.getSquare(newx, newy), color);
				options.add(op);
			}
		}
	}
}
