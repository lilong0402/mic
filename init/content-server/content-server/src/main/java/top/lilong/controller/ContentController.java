package top.lilong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.lilong.model.Book;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/9/4 14:43
 * @注释
 */
@RestController
public class ContentController {
 @GetMapping("/book/{id}")
 public Book getBook(@PathVariable int id){
  return Book.builder().id(id).name("微服务").author("后端开发").code("2e332423").build();
 }
}
