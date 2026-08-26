package Runtime.Functions.Conditions;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Not {
  public static Object not(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 1) {
      throw new Exception("NOT accepts only 1 parameter on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> paramL = (Map<String, Object>) content.get(0);

    int resL;

    if (paramL.get("type").equals("FUN")) {
      try {
        resL = Integer.parseInt(String.valueOf(FunctionMan.manageFunction(paramL, lineNum)));
      } catch (NumberFormatException e) {
        throw new Exception(
            "NOT accepts only INT values, got invalid value type or format from function on line: " + lineNum);
      }
    } else {
      if (String.valueOf(paramL.get("content")).matches(".*[a-zA-Z'\"].*")) {
        throw new Exception(
            "NOT accepts only INT values, got invalid value type or format from input on line: " + lineNum);
      }

      try {
        resL = Integer.parseInt(String.valueOf(paramL.get("content")));
      } catch (NumberFormatException e) {
        throw new Exception(
            "NOT accepts only INT values, got invalid value type or format from input on line: " + lineNum);
      }
    }
    
    
    int res = (resL != 0) ? 0 : 1;
    return res;
  }
}
