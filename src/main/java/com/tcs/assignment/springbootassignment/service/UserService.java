package com.tcs.assignment.springbootassignment.service;

import com.tcs.assignment.springbootassignment.entity.Assignment;
import com.tcs.assignment.springbootassignment.entity.User;
import com.tcs.assignment.springbootassignment.model.AssignmentModel;
import com.tcs.assignment.springbootassignment.model.UserModel;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    void createUser(UserModel userModel);

    void createAssignment(AssignmentModel assignment) throws ParseException;

    List<Assignment> getAllAssignments();

    Assignment getAssignmentById(Long id);

    void updateAssignment(Long id, AssignmentModel assignmentModel);

    void deleteAssignment(Long id);

    List<Assignment> getAssignmentOfUser(Long id);
}
