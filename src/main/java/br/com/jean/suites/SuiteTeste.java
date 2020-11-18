package br.com.jean.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.jean.core.DriverFactory;
import br.com.jean.test.TesteCadastro;
import br.com.jean.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaDriver(){
		DriverFactory.killDriver();
	}
}
