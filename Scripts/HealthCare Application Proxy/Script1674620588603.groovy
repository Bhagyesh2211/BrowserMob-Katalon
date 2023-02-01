import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.io.File as File
import java.io.IOException as IOException
import java.util.EnumSet as EnumSet
import org.openqa.selenium.Proxy as Proxy
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import org.openqa.selenium.remote.CapabilityType as CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities as DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.testng.annotations.Test as Test
import net.lightbody.bmp.BrowserMobProxy as BrowserMobProxy
import net.lightbody.bmp.BrowserMobProxyServer as BrowserMobProxyServer
import net.lightbody.bmp.core.har.Har as Har
import net.lightbody.bmp.proxy.CaptureType as CaptureType
//import io.github.bonigarcia.wdm.WebDriverManager;k
import net.lightbody.bmp.client.ClientUtil as ClientUtil
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType as WebUIDriverType
import com.kms.katalon.core.webui.util.WebDriverPropertyUtil as WebDriverPropertyUtil

BrowserMobProxy myProxy = new BrowserMobProxyServer()

myProxy.start(9091)

//2. Set SSL and HTTP proxy in SeleniumProxy
Proxy seleniumProxy = new Proxy()

seleniumProxy.setHttpProxy('localhost:' + myProxy.getPort())

seleniumProxy.setSslProxy('localhost:' + myProxy.getPort())

//3. Add Capability for PROXY in DesiredCapabilities
DesiredCapabilities capability = new DesiredCapabilities()

capability.setCapability(CapabilityType.PROXY, seleniumProxy)

capability.acceptInsecureCerts()

capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true)

//4. Set captureTypes
EnumSet<CaptureType> captureTypes = CaptureType.getAllContentCaptureTypes()

captureTypes.addAll(CaptureType.getCookieCaptureTypes())

captureTypes.addAll(CaptureType.getHeaderCaptureTypes())

captureTypes.addAll(CaptureType.getRequestCaptureTypes())

captureTypes.addAll(CaptureType.getResponseCaptureTypes())

//5. setHarCaptureTypes with above captureTypes
myProxy.setHarCaptureTypes(captureTypes)

//6. HAR name
myProxy.newHar('MyHAR')

// add our proxy to the list...
capability.setCapability(CapabilityType.PROXY, seleniumProxy)

// tell Katalon to use our custom driver...
System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())

DriverFactory.changeWebDriver(new ChromeDriver(capability))

//WebUI.openBrowser('')
WebUI.navigateToUrl('http://www.google.com/')

WebUI.delay(5)

Har har = myProxy.getHar()

File hARFile = new File(System.getProperty('user.dir') + '/Google4.har')

har.writeTo(hARFile)

//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/a_Make Appointment'))
//
//WebUI.setText(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/input_Username_username'), 
//    'John Doe')
//
//WebUI.setEncryptedText(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/input_Password_password'), 
//    'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')
//
//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/button_Login'))
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/select_Tokyo CURA Healthcare Center'), 
//    'Hongkong CURA Healthcare Center', true)
//
//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/input_Apply for hospital readmission_hospital_readmission'))
//
//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/input_Medicaid_programs'))
//
//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/span_Visit Date (Required)_glyphicon glyphicon-calendar'))
//
//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/td_25'))
//
//WebUI.setText(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/textarea_Comment_comment'), 
//    'Booking Doctor\'s Appointment')
//
//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/button_Book Appointment'))
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/h2_Appointment Confirmation'), 
//    0)
//
//WebUI.click(findTestObject('Object Repository/HealthCare Application Proxy TO/Page_CURA Healthcare Service/a_Go to Homepage'))
WebUI.closeBrowser()


WebUI.close

