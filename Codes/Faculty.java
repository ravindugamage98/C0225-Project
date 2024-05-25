import java.util.ArrayList;
import java.util.List;
public class Faculty {

    // static attributes
    public static List <Faculty> FacultyArray = new ArrayList<>();


    // non static attributes
    private String University;
    private String faculty;
    public List <Student> facultyStudents;
    public List <Department> facultyDepartments;
    public ArrayList<ArrayList<String>> firstYrCourses;

    // Constructor
    public Faculty(String University, String faculty){
        this.University = University;
        this.faculty = faculty;
        this.facultyStudents = new ArrayList<>();
        this.facultyDepartments = new ArrayList<>();
        this.firstYrCourses = new ArrayList<ArrayList<String>>();

        // Initiating the 2D listArray to store 1st year courses which is eligible to store two semester in  listArray
        for (int i=1;i<=2;++i){
            this.firstYrCourses.add(new ArrayList<String>());
        }
    }

    // Method to return the University 
    public String getUniversity(){
        return this.University;
    }
    
    // Method to return the faculty
    public String getFaculty(){
        return this.faculty;
    }

    
}
