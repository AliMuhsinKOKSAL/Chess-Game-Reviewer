package write_move;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WriteMove {
	// if you want to take a game from chessgames.com, enter its URL into the URL.
    public static void main(String args[]) {
        try {
            String url = "https://www.chessgames.com/nodejs/game/viewGamePGN?text=1&gid=1545443";
            Document doc = Jsoup.connect(url).get();

            String pgnText = doc.body().text();

            Pattern movePattern = Pattern.compile("(\\d+\\.\\s+[^\\[]+)");
            Matcher moveMatcher = movePattern.matcher(pgnText);

            StringBuilder moves = new StringBuilder();

            while (moveMatcher.find()) {
                String move = moveMatcher.group(1);
                moves.append(move).append("\n");
            }

            String fileName = "moves.txt";
            File file = new File(fileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(moves.toString().trim());
                System.out.println("Moves were written to the file.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}