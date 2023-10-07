package top.lilong.controller;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.lilong.model.Book;
import top.lilong.model.User;


/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/9/4 15:11
 * @注释
 */
@RestController
public class UserController {
@Resource
 private RestTemplate restTemplate;
private  static  final String CONTENT_SERVICE_URL = "http://localhost:9090/book/";

@GetMapping("/user/{id}")
 public User getUser(@RequestParam(name="username",defaultValue = "zhangsan") String username, @PathVariable int id){
 Book book=restTemplate.getForObject(CONTENT_SERVICE_URL + id,Book.class);
 return  User.builder().name(username).book(book).build();
}
}
