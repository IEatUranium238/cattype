package Prerun.ASTHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.AstObj;
import Components.Utils;
import Prerun.AST;

public class GotoHandler {
  public static AstObj gotoHandler(ArrayList<String> tokens, int lineNum) throws Exception {
    tokens.removeFirst();
    Map<String, Object> data = new HashMap<>();

    if (tokens.size() == 0) {
      throw new Exception("No input supplied for goto adress on line " + lineNum);
    }

    String input = tokens.get(0);

    if (Utils.isValidFunction(input)) {
      data.put("addressMode", "FUN");
      data.put("addressData", AST.parseFunction(tokens));
    } else {
      if (input.matches(".*[a-zA-Z'\"].*")) {
        throw new Exception("GOTO doesn't accept strings on line " + lineNum);
      }

      int gotoline;

      try {
        gotoline = Integer.parseInt(input);
      } catch (Exception e) {
        throw new Exception("GOTO failed to parse line number '" + input + "' on line " + lineNum);
      }

      data.put("addressMode", "IMD");
      data.put("addressData", gotoline);
    }
    data.put("hasCondition", false);

    while (tokens.size() != 0) {
      if (tokens.get(0).equals("ON")){
        break;
      }
      tokens.removeFirst();
    }

    if (tokens.size() == 0) {
      AstObj thisAst = new AstObj("GOTO", data);
      return thisAst;
    }

    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception("No input supplied for goto condition on line " + lineNum);
    }

    input = tokens.get(0);
    data.replace("hasCondition", true);

    if (Utils.isValidFunction(input)) {
      data.put("conditionMode", "FUN");
      data.put("conditionData", AST.parseFunction(tokens));
    } else {
      if (input.matches(".*[a-zA-Z'\"].*")) {
        throw new Exception("GOTO doesn't accept strings on line " + lineNum);
      }

      int gotoline;

      try {
        gotoline = Integer.parseInt(input);
      } catch (Exception e) {
        throw new Exception("GOTO failed to parse line number '" + input + "' on line " + lineNum);
      }

      data.put("conditionMode", "IMD");
      data.put("conditionData", gotoline);
    }
    
    AstObj thisAst = new AstObj("GOTO", data);
    return thisAst;
  }
}
