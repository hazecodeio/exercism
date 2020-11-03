class Acronym {

    private String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        String[] split = phrase.split("[^a-zA-Z']");
        StringBuilder acronymBuilder = new StringBuilder();
        for (String s : split) {
            if (s.isBlank())
                continue;
            acronymBuilder.append(Character.toUpperCase(s.charAt(0)));
        }

        return acronymBuilder.toString();
    }

}
