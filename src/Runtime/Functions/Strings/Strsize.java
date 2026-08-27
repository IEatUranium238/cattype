package Runtime.Functions.Strings;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Strsize {
  public static Object strsize(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 1) {
      throw new Exception("STRSIZE accepts only 1 parameter on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> param = (Map<String, Object>) content.get(0);

    if (!"FUN".equals(param.get("type"))) {
      throw new Exception("STRSIZE accepts only functions on line: " + lineNum);
    }

    Object res = FunctionMan.manageFunction(param, lineNum);

    if (!(res instanceof String)){
      throw new Exception("STRSIZE got non-STR data from function on line: " + lineNum);
    }

    return String.valueOf(res).length();
  }
}
