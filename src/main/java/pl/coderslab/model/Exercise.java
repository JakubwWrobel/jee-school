package pl.coderslab.model;

import lombok.Data;

@Data
public class Exercise {
    private int id;
    private String title;
    private String description;

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Exercise() {
    }



}