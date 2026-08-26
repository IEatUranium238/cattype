package Prerun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.Utils;
import Components.AstObj;

import Prerun.ASTHandlers.*;

public class AST {

  public static Map<String, Object> parseFunction(ArrayList<String> tokens) {
    String thisFunName = tokens.get(0);
    tokens.removeFirst();

    Map<String, Object> data = new HashMap<>();
    data.put("type", "FUN");
    data.put("name", thisFunName.substring(0, thisFunName.length() - 1));

    ArrayList<Object> params = new ArrayList<>();
    data.put("content", params);

    tokens.removeFirst();

    while (!tokens.isEmpty() && !tokens.get(0).equals(")")) {
      if (Utils.isValidFunction(tokens.get(0))) {
        params.add(parseFunction(tokens));
      } else {
        Map<String, Object> paramData = new HashMap<>();
        paramData.put("type", "PAR");
        paramData.put("content", tokens.get(0));

        params.add(paramData);
        tokens.removeFirst();
      }
    }

    if (!tokens.isEmpty() && tokens.get(0).equals(")")) {
      tokens.removeFirst();
    }

    return data;
  }

  public static AstObj makeAst(ArrayList<String> tokens, int lineNum) throws Exception {
    if (tokens.size() == 0) {
      AstObj thisAst = new AstObj("NONE", null);
      return thisAst;
    }

    String cmd = tokens.get(0);

    switch (cmd) {
      case "DEF":
        return DefHandler.defHandler(tokens, lineNum);
      case "PRINT":
        return PrintHandler.printHandler(tokens, lineNum);
      case "POINT":
        return PointHandler.pointHandler(tokens, lineNum);
      case "SET":
        return SetHandler.setHandler(tokens, lineNum);
      case "DEL":
        return DelHandler.delHandler(tokens, lineNum);
      case "IN":
        return InHandler.inHandler(tokens, lineNum);
      case "GOTO":
        return GotoHandler.gotoHandler(tokens, lineNum);
      case "EXPLODE":
        return ExplodeHandler.explodeHandler(tokens, lineNum);
      default:
        throw new Exception("Unknown command on line " + lineNum + ": " + cmd);
    }
  }
}
