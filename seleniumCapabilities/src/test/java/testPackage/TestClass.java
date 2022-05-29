package testPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestClass {
    @Test
    public void testMethod(){
        WebDriver driver = initialize();
        driver.navigate().to("https://duckduckgo.com/");
        driver.quit();
    }

    ChromeDriver initialize(){
        WebDriverManager.chromedriver().setup();
        ChromiumOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PLATFORM_NAME, "WINDOWS");
        options.setHeadless(false);
        options.addArguments("--start-maximized");
        Map<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.default_content_settings.popups", 0);
        chromePreferences.put("download.prompt_for_download", "false");
        chromePreferences.put("download.default_directory", "C:/Users/Mohab/IdeaProjects/SHAFT_Engine/target/downloadedFiles");
        options.setExperimentalOption("prefs", chromePreferences);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
        options.setPageLoadTimeout(Duration.ofSeconds(30000));
        options.setScriptTimeout(Duration.ofSeconds(30000));
        ChromeOptions chOptions = (ChromeOptions) options;
        return new ChromeDriver(chOptions);
    }
}
