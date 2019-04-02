package com.test.automatizado.ecommerce_teste.commons;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.test.automatizado.ecommerce_teste.commons.dsl.DSL;
import com.test.automatizado.ecommerce_teste.commons.helpers.ProcessHelper;

public abstract class SeleniumBase {

	protected WebDriver driver;
	protected DSL dsl;
	protected String host;
	protected String port;
	protected Boolean callFinish;
	protected ChromeOptions options;

	@Before
	public void inicializa() {
		if(System.getProperty("user.name").equals("luizmonteiro")) {
			System.setProperty("webdriver.chrome.driver" , "C:/Users/luizmonteiro/webdrivers/chromedriver.exe");
		}
		
		options = new ChromeOptions();
		//options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		dsl = new DSL(driver);
		host = "http://localhost";
		port = "8888";
		callFinish = true;
	}

	@After
	public void finaliza() {
		if (callFinish) {
			driver.quit();
			try {
				ProcessHelper.killAllChromeDriveIntances();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	protected String getUrlBase() {
		String url = String.format("%s:%s", host, port);
		return url;
	}

	protected String getRamdomNumber(Double limiter) {
		return Math.random() * limiter + "";
	}
	
	protected String getRamdomNumber(Integer limiter) {
		int random = (int) Math.random() * limiter;
		return random + "";
	}

	protected String getRamdomNumber(Integer min, Integer max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt() + "";
	}
	
	protected String getRamdomDate() {
		GregorianCalendar gc = new GregorianCalendar();
		
		int year = randBetween(1900, 2010);
		gc.set(Calendar.YEAR, year);
		int dayOfYear = randBetween(1,gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return String.format("%s/%s/%s", gc.get(Calendar.YEAR), (gc.get(Calendar.MONTH) + 1), gc.get(Calendar.DAY_OF_MONTH));
	}
	
	private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
