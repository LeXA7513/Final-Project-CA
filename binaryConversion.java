public class binaryConversion
{
    private static void getBitsText(StringBuilder sb, byte b) {
        for (int i = 0; i < 8; i++) {
            sb.append((b & 128) == 0 ? 0 : 1);
            b <<= 1;
        }
        sb.append(' ');
    }
    
    public static String toBinaryNumber(int number) {
        String NumberBinary = Integer.toBinaryString(number);
        if(NumberBinary.length()<32){
            String bit = "";
            for(int i = NumberBinary.length(); i<32 ;i++){
                bit = "0" + bit ;
            }
            NumberBinary = bit+NumberBinary;
        } else if (NumberBinary.length()>32){
            return null;
        }
        String n ="";
        for(int a = 0; a<4;a++){
            for (int i = 0; i<8; i++){
                n += Character.toString(NumberBinary.charAt(i+a*8)) ;
            }
            n= n +" ";
        }
            return n ;
    }
    public static String toBinaryText(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            getBitsText(sb, b);
        }
        return sb.toString().trim();
    }
 
    public static int fromBinaryNumber(String binary) {
        return Integer.parseInt(binary, 2);
    }
    public static String fromBinaryText(String binary) {
        String[] parts = binary.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            int intValue = Integer.parseInt(part, 2);
            sb.append(Character.toString((char) intValue));
        }
        return sb.toString();
    }
    
}