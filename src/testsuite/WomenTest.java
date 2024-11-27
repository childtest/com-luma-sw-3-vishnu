package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;
import java.util.stream.Collectors;

public class WomenTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    // set up web browser

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() {

        try {
            Thread.sleep(2000);
            clickOnElement(By.xpath("//button[@aria-label = 'Consent']"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //mouser hover on women menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Women']"));
        // mouse hover on tops
        mouseHoverToElement(By.xpath("//a[@id='ui-id-9']"));
        // click on jackets
        mouseHoverToElementAndClick(By.xpath("//a[@id='ui-id-11']"));

        selectByVisibleTextDropdown(By.id("sorter"), "Product Name");

        // get elements of products name
        List<String> productNames = getWebElements(By.xpath("//a[@class = 'product-item-link']")).stream()
                .map(WebElement::getText)
                .toList();

        // verify  product name displayed in alphabetical order
        Assert.assertTrue("Products are not sorted alphabetically by name",
                isSortedAlphabetically(productNames.toArray(new String[0])));

    }

    @Test
    public void verifyTheSortByPriceFilter() {

        //mouser hover on women menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Women']"));
        // mouse hover on tops
        mouseHoverToElement(By.xpath("//a[@id='ui-id-9']"));
        // click on jackets
        mouseHoverToElementAndClick(By.xpath("//a[@id='ui-id-11']"));

        selectByVisibleTextDropdown(By.id("sorter"), "Price");

        // Verify product prices are sorted in ascending order
        List<Double> productPrices = getWebElements(By.cssSelector(".price-wrapper.price")).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "").replace(",", "")))
                .collect(Collectors.toList());

        Assert.assertTrue("Products are not sorted by price in ascending order!",
                isSortedAscending(productPrices));

    }


    @After
    public void tearDown() {
        closeBrowser();
    }
}
