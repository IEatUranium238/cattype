package Prerun.ASTHandlers;

import java.util.ArrayList;

import Components.AstObj;
import Components.Memory;
import Components.Utils;

public class PointHandler {
  public static AstObj pointHandler(ArrayList<String> tokens, int lineNum) throws Exception{
    tokens.removeFirst();
    
    if (tokens.size() == 0){
      throw new Exception("No point name supplied for deletion on line " + lineNum);
    }

    if (!Utils.isValidValue(tokens.get(0))){
      throw new Exception("Invalid point name on line " + lineNum);
    }

    char stat = Memory.makeData(tokens.get(0), "INT", lineNum);

    if (stat == 'e'){
      throw new Exception("Point name has already been reserved by other point or value on line " + lineNum);
    }

    AstObj thisAst = new AstObj("POINT", null);
    return thisAst;
  }
}
