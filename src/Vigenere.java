import java.util.Scanner;

public class Vigenere extends Cipher {

    Scanner scan;

    public Vigenere() {
    }

    public void Encrypt() {

        String plainText = super.getPlainText();
        String longKey = generateLongKey(super.getKey(), plainText.length());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {

            int newChar = (plainText.charAt(i) + longKey.charAt(i)) % 26;
            newChar += 65;

            sb.append((char) newChar);
        }

        super.setCipherText(sb.toString());
    }

    public void Decrypt() {

        String cipherText = super.getCipherText();
        String longKey = generateLongKey(super.getKey(), cipherText.length());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {

            int newChar = (cipherText.charAt(i) - longKey.charAt(i) + 26) % 26;
            newChar += 65;

            sb.append((char) newChar);
        }

        super.setPlainText(sb.toString());
    }

    private String generateLongKey(String key, int length) {

        StringBuilder longKey = new StringBuilder();
        StringBuilder keyChunks = new StringBuilder();

        for (int i = 0; i < length; i++) {

            if (i == key.length())
                i = 0;

            if (length == key.length())
                break;

            key += key.charAt(i);
        }

        return key;
    }

    public boolean checkKey(String key) {

        if (key.matches("[a-zA-Z]+"))
            return true;
        else
            return false;
    }


}
