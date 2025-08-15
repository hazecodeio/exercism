class RotationalCipher {

    char[] encoder = new char[26];

    RotationalCipher(int shiftKey) {
        for (int k = 0; k < 26; k++)
            encoder[k] = (char) ('A' + (k + shiftKey) % 26);
    }

    String rotate(String data) {
        return transform(data, encoder);
    }

    private String transform(String str, char[] code) {
        char[] msg = str.toCharArray();
        for (int k = 0; k < msg.length; k++)
            if (Character.isAlphabetic(msg[k])) {
                int j = Character.toUpperCase(msg[k]) - 'A';
                msg[k] = Character.isLowerCase(msg[k]) ? Character.toLowerCase(code[j]) : code[j];
            }
        return String.valueOf(msg);
    }
}
