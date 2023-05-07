import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification {
    
    public static boolean isReg(String arg) {
        String[] chainesPossibles = {"T0", "T1", "T2", "T3"};
        for (String c : chainesPossibles) {
            if (c.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVar(String arg, Simulator simulation) {
        for (String c : simulation.variableNameVerification) {
            if (c.equals(arg)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isVarIndirect(String arg, Simulator simulation) {
        Pattern pattern = Pattern.compile("[-+]\\d+$");
        Matcher matcher = pattern.matcher(arg);
        if(matcher.find()){
            String arg1 = arg.replaceAll("[-+]\\d+$", "");
            for (int i =0;i<simulation.variableNameVerification.size();i++) {
                if (arg1.equals(simulation.variableNameVerification.get(i)) & ((Integer.parseInt(matcher.group()) + i ) <= simulation.variableNameVerification.size() & (Integer.parseInt(matcher.group()) + i ) >= 0) ) {
                    return true;
                }
        }}
        return false;
    }
    

    public static boolean isConst(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
