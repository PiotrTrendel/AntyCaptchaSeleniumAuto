package antycaptcha.pages;

import antycaptcha.pages.base.Solutions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

public class RadioButtonsPage extends Solutions {

    @FindBy(xpath = "//td[contains(text(),\"In the group\")]/code")
    private List<WebElement> coloursToBeClicked;

    @FindBy(xpath = "//div[@class='six columns']")
    private List<WebElement> groups;

    @FindBy(id = "solution")
    private WebElement checkSolutionButton;

    @FindBy(xpath = "//code[@class='wrap']")
    private WebElement solutionText;


    private List<String> getColoursToBeClicked() {
        return coloursToBeClicked.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private List<String> getColoursAvailableByGroupId(int groupId) {
        WebElement group = groups.get(groupId);
        List<String> colours = Arrays.asList(group.getText().split("\n"));
        return colours.subList(1, colours.size());
    }

    private List<WebElement> getRadioButtonsByGroupId(int groupId) {
        return groups.get(groupId).findElements(By.xpath(getConfigProperty("colour.buttons")));
    }

    private void clickButtonsInEachGroup(WebDriver driver, boolean isTrailCorrect) {
        List<String> coloursToClick = getColoursToBeClicked();
        for (int groupId = 0; groupId < coloursToClick.size(); groupId++) {
            String targetColour = coloursToClick.get(groupId);
            List<String> availableColours = getColoursAvailableByGroupId(groupId);
            List<WebElement> radioButtons = getRadioButtonsByGroupId(groupId);
            int index = availableColours.indexOf(targetColour);
            if (index != -1) {
                WebElement radioButton = isTrailCorrect && index < radioButtons.size() ? radioButtons.get(index) : radioButtons.get(0);
                clickElement(driver, radioButton);
            }
        }
    }

    @Override
    protected void doExercise(WebDriver driver, Boolean isTrailCorrect) {
        clickButtonsInEachGroup(driver, isTrailCorrect);
    }

    public void checkSolution(WebDriver driver) {
        clickElement(driver, checkSolutionButton);
    }

    public void clickRequiredRadioButtons(WebDriver driver) {
        doExercise(driver, true);
    }

    public void clickWrongRadioButtons(WebDriver driver) {
        doExercise(driver, false);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }
}
