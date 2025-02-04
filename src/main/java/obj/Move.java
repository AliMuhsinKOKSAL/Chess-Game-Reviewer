package obj;

import java.util.ArrayList;

import board.BoardCreater;
import board.ChessBoard;
import option.Option;
import piece.Piece;

public class Move {

	public String move;
	public PieceType type;
	public PieceColor color;

	public Move(String move, PieceType type, PieceColor color) {
		this.move = changeMoveFormat(move);
		this.type = type;
		this.color = color;
		moveBoard(changeMoveFormat(move), type, color, move);
	}

	private void moveBoard(String moves, PieceType types, PieceColor colors, String notChangeMove) {
		if (types == PieceType.short_rook) {
			if (colors == PieceColor.white) {
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(7, 0).piece, 5, 0);
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(4, 0).piece, 6, 0);
			} else {
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(7, 7).piece, 5, 7);
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(4, 7).piece, 6, 7);
			}
		} else if (types == PieceType.long_rook) {
			if (colors == PieceColor.white) {
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(0, 0).piece, 3, 0);
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(4, 0).piece, 2, 0);
			} else {
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(0, 7).piece, 3, 7);
				BoardCreater.cBoard.boardTool.move(BoardCreater.cBoard.getSquare(4, 7).piece, 2, 7);
			}
		} else {
			ArrayList<Integer> mave = new ArrayList<Integer>();
			StringBuilder numberBuilder = new StringBuilder();
			String lastValidNumber = "";

			for (int i = 0; i < moves.length(); i++) {
				char currentChar = moves.charAt(i);

				if (Character.isDigit(currentChar)) {
					numberBuilder.append(currentChar);
				} else {
					if (numberBuilder.length() > 0) {
						lastValidNumber = numberBuilder.toString();
					}
					numberBuilder.setLength(0);
				}
			}

			if (numberBuilder.length() > 0) {
				lastValidNumber = numberBuilder.toString();
			}

			if (lastValidNumber.length() >= 2) {
				String lastTwoDigits = lastValidNumber.substring(lastValidNumber.length() - 2);

				for (int j = 0; j < lastTwoDigits.length(); j++) {
					mave.add(Character.getNumericValue(lastTwoDigits.charAt(j)));
				}
			}

			try {
				BoardCreater.cBoard.boardTool.move(findPiece(changeMoveFormat(moves), types, colors, notChangeMove),
						mave.get(0) - 1, mave.get(1) - 1);
			} catch (Exception e) {
				System.err.println(findPiece(changeMoveFormat(moves), types, colors, notChangeMove));
			}
		}
	}

	private Piece findPiece(String moves, PieceType types, PieceColor colors, String notChangeMove) {

		ArrayList<Integer> mave = new ArrayList<Integer>(); // Sadece sondan iki rakam
		ArrayList<String> allNumbers = new ArrayList<String>(); // Tespit edilen tüm sayılar
		ArrayList<Integer> separatedDigits = new ArrayList<Integer>(); // Sayıların her bir rakamı
		StringBuilder numberBuilder = new StringBuilder();
		String lastValidNumber = "";

		for (int i = 0; i < moves.length(); i++) {
			char currentChar = moves.charAt(i);

			if (Character.isDigit(currentChar)) {
				numberBuilder.append(currentChar);
			} else {
				if (numberBuilder.length() > 0) {
					allNumbers.add(numberBuilder.toString());

					for (int j = 0; j < numberBuilder.length(); j++) {
						separatedDigits.add(Character.getNumericValue(numberBuilder.charAt(j)));
					}

					lastValidNumber = numberBuilder.toString();
				}
				numberBuilder.setLength(0);
			}
		}

		if (numberBuilder.length() > 0) {
			allNumbers.add(numberBuilder.toString());
			for (int j = 0; j < numberBuilder.length(); j++) {
				separatedDigits.add(Character.getNumericValue(numberBuilder.charAt(j)));
			}
			lastValidNumber = numberBuilder.toString();
		}

		if (lastValidNumber.length() >= 2) {
			String lastTwoDigits = lastValidNumber.substring(lastValidNumber.length() - 2);

			for (int j = 0; j < lastTwoDigits.length(); j++) {
				mave.add(Character.getNumericValue(lastTwoDigits.charAt(j)));
			}

			separatedDigits.remove(separatedDigits.size() - 1);
			separatedDigits.remove(separatedDigits.size() - 1);
		}

		int x = 0;
		int y = 0;

		for (int i = 0; i < ChessBoard.BOARD_SIZE; i++) {
			for (int j = 0; j < ChessBoard.BOARD_SIZE; j++) {
				if (BoardCreater.cBoard.getSquare(i, j).piece != null) {
					for (Option opt : BoardCreater.cBoard.getSquare(i, j).piece.getOptions()) {
						if (opt.xsqu.equals(BoardCreater.cBoard.getSquare(mave.get(0) - 1, mave.get(1) - 1))) {
							if (BoardCreater.cBoard.getSquare(i, j).piece.type == types) {
								if (BoardCreater.cBoard.getSquare(i, j).piece.color == colors) {
									x = i;
									y = j;
								}
							}
						}
					}
				}
			}
		}
		if (separatedDigits.size() == 1) {
			if (notChangeMove.length() > 1) {
				char ch = notChangeMove.charAt(1);
				if(types == PieceType.pawn) {
					ch = notChangeMove.charAt(0);
				}

				if (ch >= 'a' && ch <= 'h') {
					x = separatedDigits.get(0) - 1;
					for (int j = 0; j < ChessBoard.BOARD_SIZE; j++) {
						if (BoardCreater.cBoard.getSquare(x, j).piece != null) {
							for (Option opt : BoardCreater.cBoard.getSquare(x, j).piece.getOptions()) {
								if (opt.xsqu.equals(BoardCreater.cBoard.getSquare(mave.get(0) - 1, mave.get(1) - 1))) {
									if (BoardCreater.cBoard.getSquare(x, j).piece.type == types) {
										if (BoardCreater.cBoard.getSquare(x, j).piece.color == colors) {
											y = j;
										}
									}
								}
							}
						}
					}
				}else {
					y = separatedDigits.get(0) - 1;
					for (int i = 0; i < ChessBoard.BOARD_SIZE; i++) {
						if (BoardCreater.cBoard.getSquare(i, y).piece != null) {
							for (Option opt : BoardCreater.cBoard.getSquare(i, y).piece.getOptions()) {
								if (opt.xsqu.equals(BoardCreater.cBoard.getSquare(mave.get(0) - 1, mave.get(1) - 1))) {
									if (BoardCreater.cBoard.getSquare(i, y).piece.type == types) {
										if (BoardCreater.cBoard.getSquare(i, y).piece.color == colors) {
											x = i;
										}
									}
								}
							}
						}
					}
				}
				System.out.println("x: " + x + " y: " + y);
			}
		} else if (separatedDigits.size() == 2) {
			x = separatedDigits.get(0) - 1;
			y = separatedDigits.get(1) - 1;
		}
		return BoardCreater.cBoard.getSquare(x, y).piece;
	}

	public String changeMoveFormat(String moves) {
		StringBuilder modifiedText = new StringBuilder();

		for (char ch : moves.toCharArray()) {
			switch (ch) {
			case 'a':
				modifiedText.append('1');
				break;
			case 'b':
				modifiedText.append('2');
				break;
			case 'c':
				modifiedText.append('3');
				break;
			case 'd':
				modifiedText.append('4');
				break;
			case 'e':
				modifiedText.append('5');
				break;
			case 'f':
				modifiedText.append('6');
				break;
			case 'g':
				modifiedText.append('7');
				break;
			case 'h':
				modifiedText.append('8');
				break;
			default:
				modifiedText.append(ch);
				break;
			}
		}
		return modifiedText.toString();
	}

	public void addMove(ArrayList<Move> moves) {
		moves.add(new Move(move, type, color));
	}

	public String toStringMove() {
		return "Move: " + move + ", Type: " + type + ", Color: " + color;
	}

}
