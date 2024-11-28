package antycaptcha.pages;

import antycaptcha.pages.base.Solutions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadioButtonsPage extends Solutions {

    @FindBy(xpath = "//tbody/tr/td[2]/code")
    protected List<WebElement> coloursToBeClicked;

    @FindBy(xpath = "//div[@class='six columns']")
    protected List<WebElement> groups;

    @FindBy(id = "solution")
    protected WebElement checkSolutionButton;

    @FindBy(xpath = "//code[@class='wrap']")
    protected WebElement solutionText;


    private List<String> getColoursToBeClicked(WebDriver driver) {
        List<String> coloursAsString = new ArrayList<>();
        for (WebElement colour : coloursToBeClicked) {
            String text = getText(driver, colour);
            coloursAsString.add(text);
        }
        return coloursAsString;
    }

    private ArrayList<String> getColoursAvailableByGroupId(WebDriver driver, int groupId) {
        WebElement group = groups.get(groupId);
        String[] coloursAvailable = getText(driver, group).split("\n");
        ArrayList<String> coloursAvailableAsList = new ArrayList<>(Arrays.asList(coloursAvailable));
        coloursAvailableAsList.removeFirst();
        return coloursAvailableAsList;
    }

    private List<WebElement> getRadioButtonsByGroupId(int groupId) {
        WebElement group = groups.get(groupId);
        List<WebElement> radioButtons = group.findElements(By.xpath(".//input[@type='radio']"));
        return radioButtons;
    }

    private void clickButtonsInEachGroup(WebDriver driver, Boolean isTrailCorrect) {
        List<String> colours = getColoursToBeClicked(driver);
        for (int i=0; i<colours.size(); i++) {
            ArrayList<String> coloursAvailable = getColoursAvailableByGroupId(driver, i);
            for (int j=0; j<coloursAvailable.size(); j++) {
                if(coloursAvailable.get(j).equals(colours.get(i)) && isTrailCorrect) {
                    WebElement radioButton = getRadioButtonsByGroupId(i).get(j);
                    clickElement(driver, radioButton);
                    break;
                } else if (coloursAvailable.get(j).equals(colours.get(i)) && !isTrailCorrect) {
                    WebElement radioButton;
                    if (getRadioButtonsByGroupId(i).size() == j) {
                        radioButton = getRadioButtonsByGroupId(i).getFirst();
                    } else {
                        radioButton = getRadioButtonsByGroupId(i).getLast();
                    }
                    clickElement(driver, radioButton);
                    break;
                }
            }
        }
    }

    @Override
    protected void checkSolution(WebDriver driver, Boolean isTrailCorrect) {
        clickButtonsInEachGroup(driver, isTrailCorrect);
        clickElement(driver, checkSolutionButton);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }
}
