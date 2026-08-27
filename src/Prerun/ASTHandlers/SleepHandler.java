package Prerun.ASTHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.AstObj;
import Components.Utils;
import Prerun.AST;

public class SleepHandler {
  public static AstObj sleepHandler(ArrayList<String> tokens, int lineNum) throws Exception {
    tokens.removeFirst();
    Map<String, Object> data = new HashMap<>();

    if (tokens.size() == 0) {
      throw new Exception("No input supplied for sleep time on line " + lineNum);
    }

    String input = tokens.get(0);

    if (Utils.isValidFunction(input)) {
      data.put("sleepMode", "FUN");
      data.put("sleepData", AST.parseFunction(tokens));
    } else {
      if (input.matches(".*[a-zA-Z'\"].*")) {
        throw new Exception("SLEEP doesn't accept strings on line " + lineNum);
      }

      int sleepTime;

      try {
        sleepTime = Integer.parseInt(input);
      } catch (Exception e) {
        throw new Exception("SLEEP failed to parse sleep time '" + input + "' on line " + lineNum);
      }

      data.put("sleepMode", "IMD");
      data.put("sleepData", sleepTime);
    }
    
    AstObj thisAst = new AstObj("SLEEP", data);
    return thisAst;
  }
}
