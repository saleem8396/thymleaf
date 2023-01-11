package com.tcs.assignment.springbootassignment.repository;

import com.tcs.assignment.springbootassignment.entity.Assignment;
import com.tcs.assignment.springbootassignment.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {

    Assignment findByTitleAndType(String title, String type);

    @Query(
            value = "select user_id from assignment\n" +
                    "right join user_assignment_map \n" +
                    "on\n" +
                    "assignment.id = user_assignment_map.assignment_id\n" +
                    "where assignment.id=?1\n",
            nativeQuery = true
    )
    List<Long> findUsersWithAssignment(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "insert into user_assignment_map (assignment_id,user_id)\n" +
                    "values( ?1, ?2)",
            nativeQuery = true
    )
    void updateUserAssignment(Long assignment_id,Long user_id);
}
