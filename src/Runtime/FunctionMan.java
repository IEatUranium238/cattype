package Runtime;

import java.util.Map;

import Runtime.Functions.Getval;
import Runtime.Functions.Toint;
import Runtime.Functions.Tostr;
import Runtime.Functions.Math.Add;

public class FunctionMan {
  public static Object manageFunction(Map<String, Object> data, int lineNum) throws Exception {
    switch ((String) data.get("name")) {
      case "GETVAL":
        return Getval.getVal(data, lineNum);
      case "TOSTR":
        return Tostr.tostr(data, lineNum);
      case "TOINT":
        return Toint.toint(data, lineNum);
      case "ADD":
        return Add.add(data, lineNum);
      default:
        throw new Exception("Unknown build in function to execute: "+data.get("name"));
    }
  }
}
