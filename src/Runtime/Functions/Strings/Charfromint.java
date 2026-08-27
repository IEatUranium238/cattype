package Runtime.Functions.Strings;

import java.util.ArrayList;
import java.util.Map;

import Runtime.FunctionMan;

public class Charfromint {
  public static Object charfromint(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 1) {
      throw new Exception("CHARFROMINT accepts only 1 parameter on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> param = (Map<String, Object>) content.get(0);

    Object res;

    if ("FUN".equals(param.get("type"))) {
      res = FunctionMan.manageFunction(param, lineNum);
    } else {
      if (String.valueOf(param.get("content")).matches(".*[a-zA-Z'\"].*")) {
        throw new Exception(
            "CHARFROMINT accepts only INT values, got invalid value type or format from input on line: "
                + lineNum);
      }

      res = Integer.parseInt(String.valueOf(param.get("content")));
    }

    if (!(res instanceof Integer)) {
      throw new Exception("CHARFROMINT got non-INT data from function on line: " + lineNum);
    }

    return String.valueOf(Character.toChars((int) res));
  }
}
