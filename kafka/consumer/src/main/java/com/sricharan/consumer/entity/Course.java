package com.sricharan.consumer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {
    private String courseId;
    private String title;
    private String trainer;
    private double price;
}
