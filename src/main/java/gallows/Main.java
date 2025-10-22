package gallows;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    private static final String FILE_NAME = "src/main/resources/data/dictionary.json";

    public static void main(String[] args) {
        MainGame mainGame = new MainGame(FILE_NAME);
        mainGame.start();
    }
}
