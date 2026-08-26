package Prerun.ASTHandlers;

import java.util.ArrayList;

import Components.AstObj;

public class ExplodeHandler {
  public static AstObj explodeHandler(ArrayList<String> tokens, int lineNum) throws Exception{
    tokens.removeFirst();

    AstObj thisAst = new AstObj("EXPLODE", null);
    return thisAst;
  }
}
