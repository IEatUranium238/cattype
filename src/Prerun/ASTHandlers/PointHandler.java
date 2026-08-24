package Prerun.ASTHandlers;

import java.util.ArrayList;

import Components.AstObj;
import Components.Memory;

public class PointHandler {
  public static AstObj pointHandler(ArrayList<String> tokens, int lineNum) throws Exception{
    tokens.removeFirst();
    
    if (tokens.size() == 0){
      throw new Exception("No point name supplied for point creation on line " + lineNum);
    }

    Memory.makeData(tokens.get(0), "INT", lineNum);

    AstObj thisAst = new AstObj("POINT", null);
    return thisAst;
  }
}
