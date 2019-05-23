public class Integrals {
    public static String calculateAllSimpleIntegrals(String a, String b,
                                                     String n, String nP, String function) {
        String answer = "<html>Mediante Trapecios: "
                + Trapecios.calculate(function, a, b, nP, true)[0]
                + "<br>Mediante Simpson: "
                + Simpson.calculate(function, a, b, nP, true)[0]
                + "<br>Mediante Newton Cotes: "
                + NewtonCotes.newtonCotes(a, b, n, function)
                + "</html>";
        return answer;
    }


    public static String calculateAllSimpleAreas(String a, String b, String n,
                                                 String function) {
        String answer = "<html>Mediante Trapecios: "
                + Trapecios.calculate(function, a, b, n, false)[0]
                + "<br>Mediante Simpson: "
                + Simpson.calculate(function, a, b, n, false)[0] + "</html>";
        return answer;
    }



    public static String calculateAllDoubleIntegrals(String a, String b,
                                                     String c, String d, String n, String f) {
        String answer = "<html>Mediante Newton Cotes: "
                + NewtonCotes.newtonCotes2(a, b, c, d, n, f)
                + "</html>";
        return answer;
    }



    public static String calculateAllDoubleWithFunctionIntegrals(String a,
                                                                 String b, String c, String d, String n, String f) {
        String answer = "<html>Mediante Newton Cotes: "
                + NewtonCotes.newtonCotesFunc(a, b, c, d, n, f)
                + "</html>";
        return answer;
    }


    public static String calculateAllTripleIntegrals(String a, String b,
                                                     String c, String d, String g, String h, String n, String f) {
        String answer = "<html>Mediante Newton Cotes: "
                + NewtonCotes.newtonCotes3(a, b, c, d, g, h, n, f)
                + "</html>";
        return answer;
    }




}