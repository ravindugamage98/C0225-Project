//package co225-project-group16.Codes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {

    // static attributes
    // acts as a local database to store the registered students
    public static List <Student> students = new ArrayList<>();

    // non static attributes
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String University;
    private String faculty;
    private String department;
    private int batchNo;
    private int regNo;
    private int totalSemesters;
    // 2D ArrayList to store the grades for each course in every semester
    public ArrayList<ArrayList<String>> studentGrades = new ArrayList<ArrayList<String>>();
    // ArrayList to store the GPAs for every semester
    public List <Double> semesterGPAs = new ArrayList<>();

    // Constructor for a new sign up
    public Student(String username, String password, String firstName, String lastName, String University, String faculty, int batchNo, int regNo, int totalSemesters){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.University = University;
        this.faculty = faculty;
        this.batchNo = batchNo;
        this.regNo = regNo;
        this.totalSemesters = totalSemesters;
        // Initiating the studentGrades 2D ArrayList
        for (int i=1;i<=totalSemesters;++i){
            this.studentGrades.add(new ArrayList<String>());
        }
    }

    // Method to set the department
    public void setDepartment(String department){
        this.department = department;
    }

    // Method to return the username of the student
    public String getUsername(){
        return this.username;
    }

    // Method to return the password of the student
    public String getPassword(){
        return this.password;
    }

    // Method to return the first name of the student
    public String getFirstName(){
        return this.firstName;
    }

    // Method to return the last name of the student
    public String getLastName(){
        return this.lastName;
    }

    // Method to return the University of the student
    public String getUniversity(){
        return this.University;
    }
    
    // Method to return the faculty of the student
    public String getFaculty(){
        return this.faculty;
    }

    // Method to return the department of the student
    public String getDepartment(){
        return this.department;
    }

    // Method to return the batch of the student
    public int getBatchNo(){
        return this.batchNo;
    }

    // Method to return the registration number of the student
    public int getRegNo(){
        return this.regNo;
    }

    // Method to return the total semester of the student
    public int getTotalSemesters(){
        return this.totalSemesters;
    }

    // Method to add the student to the Students ArrayList(local database)
    public void addStudent(){
        Student.students.add(this);
    }

    // Method to add the student to the department ArrayList(local database)
    public void addStudenttoDepartment(){
        for (Department dptObj : Department.departments){
            String uni = dptObj.getUniversity();
            String fac = dptObj.getFaculty();
            String dept = dptObj.getDepartment();

            if (uni.equalsIgnoreCase(this.getUniversity()) && fac.equalsIgnoreCase(this.getFaculty()) && dept.equalsIgnoreCase(this.department)){
                dptObj.departmentStudents.add(this);
            }
        }
    }

    // Method to add the student to the faculty ArrayList(local database)
    public void addStudenttoFaculty(){
        for (Faculty facObj : Faculty.FacultyArray){
            String uni = facObj.getUniversity();
            String faculty = facObj.getFaculty();

            if (this.getUniversity().equalsIgnoreCase(uni) && this.getFaculty().equalsIgnoreCase(faculty)){
                facObj.facultyStudents.add(this);
            }
        }
    }

    // Method to store the grades in the 2D Array
    public void setGrades(int semesterNumber){

        if (semesterNumber > 2){
            for (Department dptObj : Department.departments){
                String uni = dptObj.getUniversity();
                String fac = dptObj.getFaculty();
                String dept = dptObj.getDepartment();

                if (uni.equalsIgnoreCase(this.getUniversity()) && fac.equalsIgnoreCase(this.getFaculty()) && dept.equalsIgnoreCase(this.department)){
                    int totalSubjects = Integer.parseInt(dptObj.semesterCourses.get(semesterNumber-1).get(0));

                    Scanner in = new Scanner(System.in);
                    for (int i=1;i<=totalSubjects;++i){
                        System.out.print("Enter the grade for " + dptObj.semesterCourses.get(semesterNumber-1).get(i) + ":");  
                        String grade = in.next();                                          // nextLine ?? / next ???
            
                        this.studentGrades.get(semesterNumber-1).add(i-1, grade);            // adding the grades into the corresponding semester
                    }
                    in.close();
                }
            }
        } else {
            for (Faculty facObj : Faculty.FacultyArray){
                String uni = facObj.getUniversity();
                String faculty = facObj.getFaculty();
    
                if (this.getUniversity().equalsIgnoreCase(uni) && this.getFaculty().equalsIgnoreCase(faculty)){
                    int totalSubjects = Integer.parseInt(facObj.firstYrCourses.get(semesterNumber-1).get(0));

                    Scanner in = new Scanner(System.in);
                    for (int i=1;i<=totalSubjects;++i){
                        System.out.print("Enter the grade for " + facObj.firstYrCourses.get(semesterNumber-1).get(i) + ":");  
                        String grade = in.next();                                          // nextLine ?? / next ???
            
                        this.studentGrades.get(semesterNumber-1).add(i-1, grade);            // adding the grades into the corresponding semester
                    }
                    in.close();
                }
            }
        }
    }

    // Method to calculate the current GPA of a given semester
    public double getCurrentGPA(int currentSemester, int totalSubjects){          // totalSubjects should be taken from the admin class
        double currentGPA = 0.0;
        for (int i=0;i<totalSubjects;++i){
            String grade = this.studentGrades.get(currentSemester).get(i);

            switch (grade) {
                case "A+"   :   currentGPA += 4.0;
                                break;
                
                case "A"    :   currentGPA += 4.0;
                                break;
                
                case "A-"   :   currentGPA += 3.7;
                                break;
                
                case "B+"   :   currentGPA += 3.3;
                                break;
                
                case "B"    :   currentGPA += 3;
                                break;
                
                case "B-"   :   currentGPA += 2.7;
                                break;
                
                case "C+"   :   currentGPA += 2.3;
                                break;

                case "C"    :   currentGPA += 2;
                                break;

                case "C-"   :   currentGPA += 1.7;
                                break;

                case "D+"   :   currentGPA += 1.3;
                                break;
                            
                case "D"    :   currentGPA += 1;
                                break;

                default     :   currentGPA += 0.0;
                                break;
            }
        }
        currentGPA = currentGPA / totalSubjects;
        this.semesterGPAs.add(currentSemester-1, currentGPA);              // used auto boxing concept
        return currentGPA;
    }

    // Method to calculate the year end GPA when the academic yr is given
    public double getYearlyMeanGPA(int academicYr){
        double YrEndMeanGPA = 0.0;
        int semesterIndex = academicYr*2 - 2;
        // If user has entered the grades of the 1st semsester
        if (this.semesterGPAs.get(semesterIndex) != null){ 
            YrEndMeanGPA = (this.semesterGPAs.get(semesterIndex) + this.semesterGPAs.get(semesterIndex+1)) / 2;
        } else {
            if (semesterIndex<2){
                for (Faculty facObj : Faculty.FacultyArray){
                    String uni = facObj.getUniversity();
                    String faculty = facObj.getFaculty();
        
                    if (this.getUniversity().equalsIgnoreCase(uni) && this.getFaculty().equalsIgnoreCase(faculty)){
                        int totalSubjects = Integer.parseInt(facObj.firstYrCourses.get(semesterIndex).get(0));
                        this.setGrades(semesterIndex);
                        double semesterIndexGPA = this.getCurrentGPA(semesterIndex, totalSubjects); 
                        YrEndMeanGPA = (semesterIndexGPA  + this.semesterGPAs.get(semesterIndex+1)) / 2;                    
                        break;
                    }
                }
            } else {
                for (Department dptObj : Department.departments){
                    String uni = dptObj.getUniversity();
                    String fac = dptObj.getFaculty();
                    String dept = dptObj.getDepartment();
    
                    if (uni.equalsIgnoreCase(this.getUniversity()) && fac.equalsIgnoreCase(this.getFaculty()) && dept.equalsIgnoreCase(this.department)){
                        int totalSubjects = Integer.parseInt(dptObj.semesterCourses.get(semesterIndex).get(0));

                        this.setGrades(semesterIndex);
                        double semesterIndexGPA = this.getCurrentGPA(semesterIndex, totalSubjects); 
                        YrEndMeanGPA = (semesterIndexGPA  + this.semesterGPAs.get(semesterIndex+1)) / 2;                    
                        break;
                    }
                }
            }
        }
        return YrEndMeanGPA;
    }

    // Method to calculate the mean GPA upto the current semester
    public double getMeanGPA(int currentSemester){
        double meanGPA =0.0;
        for (int i=0;i<currentSemester;++i){
            if (this.semesterGPAs.get(i) != null) {
                meanGPA += this.semesterGPAs.get(i);
            } else {
                if (i<2){
                    for (Faculty facObj : Faculty.FacultyArray){
                        String uni = facObj.getUniversity();
                        String faculty = facObj.getFaculty();
            
                        if (this.getUniversity().equalsIgnoreCase(uni) && this.getFaculty().equalsIgnoreCase(faculty)){
                            int totalSubjects = Integer.parseInt(facObj.firstYrCourses.get(i).get(0));
                            this.setGrades(i);
                            meanGPA += this.getCurrentGPA(i, totalSubjects);                   
                            break;
                        }
                    }
                } else {
                    for (Department dptObj : Department.departments){
                        String uni = dptObj.getUniversity();
                        String fac = dptObj.getFaculty();
                        String dept = dptObj.getDepartment();
        
                        if (uni.equalsIgnoreCase(this.getUniversity()) && fac.equalsIgnoreCase(this.getFaculty()) && dept.equalsIgnoreCase(this.department)){
                            int totalSubjects = Integer.parseInt(dptObj.semesterCourses.get(i).get(0));
                            this.setGrades(i);
                            meanGPA += this.getCurrentGPA(i, totalSubjects);                  
                            break;
                        }
                    }
                }
            }
        }
        meanGPA = meanGPA / currentSemester;
        return meanGPA;
    }

    // Method to calculate the GPA the student should maintain to obtain his/her expected GPA
    public double getMustHaveGPA(double expectedGPA, int currentSemester){
        double meanGPA = this.getMeanGPA(currentSemester);
        return ((expectedGPA*this.totalSemesters) - meanGPA*currentSemester) / (this.totalSemesters - currentSemester);
    }

    // Method to calculate the *minimum marks for a course to achieve the expected grade for a course
    public double getMarksToAchieveGrade(double continousAssessmentsMarks, String expectedGrade){
        double expectedMarks = 0.0;
        switch (expectedGrade) {
            case "A+"   :   expectedMarks = 85.0;
                            break;
            
            case "A"    :   expectedMarks = 80.0;
                            break;
            
            case "A-"   :   expectedMarks = 75.0;
                            break;
            
            case "B+"   :   expectedMarks = 70.0;
                            break;
            
            case "B"    :   expectedMarks = 65.0;
                            break;
            
            case "B-"   :   expectedMarks = 60.0;
                            break;
            
            case "C+"   :   expectedMarks = 55.0;
                            break;

            case "C"    :   expectedMarks += 50.0;
                            break;

            case "C-"   :   expectedMarks = 45.0;
                            break;

            case "D+"   :   expectedMarks = 40.0;
                            break;
                        
            case "D"    :   expectedMarks = 35.0;
                            break;

            default     :   expectedMarks = 30.0;
                            break;
        }
        if (continousAssessmentsMarks > expectedMarks){
            return 0.0;
        } else {
            return expectedMarks - continousAssessmentsMarks;
        }
    }

    // Method to calculate the mean grade for each subject to achieve the next semester expected GPA
    public String getNextSemCourseGrade(int nextSemester, double expectedGPA){
        // int totalSubjects = 7;
        String courseGrade = "";

        if (3.7 < expectedGPA && expectedGPA <= 4.00){
            courseGrade = "A";
        } else if (3.3 < expectedGPA && expectedGPA <= 3.7){
            courseGrade = "A-";
        } else if (3.0 < expectedGPA && expectedGPA <= 3.3){
            courseGrade = "B+";
        } else if (2.7 < expectedGPA && expectedGPA <= 3.0){
            courseGrade = "B";
        } else if (2.3 < expectedGPA && expectedGPA <= 2.7){
            courseGrade = "B-";
        } else if (2.0 < expectedGPA && expectedGPA <= 2.3){
            courseGrade = "C+";
        } else if (1.7 < expectedGPA && expectedGPA <= 2.0){
            courseGrade = "C";
        } else if (1.3 < expectedGPA && expectedGPA <= 1.7){
            courseGrade = "D+";
        } else if (1.0 < expectedGPA && expectedGPA <= 1.3){
            courseGrade = "D";
        }
        return courseGrade;
    }

    // Method to calculate the final GPA
    public Double getFinalGPA(){
        return this.getMeanGPA(this.getTotalSemesters());
    }

    // Method to calculate the final Grade
    public String getFinalClass(){
        Double finalGPA = this.getFinalGPA();

        String finalGrade = "";
        if (3.7 < finalGPA && finalGPA <= 4.00){
            finalGrade = "A";
        } else if (3.3 < finalGPA && finalGPA <= 3.7){
            finalGrade = "A-";
        } else if (3.0 < finalGPA && finalGPA <= 3.3){
            finalGrade = "B+";
        } else if (2.7 < finalGPA && finalGPA <= 3.0){
            finalGrade = "B";
        } else if (2.3 < finalGPA && finalGPA <= 2.7){
            finalGrade = "B-";
        } else if (2.0 < finalGPA && finalGPA <= 2.3){
            finalGrade = "C+";
        } else if (1.7 < finalGPA && finalGPA <= 2.0){
            finalGrade = "C";
        } else if (1.3 < finalGPA && finalGPA <= 1.7){
            finalGrade = "D+";
        } else if (1.0 < finalGPA && finalGPA <= 1.3){
            finalGrade = "D";
        } else {
            finalGrade = "W";
        }
        
        return finalGrade;
    }

}
