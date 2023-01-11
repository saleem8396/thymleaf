package com.tcs.assignment.springbootassignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userList")
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "unique_title_type",
                columnNames ={ "title", "type" }
        )
)
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assignmentId;
    @NotNull
    private String title;
    @NotNull
    private String dueDate;
    @NotNull
    private String startDate;
    @NotNull
    private String type ;
//    @OneToOne(
//            fetch = FetchType.EAGER
//    )
//    @JoinColumn(
//            name = "user_name",
//            referencedColumnName = "id"
//    )
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="user_assignment_map",
            joinColumns = @JoinColumn(
                    name = "assignment_id",
                    referencedColumnName = "assignmentId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "userId"
            )
    )
    @JsonIgnore
    private List<User> userList;

    public void addUserList(User user){
        if(userList==null) userList =new ArrayList<>();
        userList.add(user);
    }


}
