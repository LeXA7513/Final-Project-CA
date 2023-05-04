import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Simulator {
    public final int MEMORY_SIZE = 4096;
    private static final int REGISTER_COUNT = 4;
    private static final int MAX_CONSTANT_VALUE = 1023;

    private int[] memory; 
    public String[] memorybinary;
    private int[] registers;
    private int pc;
    private int numberlineCode, numberVar;

    private Map<String, Integer> variableMap;

    public Simulator(String filePath) {
        memory = new int[MEMORY_SIZE];
        memorybinary = new String[MEMORY_SIZE];
        registers = new int[REGISTER_COUNT];
        variableMap = new HashMap<>();

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
                    if (line.equals("#DATA")){
                    isDataSection = true;
                    isCodeSection = false;
                }
                    else if (line.equals("#CODE"))
                    {
                        isCodeSection =true;
                        isDataSection = false;
                    }
                    continue;
                }

                if (isDataSection) {
                            numberVar ++;
                            int a = 0;
                            String[] tokens = line.split(" ");
                            String variableName[] = binaryConversion.toBinaryText(tokens[0]).split(" ");
                            String initialValue[] = binaryConversion.toBinaryNumber(Integer.parseInt(tokens[1])).split(" ");
                            for(i=MEMORY_SIZE-1; i>MEMORY_SIZE-variableName.length-1; i--){
                                memorybinary[i-e]= variableName[a] ;
                                a ++ ;
                            }
                            e += variableName.length + 1;
                            a = 3;
                            for(int z=MEMORY_SIZE-1; z>MEMORY_SIZE-initialValue.length-1; z--){
                                memorybinary[z-e]= initialValue[a] ;
                                a --;
                            }
                            e += initialValue.length + 1;
                } else if(isCodeSection) {
                        numberlineCode ++ ;
                        String text_to_add = binaryConversion.toBinaryText(line) + " 00001101 00001010 ";
                        String lettres[] = text_to_add.split(" ");
                        for(i=0; i<lettres.length; i++){
                            memorybinary[n+i]= lettres[i] ;
                        }
                        n += lettres.length;
                   /* memory[pc] = assemble(line);
                    String[] tokens = line.split(" ");
                    pc += tokens.length;*/
                }
                
                
            }
            int LimitCode = n ;

            reader.close();/* 
            String text = "";
            for(i=0;i<48;i++){
             text =text + memorybinary[i] +" ";
            }
        System.out.println(text);
        System.out.println(binaryConversion.fromBinaryText(text));
        System.out.println(binaryConversion.fromBinaryNumber("00000000000000000000000000000000")); 
        System.out.println(binaryConversion.fromBinaryText("01010011 01000101 01010010 ")); 
        System.out.println(binaryConversion.fromBinaryNumber("00000000000000000000000000001111")); 
        System.out.println(binaryConversion.fromBinaryText("01000010 "));
        System.out.println(binaryConversion.fromBinaryNumber("00000000000000000000000000001010")); 
        System.out.println(binaryConversion.fromBinaryText("01000001"));*/

    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int assemble(String instruction) throws Exception {
        String[] tokens = instruction.split(" ");
        String opcode = tokens[0].toUpperCase();
        if (tokens.length != getIntsructionSize(opcode)) {
            throw new Exception("Invalid size intrustion: " + instruction);
        }
        
        String arg1 ="", arg2="", arg3="";
        if(getIntsructionSize(opcode)==2){
            arg1 = tokens[1].toUpperCase();
        }else if(getIntsructionSize(opcode)==3){
            arg1 = tokens[1].toUpperCase();
            arg2 = tokens[2].toUpperCase();
        } else if (getIntsructionSize(opcode)==4){
            arg1 = tokens[1].toUpperCase();
            arg2 = tokens[2].toUpperCase();
            arg3 = tokens[3].toUpperCase();
        }
        

        int stackTop ;
        switch (opcode) {
            case "LDA":
                registers[getRegisterIndex(arg1)] = getValue(arg2);
                return 0;
            case "ADD":
                registers[getRegisterIndex(arg1)] = registers[getRegisterIndex(arg1)] + getValue(arg2);
                return 1;
            case "HLT":
                return 2;
            case "STR":
                memory[variableMap.get(arg1)] = registers[getRegisterIndex(arg2)];
                return 3;
            case "PUSH":
                int operandValue = getValue(arg1);
                stackTop = registers[3];
                memory[stackTop] = operandValue;
                registers[3] -= 1;
                return 4;
            case "POP":
                stackTop = registers[3] + 1;
                int poppedValue = memory[stackTop];
                registers[getRegisterIndex(arg1)] = poppedValue;
                return 4;
            case "AND":
                return 5;
            case "OR":
                return 6;
            case "NOT":
                return 7;
            case "SUB":
                return 9;
            case "DIV":
                return 7;
            case "MUL":
                return 7;
            case "MOD":
                return 7; 
            case "INC":
                return 7; 
            case "DEC":
                return 7; 
            case "BEQ":
                return 7; 
            case "BNE":
                return 7; 
            case "BBG":
                return 7; 
            case "BSM":
                return 7; 
            case "JMP":
                return 7;
            default:
                System.out.println("Invalid instruction: " + instruction);
                return -1;                   
        }
    }


    private int getValue(String operand) throws Exception {
        if (operand.startsWith("T")) {
            return registers[getRegisterIndex(operand)];
        } else if (variableMap.containsKey(operand)) {
            return variableMap.get(operand);
        } else if (operand.startsWith("#")) {
            int constant = Integer.parseInt(operand.substring(1));
            if (constant > MAX_CONSTANT_VALUE) {
                throw new Exception("Constant value too large: " + operand);
            }
            return constant;
        } else {
            throw new Exception("Invalid operand: " + operand);
        }
    }

    private int getRegisterIndex(String registerName) throws Exception {
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

    private int getIntsructionSize(String opcode) throws Exception {
        switch (opcode.toUpperCase()) {
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
                throw new Exception("Invalid opcode: " + opcode);
        }
    }

    public void run() throws Exception {
        pc = 0;
    
        while (true) {
            int instruction = memory[pc];
    
            String opcode = getOpcode(instruction);
            String arg1 = getArg1(instruction);
            String arg2 = getArg2(instruction);
    
            switch (opcode) {
                case "LDA":
                    int value = getValue(arg2);
                    registers[getRegisterIndex(arg1)] = value;
                    pc += getIntsructionSize(opcode);
                    break;
                case "ADD":
                    int result = registers[getRegisterIndex(arg1)] + getValue(arg2);
                    registers[getRegisterIndex(arg1)] = result;
                    pc += getIntsructionSize(opcode);
                    break;
                case "HLT":
                    break;
                case "PUSH":
                    break;
                default:
                    throw new Exception("Invalid opcode: " + opcode);
            }
        }
    }
    
    private String getOpcode(int instruction) {
        return String.format("%02X", instruction >> 16);
    }
    
    private String getArg1(int instruction) {
        return String.format("T%d", (instruction >> 12) & 0x3);
    }
    
    private String getArg2(int instruction) {
        int value = instruction & 0xFFF;
        if (value < REGISTER_COUNT) {
            return String.format("T%d", value);
        } else if (variableMap.containsValue(value)) {
            for (Map.Entry<String, Integer> entry : variableMap.entrySet()) {
                if (entry.getValue() == value) {
                    return entry.getKey();
                }
            }
            return null;
        } else {
            return String.format("#%d", value);
        }
    }
}    