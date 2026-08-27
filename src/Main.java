import java.util.ArrayList;

import Prerun.AST;
import Components.AstObj;
import Prerun.Lexer;
import Runtime.Executor;
import Components.State;

public class Main {
  public static void main(String[] args) {
    try {
      if (args.length == 0) {
        System.out.println("Supply a file to execute!");
        return;
      }

      String filePath = args[0];

      ArrayList<ArrayList<String>> lines = Lexer.readFile(filePath);

      if (lines == null){
        throw new Exception("Failed to read or file is empty.");
      }

      ArrayList<AstObj> lineAst = new ArrayList<>();

      for (ArrayList<String> l : lines) {
        State.curLine += 1;

        AstObj ast = AST.makeAst(l, State.curLine);
        lineAst.add(ast);
      }

      State.curLine = 0;
      State.linesAmount = lineAst.size();

      while (State.working && (State.curLine < State.linesAmount)) {
        State.curLine += 1;
        Executor.executeAST(lineAst.get(State.curLine - 1), State.curLine);
      }

      State.in.close();

    } catch (Exception e) {
      System.out.println("Failed to run cattype program!\nError:\n" + e.getMessage());
      System.out.println("-- INTERNAL DEBUG TRACE --");
      e.printStackTrace();
    }
  }
}