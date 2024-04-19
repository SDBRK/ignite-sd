package com.lo.test;

//import com.itextpdf.html2pdf.ConverterProperties;
//import com.itextpdf.html2pdf.HtmlConverter;
import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author RujiangLiu
 * @date 2024/1/2
 */
public class HtmlToPdfTest {

    public static void main(String[] args) throws Exception {
        String url = "http://echarts.baidu.com/examples/";
        Integer sleepTime = 5 * 1000; // 截图等待时间
        String targetPath = "D:/test.pdf";// 生成图片文件路径
        // 设置是否启用headless模式
        System.setProperty("java.awt.headless", "true");
        // 加载谷歌浏览器驱动
//        URL resource = Test.class.getClassLoader().getResource("F:\\Program Files\\chromedriver_win32");
//        String driverPath = java.net.URLDecoder.decode(resource.getPath(), "UTF-8") + "\\chromedriver.exe";
//        String driverPath = "F:\\Program Files\\chromedriver_win32\\chromedriver.exe";
        String driverPath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        ChromeOptions option = new ChromeOptions();
//        option.addArguments("disable-infobars");
        option.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors");
        option.addArguments("--disable-dev-shm-usage", "--no-sandbox", "--print-to-pdf=plm.pdf");
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(sleepTime);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // 全屏
        FileUtils.copyFile(srcFile, new File(targetPath));
        System.out.println("===>截图完成...正在同步数据" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        robot.delay(3000);
        driver.quit();
    }

    @Test
    public void urlToPdf() {

        HtmlToPdf.create()
                .object(HtmlToPdfObject.forUrl("http://192.168.183.121:8080/plm/lk?q=VbD0NJgNodkr1hStzdwgyg1qpCFOnBBIR-9Pqcn0FPU2of7c-XKKLzuscY4RzyAV&HideCancelBtn=true&PopupWin=true --cookie= ABS_SchemeName=; LBSESSION=MGE2MWQ0MjgtNjYwZS00ZjIwLTllMjEtM2FkYzk2NzhkZmMy"))
//                .object(HtmlToPdfObject.forUrl("https://juejin.cn/post/6844903920154705927"))
                .convert("C:\\Users\\RujiangLiu\\Desktop\\private-open-sample-sign\\广州农商行3号定向资产管理计划.pdf");
        System.out.println("url转换pdf结束！");
    }

    @SneakyThrows
    @Test
    public void test() {
        String url = "wkhtmltopdf ";
        String param = "--page-size A4 --page-height 297 --page-width 210 --javascript-delay 1000 --no-stop-slow-scripts --load-error-handling ignore ";
        String cookie = "--cookie " + "LBSESSION " + "OGFkZDFiYTgtNzk5YS00ZmI3LWIxYmEtMzJhZTZiMmQ3NmVm ";
//        String requestUrl = "http://192.168.183.121:8080/plm/pre-phoenix/product-system-product-management/index.html#/product/informationQuery ";
        String requestUrl = "http://192.168.183.121:8080/plm/UIProcessor?Table=WF_ZHQSLCGZL.u&Token=98eac801fd265f9f35631a880e5da84a&WorkID=25393&StepID=23 ";
        requestUrl = requestUrl.replaceAll("&", "\\\\&");
        String linuxPath = "/home/test/";
        String fileName = "plm.pdf";

        url = url + param + cookie + requestUrl + linuxPath + fileName;

        System.out.println(url);
//        Runtime rt = Runtime.getRuntime();
//        Process process = rt.exec(url);
//        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        String line = null;
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//        int exitValue = process.waitFor();
//        System.out.println("进程返回值：" + exitValue);
    }

    @Test
    @SneakyThrows
    public void itextTest() {

        String baseUri = "C:\\Users\\RujiangLiu\\Desktop\\";
        String htmlPath = baseUri + "plm.html";
        String destUri = "C:\\Users\\RujiangLiu\\Desktop\\";
        String destPath = baseUri + "plmitext.pdf";

//        ConverterProperties properties = new ConverterProperties();
//        properties.setBaseUri(baseUri);
//        HtmlConverter.convertToPdf(new FileInputStream(htmlPath), new FileOutputStream(destPath), properties);
    }

    @Test
    @SneakyThrows
    public void headlessTest() {
        // 设置驱动地址
        System.setProperty("webdriver.chrome.driver", "F:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Boolean.TRUE);
        // 设置谷歌浏览器exe文件所在地址
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        // 这里是要执行的命令，如需修改截图页面的尺寸，修改--window-size的参数即可
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--disable-dev-shm-usage", "--no-sandbox","--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        // 访问页面
        Cookie cookie = new Cookie("LBSESSION","MGEzMDY0ZTYtMmZhNi00MGNjLTkyMDktMjBiNDllMDU0MTky");
        driver.manage().addCookie(cookie);

        driver.get("http://192.168.183.121:8080/plm/UIProcessor?Table=WF_ZHQSLCGZL.u&Token=5cd37f46b8f5e5b76a0a5f1833e78ae8&WorkID=25393&StepID=23");
        driver.wait(3);
        // 页面等待渲染时长，如果你的页面需要动态渲染数据的话一定要留出页面渲染的时间，单位默认是秒
//        Wait<WebDriver> wait = new WebDriverWait(driver, 3);
//        wait.until(new ExpectedCondition<WebElement>() {
//            @Override
//            public WebElement apply(WebDriver d) {//等待前台页面中 id为“kw”的组件渲染完毕，后截图
//                //若无需等待渲染，return true即可。 不同页面视情况设置id
//                return d.findElement(By.id("kw"));
//            }
//        });
        // 获取到截图的文件
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("D:\\plm.png"));

//        if ((screenshotFile != null) && screenshotFile.exists()) {//截取到的图片存到本地
//            FileOutputStream out = null;
//            FileInputStream in = null;
//            try {
//                in = new FileInputStream(screenshotFile);
//                out = new FileOutputStream("D:\\cut1.pdf");
//                byte[] b = new byte[1024];
//                while (true) {
//                    int temp = in.read(b, 0, b.length);// 如果temp = -1的时候，说明读取完毕
//                    if (temp == -1) {
//                        break;
//                    }
//                    out.write(b, 0, temp);
//                }
//            } catch (Exception e) {//TODO异常处理
//            }
//        }

    }
}

