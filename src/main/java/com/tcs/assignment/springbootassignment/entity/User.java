package com.tcs.assignment.springbootassignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String firstname;
    private String lastName;
    @JsonIgnore
    private String password;
    @ManyToMany(
            mappedBy = "userList"
    )
    @JsonIgnore
    private List<Assignment> assignmentList=new ArrayList<>();
}
