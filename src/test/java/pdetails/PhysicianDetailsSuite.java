package pdetails;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import details.AutoUtil;

public class PhysicianDetailsSuite {

	public HashSet<String> names = new HashSet<String>();
	public List<String> details = new ArrayList<String>();

	@Test

	public void test1() {

		WebDriver driver = AutoUtil.getDriver();

		driver.get("https://findaphysician.bcm.edu/search/search");

		WebElement menu = driver.findElement(By.xpath(".//*[@id='section-options']/div/div/div/div[1]/h3/i"));
		menu.click();

		List<WebElement> allElements = driver
				.findElements(By.xpath(".//*[@id='section-options']/div/div/div/div[1]/h3/i"));

		for (WebElement elements : allElements) {
			System.out.println(elements.getText());
		}
	}

	@Test

	public void test2() {

		WebDriver driver = AutoUtil.getDriver();

		driver.get("https://findaphysician.bcm.edu/search/search");

		WebElement element = driver.findElement(By.xpath("//div[@class='search_filter col-sm-4 ng-scope']"));
		element.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement alphSearch = driver.findElement(By.xpath("//div[@class='alphabet']"));
		List<WebElement> allList = alphSearch.findElements(By.tagName("a"));
		for (int i = 0; i <= allList.size(); i++) {

			try {
				allList.get(i).click();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				WebElement uiElement1 = driver.findElement(By.xpath("//ul[@class='unstyled-list']"));
				List<WebElement> allList1 = uiElement1.findElements(By.tagName("li"));
				for (int j = 0; j <= allList1.size(); j++) {
					if (j == 0) {

					} else {
						if (names.contains(allList1.get(j).getText())) {
							continue;
						} else {
							names.add(allList1.get(j).getText());
							allList1.get(j).findElement(By.tagName("a")).click();
							Thread.sleep(5000);

							WebElement view = driver
									.findElement(By.xpath("//a[@class='ab-strong col-xs-4 col-sm-3 col-md-12']"));
							view.click();
							Thread.sleep(2000);
							try {
								WebElement name = driver.findElement(
										By.xpath("//h2[@class'listing-primary-headline page-heading ng-binding']"));
								System.out.println("Name is : " + name.getText());

								WebElement profession = driver
										.findElement(By.xpath("//h3[@class='listing-secondary-headline ng-binding']"));
								System.out.println("Profession is : " + profession.getText());

								WebElement practiseLocation = driver
										.findElement(By.xpath("//h3[@class='listing-secondary-headline']"));
								System.out.println("practiseLocation : " + practiseLocation.getText());

								details.add(name.getText());
								details.add(profession.getText());
								details.add(practiseLocation.getText());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							driver.close();
							test2();
						}

					}
				}
			} catch (Exception e) {

			}
		}

	}

	@After

	public void printLogs() throws Exception {
		System.out.println("----------Printing logs-----------");
		
		String fileName = "\\home\\deepak\\Desktop\\Xpertdox.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		for(String string : details) {
			System.out.println(string);
		}
		writer.close();
	}

}
