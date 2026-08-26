package Runtime;

import Components.AstObj;

import Runtime.Commands.*;

public class Executor {
  public static void executeAST(AstObj ast, int lineNum) throws Exception {
    switch (ast.type) {
      case "DEFVALUE":
        DefHandler.defHandler(ast, lineNum);
        break;
      case "SETVALUE":
        SetHandler.setHandler(ast, lineNum);
        break;
      case "PRINT":
        PrintHandler.printHandler(ast, lineNum);
        break;
      case "POINT":
        break;
      case "NONE":
        break;
      case "DEL":
        DelHandler.delHandler(ast, lineNum);
        break;
      case "INPUT":
        InHandler.inHandler(ast, lineNum);
        break;
      case "GOTO":
        GotoHandler.gotoHandler(ast, lineNum);
        break;
      case "EXPLODE":
        ExplodeHandler.explodeHandler(ast, lineNum);
        break;
      default:
        throw new Exception("Unknown AST type: " + ast.type);
    }
  }
}
