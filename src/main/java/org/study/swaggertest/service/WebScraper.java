package org.study.swaggertest.service;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.swaggertest.entity.CrawledData;
import org.study.swaggertest.repository.CrawledRepository;
import org.study.swaggertest.util.WebDriverUtil;

import java.time.Duration;

@Service
@Slf4j
public class WebScraper {

    private final static Duration WEB_DRIVER_TIMEOUT = Duration.ofSeconds(30);

    @Autowired
    private CrawledRepository repository;

    public void scrapping() {
        WebDriver driver = WebDriverUtil.getChromeDriver();

        try {
            // URL 지정
            driver.get("http://blog.naver.com/zlwmqhqk");

            // 명시적 대기시간 지정
            WebDriverWait wait = new WebDriverWait(driver, WEB_DRIVER_TIMEOUT);

            // JS Executor
            JavascriptExecutor js = (JavascriptExecutor) driver;

            log.debug("페이지 로드 대기 시작");

            // JavaScript 페이지 로드가 끝날때까지 대기
            wait.until(webDriver -> {
                String readyState = js.executeScript("return document.readyState").toString();
                log.debug("현재 readyState: {}", readyState);
                return readyState.equals("complete");
            });

            log.debug("로딩 완료 : {}", driver.getTitle());
            log.debug("Page Source : {}", driver.getPageSource());

            WebElement iframe = driver.findElement(By.tagName("iframe"));
            if (iframe.isEnabled()) {
                driver.switchTo().frame(iframe);
            }

            log.debug("iFrame 전환");

            // iframe 내부 페이지 로드 대기
            wait.until(webDriver -> {
                String iframeReadyState = js.executeScript("return document.readyState").toString();
                log.debug("iframe 현재 readyState: {}", iframeReadyState);
                return iframeReadyState.equals("complete");
            });

            // 스크래핑 Target Element
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("meta-data")));
            log.debug("Page Source : {}", driver.getPageSource());
            String data = element.getText();
            // String data = driver.findElement(By.className("meta-data")).getText();


            // Xpath를 이용한 부분일치 스크래핑
            // String data = driver.findElement(By.xpath("//*[contains(@class, 'SixColumnPoster-module__common_list')]")).getText();


            CrawledData crawledData = new CrawledData();
            crawledData.setData(data);

            // repository.save(crawledData);
            log.debug("data : {}", data);
        } catch (Exception e) {
            log.error("Scraping error", e);
        } finally {
            WebDriverUtil.quit(driver);
        }
    }
}
