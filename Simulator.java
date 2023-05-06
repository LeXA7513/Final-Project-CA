import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Simulator {
    public final int MEMORY_SIZE = 4096;
    private static final int REGISTER_COUNT = 4;

    public String[] memorybinary;
    public int[] registers;
    public List<String> variableNameVerification;
    public int pc;
    private int numberlineCode, numberVar = 0, LimitCode;


    public Simulator(String filePath) {
        memorybinary = new String[MEMORY_SIZE];
        registers = new int[REGISTER_COUNT];
        variableNameVerification = new ArrayList<String>();

        loadProgram(filePath);
    }

    private void loadProgram(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int i, n = 0, e = 0;
            String line;
            boolean isDataSection = false, isCodeSection = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("!") || line.isEmpty()) {
                    // Ignore comments and empty lines
                    continue;
                } else if (line.startsWith("#")) {
                    if (line.equals("#DATA")) {
                        isDataSection = true;
                        isCodeSection = false;
                    } else if (line.equals("#CODE")) {
                        isCodeSection = true;
                        isDataSection = false;
                    }
                    continue;
                }

                if (isDataSection) {

                    int a = 0;
                    String[] tokens = line.split(" ");
                    String variableName[] = binaryConversion.toBinaryText(tokens[0]).split(" ");
                    String initialValue[] = binaryConversion.toBinaryNumber(Integer.parseInt(tokens[1])).split(" ");
                    for (i = MEMORY_SIZE - 1; i > MEMORY_SIZE - variableName.length - 1; i--) {
                        memorybinary[i - e] = variableName[a];
                        a++;
                    }
                    e += variableName.length + 1;
                    a = 3;
                    for (int z = MEMORY_SIZE - 1; z > MEMORY_SIZE - initialValue.length - 1; z--) {
                        memorybinary[z - e] = initialValue[a];
                        a--;
                    }
                    e += initialValue.length + 1;
                    variableNameVerification.add(tokens[0]);
                    numberVar++;
                } else if (isCodeSection) {
                    numberlineCode++;
                    String text_to_add = binaryConversion.toBinaryText(line) + " 00001101 00001010 ";
                    String lettres[] = text_to_add.split(" ");
                    for (i = 0; i < lettres.length; i++) {
                        memorybinary[n + i] = lettres[i];
                    }
                    n += lettres.length;
                }

            }
            LimitCode = n;

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String checkProgram(String code) throws Exception {
        String[] lines = binaryConversion.toBinaryText(code).split("00001101 00001010");
        lines[0] = binaryConversion.fromBinaryText(lines[0]);
        int compteur_ligne = 1;
        for (int i = 1; i < lines.length; i++) {
            StringBuilder MyString = new StringBuilder(lines[i]);
            lines[i] = binaryConversion.fromBinaryText(MyString.deleteCharAt(0).toString());
        }

        for (String line : lines) {
            String[] tokens = line.split(" ");
            String opcode = tokens[0].toUpperCase();
            if (tokens.length != get.getInstructionSize(opcode)) {
                return "Invalid size instruction: " + line;
            }
            String arg1 = null, arg2 = null, arg3 = null, reponse = null;
            if (get.getInstructionSize(opcode) == 2) {
                arg1 = tokens[1].toUpperCase();
                reponse = get.getGoodData(opcode, arg1, arg2, arg3, this);
            } else if (get.getInstructionSize(opcode) == 3) {
                arg1 = tokens[1].toUpperCase();
                arg2 = tokens[2].toUpperCase();
                reponse = get.getGoodData(opcode, arg1, arg2, arg3, this);
            } else if (get.getInstructionSize(opcode) == 4) {
                arg1 = tokens[1].toUpperCase();
                arg2 = tokens[2].toUpperCase();
                arg3 = tokens[3].toUpperCase();
                reponse = get.getGoodData(opcode, arg1, arg2, arg3, this);
            }
            if (reponse != null) {
                return "Line " + compteur_ligne + " : " + reponse;
            }
            compteur_ligne++;

        }
        return null;

    }

    public String simulateProgram(String code) throws Exception {
        String[] lines = binaryConversion.toBinaryText(code).split("00001101 00001010");
        lines[0] = binaryConversion.fromBinaryText(lines[0]);
        int compteur_ligne = 1;
        for (int i = 1; i < lines.length; i++) {
            StringBuilder MyString = new StringBuilder(lines[i]);
            lines[i] = binaryConversion.fromBinaryText(MyString.deleteCharAt(0).toString());
        }

        for (int numline = 0; numline < lines.length; numline++) {
            String line = lines[numline];
            String[] tokens = line.split(" ");
            String opcode = tokens[0].toUpperCase();
            if (tokens.length != get.getInstructionSize(opcode)) {
                return "Invalid size instruction: " + line;
            }
            String arg1 = null, arg2 = null, arg3 = null, reponse = null;
            if (get.getInstructionSize(opcode) == 2) {
                arg1 = tokens[1].toUpperCase();
                reponse = get.getGoodData(opcode, arg1, arg2, arg3, this);
            } else if (get.getInstructionSize(opcode) == 3) {
                arg1 = tokens[1].toUpperCase();
                arg2 = tokens[2].toUpperCase();
                reponse = get.getGoodData(opcode, arg1, arg2, arg3, this);
            } else if (get.getInstructionSize(opcode) == 4) {
                arg1 = tokens[1].toUpperCase();
                arg2 = tokens[2].toUpperCase();
                arg3 = tokens[3].toUpperCase();
                reponse = get.getGoodData(opcode, arg1, arg2, arg3, this);
            }
            if (reponse != null) {
                return "Line " + compteur_ligne + " : " + reponse;
            }
            pc = numline+1;
            compteur_ligne++;
            if(execute(opcode, arg1, arg2, arg3) == 0){break;};
            
        }
        return null;

    }

    private int execute(String opcode, String arg1, String arg2, String arg3) throws Exception {

        switch (opcode) {
            // return 0 if end program, 1 if register value change, 2 if var value change
            case "LDA":
                if (Verification.isConst(arg2)) {
                    registers[get.getRegisterIndex(arg1)] = get.getValue(arg2);
                } else if (Verification.isVar(arg2, this)) {
                    for (int i=0; i<registers.length;i++){
                    System.out.println(registers[i]);}
                    registers[get.getRegisterIndex(arg1)] = get.getValueVar(arg2, this);
                } else {
                    registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg2)];
                }
                return 1;
            case "ADD":
                if (Verification.isConst(arg2)) {
                    registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg1)] + get.getValue(arg2);
                } else if (Verification.isVar(arg2, this)) {
                    registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg1)]
                            + get.getValueVar(arg2, this);
                } else {
                    registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg1)]
                            + registers[get.getRegisterIndex(arg2)];
                }
                return 1;
            case "HLT":
                return 0;
            case "STR":
                if (Verification.isConst(arg2)) {
                    this.changeData(arg1, get.getValue(arg2));
                } else {
                    this.changeData(arg1,registers[get.getRegisterIndex(arg2)]);
                }
                return 2;
            /*
             * case "PUSH":
             * int operandValue = getValue(arg1);
             * stackTop = registers[3];
             * memory[stackTop] = operandValue;
             * registers[3] -= 1;
             * return 1;
             * case "POP":
             * stackTop = registers[3] + 1;
             * int poppedValue = memory[stackTop];
             * registers[get.getRegisterIndex(arg1)] = poppedValue;
             * return 1;
             * case "AND":
             * registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg1)]
             * & getValue(arg2);
             * return 1;
             * case "OR":
             * int orOperandValue = getValue(arg2);
             * registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg1)]
             * | orOperandValue;
             * return 1;
             * case "NOT":
             * int argVal = registers[get.getRegisterIndex(arg1)];
             * registers[get.getRegisterIndex(arg1)] = ~argVal;
             * return 1;
             * case "SUB":
             * int arg1Val = registers[get.getRegisterIndex(arg1)];
             * int arg2Val = getValue(arg2);
             * int result = arg2Val - arg1Val;
             * registers[get.getRegisterIndex(arg1)] = result;
             * return 1;
             * case "DIV":
             * int dividend = registers[get.getRegisterIndex(arg2)];
             * int divisor = getValue(arg1);
             * if (divisor == 0) {
             * System.out.println("Error: Division by zero");
             * return -1;
             * }
             * registers[get.getRegisterIndex(arg1)] = dividend / divisor;
             * return 1;
             * case "MUL":
             * registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg1)]
             * * getValue(arg2);;
             * return 1;
             * case "MOD":
             * int arg1ValMod = registers[get.getRegisterIndex(arg1)];
             * int arg2ValMod = getValue(arg2);
             * int resultMod = arg2ValMod % arg1ValMod;
             * registers[get.getRegisterIndex(arg1)] = resultMod;
             * return 1;
             * case "INC":
             * registers[get.getRegisterIndex(arg1)]++;
             * return 1;
             * case "DEC":
             * registers[get.getRegisterIndex(arg1)]--;
             * return 1;
             * case "BEQ":
             * if (getValue(arg1) == getValue(arg2)) {
             * pc = Integer.parseInt(labelTable.get(label));
             * }
             * return 0;
             * case "BNE":
             * if (getValue(arg1) != getValue(arg2)) {
             * currentInstructionIndex = labels.get(label);
             * }
             * return 0;
             * case "BBG":
             * if (arg1Val > arg2Val) {
             * return getLabelIndex(label);
             * }
             * return 1;
             * case "BSM":
             * String[] labelTokens =
             * instructionParts[instructionParts.length-1].split(":");
             * String label = labelTokens[labelTokens.length-1];
             * 
             * if ( getValue(arg1) < getValue(arg2)) {
             * return labelMap.get(label);
             * } else {
             * return currentAddress + 1;
             * 
             * case "JMP":
             * return getLabelIndex(arg1);
             * case "SRL":
             * registers[get.getRegisterIndex(arg1)]= registers[get.getRegisterIndex(arg1)]
             * << arg2;
             * return 1;
             * case "SLL":
             * registers[get.getRegisterIndex(arg1)]= registers[get.getRegisterIndex(arg1)]
             * >> arg2;
             * return 1;
             */
            default:
                System.out.println("Invalid instruction: " + opcode + " " + arg1 + " " + arg2 + " " + arg3);
                return -1;
        }
    }

    private void changeData(String var, int value) {

        String data = get.getDataTextCalculus(this);
        String[] tokens = data.split(" ");
        for (int token = 0; token < tokens.length; token++) {
            String donnee = tokens[token];
            if (donnee.equals(var)) {
                tokens[token + 1] = String.valueOf(value);
            }
        }
         /* 
        int a = 0, e = 0;
        String variableName[] = binaryConversion.toBinaryText(tokens[0]).split(" ");
        String initialValue[] = binaryConversion.toBinaryNumber(Integer.parseInt(tokens[1])).split(" ");
        for (int i = MEMORY_SIZE - 1; i > MEMORY_SIZE - variableName.length - 1; i--) {
            memorybinary[i - e] = variableName[a];
            a++;
        }
        e += variableName.length + 1;
        a = 3;
        for (int z = MEMORY_SIZE - 1; z > MEMORY_SIZE - initialValue.length - 1; z--) {
            memorybinary[z - e] = initialValue[a];
            a--;
        }
        e += initialValue.length + 1;
        */
    }
}