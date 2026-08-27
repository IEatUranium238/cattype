package Runtime.Functions.Strings;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Chartoint {
  public static Object inttochar(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 1) {
      throw new Exception("INTTOCHAR accepts only 1 parameter on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> param = (Map<String, Object>) content.get(0);

    if (!"FUN".equals(param.get("type"))) {
      throw new Exception("INTTOCHAR accepts only functions on line: " + lineNum);
    }

    Object res = FunctionMan.manageFunction(param, lineNum);

    if (!(res instanceof String)) {
      throw new Exception("INTTOCHAR got non-STR data from function on line: " + lineNum);
    }

    if (String.valueOf(res).length() > 1) {
      throw new Exception("INTTOCHAR got STR data longer than 1 on line: " + lineNum);
    }

    return Integer.valueOf(String.valueOf(res).charAt(0));
  }
}
