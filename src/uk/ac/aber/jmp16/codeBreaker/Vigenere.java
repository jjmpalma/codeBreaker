package uk.ac.aber.jmp16.codeBreaker;

public class Vigenere extends Cipher {
    /**
     * Empty constructor
     */
    public Vigenere() {
    }

    /**
     * Encryption from plain text to cipher text. Parameters given by the Cipher class
     * Vigenère Cipher algorithm
     */
    public void Encrypt() {

        String plainText = super.getPlainText();
        String longKey = generateLongKey(super.getKey(), plainText.length());
        StringBuilder cipherText = new StringBuilder();

        /*
         * 1- Find newChar using coordinates given by the plain text and long key
         * 2- Go over each plain text char and change it for the new char
         */
        for (int i = 0; i < plainText.length(); i++) {

            int newChar = (plainText.charAt(i) + longKey.charAt(i)) % 26;
            newChar += 65;

            cipherText.append((char) newChar);
        }

        super.setCipherText(cipherText.toString());
    }

    /**
     * Decryption from cipher text to plain text. Parameters given by the Cipher class
     * Vigenère Cipher algorithm
     */
    public void Decrypt() {

        String cipherText = super.getCipherText();
        String longKey = generateLongKey(super.getKey(), cipherText.length());
        StringBuilder sb = new StringBuilder();

        /*
         * 1- Find newChar using coordinates given by the cipher text and long key
         * 2- Go over each cipher text char and change it for the new char
         */
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

        //Creates key repeating it over and over until reach the given length
        for (int i = 0; i < length; i++) {

            if (i == key.length())
                i = 0;

            if (length == key.length())
                break;

            key += key.charAt(i);
        }

        return key;
    }

    /**
     * Check key format: Only letters from A-Z
     * @param key
     * @return true when format is correct and false otherwise
     */
    public boolean checkKey(String key) {

        if (key.matches("[a-zA-Z]+"))
            return true;
        else
            return false;
    }


}
