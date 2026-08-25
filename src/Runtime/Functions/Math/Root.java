package Runtime.Functions.Math;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Root {
  public static Object root(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 2) {
      throw new Exception("ROOT accepts only 2 parameters on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> paramL = (Map<String, Object>) content.get(0);

    @SuppressWarnings("unchecked")
    Map<String, Object> paramR = (Map<String, Object>) content.get(1);

    int resL;
    int resR;

    if (paramL.get("type").equals("FUN")) {
      try {
        resL = Integer.parseInt(String.valueOf(FunctionMan.manageFunction(paramL, lineNum)));
      } catch (NumberFormatException e) {
        throw new Exception(
            "ROOT accepts only INT values, got invalid value type or format from function on line: " + lineNum);
      }
    } else {
      if (String.valueOf(paramL.get("content")).matches(".*[a-zA-Z'\"].*")) {
        throw new Exception(
            "ROOT accepts only INT values, got invalid value type or format from input on line: " + lineNum);
      }

      try {
        resL = Integer.parseInt(String.valueOf(paramL.get("content")));
      } catch (NumberFormatException e) {
        throw new Exception(
            "ROOT accepts only INT values, got invalid value type or format from input on line: " + lineNum);
      }
    }

    if (paramR.get("type").equals("FUN")) {
      try {
        resR = Integer.parseInt(String.valueOf(FunctionMan.manageFunction(paramR, lineNum)));
      } catch (NumberFormatException e) {
        throw new Exception(
            "ROOT accepts only INT values, got invalid value type or format from function on line: " + lineNum);
      }
    } else {
      if (String.valueOf(paramL.get("content")).matches(".*[a-zA-Z'\"].*")) {
        throw new Exception(
            "ROOT accepts only INT values, got invalid value type or format from input on line: " + lineNum);
      }

      try {
        resR = Integer.parseInt(String.valueOf(paramR.get("content")));
      } catch (NumberFormatException e) {
        throw new Exception(
            "ROOT accepts only INT values, got invalid value type or format from input on line: " + lineNum);
      }
    }
    int result = (int) Math.round(Math.pow(resL, 1.0 / resR));
    return result;
  }
}
