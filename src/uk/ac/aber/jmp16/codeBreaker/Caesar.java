package uk.ac.aber.jmp16.codeBreaker;

public class Caesar extends Cipher {
    /**
     * Empty constructor
     */
    public Caesar() {
    }

    /**
     * Encryption from plain text to cipher text. Parameters given by the Cipher class
     * Caesar Cipher Shift algorithm
     */
    public void Encrypt() {

        String plainText = super.getPlainText();
        int key = Integer.parseInt(super.getKey());

        // Sets the plain text using shiftChar method which will shift each char
        super.setCipherText(shiftChar(plainText, key));
    }

    /**
     * Decryption from cipher text to plain text. Parameters given by the Cipher class
     * Caesar Cipher Shift algorithm
     */
    public void Decrypt() {

        String cipherText = super.getCipherText();
        int key = -Integer.parseInt(super.getKey());

        // Sets the plain text using shiftChar method which will shift each char (negative key)
        super.setPlainText(shiftChar(cipherText, key));
    }

    private String shiftChar(String text, int key){

        //String builder will store the shifted chars going over text and shifting key places
        StringBuilder shiftText = new StringBuilder();

        int offset = key % 26;

        for (char c : text.toCharArray()) {
            c += offset;
            if (c > 90)
                c -= 26;
            if (c < 65)
                c += 26;

            shiftText.append(c);
        }

        return shiftText.toString();
    }

    /**
     * Check key format: Only numeric digits
     * @param key
     * @return true when format is correct and false otherwise
     */
    public boolean checkKey(String key) {
            if (key.matches("-?[0-9]+"))
                return true;
            else
                return false;
    }
}

