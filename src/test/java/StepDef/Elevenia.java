package StepDef;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Elevenia {
    WebDriver driver;
    @Given("Open web URL {string}")
    public void openWebURL(String URL) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @And("Search {string}")
    public void search(String search) {
        driver.findElement(By.xpath("//input[@id='AKCKwd']")).sendKeys(search, Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @And("Select {string}")
    public void select(String action) {
        driver.findElement(By.xpath("//a[contains(text(),'"+action+"')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @And("Select first product")
    public void selectFirstProduct() {
        driver.findElement(By.xpath("//li[@id='prod_28911164']")).click();
    }

    @And("Fill with quantity {string}")
    public void fillWithQuantity(String three) {
        driver.findElement(By.id("optionStock0")).clear();
        driver.findElement(By.id("optionStock0")).sendKeys(three);
    }

    @And("Add to cart")
    public void addToCart() {
        driver.findElement(By.xpath("//a[contains(text(),'Tambahkan ke Cart')]")).click();
    }

    @And("Verify pop up, if there is any pop up confirm yes {string}, if not add to cart")
    public void verifyPopUpIfThereIsAnyPopUpConfirmYesIfNotAddToCart(String ya) {
        driver.findElement(By.xpath("//a[contains(text(),'"+ya+"')]")).click();
    }

    @And("Select change courier")
    public void selectChangeCourier() throws InterruptedException {
        driver.findElement(By.xpath("//a[@name='deliveryChangePopup']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Thread.sleep(1000);
    }

    @When("Select batal on the pop up")
    public void selectBatalOnThePopUp() {
        driver.switchTo().frame("ifrLayer");
        driver.findElement(By.xpath("//a[normalize-space()='Batal']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @And("Select {string} and {string}")
    public void selectAnd(String hapus, String ok) {
        driver.navigate().refresh();
        driver.findElement(By.xpath("//a[contains(text(),'"+hapus+"')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//a[@id='chkDelPopY']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Then("Verify product not exist again on cart")
    public void verifyProductNotExistAgainOnCart() {
        //verify
        driver.findElement(By.xpath("//td[@class='dataNone']")).isDisplayed();
        driver.close();
        driver.quit();
    }
}
