import java.util.*;
class Student {
    private String name;
    private List<String> preferences;
    private Project allocatedProject;

    // Student constructor
    public Student(String name, List<String> preferences) {
        this.name = name;
        this.preferences = preferences;
        this.allocatedProject = null;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public Project getAllocatedProject() {
        return allocatedProject;
    }

    // Method to allocate a project to the student
    public void allocateProject(Project project) {
        this.allocatedProject = project;
    }
}

class Project {
    private String name;
    private int capacity;
    private List<Student> allocatedStudents;

    // Project constructor
    public Project(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.allocatedStudents = new ArrayList<>();
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getAllocatedStudents() {
        return allocatedStudents;
    }

    // Method to add a student to the project's list of allocated students
    public void addAllocatedStudent(Student student) {
        allocatedStudents.add(student);
    }
}

public class ProjectAllocationManager {
    private List<Student> students;
    private List<Project> projects;
    private Scanner scanner;

    // ProjectAllocationManager constructor
    public ProjectAllocationManager() {
        students = new ArrayList<>();
        projects = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to add a student to the list of students
    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to add a project to the list of projects
    public void addProject(Project project) {
        projects.add(project);
    }

    // Method to allocate projects to students based on their preferences
    public void allocateProjects() {
        for (Student student : students) {
            for (String preference : student.getPreferences()) {
                // Find the project with the given preference
                Project project = findProjectByName(preference);
                // If the project exists and has available capacity, allocate it to the student
                if (project != null && project.getAllocatedStudents().size() < project.getCapacity()) {
                    student.allocateProject(project);
                    project.addAllocatedStudent(student);
                    break; // Stop searching for preferences once a project is allocated
                }
            }
        }
    }

    // Method to display the allocation of students to projects
    public void displayAllocation() {
        for (Project project : projects) {
            System.out.println("Project: " + project.getName());
            System.out.println("Allocated Students:");
            for (Student student : project.getAllocatedStudents()) {
                System.out.println("- " + student.getName());
            }
            System.out.println();
        }
    }

    // Method to find a project by its name
    private Project findProjectByName(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    // Method to get user input for students and projects
    public void getUserInput() {
        System.out.println("Welcome to the Project Allocation Manager by ProjectGurukul!");
        System.out.println("Please enter the number of students:");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter the details for Student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Number of preferences: ");
            int numPreferences = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            List<String> preferences = new ArrayList<>();
            for (int j = 0; j < numPreferences; j++) {
                System.out.print("Preference " + (j + 1) + ": ");
                String preference = scanner.nextLine();
                preferences.add(preference);
            }

            Student student = new Student(name, preferences);
            addStudent(student);
        }

        System.out.println("\nPlease enter the number of projects:");
        int numProjects = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numProjects; i++) {
            System.out.println("\nEnter the details for Project " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Project project = new Project(name, capacity);
            addProject(project);
        }
    }

    public static void main(String[] args) {
        // Create a ProjectAllocationManager instance, get user input, allocate projects, and display the allocation
        ProjectAllocationManager manager = new ProjectAllocationManager();
        manager.getUserInput();
        manager.allocateProjects();
        System.out.println("\nProject allocation:");
        manager.displayAllocation();
    }
}