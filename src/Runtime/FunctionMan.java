package Runtime;

import java.util.Map;

import Runtime.Functions.*;
import Runtime.Functions.Conditions.*;
import Runtime.Functions.Math.*;
import Runtime.Functions.Strings.*;

public class FunctionMan {
  public static Object manageFunction(Map<String, Object> data, int lineNum) throws Exception {
    switch ((String) data.get("name")) {
      case "GETVAL":
        return Getval.getVal(data, lineNum);
      case "TOSTR":
        return Tostr.tostr(data, lineNum);
      case "TOINT":
        return Toint.toint(data, lineNum);
      case "ADD":
        return Add.add(data, lineNum);
      case "SUB":
        return Sub.sub(data, lineNum);
      case "MUL":
        return Mul.mul(data, lineNum);
      case "DIV":
        return Div.div(data, lineNum);
      case "POW":
        return Pow.pow(data, lineNum);
      case "ROOT":
        return Root.root(data, lineNum);
      case "MOD":
        return Mod.mod(data, lineNum);
      case "JOINSTR":
        return Joinstr.joinstr(data, lineNum);
      case "EQLS":
        return Eqls.eqls(data, lineNum);
      case "MR":
        return Mr.mr(data, lineNum);
      case "LS":
        return Ls.ls(data, lineNum);
      case "NOT":
        return Not.not(data, lineNum);
      default:
        throw new Exception("Unknown build in function to execute: "+data.get("name"));
    }
  }
}
