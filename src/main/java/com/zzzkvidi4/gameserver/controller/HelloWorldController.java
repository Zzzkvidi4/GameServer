package com.zzzkvidi4.gameserver.controller;

import com.zzzkvidi4.gameserver.model.HelloWorld;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Роман on 24.05.2018.
 */

@RestController
public class HelloWorldController {

    @RequestMapping(path = "/get_int", method = RequestMethod.GET)
    public HelloWorld getInt(){
        return new HelloWorld(42);
    }

    @RequestMapping(path = "/post_int", method = RequestMethod.POST)
    public HelloWorld postInt(){
        return new HelloWorld(17);
    }
}
