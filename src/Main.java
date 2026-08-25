import java.util.ArrayList;

import Prerun.AST;
import Components.AstObj;
import Prerun.Lexer;
import Runtime.Executor;

public class Main {
  public static boolean working = true;

  public static void main(String[] args) {
    try {
      if (args.length == 0) {
        System.out.println("Supply a file to exectute!");
        return;
      }

      String filePath = args[0];

      ArrayList<ArrayList<String>> lines = Lexer.readFile(filePath);

      if (lines == null){
        throw new Exception("Failed to read or file is empty.");
      }

      int curLine = 0;
      ArrayList<AstObj> lineAst = new ArrayList<>();

      for (ArrayList<String> l : lines) {
        curLine += 1;

        AstObj ast = AST.makeAst(l, curLine);
        lineAst.add(ast);
      }

      curLine = 0;

      for (AstObj astObj : lineAst) {
        if (!working){
          return;
        }
        
        curLine += 1;
        Executor.executeAST(astObj, curLine);
      }

    } catch (Exception e) {
      System.out.println("Failed to run cattype program!\nError:\n" + e.getMessage());
      System.out.println("-- DEBUG TRACE --");
      e.printStackTrace();
    }
  }
}