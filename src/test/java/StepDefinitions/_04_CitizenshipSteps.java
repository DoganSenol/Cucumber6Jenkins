package StepDefinitions;

import Pages.DialogContent;
import Pages.LeftNav;
import Utilities.GWD;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class _04_CitizenshipSteps {
    LeftNav ln = new LeftNav();
    DialogContent dc = new DialogContent();

    @And("Navigate to Citizenship")
    public void navigateToCitizenship() {
        ln.myClick(ln.setup);
        ln.myClick(ln.parameters);
        ln.myClick(ln.citizenShip);
    }

    @When("Create a Citizenship")
    public void createACitizenship() {
        String citizenshipName = RandomStringUtils.randomAlphanumeric(8);//8 harf
        String citizenshipShCo = RandomStringUtils.randomNumeric(4); // 4 rakam

        dc.myClick(dc.addButton);
        dc.mySendKeys(dc.nameInput, citizenshipName);
        dc.mySendKeys(dc.ShortName, citizenshipShCo);
        dc.myClick(dc.saveButton);
    }

    @When("Create a Citizenship name as {string} short name as {string}")
    public void createACitizenshipNameAsShortNameAs(String name, String shortname) {
        dc.myClick(dc.addButton);
        dc.mySendKeys(dc.nameInput, name);
        dc.mySendKeys(dc.ShortName, shortname);
        dc.myClick(dc.saveButton);
    }

    @Then("Already exist message should be displayed")
    public void alreadyExistMessageShouldBeDisplayed() {
        dc.verifyContainsText(dc.alreadyExist, "already");
    }

    @When("User delete the {string}")
    public void userDeleteThe(String name) {

        dc.mySendKeys(dc.searchInput, name);
        dc.myClick(dc.searchButton);

        //beklet
        //1. StaleElemetn hatası verdi : erken buldum tez kaybettim
        //dc.wait.until(ExpectedConditions.elementToBeClickable(dc.searchButton));
        //wait.until(ExpectedConditions.stalenessOf(dc.deleteImageBtn)); //olabilir ama herzaman çözmez

        //2.yöntem sayfanın kendi waitini , loding ini yakalayalım. (en sağlam yöntem)
        //fuse-progress-bar/*    -> fuse-progress-bar ın çocukları
        // bu çocukların 0 olana bekle
        dc.wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//fuse-progress-bar/*"),0));

        dc.myClick(dc.deleteImageBtn);
        dc.myClick(dc.deleteDialogBtn);

    }
}