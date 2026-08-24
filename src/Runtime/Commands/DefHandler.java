package Runtime.Commands;

import java.util.Map;

import Components.AstObj;
import Components.Memory;
import Runtime.FunctionMan;

public class DefHandler {
  public static void defHandler(AstObj ast, int lineNum) throws Exception {
    Map<String, Object> data = ast.data;
    if (data.get("mode").equals("IMD")) {
      String valName = (String) data.get("dataName");
      String datatype = (String) data.get("dataType");
      Object val = (String) data.get("data");

      if (datatype.equals("INT")) {
        val = Integer.parseInt((String) data.get("data"));
      }

      char status = Memory.makeData(valName, datatype, val);

      if (status == 'e') {
        throw new Exception("Value " + valName + " is already defined, tried to redefine on line " + lineNum);
      }
    } else {
      String valName = (String) data.get("dataName");
      String datatype = (String) data.get("dataType");

      @SuppressWarnings("unchecked")
      Map<String, Object> functionData = (Map<String, Object>) data.get("data");

      Object returned = FunctionMan.manageFunction(functionData, lineNum);

      if (returned == null) {
        throw new Exception("Function returned no value on line " + lineNum);
      }

      if (returned instanceof Integer) {
        if (datatype.equals("STR")) {
          throw new Exception(
              "Got invalid data type for value defenition from function, expected STR on line " + lineNum);
        }

        char status = Memory.makeData(valName, datatype, returned);

        if (status == 'e') {
          throw new Exception("Value " + valName + " is already defined, tried to redefine on line " + lineNum);
        }
      } else {
        if (datatype.equals("INT")) {
          throw new Exception(
              "Got invalid data type for value defenition from function, expected INT on line " + lineNum);
        }

        char status = Memory.makeData(valName, datatype, returned);

        if (status == 'e') {
          throw new Exception("Value " + valName + " is already defined, tried to redefine on line " + lineNum);
        }
      }

    }
  }
}
