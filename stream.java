public class stream {
    
    public static void streamCipher(String inputFile, String keyFile, String mode, String output){
        
        String message = block.readFile(inputFile);

        String newMessage = streamEncrypt(message, keyFile);

        block.writeFile(output, newMessage);

    }

    public static String streamEncrypt(String message, String keyFile) {
        String key = block.readFile(keyFile);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < message.length(); i++) {

            if(key.isEmpty()) {
                throw new IllegalArgumentException("Key is empty");
            }
            
            char charA = message.charAt(i);
            char charB = key.charAt(i % key.length());

            sb.append((char) (charA ^ charB));
        }
        return sb.toString();
    }

}
