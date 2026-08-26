package Runtime.Commands;

import java.util.Map;

import Components.AstObj;
import Components.State;
import Runtime.FunctionMan;

public class GotoHandler {
  @SuppressWarnings("unchecked")
  public static void gotoHandler(AstObj ast, int lineNum) throws Exception {
    Map<String, Object> data = ast.data;

    int gotoline;

    if (data.get("addressMode").equals("FUN")) {
      try {
        gotoline = Integer.parseInt(
            String.valueOf(FunctionMan.manageFunction((Map<String, Object>) data.get("addressData"), lineNum)));
      } catch (Exception e) {
        throw new Exception("GOTO only accepts INT on line " + lineNum);
      }
    } else {
      try {
        gotoline = Integer.parseInt(String.valueOf(data.get("addressData")));
      } catch (Exception e) {
        throw new Exception("GOTO only accepts INT on line " + lineNum);
      }
    }

    if (!((boolean) data.get("hasCondition"))) {
      if (gotoline > State.linesAmount || gotoline < 1) {
        throw new Exception("GOTO got out of bounds line number to jump to on line " + lineNum);
      }
      State.curLine = gotoline;
      return;
    }

    int condition;

    if (data.get("conditionMode").equals("FUN")) {
      try {
        condition = Integer.parseInt(
            String.valueOf(FunctionMan.manageFunction((Map<String, Object>) data.get("conditionData"), lineNum)));
      } catch (Exception e) {
        throw new Exception("GOTO only accepts INT on line " + lineNum);
      }
    } else {
      try {
        condition = Integer.parseInt(String.valueOf(data.get("conditionData")));
      } catch (Exception e) {
        throw new Exception("GOTO only accepts INT on line " + lineNum);
      }
    }

    if (condition > 0) {
      if (gotoline > State.linesAmount || gotoline < 1) {
        throw new Exception("GOTO got out of bounds line number to jump to on line " + lineNum);
      }
      State.curLine = gotoline;
      return;
    }

  }
}
