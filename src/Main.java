import java.util.ArrayList;

import Prerun.AST;
import Components.AstObj;
import Prerun.Lexer;
import Runtime.Executor;

public class Main {
  public static void main(String[] args) {
    try {
      ArrayList<ArrayList<String>> lines = Lexer.readFile("./testbed/test.cat");

      int curLine = 0;
      ArrayList<AstObj> lineAst = new ArrayList<>();

      for (ArrayList<String> l : lines) {
        curLine += 1;

        AstObj ast = AST.makeAst(l, curLine);
        lineAst.add(ast);
      }

      curLine = 0;

      for (AstObj astObj : lineAst) {
        curLine += 1;
        Executor.executeAST(astObj, curLine);
      }

    } catch (Exception e) {
      System.out.println("Failed to run cattype program!\nError:\n" + e.getMessage());
      System.err.println("-- DEBUG TRACE --");
      e.printStackTrace();
    }
  }
}