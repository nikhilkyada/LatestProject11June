package org.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager extends Utils{

    LoadProp loadProp = new LoadProp();
    DesiredCapabilities caps = new DesiredCapabilities();

    public  final String USERNAME =loadProp.getProperty("bsUserName");
    public  final String AUTOMATE_KEY =loadProp.getProperty("bsKey");
    public  final String BrowseStackURL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

        boolean cloud = Boolean.parseBoolean(System.getProperty("cloud"));
//    boolean cloud =true;

        String browserName = System.getProperty("browser");
//    String browserName = "edge";


    public void openBrowser(){
        if(cloud){
            System.out.println("Running in cloud...");
            //applying conditional loop for web-browser selection
            if (browserName.equalsIgnoreCase("Chrome")){
                System.out.println("Chrome");
                //Chrome
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "11");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "latest");
                caps.setCapability("browserstack.local", "false");
                caps.setCapability("browserstack.selenium_version", "4.0.0");
        }else if (browserName.equalsIgnoreCase("firefox")){
                System.out.println("Firefox");
                //Firefox
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Firefox");
                caps.setCapability("browser_version", "latest");
                caps.setCapability("browserstack.local", "false");
                caps.setCapability("browserstack.selenium_version", "3.10.0");
            }else if (browserName.equalsIgnoreCase("Edge")){
                System.out.println("Edge");
                //EDGE
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("browser", "Edge");
                caps.setCapability("browser_version", "101.0");
                caps.setCapability("browserstack.local", "false");
                caps.setCapability("browserstack.selenium_version", "3.5.2");
            }else{
                System.out.println("Your Browser name is wrong");
            }
            try {
                driver = new RemoteWebDriver(new URL(BrowseStackURL),caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("Running Locally...");

        if(browserName.equalsIgnoreCase("Chrome")) {
            System.out.println("Chrome");
            //Open Chrome
            System.setProperty("webdriver.chrome.driver", "src/test/java/Drivers/chromedriver.exe");
            driver = new ChromeDriver();  // import chrome web-Driver dependency (In 'POM' file)

        }else if(browserName.equalsIgnoreCase("Firefox")) {
            System.out.println("Firefox");
            //Firefox
            System.setProperty("webdriver.gecko.driver","src/test/java/Drivers/geckodriver.exe" );
            driver= new FirefoxDriver();

        }else if(browserName.equalsIgnoreCase("Edge")){
            System.out.println("Edge");
            //Edge
            System.setProperty("webdriver.edge.driver","src/test/java/Drivers/msedgedriver.exe");
            driver = new EdgeDriver();

        }else {
            System.out.println("You Entered un-valid Browser name :" + browserName);

        }

            }

        //Duration to be wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  //if this line 'RED' ,Click on bulb and select first option

        //Maximize the screen
        driver.manage().window().maximize();

        //Open Web page
        driver.get("https://demo.nopcommerce.com/");

        }

    public void closeBrowser(){
        //Close the browser
        driver.quit();
    }
}
