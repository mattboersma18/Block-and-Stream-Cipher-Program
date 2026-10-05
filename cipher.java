import java.io.*;

public class cipher {
    public static void main(String[] args) {
        String cipher = args[0];
        String input = args[1];
        String output = args[2];
        String key = args[3];
        String mode = args[4];

        if (!isValidCipher(cipher)) {
            System.err.println("Invalid Function Type");
            return;
        }
        if (!isValidFile(input)) {
            System.err.println("Input File Does Not Exist");
            return;
        }
        if (!isValidFile(key)) {
            System.err.println("Key File Does Not Exist");
            return;
        }
        if (!isValidMode(mode)) {
            System.err.println("Invalid Mode Type");
            return;
        }

        String cipherType = setCipher(cipher);
        String modeType = setMode(mode);

        if(cipherType.equals("Block")){
            block.blockCipher(input, key, modeType, output);
        } else if(cipherType.equals("Stream")){
            stream.streamCipher(input, key, modeType, output);
        }
    }

    public static boolean isValidCipher(String cipher) {
        if (cipher.equals("B") || cipher.equals("S") && cipher.length() == 1) {
            return true;
        }
        return false;
    }

    public static boolean isValidMode(String mode) {
        if (mode.equals("E") || mode.equals("D") && mode.length() == 1) {
            return true;
        }
        return false;
    }

    public static String setMode(String mode) {
        String modeType = "";
        if (mode.equals("E")) {
            modeType = "Encrypt";
        } else if (mode.equals("D")) {
            modeType = "Decrypt";
        }
        return modeType;
    }

    public static String setCipher(String cipher) {
        String cipherType = "";
        if (cipher.equals("B")) {
            cipherType = "Block";
        } else if (cipher.equals("S")) {
            cipherType = "Stream";
        }
        return cipherType;
    }

    public static boolean isValidFile(String file) {
        File f = new File(file);
        if (f.exists() && f.isFile()) {
            return true;
        }
        return false;
    }
}