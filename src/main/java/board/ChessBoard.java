package board;

import obj.Square;
import piece.Piece;
import tool.BoardTool;

public class ChessBoard {
    public static final int BOARD_SIZE = 8;
    public Square[][] board;
    public BoardTool boardTool = new BoardTool(this);

    public ChessBoard() {
        this.board = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Square(i, j);
            }
        }
    }
    
    public Square getSquare(int x, int y) {
		return board[x][y];
	}
    
    public void setSquare(Square square, int newx, int newy) {
    	Piece piece = square.piece;
    	
    	square.piece = null;
    	
    	Square targetSquare = getSquare(newx, newy);
		targetSquare.piece = piece;
		piece.square = targetSquare;
    }    
}
