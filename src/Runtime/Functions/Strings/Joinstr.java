package Runtime.Functions.Strings;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Joinstr {
  public static Object joinstr(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 2) {
      throw new Exception("JOINSTR accepts only 2 parameters on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> paramL = (Map<String, Object>) content.get(0);

    @SuppressWarnings("unchecked")
    Map<String, Object> paramR = (Map<String, Object>) content.get(1);

    if (!"FUN".equals(paramL.get("type"))) {
      throw new Exception("JOINSTR accepts only functions on line: " + lineNum);
    }

    if (!"FUN".equals(paramR.get("type"))) {
      throw new Exception("JOINSTR accepts only functions on line: " + lineNum);
    }

    Object resL = FunctionMan.manageFunction(paramL, lineNum);
    Object resR = FunctionMan.manageFunction(paramR, lineNum);

    if (!(resL instanceof String) || !(resR instanceof String)){
      throw new Exception("JOINSTR got non-STR data from function on line: " + lineNum);
    }

    return String.valueOf(resL) + String.valueOf(resR);
  }
}
