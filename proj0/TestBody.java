public class TestBody {

	public static void main(String[] args) {

	}

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkBody() {
        System.out.println("Checking update...");

        Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");

        Body b2 = new Body(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        
        b1.update(2.0, 1.0, -0.5);

        checkEquals(3.4, b1.xxVel, "xxVel update()", 0.01);
        checkEquals(3.8, b1.yyVel, "yyVel update()", 0.01);
        checkEquals(7.8, b1.xxPos, "xxPos update()", 0.01);
        checkEquals(8.6, b1.yyPos, "yyPos update()", 0.01);
    }    	
}