package main;

import java.io.IOException;

import board.BoardCreater;
import game_ui.BoardUI;
public class Main {
	// you can change the game being played with 'write_move/WriteMove.class' or write directly to moves.txt .
	public static void main(String[] args) throws IOException {
		BoardCreater.cBoard.boardTool.setDefaultBoard();
		
		new BoardUI();
	}
}
