package InlineDDT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class LoginTest
{
    WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        //edge
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        //chrome
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

    @Test (dataProviderClass = LoginTest.class, dataProvider = "login-provider")
    public void logIn (String email, String password, boolean success)
    {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();

        System.out.println("Log In Credentials: " + "\n" +
                "  Email = " + email + "\n" +
                "  Password = " + password + "\n" +
                "  Successful Log In = " + success + "\n" );

        String actualResult = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).getText();
        String expectedResult = "Sign out";
        Assert.assertEquals(actualResult, expectedResult, "The Actual & Expected Results Do Not Match");


    }

    @DataProvider (name = "login-provider")
    public Object [] [] logInData ()
    {
        Object [] [] data = new Object [3] [3];

        data [0] [0] = "TestNG@Framework.com";		data [0] [1] = "TestNG1234";			data [0] [2] = true;
        data [1] [0] = "Joe@Doe.com";							data [1] [1] = "DoeDoe34";			data [1] [2] = false;
        data [2] [0] = "Test@AutomationU.com";			data [2] [1] = "TAU1234";				data [2] [2] = true;

        return data;
    }
}