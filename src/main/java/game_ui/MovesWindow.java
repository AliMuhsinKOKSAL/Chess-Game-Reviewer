package game_ui;

import javax.swing.*;

import java.awt.*;

public class MovesWindow {

	public JWindow moveWindow;
	public JButton rightButton;

	public MovesWindow() {

		moveWindow = new JWindow();
		moveWindow.setSize(BoardUI.FRAME_SIZE, 100);
		moveWindow.setLayout(new FlowLayout());

		moveWindow.setLayout(new GridLayout(1, 2));

		rightButton = new JButton(">>");

		rightButton.setSize(BoardUI.FRAME_SIZE, 125);

		Font buttonFont = new Font("Arial", Font.BOLD, 45);
		rightButton.setFont(buttonFont);

		moveWindow.add(rightButton);

		moveWindow.setVisible(true);
	}
}
