import static br.com.jean.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jean.core.DSL;
import br.com.jean.core.DriverFactory;

public class TesteGoogle {
	
	private DSL dsl;
	
	@Before
	public void inicializa(){
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
		DriverFactory.killDriver();
	}
	
	@Test
	public void teste() {
		getDriver().get("http://www.google.com");
		Assert.assertEquals("Google", getDriver().getTitle());
	}

}
