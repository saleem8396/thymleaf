package com.tcs.assignment.springbootassignment.controller;

import com.tcs.assignment.springbootassignment.entity.Assignment;
import com.tcs.assignment.springbootassignment.entity.User;
import com.tcs.assignment.springbootassignment.model.AssignmentModel;
import com.tcs.assignment.springbootassignment.model.UserModel;
import com.tcs.assignment.springbootassignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView mainPage() {
        return new ModelAndView("main");
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserModel userModel) {

        userService.createUser(userModel);
        return "user created";
    }

    @PostMapping("/createAssignment")
    public String createAssignment(@Valid @RequestBody AssignmentModel assignment) throws ParseException {

        userService.createAssignment(assignment);
        return "assignment created";
    }

    @GetMapping("/allAssignments")
    public ModelAndView hello() {

        ModelAndView mav = new ModelAndView("allAssignments");
        List<Assignment> list = userService.getAllAssignments();
        mav.addObject("assignments", list);
        return mav;

    }

    @GetMapping("/getAssignmentById/{id}")
    public ModelAndView getAssignmentById(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("allAssignments");
        List<Assignment> list = new ArrayList<>();
        list.add(userService.getAssignmentById(id));
        mav.addObject("assignments", list);
        return mav;

    }

    @PutMapping("/updateAssignment/{id}")
    public String updateAssignment(@PathVariable("id") Long id, @RequestBody AssignmentModel assignmentModel) {

        userService.updateAssignment(id, assignmentModel);
        return "Assignment updated";
    }

    @DeleteMapping("/deleteAssignment/id={id}")
    public String deleteAssignment(@PathVariable("id") Long id) {
        userService.deleteAssignment(id);
        return "assignment deleted";
    }

    @GetMapping("/getAssignmentOfUser/{id}")
    public ModelAndView getAssignmentOfUser(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("allAssignments");
        List<Assignment> list = userService.getAssignmentOfUser(id);
        mav.addObject("assignments", list);
        return mav;
    }

    @GetMapping("/createUsers")
    public ModelAndView getUser() {
        List<User> users = userService.getUsers();
        ModelAndView mav = new ModelAndView("createUser");
        return mav.addObject("users", users);
    }

    @GetMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/addUser/{userId}/ForAssignment/{assignmentId}")
    public void addUserForAssignment(@PathVariable("userId") Long userID, @PathVariable("assignmentId") Long assignId) {
        userService.addUserForAssignment(userID, assignId);
    }
}