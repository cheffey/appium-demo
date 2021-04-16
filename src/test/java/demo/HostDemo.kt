import io.appium.java_client.MobileBy
import io.appium.java_client.android.AndroidDriver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.OutputType
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL

class HostDemo {
    private var driver: AndroidDriver<WebElement>? = null

    @Before
    fun setUp() {
        val caps = DesiredCapabilities()
        caps.setCapability("platformName", "Android")
        caps.setCapability("platformVersion", "9.0")
        caps.setCapability("uuid", "10.32.39.38:5555")
        caps.setCapability("automationName", "UiAutomator2")
        caps.setCapability("appPackage", "com.ringcentral.rooms")
        caps.setCapability("appActivity", "com.ringcentral.rooms.RoomsSplashActivity")
        caps.setCapability("newCommandTimeout", 20 * 60 * 1000)
        driver = AndroidDriver<WebElement>(URL("http://localhost:4723/wd/hub"), caps)
    }

    @Test
    fun source() {
        Thread.sleep(5000)
        val pageSource = driver!!.pageSource
        if (pageSource != null) {
            println(pageSource)
            println("source success")
        }
    }

    @Test
    fun pairCodeGetText() {
        val pairCode: WebElement? = WebDriverWait(driver, 30000)
            .until {
                val element = it.findElement<WebElement>(MobileBy.id("com.ringcentral.rooms:id/pairCode"))
                if (Regex("[A-Z]+").matches(element.text)) {
                    element
                } else {
                    null
                }
            }
        println("get pair code success: ${pairCode!!.text}")
    }

    @Test
    fun signClick() {
        val signIn: WebElement = WebDriverWait(driver, 30000)
            .until {
                it.findElement(MobileBy.id("com.ringcentral.rooms:id/signInButton"))
            }
        signIn.click()
        val signInClickSuccess: WebElement = WebDriverWait(driver, 30000)
            .until {
                it.findElement(MobileBy.id("com.ringcentral.rooms:id/title"))
            }
        println("sign in button click success")
    }

    @Test
    fun screenshot() {
        val file = driver!!.getScreenshotAs(OutputType.FILE)
        if (file != null) {
            println("screenshot success: $file")
        }
    }

    @Test
    fun elementScreenshot() {
        val pairingCode: WebElement = WebDriverWait(driver, 30000)
            .until {
                it.findElement(MobileBy.xpath("//*[@text=\"Pairing code\"]"))
            }
        val file = pairingCode.getScreenshotAs(OutputType.FILE)
        if (file != null) {
            println("PAIRING CODE screenshot success: $file")
        }
    }

    @After
    fun tearDown() {
        if (driver != null) {
            driver!!.quit()
        }
    }
}