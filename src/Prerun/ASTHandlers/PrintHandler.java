package Prerun.ASTHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.AstObj;
import Components.Utils;
import Prerun.AST;

public class PrintHandler {
  public static AstObj printHandler(ArrayList<String> tokens, int lineNum) throws Exception {
    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception("No data to print supplied on line " + lineNum);
    }

    String input = tokens.get(0);

    if (Utils.isValidFunction(input)) {
      Map<String, Object> data = new HashMap<>();
      data.put("mode", "FUN");

      data.put("data", AST.parseFunction(tokens));

      AstObj thisAst = new AstObj("PRINT", data);
      return thisAst;
    }

    if (!(input.startsWith("'") || input.startsWith("\""))) {
      throw new Exception("Invalid data type for PRINT command, expected STR on line " + lineNum);
    }

    Map<String, Object> data = new HashMap<>();
    data.put("mode", "IMD");

    data.put("data", input.substring(1, input.length() - 1));

    AstObj thisAst = new AstObj("PRINT", data);
    return thisAst;
  }
}
