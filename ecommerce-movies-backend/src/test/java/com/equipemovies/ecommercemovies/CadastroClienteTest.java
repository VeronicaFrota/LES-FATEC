package com.equipemovies.ecommercemovies;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CadastroClienteTest {
	
	public static void main(String[] args) {
		File file = new File("C:\\Users\\RAFAELNUNESVAZQUEZ\\Documents\\Projeto_LES\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		WebDriver driver = new InternetExplorerDriver();
		
		driver.get("http://localhost:4200/");
	}
}
