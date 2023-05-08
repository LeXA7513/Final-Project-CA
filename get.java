import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class get {
    public static String getCode(Simulator simulate) {
        String text = "";
        for (int i = 0; i < simulate.MEMORY_SIZE; i++) {
            if (simulate.memorybinary[i] != null) {
                text = text + simulate.memorybinary[i] + " ";
            } else {
                text = binaryConversion.fromBinaryText(text);
                break;
            }
        }
        return text;
    }

    public static String getDataText(Simulator simulate) {
        String text = "";
        Boolean notend = true;
        int compteur = 0, repere;

        for (int i = simulate.MEMORY_SIZE - 1; i > 0; i--) {
            repere = i;
            if (simulate.memorybinary[i] != null) {
                if (compteur == 0) {
                    text = text + binaryConversion.fromBinaryText(simulate.memorybinary[i]);
                } else if (compteur == 1) {
                    compteur = 0;
                    String nombre = "";
                    for (int a = repere; a > repere - 4; a--) {
                        nombre = simulate.memorybinary[a] + nombre;
                    }
                    i = i - 4;
                    text = text + " " + binaryConversion.fromBinaryNumber(nombre)
                            + binaryConversion.fromBinaryText("00001101 00001010");
                }
            } else if (notend) {
                compteur++;
                text = text + " ";
                if (compteur == 2) {
                    notend = false;
                }
            } else {
                break;
            }
        }
        return text;
    }

    public static String getDataTextCalculus(Simulator simulate) {
        String text = "";
        Boolean notend = true;
        int compteur = 0, repere;

        for (int i = simulate.MEMORY_SIZE - 1; i > 0; i--) {
            repere = i;
            if (simulate.memorybinary[i] != null) {
                if (compteur == 0) {
                    text = text + binaryConversion.fromBinaryText(simulate.memorybinary[i]);
                } else if (compteur == 1) {
                    compteur = 0;
                    String nombre = "";
                    for (int a = repere; a > repere - 4; a--) {
                        nombre = simulate.memorybinary[a] + nombre;
                    }
                    i = i - 4;
                    text = text + binaryConversion.fromBinaryNumber(nombre) + " ";
                }
            } else if (notend) {
                compteur++;
                text = text + " ";
                if (compteur == 2) {
                    notend = false;
                }
            } else {
                break;
            }
        }
        return text;
    }

    public static int getRegisterIndex(String registerName) throws Exception {
        switch (registerName.toUpperCase()) {
            case "T0":
                return 0;
            case "T1":
                return 1;
            case "T2":
                return 2;
            case "T3":
                return 3;
            default:
                throw new Exception("Invalid register name: " + registerName);
        }
    }

    public static int getInstructionSize(String opcode, Simulator simulation) throws Exception {
        switch (opcode) {
            case "LDA":
                return 3;
            case "STR":
                return 3;
            case "PUSH":
                return 2;
            case "POP":
                return 2;
            case "AND":
                return 3;
            case "OR":
                return 3;
            case "NOT":
                return 2;
            case "ADD":
                return 3;
            case "SUB":
                return 3;
            case "DIV":
                return 3;
            case "MUL":
                return 3;
            case "MOD":
                return 3;
            case "INC":
                return 2;
            case "DEC":
                return 2;
            case "BEQ":
                return 4;
            case "BNE":
                return 4;
            case "BBG":
                return 4;
            case "BSM":
                return 4;
            case "JMP":
                return 2;
            case "HLT":
                return 1;
            case "SRL":
                return 3;
            case "SRR":
                return 3;
            default:
                if(Verification.isLabel(opcode,simulation)){
                    return 1;
                }
                throw new Exception("Invalid opcode: " + opcode);
        }
    }

    public static int getLineLabel(String arg, Simulator simulation) {
        for (String c : simulation.label.keySet()) {
            if (c.equals(arg)) {
                return simulation.label.get(c);
            }
        }
        return 0;
    }

    public static String getGoodData(String opcode, String arg1, String arg2, String arg3, Simulator simulation)
            throws Exception {
        switch (opcode) {
            case "LDA":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "STR":
                if (!Verification.isVar(arg1, simulation)  & !Verification.isVarIndirect(arg1, simulation)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "PUSH":
                if (!Verification.isReg(arg1) & !Verification.isVar(arg1, simulation) & !Verification.isConst(arg1) & !Verification.isVarIndirect(arg1, simulation)) {
                    return "1st arg wrong type or non-existent";
                }
                return null;
            case "POP":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                }
                return null;
            case "AND":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "OR":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "NOT":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                }
                return null;
            case "ADD":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "SUB":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "DIV":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "MUL":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "MOD":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "INC":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                }
                return null;
            case "DEC":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                }
                return null;
            case "BEQ":
                if (!Verification.isReg(arg1) & !Verification.isVar(arg1, simulation) & !Verification.isConst(arg1) & !Verification.isVarIndirect(arg1, simulation)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                } else if (!Verification.isLabelArg(arg3,simulation)) {
                    return "3rd arg wrong type or non-existent";
                }
                return null;
            case "BNE":
                if (!Verification.isReg(arg1) & !Verification.isVar(arg1, simulation) & !Verification.isConst(arg1) & !Verification.isVarIndirect(arg1, simulation)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                } else if (!Verification.isLabelArg(arg3,simulation)) {
                    return "3rd arg wrong type or non-existent";
                }
                return null;
            case "BBG":
                if (!Verification.isReg(arg1) & !Verification.isVar(arg1, simulation) & !Verification.isConst(arg1) & !Verification.isVarIndirect(arg1, simulation)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                } else if (!Verification.isLabelArg(arg3,simulation)) {
                    return "3rd arg wrong type or non-existent";
                }
                return null;
            case "BSM":
                if (!Verification.isReg(arg1) & !Verification.isVar(arg1, simulation) & !Verification.isConst(arg1) & !Verification.isVarIndirect(arg1, simulation)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isReg(arg2) & !Verification.isVar(arg2, simulation) & !Verification.isVarIndirect(arg2, simulation)
                        & !Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                } else if (!Verification.isLabelArg(arg3,simulation)) {
                    return "3rd arg wrong type or non-existent";
                }
                return null;
            case "JMP":
                if (!Verification.isLabelArg(arg1,simulation)) {
                    return "1st arg wrong type or non-existent";
                }
                return null;
            case "HLT":
                return null;
            case "SRL":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            case "SRR":
                if (!Verification.isReg(arg1)) {
                    return "1st arg wrong type or non-existent";
                } else if (!Verification.isConst(arg2)) {
                    return "2nd arg wrong type or non-existent";
                }
                return null;
            default:
                throw new Exception("Invalid opcode: " + opcode);
        }
    }

    public static int getValue(String arg) {
        return Integer.parseInt(arg);
    }

    public static int getValueVar(String arg, Simulator simulate) {
        String data = get.getDataTextCalculus(simulate);
        String[] tokens = data.split(" ");
        for (int token = 0; token < tokens.length; token++) {
            String donnee = tokens[token];
            if (donnee.equals(arg)) {
                int value = get.getValue(tokens[token + 1]);
                return value;
            }
        }
        return 0;
    }
    
    public static int getValueVarIndirect(String arg, Simulator simulate) {
        String data = get.getDataTextCalculus(simulate);
        String[] tokens = data.split(" ");
        Pattern pattern = Pattern.compile("[-+]\\d+$");
        Matcher matcher = pattern.matcher(arg);
        String arg1 = arg.replaceAll("[-+]\\d+$", "");
        if(matcher.find()){
        for (int token = 0; token < tokens.length; token++) {
            String donnee = tokens[token];
            if (donnee.equals(arg1)) {
                int value = get.getValue(tokens[token + 1+(Integer.parseInt(matcher.group())*2)]);
                return value;
            }
        }}
        
        return 0;
    }

    public static String getNameVarIndirect(String arg, Simulator simulate) {
        String data = get.getDataTextCalculus(simulate);
        String[] tokens = data.split(" ");
        Pattern pattern = Pattern.compile("[-+]\\d+$");
        Matcher matcher = pattern.matcher(arg);
        String arg1 = arg.replaceAll("[-+]\\d+$", "");
        if(matcher.find()){
        for (int token = 0; token < tokens.length; token++) {
            String donnee = tokens[token];
            if (donnee.equals(arg1)) {
                String value = tokens[token +(Integer.parseInt(matcher.group())*2)];
                return value;
            }
        }}
        
        return null;
    }

    public static int getLaValeur(String arg, Simulator simulation) throws Exception {
        if (Verification.isReg(arg)) {
            return simulation.registers[get.getRegisterIndex(arg)];
        } else if (Verification.isConst(arg)) {
            return get.getValue(arg);
        } else if (Verification.isVar(arg, simulation)) {
            return get.getValueVar(arg, simulation);
        } else {
            return get.getValueVarIndirect(arg, simulation);
        }
    };

    public static String getStackText(Simulator simulate){
        String text ="";
        if(simulate.stack.size() != 0){
            for(int i = 0; i<simulate.stack.size();i++){
                text = text + String.valueOf(i+1) +" : " + String.valueOf(simulate.stack.get(i)) + binaryConversion.fromBinaryText("00001101 00001010");
            }
            return text;
        }
        return text;
    }

    public static String getNextLine(Simulator simulate, int pc){
        String text = get.getCode(simulate);
        String doc[] = text.split(binaryConversion.fromBinaryText("00001101 00001010"));
        for (int i = 0; i < doc.length; i++) {
            if (i==pc) {
                text = doc[i] ;
                break;
            } 
        }
        return text;
    }
}
