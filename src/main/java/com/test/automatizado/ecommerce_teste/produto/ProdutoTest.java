package com.test.automatizado.ecommerce_teste.produto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.test.automatizado.ecommerce_teste.commons.dsl.DSL;
import com.test.automatizado.ecommerce_teste.commons.helpers.ProcessHelper;

public class ProdutoTest {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://localhost:8888");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		
	}

	
	
	@Test
	public void testaCadastro() {
		
	}
}
