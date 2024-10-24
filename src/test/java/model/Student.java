package model;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Student {
    private String name;
    private int rollNum;
    private String address;
    private List<Course> courseList;
}
