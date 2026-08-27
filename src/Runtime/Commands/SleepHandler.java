package Runtime.Commands;

import java.util.Map;

import Components.AstObj;
import Runtime.FunctionMan;

public class SleepHandler {
  @SuppressWarnings("unchecked")
  public static void sleepHandler(AstObj ast, int lineNum) throws Exception {
    Map<String, Object> data = ast.data;

    int sleepTime;

    if (data.get("sleepMode").equals("FUN")) {
      try {
        sleepTime = Integer.parseInt(
            String.valueOf(FunctionMan.manageFunction((Map<String, Object>) data.get("sleepData"), lineNum)));
      } catch (Exception e) {
        throw new Exception("SLEEP only accepts INT on line " + lineNum);
      }
    } else {
      try {
        sleepTime = Integer.parseInt(String.valueOf(data.get("sleepData")));
      } catch (Exception e) {
        throw new Exception("SLEEP only accepts INT on line " + lineNum);
      }
    }

    if (sleepTime < 0){
      throw new Exception("SLEEP got invalid sleep time length on line " + lineNum);
    }

    Thread.sleep(sleepTime * 1000);
  }
}
