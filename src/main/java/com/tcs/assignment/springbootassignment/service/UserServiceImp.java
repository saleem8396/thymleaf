package com.tcs.assignment.springbootassignment.service;

import com.tcs.assignment.springbootassignment.entity.Assignment;
import com.tcs.assignment.springbootassignment.entity.User;
import com.tcs.assignment.springbootassignment.model.AssignmentModel;
import com.tcs.assignment.springbootassignment.model.UserModel;
import com.tcs.assignment.springbootassignment.repository.AssignmentRepository;
import com.tcs.assignment.springbootassignment.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public void createUser(UserModel userModel) {
        User user = new User();

        user.setUserName(userModel.getFirstname() + " " + userModel.getLastName());
        user.setFirstname(userModel.getFirstname());
        user.setLastName(userModel.getLastName());
        user.setPassword(BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    @Override
    public void createAssignment(AssignmentModel assignment) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Assignment assignment1 = new Assignment();
        assignment1.setTitle(assignment.getTitle());
        assignment1.setType(assignment.getType());
        assignment1.setStartDate(format.format(assignment.getStartDate()));
        assignment1.setDueDate(format.format(assignment.getDueDate()));

//        System.out.println(assignment.getUserList().get(0).getId());
        List users = assignment.getUserList();
        List<Long> jsonUserId = new ArrayList<>();
        System.out.println(users);
        for (Object user : users) {

            User user1 = (User) user;
            jsonUserId.add(user1.getUserId());
            assignment1.addUserList((User) user);
        }

//        System.out.println(jsonUserId);
        Assignment uniqueCheck = assignmentRepository.findByTitleAndType(assignment.getTitle(), assignment.getType());
        if (!Objects.nonNull(uniqueCheck)) {
            assignmentRepository.save(assignment1);
        } else {
            List<Long> user = assignmentRepository.findUsersWithAssignment(uniqueCheck.getAssignmentId());
            Collections.sort(user);
            Collections.sort(jsonUserId);

            if (!user.equals(jsonUserId)) {

                jsonUserId.removeAll(user);
                System.out.println(jsonUserId);
                for (Long id : jsonUserId) {
                    if(userRepository.existsById(id)){

                        assignmentRepository.updateUserAssignment(uniqueCheck.getAssignmentId(),id);
                    }
                }
            }
//        assignment1.setUserList(assignment.getUserList());

        }
    }

    @Override
    public List<Assignment> getAllAssignments() {

        List<Assignment> assignmentList = assignmentRepository.findAll();
       for(Assignment assignment:assignmentList)
            System.out.println(assignment);

        return assignmentList;
    }

    @Override
    public Assignment getAssignmentById(Long id) {
        Optional<Assignment> assignmentList = assignmentRepository.findById(id);
        return assignmentList.get();
    }

    @Override
    public void updateAssignment(Long id, AssignmentModel assignmentModel) {
        UserModel userModel = new UserModel();
        Assignment assignment = assignmentRepository.findById(id).get();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if (Objects.nonNull(assignmentModel.getTitle()) && !"".equalsIgnoreCase(assignmentModel.getTitle())) {

            assignment.setTitle(assignmentModel.getTitle());
        }
        if (Objects.nonNull(assignmentModel.getStartDate()) && !"".equalsIgnoreCase(assignmentModel.getStartDate().toString())) {

            assignment.setStartDate(format.format(assignmentModel.getStartDate()));
        }
        if (Objects.nonNull(assignmentModel.getDueDate()) && !"".equalsIgnoreCase(assignmentModel.getDueDate().toString())) {

            assignment.setDueDate(format.format(assignmentModel.getDueDate()));
        }
        if (Objects.nonNull(assignmentModel.getDueDate()) && !"".equalsIgnoreCase(assignmentModel.getDueDate().toString())) {

            assignment.setDueDate(format.format(assignmentModel.getDueDate()));
        }
        if (Objects.nonNull(assignmentModel.getType()) && !"".equalsIgnoreCase(assignmentModel.getType())) {

            assignment.setType(assignmentModel.getType());
        }
//        if(Objects.nonNull(assignmentModel.getUser())) {
//            if (Objects.nonNull(assignmentModel.getUser().getFirstname()) && !"".equalsIgnoreCase(assignmentModel.getUser().getFirstname())) {
//
////                userModel.setUserName(assignmentModel.getUser().getFirstname());
//         assignment.getUser().setFirstname(assignmentModel.getUser().getFirstname());
//
//            }
//            if (Objects.nonNull(assignmentModel.getUser().getLastName()) && !"".equalsIgnoreCase(assignmentModel.getUser().getLastName())) {
//
//                userModel.setLastName(assignmentModel.getUser().getLastName());
//            }
//            assignment.getUser().setUserName(assignment.getUser().getUserName() + " " + assignment.getUser().getLastName());
//        }

        assignmentRepository.save(assignment);
    }

    @Override
    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public   List<Assignment> getAssignmentOfUser(Long id) {

    User userAssignment= userRepository.getAssignmentOfUserFromDataBase(id);
    List<Assignment> assignmentList=userAssignment.getAssignmentList();
    return assignmentList;
    }
}
