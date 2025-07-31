package com.cognizant.SpringTestAPI.Model;

public class Candidate {
    private String name;
    private String title;
    private String skills;
    private String experience;
    private double[] vector;

    public Candidate(String name, String title, String skills, String experience, double[] vector) {
        this.name = name;
        this.title = title;
        this.skills = skills;
        this.experience = experience;
        this.vector = vector;
    }

    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getSkills() { return skills; }
    public String getExperience() { return experience; }
    public double[] getVector() { return vector; }
}