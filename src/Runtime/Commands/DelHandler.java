package Runtime.Commands;

import java.util.Map;

import Components.AstObj;
import Components.Memory;

public class DelHandler {
  public static void delHandler(AstObj ast, int lineNum) throws Exception {
    Map<String, Object> data = ast.data;

    String name = (String) data.get("dataName");
    
    char stat = Memory.delData(name);

    if (stat == 'm'){
      throw new Exception("Value name for deletion doesnt exist on line " + lineNum);
    }

  }
}
