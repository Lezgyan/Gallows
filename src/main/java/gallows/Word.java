package gallows;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Word {

    private String text;

    private String help;

    public Word(String text, String help) {
        this.text = text.toUpperCase();
        this.help = help;
    }

    public void setText(String text) {
        this.text = text.toUpperCase();
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
