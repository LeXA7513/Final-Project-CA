import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class AssemblySimulator {
    private static final int MEMORY_SIZE = 4096;
    private static final int STACK_SIZE = 4096;
    private static final int NUM_REGISTERS = 4;
    private int[] memory = new int[MEMORY_SIZE];
    private int[] stack = new int[STACK_SIZE];
    private int[] registers = new int[NUM_REGISTERS];
    private int pc = 0; // program counter


    public void loadProgramFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty() && !line.startsWith("//")) {
                    parseInstruction(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }

    private void parseInstruction(String instruction) {
        String[] parts = instruction.split("\\s+");
        String opcode = parts[0].toUpperCase();
        switch (opcode) {
            case "LDA":
                if (parts.length == 3) {
                    loadRegister(parts[1], getValue(parts[2]));
                } else {
                    System.err.println("Invalid LDA instruction: " + instruction);
                }
                break;
            case "STR":
                if (parts.length == 3) {
                    storeValue(parts[1], getValue(parts[2]));
                } else {
                    System.err.println("Invalid STR instruction: " + instruction);
                }
                break;
            case "PUSH":
                if (parts.length == 2) {
                    pushValue(getValue(parts[1]));
                } else {
                    System.err.println("Invalid PUSH instruction: " + instruction);
                }
                break;
            case "POP":
                if (parts.length == 2) {
                    popValue(parts[1]);
                } else {
                    System.err.println("Invalid POP instruction: " + instruction);
                }
                break;
            case "AND":
                if (parts.length == 3) {
                    andRegisters(parts[1], getValue(parts[2]));
                } else {
                    System.err.println("Invalid AND instruction: " + instruction);
                }
                break;
            case "OR":
                if (parts.length == 3) {
                    orRegisters(parts[1], getValue(parts[2]));
                } else {
                    System.err.println("Invalid OR instruction: " + instruction);
                }
                break;
            case "NOT":
                if (parts.length == 2) {
                    notRegister(parts[1]);
                } else {
                    System.err.println("Invalid NOT instruction: " + instruction);
                }
                break;
            case "ADD":
                if (parts.length == 3) {
 //                   addRegisters(parts[1], getValue(parts[2]));
                } else {
                    System.err.println("Invalid ADD instruction: " + instruction);
                }
                break;
            case "SUB":
                if (parts.length == 3) {
 //                  subRegisters(parts[1], getValue(parts[2]));
                } else {
                    System.err.println("Invalid SUB instruction: " + instruction);
                }
                break;
            default:
                System.err.println("Unknown opcode: " + opcode);
                break;
        }
    }
    
    private boolean isValidMemoryAddress(String arg) {
        if (!arg.matches("\\d+")) {
            return false;
        }
        int address = Integer.parseInt(arg);
        return address >= 0 && address < MEMORY_SIZE;
    }
    
    private boolean isValidRegister(String reg) {
        if (reg.length() != 2 || reg.charAt(0) != 't' || !Character.isDigit(reg.charAt(1))) {
            return false;
        }
        int regNum = Integer.parseInt(reg.substring(1));
        return regNum >= 0 && regNum < NUM_REGISTERS;
    }
    

    private int getValue(String arg) {
        if (arg.startsWith("t")) {
            return registers[Integer.parseInt(arg.substring(1))];
        } else if (arg.startsWith("0x")) {
            return Integer.parseInt(arg.substring(2), 16);
        } else {
            return memory[Integer.parseInt(arg)];
        }
    }

    private void loadRegister(String reg, int value) {
        if (isValidRegister(reg)) {
            registers[Integer.parseInt(reg.substring(1))] = value;
        } else {
            System.err.println("Invalid register: " + reg);
        }
    }

    private void storeValue(String arg, int value) {
        if (isValidMemoryAddress(arg)) {
            memory[Integer.parseInt(arg)] = value;
        } else {
            System.err.println("Invalid memory address: " + arg);
        }
    }

    private void pushValue(int value) {
        if (stack.length > pc) {
            stack[pc++] = value;
        } else {
            System.err.println("Stack overflow");
        }
    }
    private void popValue(String reg) {
        if (isValidRegister(reg)) {
            if (pc > 0) {
                registers[Integer.parseInt(reg.substring(1))] = stack[--pc];
            } else {
                System.err.println("Stack underflow");
            }
        } else {
            System.err.println("Invalid register: " + reg);
        }
    }
    
    private void andRegisters(String reg1, int value) {
        if (isValidRegister(reg1)) {
            registers[Integer.parseInt(reg1.substring(1))] &= value;
        } else {
            System.err.println("Invalid register: " + reg1);
        }
    }
   
    private void orRegisters(String reg1, int value) {
        int reg1Num = Integer.parseInt(reg1.substring(1));
        registers[reg1Num] |= value;
    }
    
    private void notRegister(String reg) {
        int regNum = Integer.parseInt(reg.substring(1));
        registers[regNum] = ~registers[regNum];
    }
    
    
}
