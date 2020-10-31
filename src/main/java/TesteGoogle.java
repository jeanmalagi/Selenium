import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
		WebDriver driver = new FirefoxDriver();
		// WebDriver driver = new ChromeDriver();
		// WebDriver driver = new EdgeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 765));
		driver.manage().window().maximize();
		driver.get("http://www.google.com.br");
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
		}

}
