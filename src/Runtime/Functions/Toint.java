package Runtime.Functions;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Toint {
  @SuppressWarnings("unchecked")
  public static Object toint(Map<String, Object> data, int lineNum) throws Exception{
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 1) {
      throw new Exception("TOINT accepts only 1 parameter on line: " + lineNum);
    }
    
    Map<String, Object> param = (Map<String, Object>) content.get(0);

    if (!"FUN".equals(param.get("type"))) {
      throw new Exception("TOINT accepts only functions on line: " + lineNum);
    }

    String result = String.valueOf(FunctionMan.manageFunction(param, lineNum));

    try {
      return Integer.parseInt(result);
    } catch (Exception e) {
      throw new Exception("TOINT failed to parse string '"+result+"' to INT on line: " + lineNum);
    } 
  }
}
