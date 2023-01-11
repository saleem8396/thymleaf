package com.tcs.assignment.springbootassignment.controller;
import com.tcs.assignment.springbootassignment.entity.Assignment;
import com.tcs.assignment.springbootassignment.entity.User;
import com.tcs.assignment.springbootassignment.model.AssignmentModel;
import com.tcs.assignment.springbootassignment.model.UserModel;
import com.tcs.assignment.springbootassignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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

    @GetMapping("/getAllAssignments")
    public List<Assignment> getAllAssignments() {
        return userService.getAllAssignments();
    }

    @GetMapping("/getAssignmentById/{id}")
    public Assignment getAssignmentById(@PathVariable("id") Long id) {
        return userService.getAssignmentById(id);
    }

    @PutMapping("/updateAssignment/{id}")
    public String updateAssignment(@PathVariable("id") Long id,@RequestBody AssignmentModel assignmentModel){

        userService.updateAssignment(id,assignmentModel);
        return "Assignment updated";
    }
    @DeleteMapping("/deleteAssignment/id={id}")
    public String deleteAssignment(@PathVariable("id") Long id){
        userService.deleteAssignment(id);
        return "assignment deleted";
    }
    @GetMapping("/getAssignmentOfUser/{id}")
    public   List<Assignment> getAssignmentOfUser(@PathVariable("id") Long id){
        return userService.getAssignmentOfUser(id);
    }
}