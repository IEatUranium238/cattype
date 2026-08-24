package Prerun.ASTHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.AstObj;
import Components.Utils;
import Prerun.AST;

public class SetHandler {
  public static AstObj setHandler(ArrayList<String> tokens, int lineNum) throws Exception {
    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception("No value name supplied for value to be edited on line " + lineNum);
    }

    if (!Utils.isValidValue(tokens.get(0))) {
      throw new Exception("Invalid value name '" + tokens.get(0) + "' to change on line " + lineNum);
    }

    String valName = tokens.get(0);
    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception(
          "Expected TO for value change '" + valName + "' but found nothing on line " + lineNum);
    }

    if (!tokens.get(0).equals("TO")) {
      throw new Exception(
          "Expected TO for value change '" + valName + "' but found '" + tokens.get(0) + "' on line "
              + lineNum);
    }

    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception("No value data supplied for value creation on line " + lineNum);
    }

    String input = tokens.get(0);

    if (Utils.isValidFunction(input)) {
      Map<String, Object> data = new HashMap<>();
      data.put("mode", "FUN");
      data.put("dataName", valName);

      data.put("data", AST.parseFunction(tokens));

      AstObj thisAst = new AstObj("SETVALUE", data);
      return thisAst;
    }
    
    Map<String, Object> data = new HashMap<>();
    data.put("mode", "IMD");
    data.put("dataName", valName);
    data.put("data", input);

    AstObj thisAst = new AstObj("SETVALUE", data);
    return thisAst;
  }
}
