package cmc.pages;

import cmc.panelobjects.BasePage;
import cmc.registration.pages.enumeration.Countries;
import cmc.registration.pages.util.Translations;
//import cmc.test.NativeAppTestTemplate;
import cmc.test.TestTemplate;
import cmc.utils.data.ApplicationStatic;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static cmc.test.TestTemplate.browser;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class EmailViewer extends BasePage{
	
	@FindBy(xpath=".//*[@name='username']")
	private WebElement usernameField;
	
	@FindBy(xpath=".//*[@name='password']")
	private WebElement passwordField;
	
	@FindBy(xpath=".//*[@type='submit']")
	private WebElement loginBtn;
	
	@FindBy(xpath=".//*[@name='startDate']")
	private WebElement fromDateField;
	
	@FindBy(xpath=".//*[@name='endDate']")
	private WebElement toDateField;
	
	@FindBy(css=".day.active")
	private WebElement currentDay;
	
	@FindBy(xpath=".//*[@name='emailAddress']")
	private WebElement emailAddressField;
	
	@FindBy(xpath=".//*[@type='submit']")
	private WebElement searchBtn;

	@FindBy(xpath=".//table/tbody/tr[1]/td[7]/div/a[text()='View']")
	private WebElement emailFirstRowViewButton;

	@FindBy(xpath=".//*[text() ='I am a private investor']")
	private WebElement privateInvestorLink;

	@FindBy(xpath=".//a[contains(@href,'set-password')]")
	private WebElement setPasswordLink;

	@FindBy(xpath=".//input[@id='password-new']")
	private WebElement passwordInput;

	@FindBy(xpath=".//a[contains(@href,'confirm-registration')]")
	private WebElement verifyMyEmailLink;

	@FindBy(xpath=".//span[@class='cke_button']//a[contains(@class,'cke_button_preview')]")
	private WebElement previewButton;

	@FindBy(xpath=".//a[text() ='log in']")
	private WebElement loginLink;

	@FindBy(xpath=".//table[@class=' cke_show_border']//a[text()='here']")
	private WebElement clickhereLink;

	@FindBy(xpath = "//a[text()='here']")
	private WebElement clickHere;

	@FindBy(xpath=".//td[@class='subject']/div[text()='Your joint account application']/../../../tr[1]//a[text()='View']")
	private WebElement emailViewLink;

	@FindBy(xpath=".//h1[text()='Congratulations']")
	private WebElement emailverified;

	@FindBy(xpath=".//a[@title='CMC Markets Connect']/img")
	private WebElement cmcMarketsConnectLogo;

	@FindBy(xpath=".//td[contains(text(),'verification code')]")
	private WebElement codeVerificationSubject;

	@FindBy(xpath=".//iframe[contains(@title,'Rich text editor')]")
	private WebElement iframe;

	@FindBy(xpath = ".//a[contains(text(),'Login using Duo')]")
	private WebElement loginUsingDuo;

	@FindBy(xpath = ".//input[@id='username']")
	private WebElement duoUsernameInput;

	@FindBy(xpath = ".//input[@id='password']")
	private WebElement duoPasswordInput;

	@FindBy(id = "duo_iframe")
	private WebElement duoFrame;

	@FindBy(id = "login-button")
	private WebElement duoLoginButton;

	@FindBy(xpath = ".//button[@id='passcode']")
	private WebElement duoPasscodeButton;

	@FindBy(xpath = ".//input[@name='passcode']")
	private WebElement duoPasscodeInput;

	@FindBy(xpath = ".//span[@class='cmc-logo']")
	private WebElement cmcMarketsLogo;

	///@FindBy(xpath="//a[@title='Preview']")
	//private WebElement previewButton;

	@FindBy(xpath = "//input[@placeholder='Email, phone, or Skype']")
	private WebElement duoEmail;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement duoSubmitButton;
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement duoPassword;
	@FindBy(xpath = "//input[@id='passcode-input']")
	private WebElement passcodeInput;
	@FindBy(xpath = "//button[text()='Verify']")
	private WebElement duoVerifyButton;
	@FindBy(xpath = "//button[text()='Other options']")
	private WebElement otherOptions;
	@FindBy(xpath = "//div[text()='Bypass code']")
	private WebElement bypassCode;
	@FindBy(xpath = "//input[@value='Yes']")
	private WebElement yes;

	@FindBy(xpath = "//input[@id='password-new']")
	private WebElement resetPasswordField;

	@FindBy(xpath = "//button[text()='Reset Password']")
	private WebElement resetPasswordButton;


	//public static WebDriver emailDriver = new ChromeDriver();
	public static WebDriver emailDriver;

	String winHandleBefore=null;
	Properties emailProp;
	public EmailViewer()
	{
		super(driver);
		PageFactory.initElements(driver, this);
		emailProp = getEmailViewerProps();

	}

	public EmailViewer(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
		emailProp = getEmailViewerProps();

	}
	
	public void findAlert(String message, String user, String account)
	{
		SimpleDateFormat s_dateFormat = new SimpleDateFormat();		
		s_dateFormat.applyPattern("dd/MM/yyyy");
		
		String s_currentDate = s_dateFormat.format(new Date());
		
		assertTrue("Search page not displaying", fromDateField.isDisplayed());
		m_logger.info("Navigated to search page");

		m_logger.info("Entering current date " + s_currentDate + " in from date field");
		fromDateField.click();
		sleep(3000);
		assertTrue("Current day is not the active day on the calendar start date", 
				Integer.parseInt(currentDay.getText()) == Integer.parseInt((s_currentDate.split("/")[0])));
		currentDay.click();
		m_logger.info("Entering current date " + s_currentDate + " in to date field");
		toDateField.click();
		
		assertTrue("Current day is not the active day on the calendar end date", 
				Integer.parseInt(currentDay.getText()) == Integer.parseInt((s_currentDate.split("/")[0])));
		currentDay.click();

		m_logger.info("Entering user : "+user);
		emailAddressField.click();
		emailAddressField.clear();
		emailAddressField.sendKeys(user);
		searchBtn.click();
		m_logger.info("Clicked search button");

		sleep(500);

		List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
//		if(tableRows.size()==0){
//			tableRows = emailDriver.findElements(By.tagName("tr"));
//		}
		Iterator it = tableRows.iterator();
		//it.next();
		boolean foundAlert=false;
		while(foundAlert == false && it.hasNext())
		{
			String rowText = ((WebElement)it.next()).getText();
			
			if(rowText.contains(message) && rowText.contains(s_currentDate) && rowText.contains(account)) {
                foundAlert = true;
			}
		}
//		Assert.assertTrue("Email with subject '"+message+"' not found for the user", foundAlert);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(foundAlert){
            m_logger.info("Email with subject '" + message + "' was found for the user");
        }
        else {
		    m_logger.warn("email subject not found, subject: "+ message);
        }
	}

	public void verifyDemoAccountEmail(String subject, String confirmationMessage) {
		try{
			Actions act = new Actions(driver);
			String xpath=".//td[@class='subject']/div[contains(text(),'"+subject+"')]/../..//a[text()='View']";
			contextClickEmail(driver.findElement(By.xpath(xpath)));
			Thread.sleep(1000);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", previewButton);
			driver.close();
			driver.switchTo().window(winHandleBefore);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			Thread.sleep(2000);
			act.moveToElement(verifyMyEmailLink).perform();
			act.click(verifyMyEmailLink).perform();
			Thread.sleep(2000);
			tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(2));
			Thread.sleep(2000);
			xpath =".//*[contains(text(),'"+confirmationMessage+"')]";
			WebElement ele = driver.findElement(By.xpath(xpath));
			if(waitForVisibility(ele, 30, "return", "")!=null)
			m_logger.info("Account successfully verified");
			else m_logger.error("Failed to verify email account");
			Thread.sleep(5000);
			ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs3.get(2)).close();
			driver.switchTo().window(tabs3.get(1)).close();
			driver.switchTo().window(tabs3.get(0));
		}
		catch(Exception e){
			m_logger.error("Unable to click on private investor link");
			e.printStackTrace();
		}
	}
	
	public void login()
	{
//		Properties emailProp = getEmailViewerProps();
		m_logger.info("Navigating to EmailViewer");
		String environment= System.getProperty("environment");
		if(environment.equalsIgnoreCase("PROD")){
			emailProp = getProdEmailViewerProps();
		}
		m_logger.info("Opening URL : "+emailProp.getProperty("EmailViewer_URL_"+environment));
		try {
			sleep(2000);
			driver.get(emailProp.getProperty("EmailViewer_URL_"+environment));
			try {
				driver.manage().window().maximize();
			}
			catch(Exception e) {}
			m_logger.info("Successfully opened the email viewer");
			
		}catch(Exception e)
		{
			m_logger.error("Unable to navigate to email viewer page successfully");
			e.printStackTrace();
		}
		m_logger.info("Navigated to EmailViewer login page, Trying to login");
		if(environment.equalsIgnoreCase("PROD")){
			m_logger.info("Performing Duo sign in");
			waitForVisibility(loginUsingDuo,50,"","").click();
			sleep(1000);
			waitForVisibility(duoEmail,20,"not Visible", "");
			duoEmail.sendKeys(emailProp.getProperty("emailId"));
			m_logger.info("Entering login details");
			waitForVisibility(duoEmail,20,"email field not Visible", "");
			m_logger.info("clicking on login button");
			performActionClick(duoSubmitButton);
			sleep(2000);
			waitForVisibility(duoPassword,20,"Password button not visible","");
			performActionClick(duoPassword);
			duoPassword.sendKeys(emailProp.getProperty("pword"));
			sleep(1000);
			performActionClick(duoSubmitButton);
			m_logger.info("Submitting login button");
			sleep(5000);
			enterBypassCode();
			if(waitForVisibility(yes, 10, "return", "")!=null) {
				performActionClick(yes);
				enterBypassCode();
			}
//			waitForVisibility(usernameField, 10, "username field not displayed", "");
//			usernameField.sendKeys(emailProp.getProperty("USERNAME"));
//			passwordField.sendKeys(emailProp.getProperty("PASSWORD"));
//			loginBtn.click();
//			waitForVisibility(duoUsernameInput,50,"","").sendKeys(emailProp.getProperty("USERNAME"));
//			duoPasswordInput.sendKeys(emailProp.getProperty("PASSWORD"));
//			duoLoginButton.click();
//			try {
//				Thread.sleep(500);
//			}
//			catch(Exception e){}
//			switchToFrame(duoFrame);
//			try {
//				Thread.sleep(500);
//			}
//			catch(Exception e){}
//			waitForVisibility(duoPasscodeButton,50,"","").click();
//			duoPasscodeInput.sendKeys(emailProp.getProperty("accessCode"));
//			duoPasscodeButton.click();
//			try {
//				Thread.sleep(500);
//			}
//			catch(Exception e){}
//			driver.switchTo().defaultContent();
			m_logger.info("Successfully opened the email viewer");
		}
		else {
			usernameField.sendKeys(emailProp.getProperty("USERNAME"));
			passwordField.sendKeys(emailProp.getProperty("PASSWORD"));
			loginBtn.click();
		}

	}

	private void enterBypassCode() {
		waitForVisibility(otherOptions,20,"Other options button not visible","");
		performActionClick(otherOptions);
		sleep(3000);
		waitForVisibility(bypassCode,20,"Bypass code button not visible","");
		performActionClick(bypassCode);
		waitForVisibility(passcodeInput,20,"","");
		passcodeInput.sendKeys(emailProp.getProperty("duopassCode"));
		waitForVisibility(duoVerifyButton,20,"","");
		performActionClick(duoVerifyButton);
	}
	
	public Properties getEmailViewerProps()
	{
		
		Properties emailProp = new Properties();
		try {
			emailProp.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\EmailViewer.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			m_logger.info("Cannot initialise Email Viewer properties file");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emailProp;
	}

	public Properties getProdEmailViewerProps()
	{

		Properties emailProp = new Properties();
		try {
			emailProp.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\EmailViewerProd.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			m_logger.info("Cannot initialise Email Viewer properties file");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emailProp;
	}

	public void clickPrivateInvestor(){
		try{
			Actions act = new Actions(driver);

			contextClickEmail(emailViewLink);
			Thread.sleep(1000);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", previewButton);
			driver.close();
			driver.switchTo().window(winHandleBefore);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			Thread.sleep(2000);
			act.moveToElement(privateInvestorLink).perform();
			act.click(privateInvestorLink).perform();
			m_logger.info("Click on Private Investor link successful ");
		}
		catch(Exception e){
			m_logger.error("Unable to click on private investor link");
			e.printStackTrace();
		}
	}

	public void clickPrivateInvestor(String subject){
		try{
			Actions act = new Actions(driver);
			String xpath=".//td[@class='subject']/div[text()='Welcome to CMC Markets']/../../../tr[1]//a[text()='View']";
			contextClickEmail(driver.findElement(By.xpath(xpath)));
			Thread.sleep(1000);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", previewButton);
			driver.close();
			driver.switchTo().window(winHandleBefore);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			Thread.sleep(2000);
			act.moveToElement(privateInvestorLink).perform();
			act.click(privateInvestorLink).perform();
			m_logger.info("Click on Private Investor link successful ");
		}
		catch(Exception e){
			m_logger.error("Unable to click on private investor link");
			e.printStackTrace();
		}
	}

	public void clickLoginLink(){
		try{
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			loginLink.click();
		}
		catch(Exception e){
			m_logger.error("Email not found");
		}
	}


	public void contextClickEmail(WebElement emailViewLink){
		Actions action = new Actions(driver);

//		driver.findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL +"t");
		action.contextClick(waitForVisibility(emailViewLink,30,"","")).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
//		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
//		driver.switchTo().window(tabs2.get(1));
		 winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.manage().window().maximize();
	}

	public void openActivateJointAccount(){
		//String xpath =".//td[@class='subject']/div[text()='Your joint account application']/../../../tr[1]//a[text()='View']";
		try {
			m_logger.info("Click on View button in email");
			sleep(2000);
			contextClickEmail(emailViewLink);
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			m_logger.info("Switched to iFrame");
			String url = clickhereLink.getAttribute("data-cke-saved-href");
			m_logger.info("clicked on link");
			driver.close();
			driver.switchTo().window(winHandleBefore);
			m_logger.info("Open app form link and enter second user details");
			driver.get(url);
		}
		catch(Exception e){
			m_logger.error("email view button not found");
		}
	}


	public void openActivateJointAccountVersion5(){
		//String xpath =".//td[@class='subject']/div[text()='Your joint account application']/../../../tr[1]//a[text()='View']";
		try {
			Actions act = new Actions(driver);
			winHandleBefore = driver.getWindowHandle();
			m_logger.info("Click on View button in email");
			contextClickEmail(emailViewLink);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", previewButton);
			driver.close();
			driver.switchTo().window(winHandleBefore);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			Thread.sleep(2000);
			//String url = clickhereLink.getAttribute("href");
			act.moveToElement(clickHere).perform();
			act.click(clickHere).perform();
			Thread.sleep(2000);
//			tabs2 = new ArrayList<String> (driver.getWindowHandles());
//			driver.switchTo().window(tabs2.get(2));
			//Thread.sleep(2000);
			m_logger.info("clicked on link");
			//driver.close();
			//driver.switchTo().window(winHandleBefore);
			m_logger.info("Open app form link and enter second user details");
			//driver.get(url);
		}
		catch(Exception e){
			m_logger.error("email view button not found");
		}

	}

	public void demoLogin()
	{
		//emailDriver = new ChromeDriver();
		PageFactory.initElements(driver, this);
		Actions act = new Actions(driver);
		//act.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		Properties emailProp = getEmailViewerProps();

		m_logger.info("Navigating to EmailViewer");
		String environment= System.getProperty("environment");
		if(environment.equalsIgnoreCase("PROD")){
			emailProp = getProdEmailViewerProps();
		}
		m_logger.info("Opening URL : "+emailProp.getProperty("EmailViewer_URL_"+environment));
//		driver.close();
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver","C:\\SeleniumGrid\\chromedriver.exe");
		try
		{
//			TestSetup.setDriver(new RemoteWebDriver(new URL(TestSetup.hubURL), capabilities));
			sleep(10000);
//			emailDriver.get(emailProp.getProperty("EmailViewer_URL_"+environment));
//			emailDriver.manage().window().maximize();
			String emailURL = emailProp.getProperty("EmailViewer_URL_"+environment);
			((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", emailURL);
			m_logger.info("Successfully opened the email viewer");

		}catch(Exception e)
		{
			m_logger.error("Unable to navigate to email viewer page successfully");
			e.printStackTrace();
		}
//		wait_30.until(ExpectedConditions.visibilityOf(usernameField));
		ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		m_logger.info("Navigated to EmailViewer login page, Trying to login");
		if(environment.equalsIgnoreCase("PROD")){
			waitForVisibility(loginUsingDuo,50,"","").click();
			waitForVisibility(duoUsernameInput,50,"","").sendKeys(emailProp.getProperty("USERNAME"));
			duoPasswordInput.sendKeys(emailProp.getProperty("PASSWORD"));
			duoLoginButton.click();
			try {
				Thread.sleep(500);
			}
			catch(Exception e){}
			switchToFrame(duoFrame);
			try {
				Thread.sleep(500);
			}
			catch(Exception e){}
			waitForVisibility(duoPasscodeButton,50,"","").click();
			duoPasscodeInput.sendKeys(emailProp.getProperty("accessCode"));
			duoPasscodeButton.click();
			try {
				Thread.sleep(500);
			}
			catch(Exception e){}
			driver.switchTo().defaultContent();
		}
		else {
			act.click(usernameField).perform();
			usernameField.sendKeys(emailProp.getProperty("USERNAME"));
			act.click(passwordField).perform();
			passwordField.sendKeys(emailProp.getProperty("PASSWORD"));
			loginBtn.click();
		}
	}

	public String getVerificationCode() throws IOException {

        String verificationCode = "";
        String subject = "";

		String testCountryID = TestTemplate.testCountryId;
		Countries country = Countries.getCountry(testCountryID);
		Translations translations = new Translations(country.language());
		String verificationMessage = translations.getTranslation("verificationCodeEmail");

//		ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(1));
        //List<WebElement> tableRows = emailDriver.findElements(By.tagName("tr"));
		List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
        Iterator it = tableRows.iterator();

        boolean foundAlert = false;
        while (foundAlert == false && it.hasNext()) {
            String rowText = ((WebElement) it.next()).getText();

            if (rowText.contains(verificationMessage))
                foundAlert = true;
            subject = rowText;
        }

        String digits = subject.replaceAll("\\D+", "");
        verificationCode = digits.substring(0,6);
        m_logger.info("Verification code: "+verificationCode);

        //verificationCode = code.replace("-", "");

        return verificationCode;

	}

//	public String getVerificationCodeMobile() throws IOException {
//
//		String verificationCode = "";
//		String subject = "";
//
//		String testCountryID = NativeAppTestTemplate.testCountryId;
//		Countries country = Countries.getCountry(testCountryID);
//		Translations translations = new Translations(country.language());
//		String verificationMessage = translations.getTranslation("verificationCodeEmail");
//
////		ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
////		driver.switchTo().window(tabs.get(1));
//		//List<WebElement> tableRows = emailDriver.findElements(By.tagName("tr"));
//		List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
//		Iterator it = tableRows.iterator();
//
//		boolean foundAlert = false;
//		while (foundAlert == false && it.hasNext()) {
//			String rowText = ((WebElement) it.next()).getText();
//
//			if (rowText.contains(verificationMessage))
//				foundAlert = true;
//			subject = rowText;
//		}
//
//		String digits = subject.replaceAll("\\D+", "");
//		verificationCode = digits.substring(0,6);
//		m_logger.info("Verification code: "+verificationCode);
//
//		//verificationCode = code.replace("-", "");
//
//		return verificationCode;
//
//	}

	public boolean verifyEmailAddress(Translations translations){

		Boolean verified = false;
		m_logger.info("Verifying email address...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitForVisibility(driver.findElement(By.xpath(".//a[text()='View']")), 30, "error", "View button not visible").click();

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		if(browser.equalsIgnoreCase("ie")) {
			try {
				m_logger.info("Trying to login");
				usernameField.sendKeys(emailProp.getProperty("USERNAME"));
				passwordField.sendKeys(emailProp.getProperty("PASSWORD"));
				loginBtn.click();
			}
			catch(Exception e){}
		}
		waitForVisibility(driver.findElement(By.xpath(".//a[@id='cke_9']//span[@class='cke_icon']")), 30, "", "").click();
		m_logger.info("Email opened");
		driver.switchTo().defaultContent();
//		driver.switchTo().window(tabs2.get(0));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs3.get(2));
		String emailVerification = translations.getTranslation("emailVerification");
		String xpath ="";
		WebElement emailVerify;
		try {
			xpath = ".//a[text()='" + emailVerification + "']";
			emailVerify = driver.findElement(By.xpath(xpath));
		}
		catch(Exception e) {
			driver.switchTo().defaultContent();
			driver.switchTo().window(tabs3.get(2));
			xpath = ".//a[text()='" + emailVerification + "']";
			emailVerify = driver.findElement(By.xpath(xpath));
		}
		moveToElement(emailVerify);
		emailVerify.click();
		m_logger.info("Verify email link clicked");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<String> tabs4 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs4.get(3));

		String emailVerified = "";
		if(System.getProperty("testcountry").contains("SG_MN")){
			emailVerified = translations.getTranslation("emailVerifiedSG");
		}
		else {
			emailVerified = translations.getTranslation("emailVerified");
		}
		driver.switchTo().window(tabs4.get(3));
		WebElement verification = waitForVisibility(driver.findElement(By.xpath(".//h1[text()='"+emailVerified+"']")), 30, "return", "");
		if(verification!=null) {
			m_logger.info("Email successfully verified");
			verified = true;
		}
		else
			m_logger.error("Error in email verification");

		driver.switchTo().window(tabs4.get(3)).close();
		driver.switchTo().window(tabs4.get(2)).close();
		driver.switchTo().window(tabs4.get(1)).close();
//		driver.switchTo().window(tabs4.get(0));

		return verified;
	}


	/*For Germany and Austria customer hub tests*/
	public void activateLiveAccount(){

		m_logger.info("Activating live account...");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitForVisibility(driver.findElement(By.xpath(".//a[text()='View']")), 30, "error", "View button not visible").click();

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		sleep(4000);
		waitForVisibility(driver.findElement(By.xpath(".//a[@id='cke_9']//span[@class='cke_icon']")), 30, "", "").click();
		m_logger.info("Email opened");
		driver.switchTo().defaultContent();
//		driver.switchTo().window(tabs2.get(0));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs3.get(2));
		try {
			driver.findElement(By.xpath(".//a[@name='PostIdentbutton']")).click();
			m_logger.info("I Agree link clicked");
		}
		catch(Exception e) {
				try {
					driver.findElement(By.xpath(".//a[@title='PostIdent']")).click();
					m_logger.info("I Agree link clicked");
				}
				catch (Exception e1) {
					try {
						driver.findElement(By.xpath("(.//a[contains(@href,'oaf.')])[1]")).click();
						m_logger.info("I Agree link clicked");
					}
					catch(Exception e2) {
						try {
							driver.findElement(By.xpath(".//a[@title='Private investor']")).click();
							m_logger.info("I Agree link clicked");
							sleep(10000);
						}
						catch (Exception e3) {
							driver.findElement(By.xpath("(.//a[contains(@href,'cfdonlinetrader.')])[1]")).click();
							m_logger.info("I Agree link clicked");
							sleep(10000);
						}
					}
				}
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m_logger.info("Email successfully verified");

//		if(!System.getProperty("testcountry").equalsIgnoreCase("ES_EUR")) {
			try {
				ArrayList<String> tabs4 = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs4.get(3));
				driver.switchTo().window(tabs4.get(3)).close();
				driver.switchTo().window(tabs4.get(2)).close();
				driver.switchTo().window(tabs4.get(1)).close();
				driver.switchTo().window(tabs4.get(0));
			} catch (Exception e) {
			}
//		}
	}

	public void activateLiveAccountAppropriateness(){

		m_logger.info("Activating live account...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitForVisibility(driver.findElement(By.xpath(".//a[text()='View']")), 30, "error", "View button not visible").click();

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		sleep(4000);
		waitForVisibility(driver.findElement(By.xpath(".//a[@id='cke_9']//span[@class='cke_icon']")), 30, "", "").click();
		m_logger.info("Email opened");
		driver.switchTo().defaultContent();
//		driver.switchTo().window(tabs2.get(0));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs3.get(3));
		try {
			driver.findElement(By.xpath(".//a[@name='PostIdentbutton']")).click();
			m_logger.info("I Agree link clicked");
		}
		catch(Exception e) {
			try {
				driver.findElement(By.xpath(".//a[@title='PostIdent']")).click();
				m_logger.info("I Agree link clicked");
			}
			catch (Exception e1) {
				try {
					driver.findElement(By.xpath("(.//a[contains(@href,'oaf.cmcmarkets')])[1]")).click();
					m_logger.info("I Agree link clicked");
				}
				catch(Exception e2) {
					driver.findElement(By.xpath(".//a[@title='Private investor']")).click();
					m_logger.info("I Agree link clicked");
					sleep(10000);
				}
			}
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m_logger.info("Email successfully verified");

//		if(!System.getProperty("testcountry").equalsIgnoreCase("ES_EUR")) {
		ArrayList<String> tabs4 = new ArrayList<String>(driver.getWindowHandles());
		try {
			driver.switchTo().window(tabs4.get(4));
			driver.switchTo().window(tabs4.get(4)).close();
			driver.switchTo().window(tabs4.get(3)).close();
			driver.switchTo().window(tabs4.get(2)).close();
			driver.switchTo().window(tabs4.get(1)).close();
		} catch (Exception e) {
		}
		driver.switchTo().window(tabs4.get(0));
//		}
	}

	public void activateLiveAccountSecond(){

		m_logger.info("Activating live account...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitForVisibility(driver.findElement(By.xpath(".//a[text()='View']")), 30, "error", "View button not visible").click();

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		sleep(4000);
		waitForVisibility(driver.findElement(By.xpath(".//a[@id='cke_9']//span[@class='cke_icon']")), 30, "", "").click();
		m_logger.info("Email opened");
		driver.switchTo().defaultContent();
//		driver.switchTo().window(tabs2.get(0));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs3.get(3));
		try {
			driver.findElement(By.xpath(".//a[@name='PostIdentbutton']")).click();
			m_logger.info("I Agree link clicked");
		}
		catch(Exception e) {
			try {
				driver.findElement(By.xpath(".//a[@title='PostIdent']")).click();
				m_logger.info("I Agree link clicked");
			}
			catch (Exception e1) {
				try {
					driver.findElement(By.xpath("(.//a[contains(@href,'oaf.cmcmarkets')])[1]")).click();
					m_logger.info("I Agree link clicked");
				}
				catch(Exception e2) {
					driver.findElement(By.xpath(".//a[@title='Private investor']")).click();
					m_logger.info("I Agree link clicked");
					sleep(10000);
				}
			}
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m_logger.info("Email successfully verified");

//		if(!System.getProperty("testcountry").equalsIgnoreCase("ES_EUR")) {
		try {
			ArrayList<String> tabs4 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs4.get(3));
			driver.switchTo().window(tabs4.get(3)).close();
			driver.switchTo().window(tabs4.get(2)).close();
			driver.switchTo().window(tabs4.get(1)).close();
			driver.switchTo().window(tabs4.get(0));
		} catch (Exception e) {
		}
//		}
	}

	public void activateLiveAccountNew(){

		m_logger.info("Activating live account...");
		sleep(2000);
		waitForVisibility(driver.findElement(By.xpath(".//a[text()='View']")), 10, "error", "View button not visible").click();

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		waitForVisibility(driver.findElement(By.xpath(".//a[@id='cke_9']//span[@class='cke_icon']")), 30, "", "").click();
		m_logger.info("Email opened");
		driver.switchTo().defaultContent();
//		driver.switchTo().window(tabs2.get(0));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs3.get(3));
		sleep(2000);
		try {
			driver.findElement(By.xpath(".//a[@name='PostIdentbutton']")).click();
			m_logger.info("I Agree link clicked");
		}
		catch(Exception e) {
			try {
				driver.findElement(By.xpath(".//a[@title='PostIdent']")).click();
				m_logger.info("I Agree link clicked");
			}
			catch (Exception e1) {
				try {
					driver.findElement(By.xpath("(.//a[contains(@href,'oaf.cmcmarkets')])[1]")).click();
					m_logger.info("I Agree link clicked");
				}
				catch(Exception e2) {
					try {
						driver.findElement(By.xpath(".//a[text()='I am a private investor']")).click();
						m_logger.info("I Agree link clicked");
						sleep(2000);
					}
					catch(Exception e3) {
						driver.findElement(By.xpath("(.//a[contains(@href,'oaf.cfdonlinetrader')])[1]")).click();
						m_logger.info("I Agree link clicked");
						sleep(2000);
					}
				}
			}
		}
		sleep(10000);
		m_logger.info("Email successfully verified");

		try {
			driver.switchTo().window(tabs3.get(3));
			driver.switchTo().window(tabs3.get(3)).close();
			driver.switchTo().window(tabs3.get(2)).close();
			driver.switchTo().window(tabs3.get(1)).close();
			driver.switchTo().window(tabs3.get(0));
		} catch (Exception e) {}
	}

	public void verifyCMCMarketConnectLogo( String user, String account)
	{
		SimpleDateFormat s_dateFormat = new SimpleDateFormat();
		s_dateFormat.applyPattern("dd/MM/yyyy");

		String s_currentDate = s_dateFormat.format(new Date());

		assertTrue("Search page not displaying", fromDateField.isDisplayed());
		m_logger.info("Navigated to search page");

		m_logger.info("Entering current date " + s_currentDate + " in from date field");
		fromDateField.click();

		assertTrue("Current day is not the active day on the calendar start date",
				Integer.parseInt(currentDay.getText()) == Integer.parseInt((s_currentDate.split("/")[0])));
		currentDay.click();
		m_logger.info("Entering current date " + s_currentDate + " in to date field");
		toDateField.click();

		assertTrue("Current day is not the active day on the calendar end date",
				Integer.parseInt(currentDay.getText()) == Integer.parseInt((s_currentDate.split("/")[0])));
		currentDay.click();

		m_logger.info("Entering user : "+user);
		emailAddressField.click();
		emailAddressField.clear();
		emailAddressField.sendKeys(user);
		searchBtn.click();
		m_logger.info("Clicked search button");

		sleep(500);

		List<WebElement> emailView = driver.findElements(By.xpath(".//tr//*[text()='View']"));

		for(WebElement view : emailView) {
			try {
				moveToElement(view);
				view.click();
				Thread.sleep(3000);
				ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
				try {
					driver.switchTo().window(tabs.get(2));
				}
				catch(Exception e) {
					driver.switchTo().window(tabs.get(1));
				}
				switchToFrame(iframe);
				Thread.sleep(500);
				if(waitForVisibility(cmcMarketsConnectLogo, 10, "return", "")!=null)
					m_logger.info("CMC Markets Connect logo present in email");
				else if(waitForVisibility(codeVerificationSubject, 10, "return", "")!=null)
					m_logger.info("CMC markets Connect logo not present in code verification email");
				else m_logger.error("CMC Markets Connect logo not present in email");
				try {
					driver.switchTo().window(tabs.get(2)).close();
				}
				catch(Exception e) {
					driver.switchTo().window(tabs.get(1)).close();
				}
				driver.switchTo().window(tabs.get(0));
			}
			catch(Exception e) {
				e.printStackTrace();
				m_logger.error("Error verifying logo");
			}
		}
	}

	public void refreshEmailViewer(){
		emailDriver.navigate().refresh();
	}

	public void closeEmailDriver() {
		emailDriver.quit();
	}

	public void verifyMT4SBEmailTemplate(String subject){
		try{
			Actions act = new Actions(driver);
			String xpath=".//td[@class='subject']/div[text()='"+subject+"']/../..//a[text()='View']";
			contextClickEmail(driver.findElement(By.xpath(xpath)));
			sleep(1000);
//			JavascriptExecutor executor = (JavascriptExecutor)driver;
//			executor.executeScript("arguments[0].click();", previewButton);
//			driver.close();
			driver.switchTo().window(winHandleBefore);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(2));
			sleep(1000);
			switchToFrame(driver.findElement(By.xpath(".//iframe[contains(@title,'Rich text editor')]")));
			sleep(1000);
			WebElement accountType = driver.findElement(By.xpath(".//span[text()='Account type']/.."));
			if(waitForVisibility(accountType, 5, "return", "").getText().contains("MetaTrader 4 spread betting"))
				m_logger.info("Account type on email displayed correctly: " + accountType.getText());
			else m_logger.error("Account type on email not displayed correctly. Expected: MetaTrader 4 spread betting, Found: " + accountType.getText());
		}
		catch(Exception e){
			m_logger.error("Error verifying email template for email: " + subject);
			e.printStackTrace();
		}
	}

	public void verifyRejectAccountEmail(String subject, String rejectMessage){
		try{
			Actions act = new Actions(driver);
			String xpath=".//td[@class='subject']/div[contains(text(),'"+subject+"')]/../..//a[text()='View']";
			contextClickEmail(driver.findElement(By.xpath(xpath)));
			Thread.sleep(1000);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", previewButton);
			driver.close();
			driver.switchTo().window(winHandleBefore);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			//driver.switchTo().window(tabs2.get(1));
			Thread.sleep(2000);
			tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(2));
			Thread.sleep(2000);
			xpath =".//*[contains(normalize-space(.),'"+ rejectMessage +"')]";
			WebElement ele = driver.findElement(By.xpath(xpath));
			if(waitForVisibility(ele, 30, "return", "")!=null)
				m_logger.info("Reject message displayed as expected");
			else m_logger.error("Reject message not displayed as expected");
		}
		catch(Exception e){
			m_logger.error("Unable to click on private investor link");
			e.printStackTrace();
		}
	}

	public void setAccountPassword(String subject){
		try{
			Actions act = new Actions(driver);
			String xpath=".//td[@class='subject']/div[text()='"+subject+"']/../..//a[text()='View']";
			contextClickEmail(driver.findElement(By.xpath(xpath)));
			Thread.sleep(1000);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", previewButton);
			driver.close();
			driver.switchTo().window(winHandleBefore);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(2));
			Thread.sleep(2000);
			act.moveToElement(setPasswordLink).perform();
			act.click(setPasswordLink).perform();
			handleCookiesAlert();
			wait.until(ExpectedConditions.visibilityOf(passwordInput));
			passwordInput.sendKeys(ApplicationStatic.getAccountPassword());
			sleep(2000);
			submit3.click();
			wait.until(ExpectedConditions.visibilityOf(login));
			m_logger.info("Password successfully set, logging into platform");
		}
		catch(Exception e){
			m_logger.error("Unable to set password");
			e.printStackTrace();
		}
	}

	public void resetYourPassword(){

		m_logger.info("Activating live account...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitForVisibility(driver.findElement(By.xpath(".//a[text()='View']")), 30, "error", "View button not visible").click();

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		sleep(4000);
		waitForVisibility(driver.findElement(By.xpath(".//a[@id='cke_9']//span[@class='cke_icon']")), 30, "", "").click();
		m_logger.info("Email opened");
		driver.switchTo().defaultContent();
//		driver.switchTo().window(tabs2.get(0));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs3.get(2));
		try {
			driver.findElement(By.xpath("//a[text()='Reset password']")).click();
			m_logger.info("Reset password clicked");
		}
		catch(Exception e) {
			sleep(2000);
			driver.findElement(By.xpath("//a[text()='Reset password']")).click();
			m_logger.info("Reset password clicked");
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m_logger.info("Email successfully verified");

//		if(!System.getProperty("testcountry").equalsIgnoreCase("ES_EUR")) {
		try {
			ArrayList<String> tabs4 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs4.get(3));
			m_logger.info("Clicking on reset password field");
			performActionClick(resetPasswordField);
			m_logger.info("Entering password");
			resetPasswordField.sendKeys("Password123");
			waitForVisibility(resetPasswordButton,10,"","");
			performActionClick(resetPasswordButton);
			driver.switchTo().window(tabs4.get(3)).close();
			driver.switchTo().window(tabs4.get(2)).close();
			driver.switchTo().window(tabs4.get(1)).close();
			driver.switchTo().window(tabs4.get(0));
		} catch (Exception e) {
		}
//		}
	}

}
