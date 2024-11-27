package Asm_1;

import java.util.Scanner;

public class StudentManagement {
    private static Student[] students = new Student[5];
    private static int studentCount = 0;
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        preloadStudents();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students (Original Order)");
            System.out.println("5. Sort and Display Students by Marks (Descending)");
            System.out.println("6. Search Student by ID");
            System.out.println("7. Search Students by Rank");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    displaySortedStudents();
                    break;
                case 6:
                    searchStudentById();
                    break;
                case 7:
                    searchStudentsByRank();
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void preloadStudents() {
        students[studentCount++] = new Student(nextId++, "Alice Johnson", 8.5); // Very Good
        students[studentCount++] = new Student(nextId++, "Bob Smith", 4.8); // Fail
        students[studentCount++] = new Student(nextId++, "Charlie Brown", 6.0); // Medium
        students[studentCount++] = new Student(nextId++, "Diana Prince", 9.2); // Excellent
        students[studentCount++] = new Student(nextId++, "Eve Adams", 7.0); // Good

        System.out.println("Preloaded students added to the system.");
    }

    private static void addStudent() {
        ensureCapacity();

        int id = nextId++;
        scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        double marks;
        while (true) {
            System.out.print("Enter Student Marks (0-10): ");
            marks = scanner.nextDouble();
            if (marks >= 0 && marks <= 10) {
                break;
            }
            System.out.println("Invalid input. Marks must be between 0 and 10.");
        }

        students[studentCount++] = new Student(id, name, marks);
        System.out.println("Student added successfully with ID: " + id);
    }

    private static void editStudent() {
        System.out.print("Enter Student ID to Edit: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);
        if (student != null) {
            scanner.nextLine();
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();

            double marks;
            while (true) {
                System.out.print("Enter New Marks (0-10): ");
                marks = scanner.nextDouble();
                if (marks >= 0 && marks <= 10) {
                    break;
                }
                System.out.println("Invalid input. Marks must be between 0 and 10.");
            }

            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to Delete: ");
        int id = scanner.nextInt();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--studentCount] = null;
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void displayStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\n--- Student List (Original Order) ---");
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i]);
        }
    }

    private static void displaySortedStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }

        Student[] sortedStudents = new Student[studentCount];
        System.arraycopy(students, 0, sortedStudents, 0, studentCount);

        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (sortedStudents[j].getMarks() < sortedStudents[j + 1].getMarks()) {
                    Student temp = sortedStudents[j];
                    sortedStudents[j] = sortedStudents[j + 1];
                    sortedStudents[j + 1] = temp;
                }
            }
        }

        System.out.println("\n--- Student List (Sorted by Marks, Descending) ---");
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
    }

    private static void searchStudentById() {
        System.out.print("Enter Student ID to Search: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentsByRank() {
        scanner.nextLine();
        String rank;
        while (true) {
            System.out.print("Enter Rank to Search (Fail, Medium, Good, Very Good, Excellent): ");
            rank = scanner.nextLine();
            if (rank.equalsIgnoreCase("Fail") || rank.equalsIgnoreCase("Medium") || rank.equalsIgnoreCase("Good") ||
                rank.equalsIgnoreCase("Very Good") || rank.equalsIgnoreCase("Excellent")) {
                break;
            }
            System.out.println("Invalid rank. Please re-enter a valid rank.");
        }

        boolean found = false;
        System.out.println("\n--- Students with Rank: " + rank + " ---");
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getRank().equalsIgnoreCase(rank)) {
                System.out.println(students[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with the rank: " + rank);
        }
    }

    private static Student findStudentById(int id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }

    private static void ensureCapacity() {
        if (studentCount == students.length) {
            Student[] newStudents = new Student[students.length * 2];
            System.arraycopy(students, 0, newStudents, 0, students.length);
            students = newStudents;
        }
    }
}
