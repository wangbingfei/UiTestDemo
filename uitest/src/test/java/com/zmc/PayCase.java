package com.zmc;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class PayCase
{

    AndroidDriver<WebElement> androidDriver;
    IOSDriver<WebElement> iosDriver;


    @Test
    public void test() throws UnsupportedEncodingException, InterruptedException {
        androidTest();
        DBConnection dbConnection = new DBConnection();
        //dbConnection.select();

    }

    @BeforeTest
    public void setUp() throws MalformedURLException {
        setUpAndroid();
    }

    @AfterClass
    public void tearDown() throws Exception {
        androidDriver.quit();
        //setUpIos();
    }

    public void setUpAndroid() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File app = new File("/Users/wangbingfei/Downloads/app-zmall-release-1.1.0.apk");
        // 不需要安装的话就去掉这个
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("deviceName", "192.168.56.101:5555");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.0");
        capabilities.setCapability("appPackage", "com.isg.ZMall");
        capabilities.setCapability("appActivity", "com.isg.mall.act.WelcomeAct");
        capabilities.setCapability("sessionOverride", true);    //每次启动覆盖session
        capabilities.setCapability("unicodeKeyboard", true);    //启动键盘
        capabilities.setCapability("resetKeyboard", false);
        androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public void setUpIos() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "Test");
        capabilities.setCapability("udid", "ad497b716a5a614b9657955be51e31289676534d");
        capabilities.setCapability("platformVersion", "9.3.4");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.APP, "com.iShangGang.iShangGang");
        capabilities.setCapability(MobileCapabilityType.UDID, "ad497b716a5a614b9657955be51e31289676534d");
        iosDriver = new IOSDriver<WebElement>(new URL("http://0.0.0.0:4727/wd/hub"), capabilities);
    }

    public void androidTest() throws InterruptedException {
        androidDriver.findElement(By.id("com.isg.ZMall:id/text_ed")).click();
        androidDriver.findElement(By.id("com.isg.ZMall:id/text_ed")).sendKeys("18621594181");
        androidDriver.findElement(By.id("com.isg.ZMall:id/get_ver_code_btn")).click();
        androidDriver.findElement(By.id("com.isg.ZMall:id/ver_ed")).click();
        androidDriver.findElement(By.id("com.isg.ZMall:id/ver_ed")).sendKeys("1111");

        String a = androidDriver.findElement(By.id("com.isg.ZMall:id/login_submit")).getText();
        //assertEquals(a, "登陆");


        //登陆
        androidDriver.findElement(By.id("com.isg.ZMall:id/login_submit")).click();

        androidDriver.runAppInBackground(15);
        //Thread.sleep(3000);
        //芝蚂收益
        androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget."
                + "FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view."
                + "View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android."
                + "widget.ScrollView/android.view.View/android.view.View[2]/android.widget.TextView")).click();
    }
}