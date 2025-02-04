package piece;

import board.ChessBoard;
import board.BoardCreater;
import obj.PieceColor;
import obj.PieceType;
import obj.Square;
import option.OpType;
import option.Option;

public class Rook extends Piece {

	public Rook(Square square, PieceColor cColor) {
		super(BoardCreater.cBoard.getSquare(square.x, square.y), PieceType.rook, cColor);
		BoardCreater.cBoard.boardTool.placePiece(this);
	}

	@Override
	public void getMovement() {
		options.clear();
		// yatay sağ
		for (int i = square.x + 1; i < ChessBoard.BOARD_SIZE; i++) {

			Option opt = create(BoardCreater.cBoard.getSquare(i, square.y), color);
			if (opt.opType != OpType.take && opt.opType != OpType.notTake) {
				options.add(opt);
			} else {
				options.add(opt);
				break;
			}
		}

		// yatay sol
		for (int i = square.x - 1; i >= 0; i--) {
			Option opt = create(BoardCreater.cBoard.getSquare(i, square.y), color);
			if (opt.opType != OpType.take && opt.opType != OpType.notTake) {
				options.add(opt);
			} else {
				options.add(opt);
				break;
			}
		}

		// dikey sağ
		for (int i = square.y + 1; i < ChessBoard.BOARD_SIZE; i++) {
			Option opt = create(BoardCreater.cBoard.getSquare(square.x, i), color);
			if (opt.opType != OpType.take && opt.opType != OpType.notTake) {
				options.add(opt);
			} else {
				options.add(opt);
				break;
			}
		}

		// dikey sol
		for (int i = square.y - 1; i >= 0; i--) {
			Option opt = create(BoardCreater.cBoard.getSquare(square.x, i), color);
			if (opt.opType != OpType.take && opt.opType != OpType.notTake) {
				options.add(opt);
			} else {
				options.add(opt);
				break;
			}
		}
	}
}
