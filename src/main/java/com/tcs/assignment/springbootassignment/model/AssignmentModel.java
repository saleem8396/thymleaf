package com.tcs.assignment.springbootassignment.model;

import com.tcs.assignment.springbootassignment.entity.User;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class AssignmentModel {

    private String title;
    private Date dueDate;
    private Date startDate;
    @Pattern(regexp = "(?i)^(HOME WORK|QUIZ|MCQ)$")
    private String type;
    private List<User> userList;

    public void addUser(User user) {
        if (userList == null) userList = new ArrayList<>();
        userList.add(user);

    }
}
