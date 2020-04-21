import java.util.Scanner;

public class Vigenere extends Cipher {

    Scanner scan;

    public Vigenere() {
    }

    public void Encrypt() {

        String plainText = super.getPlainText();
        String longKey = generateLongKey(super.getKey(), plainText.length());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < plainText.length(); i++){

            int newChar = (plainText.charAt(i) + longKey.charAt(i)) %26;
            newChar += 65;

            sb.append((char)newChar);
        }

        super.setCipherText(sb.toString());
    }

    public void Decrypt() {

        String cipherText = super.getCipherText();
        String longKey = generateLongKey(super.getKey(), cipherText.length());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < cipherText.length(); i++){

            int newChar = (cipherText.charAt(i) - longKey.charAt(i) + 26) %26;
            newChar += 65;

            sb.append((char)newChar);
        }

        super.setPlainText(sb.toString());
    }

    private String generateLongKey(String key, int length) {

        StringBuilder longKey = new StringBuilder();
        StringBuilder keyChunks = new StringBuilder();

        for(int i = 0; i < length; i++){

            if(i == key.length())
                i = 0;

            if(length == key.length())
                break;

            key += key.charAt(i);
        }

        return key;
    }

    public String editKey() {
        //CHECK: key contains just chars from a-z A-Z
        scan = new Scanner(System.in);
        String key;

        while (true) {
                System.out.println("Enter key value: ");
                key = scan.nextLine();

                if (key.matches("[a-zA-Z]+")) {
                    return super.processText(key);
                } else
                    System.err.println("Enter just letters a-z");
        }
    }





}
