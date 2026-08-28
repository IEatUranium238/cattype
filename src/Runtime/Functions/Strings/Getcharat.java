package Runtime.Functions.Strings;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Getcharat {
  public static Object getcharat(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 2) {
      throw new Exception("GETCHARAT accepts only 2 parameter on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> paramL = (Map<String, Object>) content.get(0);

    @SuppressWarnings("unchecked")
    Map<String, Object> paramR = (Map<String, Object>) content.get(1);

    if (!"FUN".equals(paramL.get("type"))) {
      throw new Exception("GETCHARAT accepts only functions as first param on line: " + lineNum);
    }

    Object resL = FunctionMan.manageFunction(paramL, lineNum);
    Object resR;

    if ("FUN".equals(paramR.get("type"))) {
      resR = FunctionMan.manageFunction(paramR, lineNum);
    } else {
      if (String.valueOf(paramR.get("content")).matches(".*[a-zA-Z'\"].*")) {
        throw new Exception(
            "GETCHARAT accepts only INT values for second param, got invalid value type or format from input on line: "
                + lineNum);
      }

      resR = Integer.parseInt(String.valueOf(paramR.get("content")));
    }

    if (!(resL instanceof String)) {
      throw new Exception("GETCHARAT got non-STR data from function for first param on line: " + lineNum);
    }

    if (!(resR instanceof Integer)) {
      throw new Exception("GETCHARAT got non-INT data from function for second param on line: " + lineNum);
    }

    return String.valueOf(String.valueOf(resL).charAt(Integer.parseInt(String.valueOf(resR))));
  }
}
