package in.srssprojects.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility {
	static boolean status = false;

	public static void webTable(WebDriver driver, By tableLoc, String data, String operation) {
		boolean flag = false;
		WebElement table = driver.findElement(tableLoc);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		String[] pageLinks = rows.get(rows.size() - 1).getText().split(" ");
		for (int i = 1; i < pageLinks.length; i++) {
			table = driver.findElement(tableLoc);
			rows = table.findElements(By.tagName("tr"));
			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				for (WebElement cell : cells) {
					if (cells.get(0).getText().equalsIgnoreCase(data)) {
						if (operation.equalsIgnoreCase("update")) {
							// update operation
							cells.get(cells.size() - 2).findElement(By.tagName("a")).click();
						} else if (operation.equalsIgnoreCase("delete")) {
							// delete operation
							cells.get(cells.size() - 1).findElement(By.tagName("a")).click();
						}
						flag = true;
						break;// cells
					}

				}
				if (flag == true) {
					break;// rows
				}
			}
			if(flag==true){
				break;//pages
			}

			try {
				if (pageLinks[i].equals("...") && status == true) {
					// xpath to click on second ...
					table.findElement(By.xpath(".//a[text()='...'][2]")).click();

				} else {
					table.findElement(By.linkText(pageLinks[i])).click();
				}
			} catch (Exception e) {

			}
			if (pageLinks[i].equals("...")) {
				status = true;
				webTable(driver, tableLoc, data, operation);
			}
		}
	}

}
