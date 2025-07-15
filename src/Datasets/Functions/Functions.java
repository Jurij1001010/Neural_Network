package Datasets.Functions;

import java.util.Random;

public class Functions {
    static Random rand = new Random();
    
    public static Function circle = new Function() {
        public final int output_neuron_number = 2;
        private int r = 4;
        @Override
        public boolean execute(double x, double y) {
            boolean f = x*x+y*y == r*r;

            return f;
        }

        @Override
        public double execute(double x) {
            double a =Math.acos(x/r);
            return rand.nextDouble()>0.5?Math.sin(a)*r:-Math.sin(a)*r;
        }
    };
    public static Function circle1 = new Function() {
        public final int output_neuron_number = 2;
        private int r = 2;
        @Override
        public boolean execute(double x, double y) {
            boolean f = x*x+y*y == r*r;

            return f;
        }

        @Override
        public double execute(double x) {
            double a =Math.acos(x/r);
            return rand.nextDouble()>0.5?Math.sin(a)*r:-Math.sin(a)*r;
        }
    };

    public static Function diagonal = new Function() {
        public final int output_neuron_number = 2;
        @Override
        public boolean execute(double x, double y) {
            boolean f = y==x+6;

            return f;
        }

        @Override
        public double execute(double x) {
            return x+6;
        }
    };
    public static Function log = new Function() {
        public final int output_neuron_number = 2;
        @Override
        public boolean execute(double x, double y) {
            boolean f = y==Math.log10(x-2)-5;

            return f;
        }

        @Override
        public double execute(double x) {
            return Math.log10(x-2)-5;
        }
    };
    public static Function polinom = new Function() {
        public final int output_neuron_number = 2;
        @Override
        public boolean execute(double x, double y) {
            boolean f = y==Math.pow(x+5, 4)+3*Math.pow(x+5, 3)+8;

            return f;
        }

        @Override
        public double execute(double x) {
            return Math.pow(x+5, 4)+3*Math.pow(x+5, 3)+8;
        }
    };


    public static Function racional_function = new Function() {
        public final int output_neuron_number = 2;
        @Override
        public boolean execute(double x, double y) {
            boolean f = y==(x*x*x+3)/(2*(x*x+2*x));

            return f;
        }

        @Override
        public double execute(double x) {
            return (x*x*x+3)/(2*(x*x+2*x));
        }
    };

}
