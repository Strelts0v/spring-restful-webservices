package com.straltsou.rest.ws.restfulwebservices.controller;

import com.straltsou.rest.ws.restfulwebservices.dto.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Annotation @RestController -
 * a convenience annotation that is itself annotated with @Controller and @ResponseBody.
 * Types that carry this annotation are treated as controllers
 * where @RequestMapping methods assume @ResponseBody semantics by default.
 */
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    /**
     * Jackson Http Message Converter is used by default in Spring Boot App,
     * so your POJOs will be automatically converted to JSON format.
     * You can check spring-boot-starter-web for more details
     */
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-var/{name}")
    public HelloWorldBean helloWorldBeanPathVar(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
    }
}
