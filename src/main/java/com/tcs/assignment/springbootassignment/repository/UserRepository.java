package com.tcs.assignment.springbootassignment.repository;

import com.tcs.assignment.springbootassignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{

    @Query(
            value = "select user.*,assignment.* from user \n" +
                    "right join user_assignment_map \n" +
                    "on user.user_id=user_assignment_map.user_id\n" +
                    "left join assignment\n" +
                    "on assignment.assignment_id=user_assignment_map.assignment_id\n" +
                    "where user.user_id=?1",
            nativeQuery = true
    )
    User getAssignmentOfUserFromDataBase(Long id);
}
