import java.util.LinkedList;
import java.util.List;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

public class Simpson {

    public static double[] calculate(String function, String a, String b,String n, boolean integral) {
        String ioA;
        if (integral == true) {
            ioA = "integral";
        } else {
            ioA = "area";
        }
        return metodoSimpson(function, new Double(a), new Double(b),
                new Integer(n), ioA);
    }



    public static double[] metodoSimpson(String function, double a, double b,
                                         int num, String ioA) {
        if (num % 2 != 0) {
            System.out.println("n tiene que ser un numero par");
            return null;
        }
        DJep j = new DJep();
        j.addStandardConstants();
        j.addStandardFunctions();
        j.addComplex();
        j.setAllowUndeclared(true);
        j.setAllowAssignment(true);
        j.setImplicitMul(true);
        j.addStandardDiffRules();
        double c1 = 0;
        if (Math.abs(b - a) <= 0.01) {
            c1 = 0.00001;
        } else if (Math.abs(b - a) <= 1000) {
            c1 = 0.01;
        } else if (Math.abs(b - a) <= 10000) {
            c1 = 0.1;
        } else {
            c1 = 1;
        }
        double h = ((b - a) / num);
        double m = 0;
        List<Double> e = new LinkedList<>();
        List<Double> i = new LinkedList<>();
        List<Double> p = new LinkedList<>();
        try {
            Node f = j.parse(function);
            f = j.simplify(f);
            for (int k = 0; k <= num; k++) {
                j.addVariable("x", (a + (k * h)));
                Double ad = (Double) j.evaluate(f);
                if (k == 0 || k == num) {
                    e.add(ad);
                } else if (k % 2 == 0) {
                    p.add(ad);
                } else {
                    i.add(ad);
                }
            }
            Node diff = j.differentiate(f, "x");
            for (int k = 0; k < 3; k++) {
                diff = j.differentiate(diff, "x");
            }
            Node d4 = j.simplify(diff);
            double max = 0;
            for (double k = a; k <= b; k += c1) {
                j.addVariable("x", (a + k));
                Double ad = (Double) j.evaluate(d4);
                if (max < Math.abs(ad)) {
                    max = Math.abs(ad);
                }
            }
            m = max;
        } catch (ParseException e1) {
            System.out.println("Error with parsing");
        } catch (Exception e2) {
            System.out.println("Error with casting");
        }
        double[] previo = new double[2];
        Double sumE = sum(e, ioA);
        Double sumI = sum(i, ioA);
        Double sumP = sum(p, ioA);
        Double ansInt = (h / 3.0) * (sumE + (4.0 * sumI) + (2.0 * sumP));
        Double epsilon = (1.0 / 180.0) * Math.pow(h, 4) * m * (b - a);
        previo[0] = ansInt;
        previo[1] = epsilon;
        return previo;
    }


    public double metodoSimpson(double[] nums, double a, double b, double h,String ioA) {
        int num = nums.length;
        if (num % 2 != 0) {
            System.out.println("n tiene que ser un numero par");
            return 0.0;
        }
        List<Double> e = new LinkedList<>();
        List<Double> i = new LinkedList<>();
        List<Double> p = new LinkedList<>();
        for (int k = 0; k < num; k++) {
            if (k == 0 || k == num - 1) {
                e.add(nums[k]);
            } else if (k % 2 == 0) {
                p.add(nums[k]);
            } else {
                i.add(nums[k]);
            }
        }
        Double sumE = sum(e, ioA);
        Double sumI = sum(i, ioA);
        Double sumP = sum(p, ioA);
        Double ans1 = (h / 3.0) * (sumE + (4.0 * sumI) + (2.0 * sumP));
        return ans1;
    }

    private static Double sum(List<Double> l, String ioA) {
        Double sum = 0.0;
        if (ioA == "area") {
            for (Double i : l) {
                sum += Math.abs(i);
            }
        } else {
            for (Double i : l) {
                sum += i;
            }
        }
        return sum;
    }
}