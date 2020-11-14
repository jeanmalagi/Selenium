package br.jean.suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.jean.test.TesteAjax;
import br.jean.test.TesteCadastro;
import br.jean.test.TesteCampoTreinamento;
import br.jean.test.TesteFramesJanelas;
import br.jean.test.TestePrimeFaces;
import br.jean.test.TesteRegrasCadastro;
import br.jean.test.TesteSincronismo;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
	TesteCampoTreinamento.class,
	TesteAjax.class,
	TesteFramesJanelas.class,
	TestePrimeFaces.class,
	TesteSincronismo.class,
})
public class SuiteTeste {

}
