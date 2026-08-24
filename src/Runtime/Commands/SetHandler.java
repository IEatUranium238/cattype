package Runtime.Commands;

import java.util.Map;

import Components.AstObj;
import Components.MemObj;
import Components.Memory;
import Runtime.FunctionMan;

public class SetHandler {
  public static void setHandler(AstObj ast, int lineNum) throws Exception {
    Map<String, Object> data = ast.data;

    if (data.get("mode").equals("IMD")) {
      String valName = (String) data.get("dataName");
      MemObj valueToManip = (MemObj) Memory.getData(valName);

      if (valueToManip == null) {
        throw new Exception("Value " + valName + " is not defined, tried to change on line " + lineNum);
      }

      String datatype = (String) valueToManip.type;
      String input = (String) data.get("data");
      Object inputval = (String) data.get("data");

      if (!(input.startsWith("\"") || input.startsWith("'")) && input.matches("\\d+\\.\\d+")) {
        throw new Exception(
            "Tried to use floating point number to change value named '" + valName + "' on line " + lineNum);
      }

      if (datatype.equals("INT") && input.matches(".*[a-zA-Z'\"].*")) {
        throw new Exception("Tried change integer value named '" + valName + "' with string on line " + lineNum);
      }

      if (input.matches("\\d+") && datatype.equals("STR")) {
        throw new Exception("Tried change string value named '" + valName + "' with integer on line " + lineNum);
      }

      if (datatype.equals("STR")) {
        inputval = input.substring(1, input.length() - 1);
      }

      if (datatype.equals("INT")) {
        inputval = Integer.parseInt(input);
      }

      char status = Memory.changeData(valName, inputval);

      if (status == 'm') {
        throw new Exception("Value " + valName + " is not defined, tried to change on line " + lineNum);
      }

    } else {
      String valName = (String) data.get("dataName");
      MemObj valueToManip = (MemObj) Memory.getData(valName);

      if (valueToManip == null) {
        throw new Exception("Value " + valName + " is not defined, tried to change on line " + lineNum);
      }

      String datatype = (String) valueToManip.type;

      @SuppressWarnings("unchecked")
      Map<String, Object> functionData = (Map<String, Object>) data.get("data");

      Object returned = FunctionMan.manageFunction(functionData, lineNum);

      if (returned == null) {
        throw new Exception("Function returned no value on line " + lineNum);
      }

      if (returned.getClass() == Integer.class) {
        if (datatype.equals("STR")) {
          throw new Exception(
              "Got invalid data type for value change from function, expected STR on line " + lineNum);
        }

        char status = Memory.changeData(valName, Integer.parseInt(String.valueOf(returned)));

        if (status == 'm') {
          throw new Exception("Value " + valName + " is not defined, tried to change on line " + lineNum);
        }

        if (status == 't') {
          throw new Exception("Value " + valName + " has " + valueToManip.type + " data type, tried to change to non-"
              + valueToManip.type + " on line " + lineNum);
        }
      } else {
        if (datatype.equals("INT")) {
          throw new Exception(
              "Got invalid data type for value change from function, expected INT on line " + lineNum);
        }

        char status = Memory.changeData(valName, returned);

        if (status == 'm') {
          throw new Exception("Value " + valName + " is not defined, tried to change on line " + lineNum);
        }
      }
    }
  }
}
