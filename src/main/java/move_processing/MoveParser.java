package move_processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import obj.Move;
import obj.PieceColor;
import obj.PieceType;

public class MoveParser {

	public static ArrayList<String> allMovesString = new ArrayList<>();

	public static ArrayList<PieceType> allMovesType = new ArrayList<PieceType>();

	public static ArrayList<PieceColor> allMovesColor = new ArrayList<PieceColor>();

	public static ArrayList<Move> allMoves = new ArrayList<Move>();

	public MoveParser() {
		String fileName = "moves.txt";
		String moves = "";

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				moves = line;
			}
		} catch (IOException e) {
			System.err.println("An error occurred while reading the file: " + e.getMessage());
		}

		int lastIndex = 0;

		for (int i = 0; i < moves.length(); i++) {
			if (Character.isDigit(moves.charAt(i)) || moves.charAt(i) == '.') {
				continue;
			}

			if (moves.charAt(i) == ' ') {
				String segment = moves.substring(lastIndex, i).trim();
				if (!segment.isEmpty()) {
					allMovesString.add(segment);
				}
				lastIndex = i + 1;
			}
		}

		String lastSegment = moves.substring(lastIndex).trim();
		if (!lastSegment.isEmpty()) {
			allMovesString.add(lastSegment);
		}

		int q = 1;
		Iterator<String> iterator = allMovesString.iterator();
		while (iterator.hasNext()) {
			String opt = iterator.next();
			if (opt.equals(q + ".")) {
				iterator.remove();
				q++;
			}
		}
	}

	public void addAndPrintMove(int i) {
		try {
			char c = allMovesString.get(i).charAt(0);
			PieceType type;
			switch (c) {
			case 'R':
				type = PieceType.rook;
				break;
			case 'N':
				type = PieceType.knight;
				break;
			case 'B':
				type = PieceType.bishop;
				break;
			case 'Q':
				type = PieceType.queen;
				break;
			case 'K':
				type = PieceType.king;
				break;
			case 'O':
				if (allMovesString.get(i).equals("O-O-O")) {
					type = PieceType.long_rook;
				} else {
					type = PieceType.short_rook;
				}
				break;
			default:
				type = PieceType.pawn;
				break;
			}
			allMovesType.add(type);

			if (i % 2 == 0) {
				allMovesColor.add(PieceColor.white);
			} else {
				allMovesColor.add(PieceColor.black);
			}

			Move move = new Move(allMovesString.get(i), allMovesType.get(i), allMovesColor.get(i));

			allMoves.add(move);

			System.out.println("key: " + allMoves.get(i).move + " |type: " + allMoves.get(i).type + " |color: "
					+ allMoves.get(i).color);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Game Over");
		}
	}

}