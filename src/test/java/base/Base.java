package base;

import ModularDDT.SignInDP;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Base {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp () {
        //edge
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        //chrome
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
