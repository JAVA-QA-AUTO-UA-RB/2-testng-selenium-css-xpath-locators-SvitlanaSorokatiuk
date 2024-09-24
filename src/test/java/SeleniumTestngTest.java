import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;

// This test class inherits BasicSetupTest class, where the browser is initialized
// browser variable is available here as it's inherited, so you'll have it available at any place
public class SeleniumTestngTest extends BasicSetupTest {

    @Test
    public void abTestingPageHasSpecificTextTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/");
        // Write your code here (just an example provided)
        WebElement abTestingTaskLink = browser.findElement(By.linkText("A/B Testing"));
        abTestingTaskLink.click();

        // Write your assertions in the after the steps of scenario are executed to validate results
        Assert.assertTrue(true);
    }

    // Write the rest of TEST METHODS according to the task here, each method checking one scenario described in README.md file
    // In the end you should have a set of test methods each of them describing some specific scenario

    //@Test
    //public void abTestingPageHasAnotherSpecificTextTest() throws InterruptedException {
      //  browser.get("https://the-internet.herokuapp.com/abtest");
        //WebElement abTestingAnotherTaskLink = browser.findElement(By.linkText("A/B Test Control"));
        //Assert.assertTrue(true);
    //}

    @Test
    public void addRemoveElementsTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addDeleteButton = browser.findElement(By.xpath("/html/body/div[2]/div/div/button"));
        for (int i=0; i<3; i++) {
            addDeleteButton.click();
        }
        List<WebElement> deleteButtons = (List<WebElement>) browser.findElements(By.cssSelector("button.added-manually"));
        Assert.assertEquals(3, deleteButtons.size());

        for (WebElement deleteButton : deleteButtons) {
            deleteButton.click();
        }

        deleteButtons = (List<WebElement>) browser.findElements(By.cssSelector("button.added-manually"));
        Assert.assertTrue(deleteButtons.isEmpty());
    }

    @Test
    public void checkboxesTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkboxes = browser.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @Test
    public void dropdownTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = browser.findElement(By.cssSelector("option[value='2']"));
        dropdown.click();
        Assert.assertTrue(dropdown.isSelected());
    }

    @Test
    public void formAuthenticationTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/login");
        WebElement username = browser.findElement(By.cssSelector("#username"));
        username.sendKeys("tomsmith");
        WebElement password = browser.findElement(By.cssSelector("#password"));
        password.sendKeys("SuperSecretPassword!");
        WebElement loginButton = browser.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
        WebElement successMessage = browser.findElement(By.cssSelector("div#flash"));
        Assert.assertTrue(successMessage.isDisplayed());
        WebElement logoutButton = browser.findElement(By.cssSelector(".button"));
        logoutButton.click();
        WebElement loginPageButton = browser.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(loginPageButton.isDisplayed());
    }

    @Test
    public void dragAndDropTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement a = browser.findElement(By.xpath("//*[@id=\"column-a\"]"));
        WebElement b = browser.findElement(By.xpath("//*[@id=\"column-b\"]"));
        Actions actions = new Actions(browser);
        actions.dragAndDrop(a, b).perform();
        WebElement text = browser.findElement(By.xpath("//*[@id=\"column-b\"]/header"));
        Assert.assertEquals("A", text.getText());
    }

    @Test
    public void horizontalSliderTest() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/horizontal_slider");
        WebElement slider = browser.findElement(By.cssSelector("input[type='range']"));
        slider.sendKeys("2");
        Actions actions = new Actions(browser);
        actions.clickAndHold(slider).moveByOffset(2, 0).release().perform();
        Thread.sleep(3000);
        WebElement sliderValue = browser.findElement(By.cssSelector("#range"));
        Assert.assertEquals("2.5", sliderValue.getText());
    }


}
