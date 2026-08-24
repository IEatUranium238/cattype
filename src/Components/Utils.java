package Components;
public class Utils {
  public static boolean isValidValue(String name){
    if (name == null || name.isEmpty()){
      return false;
    }

    if (!Character.isLetter(name.charAt(0))){
      return false;
    }

    if (name.charAt(name.length() - 1) == '!'){
      return false;
    }

    if (!(name == name.toLowerCase())){
      return false;
    }

    return true;
  }

  public static boolean isValidCommand(String name){
    if (!(name == name.toUpperCase())){
      return false;
    }

    return true;
  }

  public static boolean isValidFunction(String name){
    if (name == null || name.isEmpty()){
      return false;
    }

    if (!(name.charAt(name.length() - 1) == '!')){
      return false;
    }

    if (!(name == name.toUpperCase())){
      return false;
    }

    return true;
  }
}
