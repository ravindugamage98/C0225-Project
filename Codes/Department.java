import java.util.ArrayList;
import java.util.List;

public class Department extends Faculty {

    // static attributes
    // list of departments which a faculty has
    public static List <Department> departments = new ArrayList<>();

    // non static attributes
    private String department;
    private int totalSemesters;
    // acts as a local database to store the registered students who belongs to a certain department
    public List <Student> departmentStudents;
    // ArrayList to store the courses for each semester of a certain department
    public ArrayList<ArrayList<String>> semesterCourses;


    public Department(String University, String faculty, String department, int totalSemesters){
        super(University, faculty);
        this.department = department;
        this.totalSemesters = totalSemesters-2;
        this.departmentStudents = new ArrayList<>();
        this.semesterCourses = new ArrayList<ArrayList<String>>();

        // Initiating the 2D semester courses listArray
        for (int i=1;i<=this.totalSemesters;++i){
            this.semesterCourses.add(new ArrayList<String>());
        }
    }

    // Method to return the department
    public String getDepartment(){
        return this.department;
    }

}
