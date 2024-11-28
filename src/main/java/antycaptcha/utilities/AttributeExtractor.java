package antycaptcha.utilities;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeExtractor {

    public String extractSeed(List<WebElement> elements) {
        String seed = null;
        for (int i = 0; i < elements.size(); i++) {
            String btnInnerHtml = elements.get(i).getAttribute("innerHTML");
            String regex = "seed=([a-f0-9-]{36})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(btnInnerHtml);
            if (matcher.find()) {
                seed = matcher.group(1);
                break;
            } else {
                seed = "no seed number.";
            }
        }
        return seed;
    }

    public String extractTrail(String elementText) {
        String trail;
        String regex = "Trail set to:\\s([a-b1-2]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(elementText);
        if (matcher.find()) {
            trail = matcher.group(1);
        } else {
            trail = "no seed number.";
        }
        return trail;
    }
}
