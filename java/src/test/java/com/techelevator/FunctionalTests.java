package com.techelevator;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class FunctionalTests {
    private static WebDriver webDriver;

    /* Creating an instance of the WebDriver is time-consuming
     * since it opens up a browser window. Therefore, we do this
     * only once at the start of the class and reuse the same
     * browser window for all tests. */
    @BeforeClass
    public static void openWebBrowserForTesting() {

        String homeDir = System.getProperty("user.home"); // ~ or $HOME
        /* The ChromeDriver requires a system property with the name "webdriver.chrome.driver" that
         * contains the directory path to the ChromeDriver executable. The following line assumes
         * we have installed the ChromeDriver in a known location (in our home directory) */
        System.setProperty("webdriver.chrome.driver", homeDir + "\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Before
    public void openHomePage() {
        webDriver.get("http://localhost:9000/");
    }

    @AfterClass
    public static void closeWebBrowser() {
        webDriver.quit();
    }

    /* In order for Selenium to interact with a web page, it needs to be
     * able to locate elements within the page. It does this using the
     * WebDriver.findElementBy(...) and WebDriver.findElementsBy(...)
     * methods. Both of these elements take a org.openqa.selenium.By
     * object as an argument. The following static methods return
     * By objects for different kinds of element queries:
     *
     *  - By.className(String className) .
     *  - By.cssSelector(String selector)
     *  - By.id(String id)  #
     *  - By.linkText(String linkText)
     *  - By.name(String name)      name='whatever'
     *  - By.tagName(String name)  <tag>
     */

    @Test
    /* Whenever possible, it is best to find page elements using the
     * HTML id attribute since this is the most efficient. Remember
     * that the element id is supposed to be unique per page */
    public void elements_can_be_found_by_id() {
        WebElement mainDiv = webDriver.findElement(By.id("app"));    // #app

        assertNotNull(mainDiv.getText());
    }

}
