import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteRegraNegocio {
	
	private WebDriver driver; 
	
	@Before
	public void inicializacao(){
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
	}
	
	@After
	public void finalizacao(){
		driver.quit();
	}
	
	@Test
	public void validarCampoNome(){
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		driver.quit();
	}
	
	@Test
	public void validarCampoSobrenome(){
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste Nome");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		driver.quit();
	}
	
	@Test
	public void validarCheckSexo(){
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teste sobrenome");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		driver.quit();
	}
	
	@Test
	public void validarPraticaEsportes(){
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teste sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		driver.quit();
	}

}
