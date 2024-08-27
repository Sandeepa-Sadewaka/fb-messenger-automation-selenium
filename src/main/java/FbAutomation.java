import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FbAutomation {
    private WebDriver driver;
    String userName;
    String sendMessage;
    String getUserName;
    String Password;
    public String returnValue;

    public WebDriver getDriver(){
        return driver;
    }
    public void loginDetails(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\94784\\Desktop\\QA Assignment\\UserLogin.txt"));
            this.userName = reader.readLine();
            this.Password = reader.readLine();
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Login FB
    public void LoginFb() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://web.facebook.com/?_rdc=1&_rdr");
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys(userName);
        driver.findElement(By.id("pass")).sendKeys(Password + Keys.ENTER);

        //wait 5 second
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----Login success----");
        WebElement massageButton = this.driver.findElement(By.xpath("//div[@aria-label='Messenger' and @role='button']"));
        // Click the div element
        massageButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //message button click
        WebElement searchInputElementClick = this.driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/div/div/label"));
        searchInputElementClick.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // send name function
    public void senderName(String name , WebDriver webDriver) {
        userName = name;
        //check name null or empty
        if (userName == null|| userName.isEmpty()){
            try {
                WebElement searchName = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/div/div/label/input"));
                searchName.sendKeys(userName);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("----User name null or empty----");
                returnValue = null;
            }
        }
        // name is not null or empty
        else {
            WebElement searchName = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/div/div/label/input"));
            searchName.sendKeys(name);

            System.out.println("----search success----");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WebElement resultBar = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[2]/div/div/div[1]/div[2]/div/div/div[1]"));
            List<WebElement> listItems = resultBar.findElements(By.tagName("li"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // check no result for the name
            if (listItems.isEmpty()) {
                returnValue = "List Null";
                System.out.println("----Empty list----");
            } else {
                WebElement clickName = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[5]/div[2]/div/div/div[1]/div[2]/div/div/div[1]/div[1]/div/div[1]/ul/li[1]/ul/div[1]/li/a/div[1]"));
                clickName.click();
                returnValue = name;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    //send message function
    public void message(String massages ,WebDriver webDriver){

        sendMessage = massages;
        // check message null or empty
        if (sendMessage==null|| sendMessage.isEmpty()){
            WebElement clickMessagebar = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[5]/div[1]/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[5]/div"));
            clickMessagebar.click();

            try {
                WebElement massage;
                massage = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[5]/div[1]/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[5]/div/div[1]/div[1]"));
                massage.sendKeys(sendMessage);
                System.out.println("----Message cannot be null----");
                returnValue =null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            WebElement clickMassagebar = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[5]/div[1]/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[5]/div"));
            clickMassagebar.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WebElement massage;
            massage = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[5]/div[1]/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[5]/div/div[1]/div[1]"));
            massage.sendKeys(massages);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            massage.sendKeys(Keys.ENTER);
            returnValue = "Send";
            System.out.println("----Message sent----");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //close message bar
    public void closeMessageBar(WebDriver webDriver){
        WebElement close = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[5]/div[1]/div[1]/div[1]/div/div/div/div/div/div/div/div[1]/div/div[2]/span[4]/div/div"));
        close.click();
        WebElement home = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[4]/div/div[1]/div[1]/ul/li[1]/span/div/a"));
        home.click();
        returnValue = "Closed";
        System.out.println("----closed massage bar----");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeWindow(){
        driver.quit();
    }
    //print result
    public void printResult(){
        System.out.println("\n\n--------------------------------------------------------------");
        System.out.println("sender name : " + userName + " -----> " + sendMessage + ".");
        returnValue ="Printed";
    }
}
