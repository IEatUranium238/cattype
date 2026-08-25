package Prerun.ASTHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.AstObj;
import Components.Utils;
import Prerun.AST;

public class DefHandler {
  public static AstObj defHandler(ArrayList<String> tokens, int lineNum) throws Exception {
    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception("No value name supplied for value creation on line " + lineNum);
    }

    if (!Utils.isValidValue(tokens.get(0))) {
      throw new Exception("Invalid value name '" + tokens.get(0) + "' on line " + lineNum);
    }

    String valName = tokens.get(0);
    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception(
          "Expected AS type defenition for value '" + valName + "' but found nothing on line " + lineNum);
    }

    if (!tokens.get(0).equals("AS")) {
      throw new Exception(
          "Expected AS type defenition for value '" + valName + "' but found '" + tokens.get(0) + "' on line "
              + lineNum);
    }

    tokens.removeFirst();

    if (tokens.size() == 0) {
      throw new Exception("No value data type supplied for value creation on line " + lineNum);
    }

    String valType = tokens.get(0);
    tokens.removeFirst();

    if (!(valType.equals("INT")) && !(valType.equals("STR"))) {
      throw new Exception("Invalid data type for value named '" + valName + "' on line " + lineNum);
    }

    if (tokens.size() == 0) {
      throw new Exception("No value data supplied for value creation on line " + lineNum);
    }

    String input = tokens.get(0);

    if (Utils.isValidFunction(input)) {
      Map<String, Object> data = new HashMap<>();
      data.put("mode", "FUN");
      data.put("dataType", valType);
      data.put("dataName", valName);

      data.put("data", AST.parseFunction(tokens));

      AstObj thisAst = new AstObj("DEFVALUE", data);
      return thisAst;
    }

    if (!(input.startsWith("\"") || input.startsWith("'")) && input.matches("\\d+\\.\\d+")) {
      throw new Exception("Tried to use floating point number for value named '" + valName + "' on line " + lineNum);
    }

    if (valType.equals("INT") && input.matches(".*[a-zA-Z'\"].*")) {
      throw new Exception("Tried create integer value named '" + valName + "' with string on line " + lineNum);
    }

    if (input.matches("\\d+") && valType.equals("STR")) {
      throw new Exception("Tried create string value named '" + valName + "' with integer on line " + lineNum);
    }

    if (!(input.startsWith("\"") || input.startsWith("'")) && valType.equals("STR")) {
      throw new Exception("Tried to use string without quoutes for value named '" + valName + "' on line " + lineNum);
    }

    if (valType.equals("STR")){
      input = input.substring(1, input.length() - 1);
    }
    
    Map<String, Object> data = new HashMap<>();
    data.put("mode", "IMD");
    data.put("dataType", valType);
    data.put("dataName", valName);
    data.put("data", input);

    AstObj thisAst = new AstObj("DEFVALUE", data);
    return thisAst;
  }
}
