package antycaptcha.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeExtractor {

    public String extractTrail(String elementText) {
        String trail;
        String regex = "Trail set to:\\s([a-b1-2]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(elementText);
        if (matcher.find()) {
            trail = matcher.group(1);
        } else {
            trail = "no such trail.";
        }
        return trail;
    }
}
