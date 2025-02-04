package game_ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import board.ChessBoard;
import move_processing.MoveParser;
import board.BoardCreater;
import obj.PieceColor;
import piece.Piece;

@SuppressWarnings("serial")
public class BoardUI extends JFrame {

	public static final int FRAME_SIZE = ChessBoard.BOARD_SIZE * 64;
	JButton[][] squares;
	JFrame frame;
	private static final HashMap<String, ImageIcon> imageCache = new HashMap<String, ImageIcon>();
	boolean isPressed = false;
    int r = 0;
	MovesWindow cFrame;
	int btnX;
	int btnY;
	int w = 0;

	public BoardUI() throws IOException {
		frame = new JFrame();
		frame.setTitle("Chess Game Reviewer");
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		frame.setLayout(new GridLayout(ChessBoard.BOARD_SIZE, ChessBoard.BOARD_SIZE));
		cFrame = new MovesWindow();

		squares = new JButton[ChessBoard.BOARD_SIZE][ChessBoard.BOARD_SIZE];
		startGame();
	}

	public void startGame() throws IOException {
		for (int i = ChessBoard.BOARD_SIZE - 1; i >= 0; i--) {
			for (int j = 0; j < ChessBoard.BOARD_SIZE; j++) {
				squares[i][j] = new JButton();
				squares[i][j].setBorderPainted(false);

				setSquareColor(i, j);
				setPieceInSquares(i, j);

				frame.add(squares[i][j]);
			}
		}
		
		windowActionListener();
		componentListener();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void refreshBoard() throws IOException {
		frame.getContentPane().removeAll();

		for (int i = ChessBoard.BOARD_SIZE - 1; i >= 0; i--) {
			for (int j = 0; j < ChessBoard.BOARD_SIZE; j++) {
				squares[i][j] = new JButton();
				squares[i][j].setBorderPainted(false);

				setSquareColor(i, j);
				setPieceInSquares(i, j);

				frame.add(squares[i][j]);
			}
		}
		
		frame.revalidate();
		frame.repaint();
	}
	
	private void windowActionListener(){
		MoveParser rMoveFile = new MoveParser();

		cFrame.rightButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rMoveFile.addAndPrintMove(w);
				w = w + 1;
				try {
					refreshBoard();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public void componentListener() {

		frame.setAlwaysOnTop(true);
		
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				cFrame.moveWindow.setAlwaysOnTop(true);
				
				int centerX = frame.getX();
				int centerY = frame.getY();
				int WY = centerY + cFrame.moveWindow.getWidth();
				
				cFrame.moveWindow.setLocation(centerX, WY);
			}
		});
	}

	private void setPieceInSquares(int i, int j) throws IOException {
		if (BoardCreater.cBoard.getSquare(j, i).piece != null) {
			squares[i][j].setIcon(setImagePath(BoardCreater.cBoard.getSquare(j, i).piece));

		}
	}

	void setSquareColor(int i, int j) {
		if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
			squares[i][j].setBackground(new Color(181, 136, 99));
		} else {
			squares[i][j].setBackground(new Color(240, 217, 181));
		}
	}

	ImageIcon setFrameImage(String path) throws IOException {
		if (!imageCache.containsKey(path)) {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			ImageIcon icon = new ImageIcon(image);
			imageCache.put(path, icon);
		}
		return imageCache.get(path);
	}

	ImageIcon setImagePath(Piece piece) throws IOException {
		String path = getPieceImgPath(piece);
		if (!imageCache.containsKey(path)) {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			ImageIcon icon = new ImageIcon(image);
			imageCache.put(path, icon);
		}
		return imageCache.get(path);
	}

	String getPieceImgPath(Piece piece) {
		String color = piece.color == PieceColor.white ? "white" : "black";
		String type = piece.type + "";
		return "/piece_pic/" + color + "_" + type + ".png";
	}

}
