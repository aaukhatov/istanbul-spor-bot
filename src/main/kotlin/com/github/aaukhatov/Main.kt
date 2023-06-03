package com.github.aaukhatov

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


fun main(args: Array<String>) {
    println("Spor İstanbul iBB Bot")

    val options = ChromeOptions()
    val driver = ChromeDriver(options)

    // sign-in to the portal
    driver.get("https://online.spor.istanbul/uyegiris")

    // TODO verify input args
    // fill out login params
    val tcNumber = args[0]
    val password = args[1]

    driver.findElement(By.xpath("//*[@id='txtTCPasaport']")).sendKeys(tcNumber)
    driver.findElement(By.xpath("//*[@id='txtSifre']")).sendKeys(password)

    // login
    driver.findElement(By.xpath("//*[@id='btnGirisYap']")).click()

    // handling timeouts
    val wait = WebDriverWait(driver, Duration.ofMillis(3000))

    // close the banner
    val banner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='basic']")))
    (driver as JavascriptExecutor).executeScript("arguments[0].click();", banner)

    // go the rent page
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='satiskiralik.aspx']"))).click()

    // the rest of the logic
}