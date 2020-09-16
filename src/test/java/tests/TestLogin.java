package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TestLogin {

    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions chromeOptions;

        @BeforeTest
        public void beforeClass() {
            System.setProperty("webdriver.chrome.driver","./chromeDriver/chromedriver.exe");
            chromeOptions  = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
            wait = new WebDriverWait(driver, 15);
            driver.manage().window().maximize();
            driver.get("http://localhost:8880");
        }

    @Test
    public void  test1(){
            //giriş
        FillInputByXpath("//input[@name= 'kullaniciadi']", "firatcelik");
        FillInputByXpath("//input[@name= 'sifre']",  "123456");
        ClickElementByXpath("//input[@type= 'submit']");
        String loginMessage = driver.findElements(By.tagName("td")).get(4).getText();
        Assert.assertEquals(loginMessage,"Merhaba Fırat Çelik başarı ile giriş yaptınız.");
        ClickElementByXpath("//a[@href= 'cikis.php']");
    }
    @Test
    public void  test2(){
            //kayıt
        ClickElementByXpath("//a[@href= 'uyeol.php']");
        FillInputByXpath("//input[@name= 'kullaniciadi']", "recepivedik"+randomNumber());
        FillInputByXpath("//input[@name= 'sifre']", "123456");
        FillInputByXpath("//input[@name= 'adsoyad']", "Recep İvedik");
        FillInputByXpath("//input[@name= 'emailadresi']", "recepivedik"+randomNumber()+"@gmail.com");
        ClickElementByXpath("//input[@value= 'Kayıt Ol']");
        String loginMessage2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertEquals(loginMessage2,"TEBRİKLER" + "\n" +
                 "Kullanıcı Kaydı Başarıyla Tamamlandı." + "\n" +
                 "Ana Sayafaya Dönmek İçin Lütfen Buraya Tıklayınız.");
        ClickElementByXpath("//a[@href= 'index.php']");

        //giriş
        FillInputByXpath("//input[@name= 'kullaniciadi']", "recepivedik");
        FillInputByXpath("//input[@name= 'sifre']",  "123456");
        ClickElementByXpath("//input[@type= 'submit']");
        String loginMessage = driver.findElements(By.tagName("td")).get(4).getText();
        Assert.assertEquals(loginMessage,"Merhaba Recep İvedik başarı ile giriş yaptınız.");
        ClickElementByXpath("//a[@href= 'cikis.php']");
    }

     public  void ClickElementByXpath (String element){
         wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(element)))).click();
     }
     public  void FillInputByXpath (String xpath,  String text){
     wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))).sendKeys(text);
     }
    protected String randomNumber(){
        Random r=new Random();
        int rndNumber = r.nextInt(1000);
        return String.valueOf(rndNumber);
    }
    @AfterTest()
    public void tearDown() {
        driver.quit();
    }


}
