package com.testautomation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open the site
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

            // Enter username
            WebElement username = driver.findElement(By.id("user-name"));
            username.sendKeys("standard_user");

            // Enter password
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("secret_sauce");

            // Click login button
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            // Check if login was successful
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("inventory.html")) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }

            Thread.sleep(2000); // Wait to see the page
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
