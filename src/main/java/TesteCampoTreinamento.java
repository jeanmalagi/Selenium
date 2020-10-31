import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
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
	public void testeTextField(){
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Testo Escrito");
	}
	@Test
	public void deveIntegarirComTextoArea(){
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Sugestões testes");
	}
	
	@Test
	public void deveIntegarirComRadioButton(){
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	
	@Test
	public void deveIntegarirComCheckBox(){
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:3")).isSelected());
	}
	@Test
	public void deveIntegarirComComboBox(){
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Mestrado");
		Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
	}
	@Test
	public void deveVerificarValoresComboBox(){
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrouValor = false;
		for(WebElement option: options){
			if(option.getText().equals("Mestrado")){
				encontrouValor = true;
				break;
			}
		}
		Assert.assertTrue(encontrouValor);
	}
	@Test
	public void deveVerificarValoresComboMultiplo(){
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("Karate");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Natacao");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	@Test
	public void deveIntegarirComBotoes(){ 
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	@Test
	public void deveIntegarirComLinks(){ 
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
	}
	@Test
	public void deveBuscarTextos(){ 
		Assert.assertEquals("Campo de Treinamento", 
				driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Nome", 
				driver.findElement(By.tagName("th")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				driver.findElement(By.className("facilAchar")).getText());
	}
	@Test
	public void deveInteragirComAlertSimples(){
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	@Test
	public void deveInteragirComAlertConfirmSim(){
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Confirm Simples", texto);
		alert.accept();
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();
	}
	@Test
	public void deveInteragirComAlertConfirmNao(){
			
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Confirm Simples", texto);
		alert.dismiss();
		Assert.assertEquals("Negado", alert.getText());
		alert.accept();
	}
	@Test
	public void deveInteragirComPrompt(){
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("30");
		alerta.accept();
		Assert.assertEquals("Era 30?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
	}
	@Test
	public void deveRealizarCadastro(){
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto campo nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Texto campo Sobrenome");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByIndex(6);	
		
		WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
		Select combo2 = new Select(element2);
		combo2.selectByVisibleText("Natacao");
		combo2.selectByVisibleText("Futebol");
		combo2.selectByVisibleText("Karate");
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Texto sugestões");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
	}
	@Test
	public void deveAcionarFrame(){
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	@Test
	public void deveInteragirComJanela(){
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
	}
	@Test
	public void deveInteragirComJanelaSemTitulo(){
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Será que vai dar certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("Acho que foi hein");
	}
}
