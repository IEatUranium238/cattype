package Runtime.Commands;

import java.util.Map;

import Components.AstObj;
import Runtime.FunctionMan;

public class PrintHandler {
  public static void printHandler(AstObj ast, int lineNum) throws Exception {
    Map<String, Object> data = ast.data;

    if (data.get("mode").equals("IMD")) {
      System.out.println(data.get("data"));
    } else {
      @SuppressWarnings("unchecked")
      Map<String, Object> functionData = (Map<String, Object>) data.get("data");

      Object returned = FunctionMan.manageFunction(functionData, lineNum);

      if (returned instanceof String){
        System.out.println((String) returned);
      } else {
        throw new Exception("Got invalid data type for PRINT command from function, expected STR on line " + lineNum);
      }
    }
  }
}
