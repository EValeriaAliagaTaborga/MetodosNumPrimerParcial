import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

public class Trapecios {
    private static DJep _j;
    private static String _y;
    private static Node _funcion;
    private static Node _segundaDiff;
    private static double _a;
    private static double _b;
    private static int _n;
    private static double _h;

    public static double[] calculate(String y, String a, String b, String n, boolean integral) {
        return calculate(y, new Double(a), new Double(b), new Integer(n), integral);
    }

    public static double[] calculate(String y, double a, double b, int n, boolean integral) {
        double[] respuestas = new double[2];
        setValues(y, a, b, n);
        if (integral) {
            respuestas[0] = calcIntegral();
        } else {
            respuestas[0] = calcArea();
        }
        respuestas[1] = calcError();
        return respuestas;
    }

    private static void setValues(String y, double a, double b, int n) {
        _j = new DJep();
        _j.addStandardConstants();
        _j.addStandardFunctions();
        _j.addComplex();
        _j.setAllowUndeclared(true);
        _j.setAllowAssignment(true);
        _j.setImplicitMul(true);
        _j.addStandardDiffRules();
        _y = y;
        _a = a;
        _b = b;
        _n = n;
        _h = (_b - _a) / _n;
        try {
            Node uglyFunction = _j.parse(_y);
            _funcion = _j.simplify(uglyFunction);
            _segundaDiff = _j.differentiate(_j.differentiate(_funcion, "x"), "x");
        } catch (ParseException e) {
        }
    }

    private static double calcIntegral() {
        double fInA = 0;
        double fInB = 0;
        double suma = 0;
        try {
            _j.addVariable("x", _a);
            fInA = (double) _j.evaluate(_funcion);
            _j.addVariable("x", _b);
            fInB = (double) _j.evaluate(_funcion);
            for (int k = 1; k <= _n - 1; k++) {
                _j.addVariable("x", _a + (k * _h));
                suma += (double) _j.evaluate(_funcion);
            }
        } catch (Exception e) {
        }
        double answer = _h * (((fInA + fInB) / 2) + suma);
        return answer;
    }

    private static double calcArea() {
        double fInA = 0;
        double fInB = 0;
        double suma = 0;
        try {
            _j.addVariable("x", _a);
            fInA = Math.abs((double) _j.evaluate(_funcion));
            _j.addVariable("x", _b);
            fInB = Math.abs((double) _j.evaluate(_funcion));
            for (double k = 1; k < _n; k++) {
                _j.addVariable("x", _a + (k * ((_b - _a) / _n)));
                suma += Math.abs((double) _j.evaluate(_funcion));
            }
        } catch (Exception e) {
        }
        double respuesta = _h * (((fInA + fInB) / 2) + suma);
        return respuesta;
    }

    private static double calcError() {
        double maximo = 0;
        try {
            double incremento = 0;
            if ((_b - _a) < 1000) {
                incremento = 0.01;
            } else {
                incremento = 1;
            }
            double secondDiffValue;
            for (double x = _a; x <= _b; x += incremento) {
                _j.addVariable("x", x);
                secondDiffValue = Math.abs((double) _j.evaluate(_segundaDiff));
                if (maximo < secondDiffValue) {
                    maximo = secondDiffValue;
                }
            }
        } catch (Exception e) {
        }
        double error = ((Math.pow(_h, 2) / 12) * (_b - _a) * maximo);
        return error;
    }
}