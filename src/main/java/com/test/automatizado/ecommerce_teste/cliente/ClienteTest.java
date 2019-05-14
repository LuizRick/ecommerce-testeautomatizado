package com.test.automatizado.ecommerce_teste.cliente;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automatizado.ecommerce_teste.commons.SeleniumBase;
import com.test.automatizado.ecommerce_teste.commons.geradores.GeraCpfCnpj;

public class ClienteTest extends SeleniumBase {

	
	@Test
	public void testaCadastro() throws InterruptedException {
		GeraCpfCnpj gCpfCnpj = new GeraCpfCnpj();
		driver.navigate().to(getUrlBase() + "/admin/cliente/cadastro");
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("nome")));
		dsl.escrever(By.id("nome"), "fulano num " + getRamdomNumber(1, 465456465));
		dsl.escrever(By.id("dataNascimento"), "03/03/1993");
		dsl.escrever(By.id("cpf"), gCpfCnpj.cpf());
		dsl.escrever(By.id("usuario.email"), String.format("email_%s@gmail.com", getRamdomNumber(1, 100000)));
		dsl.escrever(By.id("telefone.ddd"), "11");
		dsl.escrever(By.id("telefone.numero"), "456895253");
		dsl.selecionarCombo("telefone.tipo", "RESIDENCIAL");
		dsl.clicarRadio("genero" + getRamdomNumber(1, 2));
		dsl.clicarBotao("btntab-endereco");
		dsl.clicarBotao("btnAddEndereco");
		(new WebDriverWait(driver, 30)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		dsl.clicarBotao("btnAddCartao");
		(new WebDriverWait(driver, 70)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-endereco"))).click();
		for(int i = 0; i < 2;i++) {
			(new WebDriverWait(driver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.id("enderecos" + i + ".descricao")));
			dsl.escrever(By.id("enderecos" + i + ".descricao"), "decricao num :" + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i + ".tipoResidencia"), "casa");
			dsl.escrever(By.id("enderecos" + i + ".tipoLogradouro"), "rua");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), getRamdomNumber(1,1255));
			
			
			dsl.escrever(By.id("enderecos" + i  + ".bairro"), "bairro: " + getRamdomNumber(1000,1255));
			dsl.escrever(By.id("enderecos" + i  + ".cep"),  getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i  + ".pais"), "Brasil");
			dsl.escrever(By.id("enderecos" + i  + ".estado"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".cidade"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), "Mogi das Cruzes");
		}
		
		dsl.selecionarCombo("enderecos0.tipo", "COBRANCA");
		dsl.selecionarCombo("enderecos1.tipo", "ENTREGA");
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		
		for(int i = 0; i < 2;i++) {
			dsl.escrever(By.id("cartoes" + i + ".titular"), "titular num: " + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("cartoes" + i + ".numero"), "546745674894" + getRamdomNumber(1000, 9999));
			dsl.escrever(By.id("cartoes" + i + ".codigoSeguranca"), getRamdomNumber(100,999));
			dsl.selecionarComboIndex("cartoes"  + i +".bandeira",getRamdomNumber(1,5));
		}
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-dados"))).click();		
		dsl.escrever(By.id("usuario.password"),"LH!336mn");
		dsl.escrever(By.id("usuario.confirmaPassword"), "LH!336mn");
		
		dsl.clicarBotao("btnSalvarCliente");
		callFinish = true;
	}
	
	
	
	@Test
	public void testConsultaCliente() {
		driver.navigate().to(getUrlBase() + "/admin/clientes/consultar");
		driver.findElement(By.xpath("//*[@id=\"frmCliente\"]/button")).click();
		callFinish = false;
	}
	
	
	@Test
	public void testConsultaClientePorNome() {
		driver.navigate().to(getUrlBase() + "/admin/clientes/consultar");
		dsl.escrever(By.id("nome"), "Luiz henrique");
		driver.findElement(By.xpath("//*[@id=\"frmCliente\"]/button")).click();
		callFinish = false;
	}
	
	@Test
	public void deveVisualizarEditar() {
		driver.navigate().to(getUrlBase() + "/admin/clientes/consultar");
		driver.findElement(By.xpath("//*[@id=\"frmCliente\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"resultadoPesquisa\"]/tbody/tr/td[5]/a[1]")).click();
		(new WebDriverWait(driver, 30)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		GeraCpfCnpj gCpfCnpj = new GeraCpfCnpj();
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("nome")));
		dsl.escrever(By.id("nome"), "fulano num " + getRamdomNumber(1, 465456465));
		dsl.escrever(By.id("dataNascimento"), "03/03/1993");
		dsl.escrever(By.id("cpf"), gCpfCnpj.cpf());
		dsl.escrever(By.id("telefone.ddd"), "11");
		dsl.escrever(By.id("telefone.numero"), "456895253");
		dsl.selecionarCombo("telefone.tipo", "RESIDENCIAL");
		dsl.clicarRadio("genero" + getRamdomNumber(1, 2));
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-endereco"))).click();
		for(int i = 0; i < 2;i++) {
			(new WebDriverWait(driver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.id("enderecos" + i + ".descricao")));
			dsl.escrever(By.id("enderecos" + i + ".descricao"), "decricao num :" + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i + ".tipoResidencia"), "casa");
			dsl.escrever(By.id("enderecos" + i + ".tipoLogradouro"), "rua");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), getRamdomNumber(1,1255));
			
			
			dsl.escrever(By.id("enderecos" + i  + ".bairro"), "bairro: " + getRamdomNumber(1000,1255));
			dsl.escrever(By.id("enderecos" + i  + ".cep"),  getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i  + ".pais"), "Brasil");
			dsl.escrever(By.id("enderecos" + i  + ".estado"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".cidade"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), "Mogi das Cruzes");
		}
		
		dsl.selecionarCombo("enderecos0.tipo", "COBRANCA");
		dsl.selecionarCombo("enderecos1.tipo", "ENTREGA");
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		
		for(int i = 0; i < 2;i++) {
			dsl.escrever(By.id("cartoes" + i + ".titular"), "titular num: " + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("cartoes" + i + ".numero"), "546745674894" + getRamdomNumber(1000, 9999));
			dsl.escrever(By.id("cartoes" + i + ".codigoSeguranca"), getRamdomNumber(100,999));
			dsl.selecionarComboIndex("cartoes"  + i +".bandeira",getRamdomNumber(1,5));
		}
		
		dsl.clicarBotao("btnEditarCliente");
		callFinish = false;
	}
	
	//validação
	
	@Test
	public void deveRetornarValidacaoObrigatorioCliente() {
		driver.navigate().to(getUrlBase() + "/admin/cliente/cadastro");
		dsl.clicarBotao("btnSalvarCliente");
		callFinish = false;
	}
	
	
	@Test
	public void deveRetornarValidacaoEnderecoEntrega() {
		GeraCpfCnpj gCpfCnpj = new GeraCpfCnpj();
		driver.navigate().to(getUrlBase() + "/admin/cliente/cadastro");
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("nome")));
		dsl.escrever(By.id("nome"), "fulano num " + getRamdomNumber(1, 465456465));
		dsl.escrever(By.id("dataNascimento"), "03/03/1993");
		dsl.escrever(By.id("cpf"), gCpfCnpj.cpf());
		dsl.escrever(By.id("usuario.email"), String.format("email_%s@gmail.com", getRamdomNumber(1, 100000)));
		dsl.escrever(By.id("telefone.ddd"), "11");
		dsl.escrever(By.id("telefone.numero"), "456895253");
		dsl.selecionarCombo("telefone.tipo", "RESIDENCIAL");
		dsl.clicarRadio("genero" + getRamdomNumber(1, 2));
		dsl.clicarBotao("btntab-endereco");
		dsl.clicarBotao("btnAddEndereco");
		(new WebDriverWait(driver, 30)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		dsl.clicarBotao("btnAddCartao");
		(new WebDriverWait(driver, 70)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-endereco"))).click();
		for(int i = 0; i < 2;i++) {
			(new WebDriverWait(driver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.id("enderecos" + i + ".descricao")));
			dsl.escrever(By.id("enderecos" + i + ".descricao"), "decricao num :" + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i + ".tipoResidencia"), "casa");
			dsl.escrever(By.id("enderecos" + i + ".tipoLogradouro"), "rua");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), getRamdomNumber(1,1255));
			
			
			dsl.escrever(By.id("enderecos" + i  + ".bairro"), "bairro: " + getRamdomNumber(1000,1255));
			dsl.escrever(By.id("enderecos" + i  + ".cep"),  getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i  + ".pais"), "Brasil");
			dsl.escrever(By.id("enderecos" + i  + ".estado"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".cidade"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), "Mogi das Cruzes");
		}
		
		dsl.selecionarCombo("enderecos0.tipo", "COBRANCA");
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		
		for(int i = 0; i < 2;i++) {
			dsl.escrever(By.id("cartoes" + i + ".titular"), "titular num: " + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("cartoes" + i + ".numero"), "546745674894" + getRamdomNumber(1000, 9999));
			dsl.escrever(By.id("cartoes" + i + ".codigoSeguranca"), getRamdomNumber(100,999));
			dsl.selecionarComboIndex("cartoes"  + i +".bandeira",getRamdomNumber(1,5));
		}
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-dados"))).click();		
		dsl.escrever(By.id("usuario.password"),"LH!336mn");
		dsl.escrever(By.id("usuario.confirmaPassword"), "LH!336mn");
		
		dsl.clicarBotao("btnSalvarCliente");
		callFinish = false;
	}
	
	
	@Test
	public void deveRetornarValidacaoEnderecoCobranca() {
		GeraCpfCnpj gCpfCnpj = new GeraCpfCnpj();
		driver.navigate().to(getUrlBase() + "/admin/cliente/cadastro");
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("nome")));
		dsl.escrever(By.id("nome"), "fulano num " + getRamdomNumber(1, 465456465));
		dsl.escrever(By.id("dataNascimento"), "03/03/1993");
		dsl.escrever(By.id("cpf"), gCpfCnpj.cpf());
		dsl.escrever(By.id("usuario.email"), String.format("email_%s@gmail.com", getRamdomNumber(1, 100000)));
		dsl.escrever(By.id("telefone.ddd"), "11");
		dsl.escrever(By.id("telefone.numero"), "456895253");
		dsl.selecionarCombo("telefone.tipo", "RESIDENCIAL");
		dsl.clicarRadio("genero" + getRamdomNumber(1, 2));
		dsl.clicarBotao("btntab-endereco");
		dsl.clicarBotao("btnAddEndereco");
		(new WebDriverWait(driver, 30)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		dsl.clicarBotao("btnAddCartao");
		(new WebDriverWait(driver, 70)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-endereco"))).click();
		for(int i = 0; i < 2;i++) {
			(new WebDriverWait(driver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.id("enderecos" + i + ".descricao")));
			dsl.escrever(By.id("enderecos" + i + ".descricao"), "decricao num :" + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i + ".tipoResidencia"), "casa");
			dsl.escrever(By.id("enderecos" + i + ".tipoLogradouro"), "rua");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), getRamdomNumber(1,1255));
			
			
			dsl.escrever(By.id("enderecos" + i  + ".bairro"), "bairro: " + getRamdomNumber(1000,1255));
			dsl.escrever(By.id("enderecos" + i  + ".cep"),  getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i  + ".pais"), "Brasil");
			dsl.escrever(By.id("enderecos" + i  + ".estado"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".cidade"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), "Mogi das Cruzes");
		}
		
		dsl.selecionarCombo("enderecos0.tipo", "ENTREGA");
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		
		for(int i = 0; i < 2;i++) {
			dsl.escrever(By.id("cartoes" + i + ".titular"), "titular num: " + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("cartoes" + i + ".numero"), "546745674894" + getRamdomNumber(1000, 9999));
			dsl.escrever(By.id("cartoes" + i + ".codigoSeguranca"), getRamdomNumber(100,999));
			dsl.selecionarComboIndex("cartoes"  + i +".bandeira",getRamdomNumber(1,5));
		}
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-dados"))).click();		
		dsl.escrever(By.id("usuario.password"),"LH!336mn");
		dsl.escrever(By.id("usuario.confirmaPassword"), "LH!336mn");
		
		dsl.clicarBotao("btnSalvarCliente");
		callFinish = false;
	}
	
	@Test
	public void deveRetornarValidcaoSenhaForte() {
		GeraCpfCnpj gCpfCnpj = new GeraCpfCnpj();
		driver.navigate().to(getUrlBase() + "/admin/cliente/cadastro");
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("nome")));
		dsl.escrever(By.id("nome"), "fulano num " + getRamdomNumber(1, 465456465));
		dsl.escrever(By.id("dataNascimento"), "03/03/1993");
		dsl.escrever(By.id("cpf"), gCpfCnpj.cpf());
		dsl.escrever(By.id("usuario.email"), String.format("email_%s@gmail.com", getRamdomNumber(1, 100000)));
		dsl.escrever(By.id("telefone.ddd"), "11");
		dsl.escrever(By.id("telefone.numero"), "456895253");
		dsl.selecionarCombo("telefone.tipo", "RESIDENCIAL");
		dsl.clicarRadio("genero" + getRamdomNumber(1, 2));
		dsl.clicarBotao("btntab-endereco");
		dsl.clicarBotao("btnAddEndereco");
		(new WebDriverWait(driver, 30)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		dsl.clicarBotao("btnAddCartao");
		(new WebDriverWait(driver, 70)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-endereco"))).click();
		for(int i = 0; i < 2;i++) {
			(new WebDriverWait(driver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.id("enderecos" + i + ".descricao")));
			dsl.escrever(By.id("enderecos" + i + ".descricao"), "decricao num :" + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i + ".tipoResidencia"), "casa");
			dsl.escrever(By.id("enderecos" + i + ".tipoLogradouro"), "rua");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), getRamdomNumber(1,1255));
			
			
			dsl.escrever(By.id("enderecos" + i  + ".bairro"), "bairro: " + getRamdomNumber(1000,1255));
			dsl.escrever(By.id("enderecos" + i  + ".cep"),  getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i  + ".pais"), "Brasil");
			dsl.escrever(By.id("enderecos" + i  + ".estado"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".cidade"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), "Mogi das Cruzes");
		}
		
		dsl.selecionarCombo("enderecos0.tipo", "COBRANCA");
		dsl.selecionarCombo("enderecos1.tipo", "ENTREGA");
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		
		for(int i = 0; i < 2;i++) {
			dsl.escrever(By.id("cartoes" + i + ".titular"), "titular num: " + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("cartoes" + i + ".numero"), "546745674894" + getRamdomNumber(1000, 9999));
			dsl.escrever(By.id("cartoes" + i + ".codigoSeguranca"), getRamdomNumber(100,999));
			dsl.selecionarComboIndex("cartoes"  + i +".bandeira",getRamdomNumber(1,5));
		}
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-dados"))).click();		
		dsl.escrever(By.id("usuario.password"),"aas");
		dsl.escrever(By.id("usuario.confirmaPassword"), "aass");
		
		dsl.clicarBotao("btnSalvarCliente");
		callFinish = false;
	}
	
	@Test
	public void deveRetornarValidacaoConfirmacaoSenha() {
		GeraCpfCnpj gCpfCnpj = new GeraCpfCnpj();
		driver.navigate().to(getUrlBase() + "/admin/cliente/cadastro");
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("nome")));
		dsl.escrever(By.id("nome"), "fulano num " + getRamdomNumber(1, 465456465));
		dsl.escrever(By.id("dataNascimento"), "03/03/1993");
		dsl.escrever(By.id("cpf"), gCpfCnpj.cpf());
		dsl.escrever(By.id("usuario.email"), String.format("email_%s@gmail.com", getRamdomNumber(1, 100000)));
		dsl.escrever(By.id("telefone.ddd"), "11");
		dsl.escrever(By.id("telefone.numero"), "456895253");
		dsl.selecionarCombo("telefone.tipo", "RESIDENCIAL");
		dsl.clicarRadio("genero" + getRamdomNumber(1, 2));
		dsl.clicarBotao("btntab-endereco");
		dsl.clicarBotao("btnAddEndereco");
		(new WebDriverWait(driver, 30)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		dsl.clicarBotao("btnAddCartao");
		(new WebDriverWait(driver, 70)).until(driver -> dsl.executarJS("return document.readyState").equals("complete"));
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-endereco"))).click();
		for(int i = 0; i < 2;i++) {
			(new WebDriverWait(driver, 70)).until(ExpectedConditions.visibilityOfElementLocated(By.id("enderecos" + i + ".descricao")));
			dsl.escrever(By.id("enderecos" + i + ".descricao"), "decricao num :" + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i + ".tipoResidencia"), "casa");
			dsl.escrever(By.id("enderecos" + i + ".tipoLogradouro"), "rua");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), getRamdomNumber(1,1255));
			
			
			dsl.escrever(By.id("enderecos" + i  + ".bairro"), "bairro: " + getRamdomNumber(1000,1255));
			dsl.escrever(By.id("enderecos" + i  + ".cep"),  getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("enderecos" + i  + ".pais"), "Brasil");
			dsl.escrever(By.id("enderecos" + i  + ".estado"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".cidade"), "São paulo");
			dsl.escrever(By.id("enderecos" + i  + ".numero"), "Mogi das Cruzes");
		}
		
		dsl.selecionarCombo("enderecos0.tipo", "ENTREGA");
		dsl.selecionarCombo("enderecos1.tipo", "COBRANCA");
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-cartoes"))).click();
		
		for(int i = 0; i < 2;i++) {
			dsl.escrever(By.id("cartoes" + i + ".titular"), "titular num: " + getRamdomNumber(1, 465456465));
			dsl.escrever(By.id("cartoes" + i + ".numero"), "546745674894" + getRamdomNumber(1000, 9999));
			dsl.escrever(By.id("cartoes" + i + ".codigoSeguranca"), getRamdomNumber(100,999));
			dsl.selecionarComboIndex("cartoes"  + i +".bandeira",getRamdomNumber(1,5));
		}
		
		(new WebDriverWait(driver, 70)).until(ExpectedConditions.elementToBeClickable(By.id("btntab-dados"))).click();		
		dsl.escrever(By.id("usuario.password"),"LH!336mn");
		dsl.escrever(By.id("usuario.confirmaPassword"), "LH!336ms");
		
		dsl.clicarBotao("btnSalvarCliente");
		callFinish = false;
	}
}
