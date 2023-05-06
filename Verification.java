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

    public static boolean isConst(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
