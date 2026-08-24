package Components;

import java.util.Map;

public class AstObj {
  public String type;
  public Map<String, Object> data;

  public AstObj(String type, Map<String, Object> data) {
    this.type = type;
    this.data = data;
  }
}