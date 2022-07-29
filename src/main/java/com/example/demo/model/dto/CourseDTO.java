package com.example.demo.model.dto;

public class CourseDTO {

    private String title;

    private Integer room;

    private String lecturer;

    public CourseDTO() {
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
