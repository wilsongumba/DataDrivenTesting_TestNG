package ModularDDT;

import base.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OrangeHRM extends Base {

    @Test(dataProviderClass = SignInDP.class, dataProvider = "signin-provider")
    public void signIn (String usename, String password, boolean success)
    {
        driver.findElement(By.xpath("//*[@id=\"txtUsername\"]")).sendKeys(usename);
        driver.findElement(By.xpath("//*[@id=\"txtPassword\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();

        System.out.println("Sign In Credentials: " + "\n" +
                "  Username = " + usename + "\n" +
                "  Password = " + password + "\n" +
                "  Successful Sign In = " + success + "\n" );

        String actualResult = driver.findElement(By.id("welcome")).getText();
        String expectedResult = "Welcome Admin";
        Assert.assertEquals(actualResult, expectedResult, "The Actual & Expected Results Do Not Match");

    }
}
