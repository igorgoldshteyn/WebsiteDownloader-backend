package com.WebsiteDownloader.WebsiteDownloader.Controllers;

import com.WebsiteDownloader.WebsiteDownloader.Model.User;
import com.WebsiteDownloader.WebsiteDownloader.Repository.UserRepository;
import com.WebsiteDownloader.WebsiteDownloader.Services.DownloadService;
import com.WebsiteDownloader.WebsiteDownloader.WebsiteDownloaderApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository){
        this.repository = repository;
    }

    private static Logger logger = LoggerFactory.getLogger(WebsiteDownloaderApplication.class);

    @GetMapping("/users")
    List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new); // поправить тут ошибку

    }


    @PostMapping
        public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
            User savedUser = repository.save(user);
            return ResponseEntity.created(new URI("/clients/" + savedUser.getId())).body(savedUser);
        }
//
        @PutMapping("/users/{id}")
        public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
            User currentUser = repository.findById(id).orElseThrow(RuntimeException::new);
            currentUser.setName(user.getName());
            currentUser.setEmail(user.getEmail());
            currentUser = repository.save(user);

            return ResponseEntity.ok(currentUser);
        }

        @DeleteMapping("/users/{id}")
        public ResponseEntity deleteUser(@PathVariable Long id) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }


    /////////////////////////////////////////////////////////////////////////////////////////



    @GetMapping("/downloadByUrl")
    public String downloadByUrl(@RequestParam String url) throws IOException, InterruptedException {
        if (url == null) {
            System.out.println("URL is null");
            return "URL is null";

        }

        logger.info("downloadByUrl");
        logger.info(url);
        return DownloadService.downloadByUrl(url);
    }

    @PostMapping("/signIn")
    public String signIn(@RequestParam String login, @RequestParam String password) {

        return "OK";
    }

    @GetMapping("/getDownloadRequestsByUserId")
    public String getDownloadRequestsByUser(@RequestParam String id) throws IOException, InterruptedException {
        return DownloadService.downloadByUrl(id);
    }
}