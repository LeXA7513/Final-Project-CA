public class get {
    public static String getCode(Simulator simulate) {
        String text = "";
        for(int i=0;i<simulate.MEMORY_SIZE;i++){
            if(simulate.memorybinary[i] != null){
                text =text + simulate.memorybinary[i] +" ";
            }
            else{
                text = binaryConversion.fromBinaryText(text);
                break;
            }
            }  
        return text;
    }
    public static String getData(Simulator simulate) {
        String text = "";
        Boolean notend = true;
        int compteur = 0, repere;

        for(int i=simulate.MEMORY_SIZE-1;i>0;i--){
            repere = i;
            if(simulate.memorybinary[i] != null){
                if(compteur == 0){
                    text =text + binaryConversion.fromBinaryText(simulate.memorybinary[i]);
                } else if(compteur==1) {
                    compteur = 0;
                    String nombre = "";
                    for (int a=repere;a>repere-4;a--){
                        nombre = simulate.memorybinary[a] + nombre;
                    }
                    i = i-4;
                    text =text +" "+ binaryConversion.fromBinaryNumber(nombre)+binaryConversion.fromBinaryText("00001101 00001010");
                }    
            }
            else if(notend){
                compteur ++;
                text = text + " ";
                if (compteur==2){
                    notend = false;
                }
            }
            else{
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

    public static int getInstructionSize(String opcode) throws Exception {
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
}

    
