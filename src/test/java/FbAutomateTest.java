import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FbAutomateTest {
    WebDriver driver;
    String userName;
    String Password;
    FbAutomation object = new FbAutomation();
    @Before
    public void loginFb(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\94784\\Desktop\\QA Assignment\\UserLogin.txt"));
            this.userName = reader.readLine();
            this.Password = reader.readLine();
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://web.facebook.com/?_rdc=1&_rdr");
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys(userName);
        driver.findElement(By.id("pass")).sendKeys(Password + Keys.ENTER);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement massageButton = driver.findElement(By.xpath("//div[@aria-label='Messenger' and @role='button']"));
        // Click the div element
        massageButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement searchInputElementClick = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/div/div/label"));
        searchInputElementClick.click();
    }

   @Test
   @Order(1)
   //Send valid name, valid message and test print
    public void test1(){
        object.senderName("Sandeepa Sadewaka",driver);
        object.message("Hey",driver);
        object.printResult();
        Assert.assertEquals("Printed",object.returnValue);

    }
    //Test close the message window
   @Test
    @Order(2)
    public void test2(){
        object.senderName("Sandeepa Sadewaka",driver);
        object.message("Hey",driver);
        object.closeMessageBar(driver);
        Assert.assertEquals("Closed",object.returnValue);
    }
    //Test empty name
    @Test
    @Order(3)
    public void test3(){
        object.senderName("",driver);
        Assert.assertNull(object.returnValue);

    }
    //Test  null name
    @Test
    @Order(4)
    public void test4(){
        object.senderName(null,driver);
        Assert.assertNull(object.returnValue);
    }
    //Test empty message
    @Test
    @Order(5)
    public void test5(){
        object.senderName("Sandeepa Sadewaka",driver);
        object.message("",driver);
        Assert.assertNull(object.returnValue);

    }
    //Test invalid name
     @Test
    @Order(6)
    public void test6() {
        object.senderName("ausdxgygzxsau@#$54$", driver);
        Assert.assertEquals("List Null",object.returnValue);
    }
    //Test invalid message
    @Test
    @Order(7)
    public void test7(){
        object.senderName("Sandeepa Sadewaka",driver);
        object.message("@#$%^&uyzdhj45613",driver);
        Assert.assertEquals("Send",object.returnValue);
    }
    //Test long message
    @Test
    @Order(8)
    public void test8(){
        String longMessage = generateLongMessage(100);
        object.senderName("Sandeepa Sadewaka",driver);
        object.message(longMessage,driver);
        Assert.assertEquals("Send",object.returnValue);
    }
    //Convert a long message
    public static String generateLongMessage(int repeatCount) {
        String baseMessage = "This is a very long message that exceeds typical character limits. ";
        StringBuilder longMessage = new StringBuilder();
        int i;
        for (i = 0; i < repeatCount; i++) {
            longMessage.append(baseMessage);
        }
        return longMessage.toString();
    }


}
