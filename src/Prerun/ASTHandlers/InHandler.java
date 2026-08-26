package Prerun.ASTHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.AstObj;
import Components.Utils;

public class InHandler {
  public static AstObj inHandler(ArrayList<String> tokens, int lineNum) throws Exception{
    tokens.removeFirst();
    
    if (tokens.size() == 0){
      throw new Exception("No value name supplied for data input on line " + lineNum);
    }

    if (!Utils.isValidValue(tokens.get(0))){
      throw new Exception("Invalid name to input to on line " + lineNum);
    }

    Map<String, Object> data = new HashMap<>();
    data.put("dataName", tokens.get(0));

    AstObj thisAst = new AstObj("INPUT", data);
    return thisAst;
  }
}
