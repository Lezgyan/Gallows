package gallows;

import lombok.Getter;

@Getter
public enum Command {

    START("start"), STOP("stop"), OTHER("Unknown command"), HELP("help"), SETTINGS("settings");

    private final String text;

    Command(String text) {
        this.text = text;
    }

    public static Command fromString(String str) {
        for (Command i : values()) {
            if (i.text.equalsIgnoreCase(str)) {
                return i;
            }
        }
        return OTHER;
    }

}
