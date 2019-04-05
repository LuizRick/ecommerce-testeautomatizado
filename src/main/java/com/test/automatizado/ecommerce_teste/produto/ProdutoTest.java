package com.test.automatizado.ecommerce_teste.produto;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automatizado.ecommerce_teste.commons.SeleniumBase;

public class ProdutoTest extends SeleniumBase {

	
	@Test
	public void testaCadastro() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/cadastro");
		dsl.escrever(By.id("descricao"), "Produto teste " + Math.random() * 100);
		dsl.escrever(By.id("marca"), "Marca teste " + Math.random() * 100);
		dsl.escrever(By.id("modelo"), "Modelo teste " + Math.random() * 100);
		dsl.escrever(By.id("peso"), getRamdomNumber(15D));
		dsl.escrever(By.id("altura"), getRamdomNumber(15D));
		dsl.escrever(By.id("largura"), getRamdomNumber(20D));
		dsl.selecionarCombo("departamento", getRamdomNumber(1,8));
		dsl.selecionarCombo("grupoPrecificacao", getRamdomNumber(1, 4));
		dsl.escrever(By.id("codigoBarras"), getRamdomNumber(130000000));
		dsl.escrever(By.id("estoque"), getRamdomNumber(1,100));
		dsl.escrever(By.id("valorCusto"), getRamdomNumber(100,4000));
		dsl.escrever(By.id("caracteristicas"), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ex metus, "
				+ "semper et euismod varius, euismod vitae velit. Sed posuere est non odio euismod efficitur. Quisque efficitur "
				+ "placerat ornare. Maecenas interdum urna eget justo accumsan, lacinia finibus massa porttitor. Proin egestas");
		dsl.clicarBotao("btnSalvar");
		callFinish = true;
	}
	
	
	
	@Test
	public void deveRetornarResultadoPesquisa() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		callFinish = false;
	}
	
	
	@Test
	public void deveRetornarResultadoPesquisaHardware() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.selecionarCombo("departamento", "1");
		dsl.clicarBotao("btnConsultar");
		callFinish = false;
	}
	
	
	@Test
	public void deveRetornarResultadoVisualizar() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		dsl.executarJS("document.querySelector('#resultadoPesquisa a').click()", "");
		callFinish = false;
	}
	
	
	@Test
	public void deveEditarProduto() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		dsl.executarJS("document.querySelectorAll('#resultadoPesquisa a')[5].click()", "");
		dsl.escrever(By.id("descricao"), "Produto teste " + Math.random() * 100);
		dsl.escrever(By.id("marca"), "Marca teste " + Math.random() * 100);
		dsl.escrever(By.id("modelo"), "Modelo teste " + Math.random() * 100);
		dsl.escrever(By.id("peso"), getRamdomNumber(15D));
		dsl.escrever(By.id("altura"), getRamdomNumber(15D));
		dsl.escrever(By.id("largura"), getRamdomNumber(20D));
		dsl.selecionarCombo("departamento", getRamdomNumber(1,8));
		dsl.selecionarCombo("grupoPrecificacao", getRamdomNumber(1, 4));
		dsl.escrever(By.id("codigoBarras"), getRamdomNumber(130000000));
		dsl.escrever(By.id("valorCusto"), getRamdomNumber(4000D));
		dsl.escrever(By.id("caracteristicas"), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ex metus, "
				+ "semper et euismod varius, euismod vitae velit. Sed posuere est non odio euismod efficitur.");
		dsl.clicarBotao("btnSalvar");
		callFinish = false;
	}
	
	
	@Test
	public void InativarProduto() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		dsl.executarJS("document.querySelectorAll('#resultadoPesquisa a')[3].click()", "");
		dsl.clicarBotao("btnInativar");
		dsl.selecionarCombo("categoriaInativacao", "FORAMERCADO");
		dsl.escrever(By.id("justificativaInativacao"), "teste automaitzado de inativação");
		dsl.clicarBotao("btnInativar");
		callFinish = false;
	}
	
	@Test
	public void AtivarProduto() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		dsl.executarJS("document.querySelectorAll('#resultadoPesquisa a')[3].click()", "");
		dsl.clicarBotao("btnAtivar");
		dsl.selecionarCombo("categoriaInativacao", "RETORNOMERCADO");
		dsl.escrever(By.id("justificativaInativacao"), "teste automaitzado de inativação");
		dsl.clicarBotao("btnAtivar");
		callFinish = false;
	}
	
	//teste de validação
	
	@Test
	public void DeveRetornarErroAtivacao() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		dsl.executarJS("document.querySelectorAll('#resultadoPesquisa a')[3].click()", "");
		dsl.clicarBotao("btnAtivar");
		dsl.selecionarCombo("categoriaInativacao", "FORAMERCADO");
		dsl.escrever(By.id("justificativaInativacao"), "teste automaitzado de inativação");
		dsl.clicarBotao("btnAtivar");
		callFinish = false;
	}
	
	@Test
	public void DeveRetornarErroInativacao() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		dsl.executarJS("document.querySelectorAll('#resultadoPesquisa a')[3].click()", "");
		dsl.clicarBotao("btnInativar");
		dsl.selecionarCombo("categoriaInativacao", "RETORNOMERCADO");
		dsl.escrever(By.id("justificativaInativacao"), "teste automaitzado de inativação");
		dsl.clicarBotao("btnInativar");
		callFinish = false;
	}
	
	@Test
	public void deveRetornarDadosObrigatorios() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/cadastro");
		dsl.clicarBotao("btnSalvar");
		callFinish = false;
	}
	
	
	@Test
	public void deveRetonarValidacaoReentrada() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btnConsultar")));
		dsl.clicarBotao("btnConsultar");
		dsl.escrever(By.id("estoque"), "100");
		dsl.clicarBotao("btnSalvar");
		callFinish = false;
	}
	
	
	@Test
	public void deveRetornarValidacaoValorVenda() {
		driver.navigate().to(getUrlBase() + "/admin/produtos/consultar");
		dsl.clicarBotao("btnConsultar");
		dsl.executarJS("document.querySelectorAll('#resultadoPesquisa a')[0].click()", "");
		dsl.escrever(By.id("valorVenda"), "19.0");
		dsl.clicarBotao("btnSalvar");
		callFinish = false;
	}
	
}
