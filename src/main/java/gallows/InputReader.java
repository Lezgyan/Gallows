package gallows;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class InputReader {

    public static Character readLetter(String line) {
        String str = line.trim();
        if (str.length() == 1) {
            char character = str.charAt(0);
            if (Character.isLetter(character)) {
                return Character.toUpperCase(character);
            }
        }
        return null;
    }

    public static Command readCommand(String line) {
        return Command.fromString(line.trim());
    }

    public static Integer readNumber(String line) {
        try {
            String num = line.trim();
            return Integer.parseInt(num);
        } catch (Exception e) {
            return null;
        }
    }
}
