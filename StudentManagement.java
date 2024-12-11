import java.util.Random;
import java.util.Scanner;

public class StudentManagement {
    private static Student[] students = new Student[100]; // Set array size to 100
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
            System.out.println("8. Compare Bubble Sort, Merge Sort, and Quick Sort");
            System.out.println("9. Exit");
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
                    compareSortingAlgorithms();
                    break;
                case 9:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void preloadStudents() {
        Random rand = new Random();
        String[] firstNames = {"Alex", "Jamie", "Taylor", "Jordan", "Morgan", "Chris", "Casey", "Riley", "Avery", "Drew"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Wilson", "Clark", "Lewis", "Walker", "Hall", "Allen"};

        for (int i = 0; i < 10; i++) {
            int id = nextId++;
            String name = firstNames[rand.nextInt(firstNames.length)] + " "
                    + lastNames[rand.nextInt(lastNames.length)];
            double marks = Math.round((rand.nextDouble() * 10) * 100.0) / 100.0; // Marks between 0 and 10

            students[studentCount++] = new Student(id, name, marks);
        }

        System.out.println("100 random students preloaded successfully.");
    }


    private static void addStudent() {
        ensureCapacity();

        int id = nextId++;
        scanner.nextLine();  // Clear the buffer before reading the next line
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine(); // Correctly read full name

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

        // Sort and display using Bubble Sort
        System.out.println("\n--- Sorting Students by Marks (Descending) ---");
        bubbleSort(students);
        displayStudents();
    }

    private static void compareSortingAlgorithms() {
        if (studentCount == 0) {
            System.out.println("No students to compare sorting algorithms.");
            return;
        }

        // Bubble Sort
        Student[] bubbleSorted = new Student[studentCount];
        System.arraycopy(students, 0, bubbleSorted, 0, studentCount);
        long bubbleStartTime = System.nanoTime();
        bubbleSort(bubbleSorted);
        long bubbleEndTime = System.nanoTime();
        System.out.println("\n--- Bubble Sort ---");
        displayStudentArray(bubbleSorted);
        System.out.println("Bubble Sort Time: " + (bubbleEndTime - bubbleStartTime) + " ns");

        // Merge Sort
        Student[] mergeSorted = new Student[studentCount];
        System.arraycopy(students, 0, mergeSorted, 0, studentCount);
        long mergeStartTime = System.nanoTime();
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        long mergeEndTime = System.nanoTime();
        System.out.println("\n--- Merge Sort ---");
        displayStudentArray(mergeSorted);
        System.out.println("Merge Sort Time: " + (mergeEndTime - mergeStartTime) + " ns");

        // Quick Sort
        Student[] quickSorted = new Student[studentCount];
        System.arraycopy(students, 0, quickSorted, 0, studentCount);
        long quickStartTime = System.nanoTime();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        long quickEndTime = System.nanoTime();
        System.out.println("\n--- Quick Sort ---");
        displayStudentArray(quickSorted);
        System.out.println("Quick Sort Time: " + (quickEndTime - quickStartTime) + " ns");
    }

    private static void displayStudentArray(Student[] studentsArray) {
        for (Student student : studentsArray) {
            System.out.println(student);
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

    // Bubble Sort
    private static void bubbleSort(Student[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getMarks() < arr[j + 1].getMarks()) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort
    private static void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Student[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Student[] leftArr = new Student[n1];
        Student[] rightArr = new Student[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].getMarks() >= rightArr[j].getMarks()) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Quick Sort
    private static void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Student[] arr, int low, int high) {
        double pivot = arr[high].getMarks();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].getMarks() >= pivot) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
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
        scanner.nextLine();  // Clear the buffer before reading the next line
        System.out.print("Enter Rank to Search (Fail, Medium, Good, Very Good, Excellent): ");
        String rank = scanner.nextLine(); // Correctly read the rank as a string

        for (int i = 0; i < studentCount; i++) {
            if (students[i].getRank().equalsIgnoreCase(rank)) {
                System.out.println(students[i]);
            }
        }
    }
}
