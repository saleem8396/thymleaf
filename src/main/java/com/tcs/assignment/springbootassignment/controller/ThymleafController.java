package com.tcs.assignment.springbootassignment.controller;

import com.tcs.assignment.springbootassignment.entity.Assignment;
import com.tcs.assignment.springbootassignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ThymleafController {

    @Autowired
    private UserService userService;

    @GetMapping("/allAssignments")
    public ModelAndView hello(){

    ModelAndView mav =new ModelAndView("allAssignments");
      List<Assignment> list=userService.getAllAssignments();
        System.out.println(list.isEmpty());
        System.out.println(list);
      mav.addObject("assignments",list);
      return mav;

    }

    @GetMapping("/")
    public ModelAndView mainPage(){
       return new ModelAndView("main");
    }

    @GetMapping("/createUsers")
    public ModelAndView userCreate(){
        return new ModelAndView("createUser");
    }
}
