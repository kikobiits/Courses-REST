package com.example.demo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer room;

    private String lecturer;

    public CourseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}
