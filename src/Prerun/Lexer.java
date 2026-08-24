package Prerun;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lexer {
  private static int lineNum = 1;

  private static ArrayList<String> readSeg(String line) throws Exception {
    line = line.trim();

    if (line.isEmpty() || line.startsWith("#")) {
      return null;
    }

    ArrayList<String> tokens = new ArrayList<>();
    String buildStr = "";

    boolean inString = false;
    boolean inEscape = false;
    char stringContainer = '\0';
    ArrayList<Integer> openParenPositions = new ArrayList<>();

    int curChar = 0;

    for (char c : line.toCharArray()) {
      curChar += 1;

      if (inEscape) {
        buildStr += c;
        inEscape = false;
        continue;
      }

      if (c == '\\') {
        inEscape = true;
        continue;
      }

      if (c == '"' || c == '\'') {
        if (!inString) {
          inString = true;
          stringContainer = c;
        } else if (c == stringContainer) {
          inString = false;
          stringContainer = '\0';
        }

      }

      if ((c == '(' || c == ')') && !inString) {
        if (c == '(') {
          openParenPositions.add(buildStr.length());
        } else {
          if (openParenPositions.isEmpty()) {
            throw new Exception("Unexpected closing parenthesis on " + lineNum + ":" + curChar);
          }

          openParenPositions.remove(openParenPositions.size() - 1);
        }

        if (!buildStr.isEmpty()) {
          tokens.add(buildStr);
          buildStr = "";
        }

        tokens.add(Character.toString(c));
        continue;
      }

      if (Character.isWhitespace(c) && !inString) {
        if (!buildStr.isEmpty()) {
          tokens.add(buildStr);
          buildStr = "";
        }
        continue;
      }

      buildStr += c;
    }

    if (!buildStr.isEmpty()) {
      tokens.add(buildStr);
    }

    if (inString) {
      throw new Exception("Open string on " + lineNum + ":" + line.length() + " :\n  " + line + "\n  "
          + "~".repeat(line.length()) + "^");
    }

    if (!openParenPositions.isEmpty()) {
      int pos = openParenPositions.get(openParenPositions.size() - 1);

      throw new Exception("Open parenthesis on " + lineNum + ":" + pos + "\n  " + line + "\n  " + "~".repeat(pos)
          + "^" + "~".repeat(line.length() - pos - 1) + "^");
    }

    return tokens;
  }

  private static ArrayList<String> readLine(String line) throws Exception {
    ArrayList<String> tokens = new ArrayList<>();
    line = line.trim();

    if (line.isEmpty() || line.startsWith("#")) {
      return tokens;
    }

    String[] segs = line.split(";");

    for (String seg : segs) {
      ArrayList<String> segTokens = readSeg(seg);

      if (segTokens != null) {
        tokens.addAll(segTokens);
      }
    }

    return tokens;
  }

  public static ArrayList<ArrayList<String>> readFile(String fp) throws Exception {
    ArrayList<ArrayList<String>> linesTokens = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fp))) {
      String line;

      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()){
          linesTokens.add(readLine(line));
        }
      }

      return linesTokens;
    } catch (IOException e) {
      System.out.println("Failed to read file at path:\n" + fp + "\nError:\n" + e);
      return null;
    }
  }
}