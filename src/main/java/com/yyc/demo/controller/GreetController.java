package com.yyc.demo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.yyc.demo.dao.GreetingRepository;
import com.yyc.demo.entity.Greeting;
import com.yyc.demo.exception.ApiException;
import com.yyc.demo.vo.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @program: demorestful
 * @description: 测试RESTFUL
 * @author: yyc
 * @create: 2020-05-11 15:39
 **/
@Api(tags="XXX管理")
@RestController
public class GreetController {

    private static final String TEMPLATE = "Hello,%s";
    private final AtomicLong counter = new AtomicLong();
    private final GreetingRepository greetingRepository;

    public GreetController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    /**
     * 测试方法1
     *
     * 注解@RequestParam，一般使用在 PUT，POST，
     * 用来获取 表单或url中的 基本数据类型
     * @param name
     * @return
     */
    @ApiOperation(value = "测试接口",notes = "测试方法接口，请求/test。" + "<br>默认返回Hello World!")
    @GetMapping("/test")
    public Greeting test(@RequestParam(value = "name",defaultValue = "World!") String name){
        return new Greeting(counter.incrementAndGet(),String.format(TEMPLATE,name));
    }

    /**
     * 测试方法2
     *
     * @param data
     * @return
     */
    @GetMapping("/test2")
    public String test2(@RequestParam(value = "data",defaultValue = "Hello,World!") String data){
        return data;
    }


    /**
     * 获取所有的Greeting
     * @return
     */
    @GetMapping("/greeting")
    List<Greeting> getGreets(){
        return this.greetingRepository.findAll();
    }

    /**
     * 根据ID获取
     *
     * 注解@PathVariable，一般在使用GET，DELETE，PUT 方法的时候使用
     * @param id
     * @return
     */
    @GetMapping("/greeting/{id}")
    Greeting getGreet(@PathVariable Long id){
        return this.greetingRepository.findById(id)
                .orElseThrow(()-> new ApiException(ResponseCode.NOT_FOUND_EXCEPTION));
    }

    /**
     * PUT更新
     *
     * 注解 @RequestBody，与 @RequestParam 一样，但是他是用来接收 Json 对象的。
     * @return
     */
    @PutMapping("/greeting/{id}")
    Greeting updateGreet(@PathVariable Long id,@RequestBody Greeting newGreeting){
        // 有就修改，否则添加
        return this.greetingRepository.findById(id)
                .map(greeting -> {
                    greeting.setContent(newGreeting.getContent());
                    return greetingRepository.save(greeting);
                })
                .orElseGet(() -> {
                    newGreeting.setId(id);
                    return greetingRepository.save(newGreeting);
                });
    }


    /**
     *  删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/greeting/{id}")
    void deleteGreet(@PathVariable Long id){

        this.greetingRepository.deleteById(id);
    }

    /**
     * 新增一个Greet
     *
     * 不加@RequestBody能使用 url 参数，或者表单的形式传输，
     * 并且可以传输对象，SpringBoot 会自动对字段进行映射
     *
     * "@Vallid"注解用来校验参数，相应的Greeting实体属于要用注解配置校验规则
     * @param greeting
     * @return
     */
    @PostMapping("/greeting")
    Greeting addGreet(@RequestBody @Valid Greeting greeting){
        return this.greetingRepository.save(greeting);
    }

}
