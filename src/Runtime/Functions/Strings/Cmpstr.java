package Runtime.Functions.Strings;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Cmpstr {
  public static Object cmpstr(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 2) {
      throw new Exception("CMPSTR accepts only 2 parameters on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> paramL = (Map<String, Object>) content.get(0);

    @SuppressWarnings("unchecked")
    Map<String, Object> paramR = (Map<String, Object>) content.get(1);

    if (!"FUN".equals(paramL.get("type"))) {
      throw new Exception("CMPSTR accepts only functions on line: " + lineNum);
    }

    if (!"FUN".equals(paramR.get("type"))) {
      throw new Exception("CMPSTR accepts only functions on line: " + lineNum);
    }

    Object resL = FunctionMan.manageFunction(paramL, lineNum);
    Object resR = FunctionMan.manageFunction(paramR, lineNum);

    if (!(resL instanceof String) || !(resR instanceof String)){
      throw new Exception("CMPSTR got non-STR data from function on line: " + lineNum);
    }
    int res = (String.valueOf(resL).equals(String.valueOf(resR))) ? 1 : 0;
    return res;
  }
}
