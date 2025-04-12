package Datasets.Inputs;

public class Inputs {
    public static Input x = new Input() {
        @Override
        public double execute(double x, double y) {
            return x;
        }
    };

    public static Input y = new Input() {
        @Override
        public double execute(double x, double y) {
            return y;
        }
    };

    public static Input sum = new Input() {
        @Override
        public double execute(double x, double y) {
            return x+y;
        }
    };

    public static Input xPow2 = new Input() {
        @Override
        public double execute(double x, double y) {
            return x*x;
        }
    };

    public static Input yPow2 = new Input() {
        @Override
        public double execute(double x, double y) {
            return y*y;
        }
    };

    public static Input xy = new Input() {
        @Override
        public double execute(double x, double y) {
            return x*y;
        }
    };
    public static Input sinX = new Input() {
        @Override
        public double execute(double x, double y) {
            return Math.sin(x);
        }
    };
    public static Input sinY = new Input() {
        @Override
        public double execute(double x, double y) {
            return Math.sin(y);
        }
    };
    public static Input lnX = new Input() {
        @Override
        public double execute(double x, double y) {
            return Math.log(Math.abs(x));
        }
    };
    public static Input lnY = new Input() {
        @Override
        public double execute(double x, double y) {
            return Math.log(Math.abs(y));
        }
    };

}
