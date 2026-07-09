public class GradeMethod {
    public static void main(String[] args) {
        int java = 85;
        int english = 78;
        int math = 92;

        double avg = calculateAverage(java, english, math);

        String grade = getGrade(avg);

        System.out.println("平均分數: " + avg);
        System.out.println("成績等第: " + grade);
    }

    public static double calculateAverage(int javaScore, int englishScore, int mathScore) {
        return (javaScore + englishScore + mathScore) / 3.0;
    }

    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
