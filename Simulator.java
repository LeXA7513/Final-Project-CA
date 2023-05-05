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
    private int numberlineCode, numberVar, LimitCode;

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
                }
                
                
            }
            LimitCode = n ;

            reader.close(); 

    } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkProgram(String code) throws Exception {
        String[] lines = binaryConversion.toBinaryText(code).split("00001101 00001010");
        lines[0] = binaryConversion.fromBinaryText(lines[0]);
        for (int i=1;i<lines.length;i++){
            StringBuilder MyString = new StringBuilder(lines[i]);
            lines[i] = binaryConversion.fromBinaryText(MyString.deleteCharAt(0).toString());
        }
        for (String line : lines) {
            String[] tokens = line.split(" ");
            String opcode = tokens[0].toUpperCase();
            if (tokens.length != get.getInstructionSize(opcode)) {
                throw new Exception("Invalid size instruction: " + line);
            }
            String arg1 ="", arg2="", arg3="";
            if(get.getInstructionSize(opcode)==2){
                arg1 = tokens[1].toUpperCase();
            }else if(get.getInstructionSize(opcode)==3){
                arg1 = tokens[1].toUpperCase();
                arg2 = tokens[2].toUpperCase();
            } else if (get.getInstructionSize(opcode)==4){
                arg1 = tokens[1].toUpperCase();
                arg2 = tokens[2].toUpperCase();
                arg3 = tokens[3].toUpperCase();
            }
        }

        
    }
    private int assemble(String instruction) throws Exception {
        String[] tokens = instruction.split(" ");
        String opcode = tokens[0].toUpperCase();
        if (tokens.length != get.getInstructionSize(opcode)) {
            throw new Exception("Invalid size instruction: " + instruction);
        }
        
        String arg1 ="", arg2="", arg3="";
        if(get.getInstructionSize(opcode)==2){
            arg1 = tokens[1].toUpperCase();
        }else if(get.getInstructionSize(opcode)==3){
            arg1 = tokens[1].toUpperCase();
            arg2 = tokens[2].toUpperCase();
        } else if (get.getInstructionSize(opcode)==4){
            arg1 = tokens[1].toUpperCase();
            arg2 = tokens[2].toUpperCase();
            arg3 = tokens[3].toUpperCase();
        }
        

        int stackTop ;
        switch (opcode) {
            case "LDA":
                registers[get.getRegisterIndex(arg1)] = getValue(arg2);
                return 0;
            case "ADD":
                registers[get.getRegisterIndex(arg1)] = registers[get.getRegisterIndex(arg1)] + getValue(arg2);
                return 1;
            case "HLT":
                return 2;
            case "STR":
                memory[variableMap.get(arg1)] = registers[get.getRegisterIndex(arg2)];
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
                registers[get.getRegisterIndex(arg1)] = poppedValue;
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
            return registers[get.getRegisterIndex(operand)];
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
                    registers[get.getRegisterIndex(arg1)] = value;
                    pc += get.getInstructionSize(opcode);
                    break;
                case "ADD":
                    int result = registers[get.getRegisterIndex(arg1)] + getValue(arg2);
                    registers[get.getRegisterIndex(arg1)] = result;
                    pc += get.getInstructionSize(opcode);
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