package Runtime.Functions;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Tostr {
  @SuppressWarnings("unchecked")
  public static Object tostr(Map<String, Object> data, int lineNum) throws Exception{
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 1) {
      throw new Exception("TOSTR accepts only 1 parameter on line: " + lineNum);
    }
    
    Map<String, Object> param = (Map<String, Object>) content.get(0);

    if (param.get("type") != "FUN") {
      throw new Exception("TOSTR accepts only functions on line: " + lineNum);
    }

    return String.valueOf(FunctionMan.manageFunction(param, lineNum));
  }
}
