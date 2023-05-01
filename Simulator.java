import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Simulator {
    private static final int MEMORY_SIZE = 4096;
    private static final int REGISTER_COUNT = 4;
    private static final int INSTRUCTION_SIZE = 3;
    private static final int MAX_CONSTANT_VALUE = 1023;

    private int[] memory;
    private int[] registers;
    private int pc;

    private Map<String, Integer> variableMap;

    public Simulator(String filePath) {
        memory = new int[MEMORY_SIZE];
        registers = new int[REGISTER_COUNT];
        variableMap = new HashMap<>();

        loadProgram(filePath);
    }

    private void loadProgram(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            boolean isDataSection = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("!") || line.isEmpty()) {
                    // Ignore comments and empty lines
                    continue;
                } else if (line.startsWith("#data")) {
                    isDataSection = true;
                    continue;
                } else if (line.startsWith("#code")) {
                    isDataSection = false;
                    continue;
                }

                if (isDataSection) {
                    String[] tokens = line.split(" ");
                    String variableName = tokens[0];
                    int initialValue = Integer.parseInt(tokens[1]);
                    variableMap.put(variableName, initialValue);
                } else {
                    memory[pc] = assemble(line);
                    pc += INSTRUCTION_SIZE;
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int assemble(String instruction) throws Exception {
        String[] tokens = instruction.split(" ");

        if (tokens.length != INSTRUCTION_SIZE) {
            throw new Exception("Invalid instruction: " + instruction);
        }

        String opcode = tokens[0].toUpperCase();
        String arg1 = tokens[1].toUpperCase();
        String arg2 = tokens[2].toUpperCase();

        switch (opcode) {
            case "LDA":
                int value = getValue(arg2);
                registers[getRegisterIndex(arg1)] = value;
                return 0;
            case "ADD":
                int result = registers[getRegisterIndex(arg1)] + getValue(arg2);
                registers[getRegisterIndex(arg1)] = result;
                return 1;
            case "HLT":
                return 2;
            default:
                throw new Exception("Invalid opcode: " + opcode);
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
                    pc += INSTRUCTION_SIZE;
                    break;
                case "ADD":
                    int result = registers[getRegisterIndex(arg1)] + getValue(arg2);
                    registers[getRegisterIndex(arg1)] = result;
                    pc += INSTRUCTION_SIZE;
                    break;
                case "HLT":
                    return;
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