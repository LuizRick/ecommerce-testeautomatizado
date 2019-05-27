package com.test.automatizado.ecommerce_teste;

import org.junit.Test;

import com.test.automatizado.ecommerce_teste.commons.helpers.ProcessHelper;

public class AmbienteTestes {

	
	@Test
	public void killPorcesstest() {
		try {
			ProcessHelper.killAllChromeDriveIntances();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
