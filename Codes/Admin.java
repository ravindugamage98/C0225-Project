//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class Admin {
    
    // non static attributes
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String University;
    private String faculty;
    private String department;
    private int year;
    private int totalSemesters;
    // private int dptDividingSem;

    // Constructor for a new sign up
    public Admin (String username, String password, String firstName, String lastName, String University, String faculty, int year, int totalSemesters){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.University = University;
        this.faculty = faculty;
        this.year = year;
        this.totalSemesters = totalSemesters;
        // this.dptDividingSem = dptDividingSem;
    }

    // Method to set the department
    public void setDepartment(String department){
        this.department = department;
    }

    // Method to return the username of the admin
    public String getUsername(){
        return this.username;
    }

    // Method to return the password of the admin
    public String getPassword(){
        return this.password;
    }

    // Method to return the first name of the admin
    public String getFirstName(){
        return this.firstName;
    }

    // Method to return the last name of the admin
    public String getLastName(){
        return this.lastName;
    }

    // Method to return the University of the admin
    public String getUniversity(){
        return this.University;
    }
    
    // Method to return the faculty of the admin
    public String getFaculty(){
        return this.faculty;
    }

    // Method to return the department of the admin
    public String getDepartment(){
        return this.department;
    }

    // Method to return the Year of the admin
    public int getYear(){
        return this.year;
    }

    // Method to return the number of total semesters
    public int getTotalSemesters(){
        return this.totalSemesters;
    }

    /* // Method to return the department dividing semester
    public int getDptDividingSem(){
        return this.dptDividingSem;
    } */

    // Method to check the admin is eleigible to add courses or not
    public boolean checkAdmin(){
        boolean adminOK = false;
        // checking whether the relevant semester courses of the admin's faculty is already entered or not
        // if it is already entered the system won't let him add courses
        // and this method will return false;
        // if it is not entered this method will return true;
        return adminOK;
    }

    // Method to add courses
    public void addCourses(){
        boolean depExists = false;
        boolean facExists = false;
        if (this.year != 1){
            for (Department dptObj : Department.departments){
                String uni = dptObj.getUniversity();
                String fac = dptObj.getFaculty();
                String dept = dptObj.getDepartment();
    
                if (uni.equalsIgnoreCase(this.getUniversity()) && fac.equalsIgnoreCase(this.getFaculty()) && dept.equalsIgnoreCase(this.getDepartment())){
                    depExists = true;
                    int semester = this.year*2 -4;
                    Scanner in = new Scanner(System.in);
                    // Each admin can add only two semesters
                    for (int i =0;i<2;++i){
                        System.out.println("Please enter the total subjects in semester " + (semester+i+3) + ": ");
                        int totalSubjects = in.nextInt();
                        dptObj.semesterCourses.get(semester+i).add(Integer.toString(totalSubjects));
                        for (int sub=1;sub<=totalSubjects;++sub){
                            System.out.println("Enter the Course code "+ (sub) +": ");
                            String courseCode = in.next();
                            System.out.println("Enter the Course name "+ (sub) +": ");
                            String courseName = in.next();
                            String course = courseCode + " " + courseName;
                            dptObj.semesterCourses.get(semester+i).add(course);
                        }
                    }
                    in.close();
                }
            }
            if (!depExists){
                Department dept = new Department(this.University, this.faculty, this.department, this.totalSemesters);
                int semester = this.year*2 -4;
                Scanner in = new Scanner(System.in);
                for (int i=0;i<2;++i){
                    System.out.println("Please enter the total subjects in semester " + (semester+i+3) + ": ");
                    int totalSubjects = in.nextInt();
                    dept.semesterCourses.get(semester+i).add(Integer.toString(totalSubjects));
                    for (int sub=1;sub<=totalSubjects;++sub){
                        System.out.println("Enter the Course code "+ (sub) +": ");
                        String courseCode = in.next();
                        System.out.println("Enter the Course name "+ (sub) +": ");
                        String courseName = in.next();
                        String course = courseCode + " " + courseName;
                        dept.semesterCourses.get(semester+i).add(course);
                    }
                }
                Department.departments.add(dept);
                addFacultytoFacultyArray(this,dept);
                in.close();
            }
        } else {
            for (Faculty facObj : Faculty.FacultyArray){
                String Uni = facObj.getUniversity();
                String faculty = facObj.getFaculty();

                if (this.getUniversity().equalsIgnoreCase(Uni) && this.getFaculty().equalsIgnoreCase(faculty)){
                    facExists = true;
                    Scanner in = new Scanner(System.in);
                    for (int i =0;i<2;++i){
                        System.out.println("Please enter the total subjects in semester " + (i+1) + ": ");
                        int totalSubjects = in.nextInt();
                        facObj.firstYrCourses.get(i).add(Integer.toString(totalSubjects));
                        for (int sub=1;sub<=totalSubjects;++sub){
                            System.out.println("Enter the Course code "+ (sub) +": ");
                            String courseCode = in.next();
                            System.out.println("Enter the Course name "+ (sub) +": ");
                            String courseName = in.next();
                            String course = courseCode + " " + courseName;
                            facObj.firstYrCourses.get(i).add(course);
                        }
                    }
                    in.close();
                } 
            }
            if (!facExists){
                Faculty fac = new Faculty(this.getUniversity(), this.getFaculty());
                Scanner in = new Scanner(System.in);
                for (int i =0;i<2;++i){
                    System.out.println("Please enter the total subjects in semester " + (i+1) + ": ");
                    int totalSubjects = in.nextInt();
                    fac.firstYrCourses.get(i).add(Integer.toString(totalSubjects));
                    for (int sub=1;sub<=totalSubjects;++sub){
                        System.out.println("Enter the Course code "+ (sub) +": ");
                        String courseCode = in.next();
                        System.out.println("Enter the Course name "+ (sub) +": ");
                        String courseName = in.next();
                        String course = courseCode + " " + courseName;
                        fac.firstYrCourses.get(i).add(course);
                    }
                }
                in.close();
                Faculty.FacultyArray.add(fac);
            }
        }
    }

    // Method to add a new department to the faculty
    public void addFacultytoFacultyArray(Admin ad, Department dept){
        boolean isExists = false;

        for (Faculty fac : Faculty.FacultyArray){
            String uni = fac.getUniversity();
            String faculty = fac.getFaculty();

            if (ad.getUniversity().equalsIgnoreCase(uni) && ad.getFaculty().equalsIgnoreCase(faculty)){
                isExists = true;
                fac.facultyDepartments.add(dept);
            }
        }

        if (!isExists){
            Faculty fac = new Faculty(ad.getUniversity(), ad.getFaculty());
            fac.facultyDepartments.add(dept);
            Faculty.FacultyArray.add(fac);
        }
    }



}
