package uk.ac.aber.jmp16.codeBreaker;

public abstract class Cipher {

    private String plainText;
    private String cipherText;
    private String key;

    /**
     * Encryption from plain text to cipher text. Parameters given by the Cipher class
     */
    public abstract void Encrypt();

    /**
     * Decryption from cipher text to plain text. Parameters given by the Cipher class
     */
    public abstract void Decrypt();

    /**
     * Check key format for the three given cipher algorithms
     * @param key
     * @return true when format is correct and false otherwise
     */
    public abstract boolean checkKey(String key);

    /**
     * Return plain text
     * @return plainText
     */
    public String getPlainText() {
        return plainText;
    }

    /**
     * Set plain text
     * @param plainText
     */
    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    /**
     * Return cipher text
     * @return cipherText
     */
    public String getCipherText() {
        return cipherText;
    }

    /**
     * Set cipher text
     * @param cipherText
     */
    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    /**
     * Return key
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set key
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

}
