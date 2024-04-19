package com.lo.test;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.PDFOptions;
import com.ruiyun.jvppeteer.options.PageNavigateOptions;
import com.ruiyun.jvppeteer.protocol.network.CookieParam;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author RujiangLiu
 * @date 2024/1/12
 */
public class PuppeteerTest {

    public static void main(String[] args) throws Exception {
        //自动下载，第一次下载后不会再下载
//        System.out.println(new BrowserFetcher().getDownloadsFolder());
//        BrowserFetcher.downloadIfNotExist(null);

        TimeInterval time = new TimeInterval();
        time.start();
        ArrayList<String> arrayList = new ArrayList<>();
        //生成pdf必须在无厘头模式下才能生效
        LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).build();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        arrayList.add("--window-size=1680,1050");
        Browser browser = Puppeteer.launch(options);
        Page page = browser.newPage();

//        Browser browser = launch("F:\\Program Files\\idea-workspace\\ignite-sd\\.local-browser\\win64-722234\\chrome-win\\chrome.exe");
//        Page page = browser.newPage();

        CookieParam cookieParam = new CookieParam();
        cookieParam.setName("LBSESSION");
        cookieParam.setValue("NWQyOTlhMTUtYjRkNy00NDlkLTgxNzEtOTU1ZDZhMzcwY2Vk");
        cookieParam.setDomain("192.168.183.121");
        cookieParam.setPath("/");
        cookieParam.setExpires(System.currentTimeMillis() + 3600 * 1000);
        List<CookieParam> cookieParamList = new ArrayList<>();
        cookieParamList.add(cookieParam);
        page.setCookie(cookieParamList);

        PageNavigateOptions navigateOptions = new PageNavigateOptions();
        navigateOptions.setWaitUntil(Collections.singletonList("networkidle2"));

        page.goTo("http://192.168.183.121:8080/plm/UIProcessor?Table=WF_ZH1CPBALCGZL.u&Token=fa6e13b84ba80da3af42a8c2b582f53f&WorkID=25387&StepID=6", navigateOptions);

        //线程池方法如何拿到执行数据
//        ThreadFactory factory = (Runnable r) -> {
//            Thread thread = new Thread(r);
//            thread.setName("页面转PDF");
//            return thread;
//        };
//
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 40, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), factory);
//
//        CompletionService<Object> service = new ExecutorCompletionService<>(executor);
//        List<Future<Object>> futureList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Future<Object> submit = service.submit(() -> {
//                //定义执行的方法 ，在java这里不能像nodejs一样直接书写js代码，这里以字符串代替，可以在vs code上编辑代码后再粘贴过来即可。
//                String pageFunction = "() => {\n" +
//                        "    return {\n" +
//                        "      scrollHeight: document.getElementsByClassName(\"lb-LBFormLayoutContainer-root\")[0].scrollHeight,\n" +
//                        "      headerHeight: document.getElementsByClassName(\"lb-LBWorkflowDetailHeader-header\")[0].scrollHeight,\n" +
//                        "      footerHeight: document.getElementsByClassName(\"lb-LBWorkflowFooterActions-root\")[0].scrollHeight,\n" +
//                        "      deviceScaleFactor: window.devicePixelRatio\n" +
//                        "    };\n" +
//                        "  }";
//                Object result = page.evaluate(pageFunction);
//                System.out.println("result:" + Constant.OBJECTMAPPER.writeValueAsString(result));
//                return true;
//            });
//            futureList.add(submit);
//        }

        String pageFunction = "() => {\n" +
                "    return {\n" +
                "      scrollHeight: document.getElementsByClassName(\"lb-LBFormLayoutContainer-root\")[0].scrollHeight,\n" +
                "      headerHeight: document.getElementsByClassName(\"lb-LBWorkflowDetailHeader-header\")[0].scrollHeight,\n" +
                "      footerHeight: document.getElementsByClassName(\"lb-LBWorkflowFooterActions-root\")[0].scrollHeight,\n" +
                "      deviceScaleFactor: window.devicePixelRatio\n" +
                "    };\n" +
                "  }";
        Object result = page.evaluate(pageFunction);
        JSONObject heights = JSON.parseObject(JSONObject.toJSONString(result));
        System.out.println("滚动高度：" + heights.getString("scrollHeight"));
        System.out.println("头高度：" + heights.getString("headerHeight"));
        System.out.println("尾高度：" + heights.getString("footerHeight"));
        Integer totalHeight = heights.getInteger("scrollHeight") + heights.getInteger("headerHeight") + heights.getInteger("footerHeight") + 28 + 36;
        System.out.println("总高度：" + totalHeight);

        PDFOptions pdfOptions = new PDFOptions();
        pdfOptions.setPath("test2.pdf");
        pdfOptions.setPrintBackground(true);
        pdfOptions.setWidth("1680");
        pdfOptions.setHeight(String.valueOf(totalHeight));
//        byte[] bytes = page.pdf(pdfOptions);
//        FileUtil.writeBytes(bytes, "F:\\Program Files\\puppeteer\\test1.pdf");
        page.close();
        browser.close();
        // 花费秒数
        System.out.println("转换PDF成功！用时：" + time.intervalSecond() + "s");
    }

    private static Browser launch(String path) throws Exception {
        ArrayList<String> arrayList = new ArrayList<>();
        LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).withExecutablePath(path).withUserDataDir("F:\\Program Files\\puppeteer").build();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        arrayList.add("--window-size=1680,1050");
        return Puppeteer.launch(options);
    }

}
