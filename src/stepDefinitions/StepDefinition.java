package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinition {
	
	public static WebDriver driver;
		
	public static ExtentSparkReporter spark;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest logger;
	
	@Before
	public void SparkReporterHandling(){
	spark = new ExtentSparkReporter("./TestReports/Sparkreport.html");
	spark.config().setTheme(Theme.STANDARD);
	extent.attachReporter(spark);
	}
	
	@Given("launch Browser and Navigate to Amazon website")
	public void given_Amazon_website_launched() throws InterruptedException {
		logger = extent.createTest("Amazon web search Functionality Verification");
		
		//Setting system properties of ChromeDriver 
		System.setProperty("webdriver.chrome.driver", "D:\\SELENIUM\\Software\\chromedriver.exe");
		//Creating an object of ChromeDriver
		driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		logger.info("Launching Amazon web site.....");
		String actual_URL = driver.getCurrentUrl();//https://www.amazon.com/
		actual_URL.contains("https://www.amazon.in/");
		Thread.sleep(2000);		
	    logger.pass("Webpage launch & Verified");
	    
	}
	
	@When("User search something")
	public void user_enters_to_search() throws InterruptedException {
		String search_item = "Mobile";
	    WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	    searchTextBox.click();
	    searchTextBox.clear();
	    searchTextBox.sendKeys(search_item);
	    searchTextBox.isDisplayed();
	    searchTextBox.getAttribute("value").equals(search_item);
	    System.out.println("Searched item - "+ search_item + " visible correctly");
	    logger.pass("Searched item - "+ search_item + " visible correctly");
	    
	    WebElement searchbutton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
	    searchbutton.click();
	}

	@Then("list all related Search appears")
	public void Amazon_SearchResults() throws InterruptedException {
		Thread.sleep(2000); 
	    WebElement searchresults = driver.findElement(By.xpath("//span[normalize-space()='1-16 of over 2,000 results for']"));
	    String Search_results = searchresults.getText();
	    
	    System.out.println("Searched results - "+ Search_results + " visible correctly");
	    logger.pass("Searched results - "+ Search_results + " ");
	}

	@After
	public void ReportClosure(){
		System.out.println("Browser closed");
   	    extent.flush();
   	    driver.close();
	}
	 
}