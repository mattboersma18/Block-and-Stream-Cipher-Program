import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class block {
    
    public static void blockCipher(String inputFile, String keyFile, String mode, String output){
        String message = readFile(inputFile);

        if (mode.equals("Encrypt")){

            message = padMessage(message);

            String encrypted = encrypt(message, readFile(keyFile));

            writeFile(output, encrypted);

        } else if (mode.equals("Decrypt")){

            String encoded = encrypt(message, readFile(keyFile));

            String decrypted = dePad(encoded, keyFile);

            writeFile(output, decrypted);
        }
    }

    public static String readFile(String fileName) {
        String content = "";
        Path filePath = Path.of(fileName);
        try {
            content = Files.readString(filePath);
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return content;
    }

    //Generative AI was partially used for this function. I had it originally wrote one way and it was close to the assingment.
    //The AI removed some lines and kept most of my original work, but it changed some tweaks.
    //What I was doing wrong was manually using a zero to pad the message, but I shoudlve been adding the 0x81 byte to pad the message.
    public static String padMessage(String message){
        int paddingLength = 16 - (message.length() % 16);
        byte[] padded = new byte[message.length() + paddingLength];
        System.arraycopy(message.getBytes(StandardCharsets.ISO_8859_1), 0, padded, 0, message.length());

        for (int i = message.length(); i < padded.length; i++) {
            padded[i] = (byte) 0x81;
        }

        return new String(padded, StandardCharsets.ISO_8859_1);
    }

    public static String encrypt(String message, String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            char keyChar = key.charAt(i % key.length());
            char encryptedChar = (char) (messageChar ^ keyChar);
            sb.append(encryptedChar);
        }
        return sb.toString();
    }

    //AI was used in this metod here to assit me with the StandardCharsets syntax, since Ive never used it, it helped me understand how I was converting the text to ASCII and the encrypted or decrpyted characters
    public static String dePad(String message, String key) {
        byte[] padded = message.getBytes(StandardCharsets.ISO_8859_1);
        int paddingLength = padded[padded.length - 1] & 0xFF;

        if (paddingLength < 1 || paddingLength > 16 || paddingLength > padded.length) {
            throw new IllegalArgumentException("Invalid padding");
        }
        
        byte[] unpadded = new byte[padded.length - paddingLength];
        System.arraycopy(padded, 0, unpadded, 0, unpadded.length);

        return new String(unpadded, StandardCharsets.ISO_8859_1);
    }

    public static void writeFile(String output, String content) {
        try {
            Files.write(Path.of(output), content.getBytes(StandardCharsets.ISO_8859_1));
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
