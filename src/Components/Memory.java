package Components;

import java.util.HashMap;
import java.util.Map;


public class Memory {
  private static Map<String, MemObj> vals = new HashMap<>();

  public static char makeData(String name, String type, Object val) {
    if (vals.containsKey(name)) {
      return 'e';
    }

    vals.put(name, new MemObj(val, type));

    return 'o';
  }

  public static char changeData(String name, Object val) {
    if (!vals.containsKey(name)) {
      return 'm';
    }

    MemObj data = vals.get(name);

    if (data.value == null || val == null || data.value.getClass() != val.getClass()) {
      return 't';
    }

    data.value = val;
    vals.replace(name, data);

    return 'o';
  }

  public static Object getData(String name) {
    if (!vals.containsKey(name)) {
      return null;
    }

    return vals.get(name);
  }

  public static char delData(String name) {
    if (!vals.containsKey(name)) {
      return 'm';
    }

    vals.remove(name);

    return 'o';
  }
}
