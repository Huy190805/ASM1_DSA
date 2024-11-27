package Asm_1;

public class Student {
    private int id;
    private String name;
    private double marks;
    private String rank;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rank = determineRank(marks);
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.rank = determineRank(marks);
    }

    public double getMarks() {
        return marks;
    }

    public String getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    private String determineRank(double marks) {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Marks: %.2f, Rank: %s", id, name, marks, rank);
    }
}

