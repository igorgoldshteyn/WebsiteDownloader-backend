package com.WebsiteDownloader.Controllers;


import com.WebsiteDownloader.Services.DownloadService;
import com.WebsiteDownloader.WebsiteDownloaderApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WebsiteDownloadController {

//    private static Logger logger = LoggerFactory.getLogger(WebsiteDownloaderApplication.class);

    @GetMapping("/downloadByUrl")
    public String downloadByUrl(@RequestParam String url) throws IOException, InterruptedException {
        if (url != null) {
//        logger.info("downloadByUrl");
//        logger.info(url);
            return DownloadService.downloadByUrl(url);

        } else {
            System.out.println("URL is null");
            return "URL is null";
        }
    }

        @GetMapping("/getDownloadRequestsByUserId")
        public String getDownloadRequestsByUser (@RequestParam String id) throws IOException, InterruptedException {
            return DownloadService.downloadByUrl(id);
        }
    }
