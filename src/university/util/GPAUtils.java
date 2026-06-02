package university.util;

import university.entities.Enrollment;
import university.enums.Grade;

public class GPAUtils {
    public static double calculateGPA(Enrollment[] enrollments) {
        double totalPoints = 0.0;
        int totalCredits = 0;

        for (int i = 0; i < enrollments.length; i++) {
            Grade grade = enrollments[i].getGrade();

            if (grade.isFinalGrade()) {
                int credits = enrollments[i].getCourse().getCredits();
                totalPoints += grade.getPoints() * credits;
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return totalPoints / totalCredits;
    }
}
