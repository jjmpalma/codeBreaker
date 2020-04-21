public abstract class Cipher {

    private String plainText;
    private String cipherText;
    private String key;

    abstract void Encrypt();

    abstract void Decrypt();

    abstract boolean checkKey(String key);

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }

    public String processText(String text) {
        text = text.replaceAll("[^A-Za-z]+", "");
        text = text.toUpperCase();

        return text;
    }

}
