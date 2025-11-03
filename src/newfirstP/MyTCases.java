package newfirstP;

import static org.testng.Assert.assertEquals;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.Assert;

public class MyTCases {
	WebDriver driver= new ChromeDriver();

	String WebSite = "https://codenboxautomationlab.com/practice/";

	Random rand = new Random();
	JavascriptExecutor js= (JavascriptExecutor)driver;
	Actions action=new Actions(driver);
	@BeforeTest
	public void mysitting() {
		
		driver.get(WebSite);
		driver.manage().window().maximize();
	}
	
	
	@Test(priority = 1, enabled = true)
	public void Radio_Button() throws InterruptedException {
		
		List<WebElement> radioButtonElements= driver.findElements(By.className("radioButton"));
		int RandomButton= rand.nextInt(radioButtonElements.size());
		radioButtonElements.get(RandomButton).click();
		
		boolean ExpectedResult= true;
		boolean ActualResult=radioButtonElements.get(RandomButton).isSelected();
	Assert.assertEquals(ActualResult, ExpectedResult);

		
	}
	
	@Test(priority = 2, enabled = true)
	public void dynamic_Dropdown() throws InterruptedException {
		String[] countryCodes = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES", "AM" };
		int randomIndex= rand.nextInt(countryCodes.length);
		WebElement dynamicList= driver.findElement(By.id("autocomplete"));
		dynamicList.sendKeys(countryCodes[randomIndex]);
		Thread.sleep(1000);
		dynamicList.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		boolean ExpectedResult= true;
		
		String dataInsideInputString= (String) js.executeScript("return arguments[0].value", dynamicList);
		//برجع اول argument موجودة ب ويب ايليمنت معين والي هو بحالتنا داينمك ليست
//		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", DynamicListInput);

		String UpperDataInsideInput=dataInsideInputString.toUpperCase();
		boolean ActualResult= UpperDataInsideInput.contains(countryCodes[randomIndex].toUpperCase());
		
        Assert.assertEquals(ActualResult, ExpectedResult);
		

	}
	
	@Test(priority = 3, enabled = false)
	public void Static_DropDown() throws InterruptedException {
		
		WebElement staticListElement= driver.findElement(By.id("dropdown-class-example"));
		Select sel=new Select(staticListElement);
		List<WebElement> allElements= sel.getOptions();
		
		int totalOption=allElements.size();
		int randomOption= rand.nextInt(totalOption-1)+1;
		sel.selectByIndex(randomOption);
		
	}
	
	@Test(priority = 4, enabled = false)
	public void CheckBox() throws InterruptedException {
		
		List<WebElement> optionsElements= driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		int RandOption=rand.nextInt(optionsElements.size());
//		optionsElements.get(RandOption).click();
		for(int i=0;i<optionsElements.size();i++) {
			optionsElements.get(i).click();
			boolean ExpectedResult= true;
			boolean ActualResult= optionsElements.get(i).isSelected();
	        Assert.assertEquals(ActualResult, ExpectedResult);


		}
	}
	
	@Test(priority = 5, enabled = false)
	public void NewWindow() throws InterruptedException {
		WebElement windowButtonElement= driver.findElement(By.id("openwindow"));
		windowButtonElement.click();
		Thread.sleep(2000);
		List<String> windowhandles= new ArrayList<String>(driver.getWindowHandles());
		System.out.println(windowhandles.size());

		driver.switchTo().window(windowhandles.get(1));
		WebElement ContactButton = driver.findElement(By.id("menu-item-9680"));
		ContactButton.click();
		System.out.println(driver.getTitle() + " hello from the second window");
		driver.close();
		// switch to the first window
		driver.switchTo().window(windowhandles.get(0));
	}
	
	
	@Test(priority = 6, enabled = false)
	public void opentab() throws InterruptedException {
		WebElement opentabElement= driver.findElement(By.id("opentab"));
		opentabElement.click();
		List<String> windowHandles= new ArrayList<String>(driver.getWindowHandles());
		System.out.println(windowHandles.size());
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(2000);

		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		driver.close();

		driver.switchTo().window(windowHandles.get(0));
		System.out.println(driver.getTitle());


	}
	
	@Test(priority = 7, enabled = false)
	public void Alert() throws InterruptedException {
		WebElement NameInput= driver.findElement(By.id("name"));
		NameInput.sendKeys("eman");
//		WebElement Alertbtn= driver.findElement(By.id("alertbtn"));
//		Alertbtn.click();
//		Thread.sleep(2000);
//		driver.switchTo().alert().accept();
		
		WebElement ConfirmBox = driver.findElement(By.id("confirmbtn"));
		ConfirmBox.click();
		Thread.sleep(1000);
		 System.out.println(driver.switchTo().alert().getText());

		 driver.switchTo().alert().accept();
//		 driver.switchTo().alert().dismiss();


		
	}
	
	
	@Test(priority = 8, enabled = false)
	public void WebTable() throws InterruptedException {
		WebElement tablElement= driver.findElement(By.id("product"));
		List<WebElement> dataInsideTable= driver.findElements(By.tagName("tr"));
		
		for(int i=1; i<dataInsideTable.size();i++) {
			int TotalTDinRow= dataInsideTable.get(i).findElements(By.tagName("td")).size();
			
			System.out.println(dataInsideTable.get(i).findElements(By.tagName("td")).get(TotalTDinRow-1).getText());


		}
	}

	@Test(priority = 9, enabled = false)
	public void Displayed() throws InterruptedException {
		js.executeScript("window.scrollTo(0,1500)");
		WebElement displayedtextInput=driver.findElement(By.id("displayed-text"));

		
		WebElement HideBtn=driver.findElement(By.id("hide-textbox"));
		WebElement ShowBtn=driver.findElement(By.id("show-textbox"));
		
		HideBtn.click();
		boolean ExpectedResult= false;
		boolean ActualResult= displayedtextInput.isDisplayed();
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		
		Thread.sleep(2000);
		
		ShowBtn.click();
		boolean ExpectedResult2= true;
		boolean ActualResult2= displayedtextInput.isDisplayed();
		
		Assert.assertEquals(ActualResult2, ExpectedResult2);
		

	}
	
	
	@Test(priority = 10, enabled = false)
	public void Enabled() throws InterruptedException {
		js.executeScript("window.scrollTo(0,1500)");
		
		WebElement EnabledExampleInput=driver.findElement(By.id("enabled-example-input"));

		WebElement disabledButton=driver.findElement(By.id("disabled-button"));
		WebElement enabledButton=driver.findElement(By.id("enabled-button"));
		
		disabledButton.click();
		boolean ExpectedResult= false;
		boolean ActualResult= EnabledExampleInput.isEnabled();
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		
		
		

		
		Thread.sleep(2000);
		
		enabledButton.click();
		boolean ExpectedResult2= true;
		boolean ActualResult2= EnabledExampleInput.isEnabled();
		
		Assert.assertEquals(ActualResult2, ExpectedResult2);
		

	}
	
	@Test(priority = 11, enabled = true)
	
	public void Mousehover() throws InterruptedException {
		js.executeScript("window.scrollTo(0,1800)");

		
		WebElement mousehoverBtn= driver.findElement(By.id("mousehover"));
		action.moveToElement(mousehoverBtn).perform();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Top")).click();
	
	}
	
	
	
//	
//	@AfterTest
//	public void AfterTest() {
//		
//		driver.quit();
//	}

}
