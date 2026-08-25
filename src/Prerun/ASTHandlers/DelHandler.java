package Prerun.ASTHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.AstObj;
import Components.Utils;

public class DelHandler {
  public static AstObj delHandler(ArrayList<String> tokens, int lineNum) throws Exception{
    tokens.removeFirst();
    
    if (tokens.size() == 0){
      throw new Exception("No point name supplied for point creation on line " + lineNum);
    }

    if (!Utils.isValidValue(tokens.get(0))){
      throw new Exception("Invalid name to delete on line " + lineNum);
    }


    Map<String, Object> data = new HashMap<>();
    data.put("dataName", tokens.get(0));

    AstObj thisAst = new AstObj("DEL", data);
    return thisAst;
  }
}
