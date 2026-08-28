package Runtime.Functions;

import java.util.ArrayList;
import java.util.Map;

import Components.MemObj;
import Components.Memory;
import Components.Utils;

public class Getval {
  public static Object getVal(Map<String, Object> data, int lineNum) throws Exception {
    @SuppressWarnings("unchecked")
    ArrayList<Object> content = (ArrayList<Object>) data.get("content");
    if (content.size() != 1) {
      throw new Exception("GETVAL accepts only 1 parameter on line: " + lineNum);
    }

    @SuppressWarnings("unchecked")
    Map<String, Object> param = (Map<String, Object>) content.get(0);

    if (!"PAR".equals(param.get("type"))) {
      throw new Exception("GETVAL accepts only values on line: " + lineNum);
    }

    String name = (String) param.get("content");

    if (!Utils.isValidValue(name)) {
      throw new Exception("Invalid value name for GETVAL on line: " + lineNum);
    }

    MemObj res = (MemObj) Memory.getData(name);

    if (res == null) {
      throw new Exception("Value '" + name + "' does not exist on line " + lineNum);
    }
    
    String type = res.type;
    if (type.equals("STR")){
      return String.valueOf(res.value);
    } else {
      return Integer.parseInt(String.valueOf(res.value));
    }
  }
}
