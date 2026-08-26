package Runtime.Commands;

import java.util.Map;
import java.util.Scanner;

import Components.AstObj;
import Components.MemObj;
import Components.Memory;

public class InHandler {
  public static void inHandler(AstObj ast, int lineNum) throws Exception {
    Map<String, Object> data = ast.data;

    String name = (String) data.get("dataName");

    Scanner in = new Scanner(System.in);

    Object input = in.nextLine();

    in.close();

    MemObj obj = (MemObj) Memory.getData(name);
    String dataType = obj.type;

    if (dataType .equals("INT")) {
      try {
        input = Integer.parseInt(String.valueOf(input));
      } catch (Exception e) {
        throw new Exception("Failed to convert user input for value '" + name + "' to INT on line " + lineNum);
      }
    } else {
      input = String.valueOf(input);
    }

    char stat = Memory.changeData(name, input);

    if (stat == 'm') {
      throw new Exception("Value named '" + name + "' for input doesnt exist on line " + lineNum);
    }

    if (stat == 't') {
      throw new Exception("Value named '" + name + "' for input has type " + dataType + " tried to input non-"
          + dataType + " on line " + lineNum);
    }

  }
}
