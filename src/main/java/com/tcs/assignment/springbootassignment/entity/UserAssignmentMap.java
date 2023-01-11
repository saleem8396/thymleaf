//package com.tcs.assignment.springbootassignment.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.List;
//
//@Entity
//@Data
//public class UserAssignmentMap {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @ManyToOne
//    @JoinColumn(
//            referencedColumnName = "id"
//    )
//    private List<User> userId;
//    @ManyToOne
//    @JoinColumn(
//            referencedColumnName = "id"
//    )
//    private List<Assignment> assignmentId;
//}
