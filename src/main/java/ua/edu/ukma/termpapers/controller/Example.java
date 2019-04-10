package ua.edu.ukma.termpapers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class Example {

  @GetMapping(value = "/test")
  public String testGet() {
    return "Hello World!";
  }

  @GetMapping
  public String newTest() {
    return "New Test";
  }
}
