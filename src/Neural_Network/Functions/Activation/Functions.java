package Neural_Network.Functions.Activation;

import java.util.Arrays;


public class Functions {

    //from -1 to 1
    public static Function tanHFunction = new Function() {
        final String name = "tanHFunction";
        @Override
        public double execute(double input) {
            double x_plus = Math.exp(input);
            double x_minus = Math.exp(-input);

            double p1= (x_plus-x_minus);
            double p2 = (x_plus+x_minus);
            double output =p1/p2;
            if(Double.isNaN(output)){
                System.out.println("tanH function NaN");
            }

            if(Double.isInfinite(input)) return 1;
            return output;
        }

        @Override
        public double execute(double value, double[] values) {
            //System.out.println("Wrong use of tanH function!");
            return tanHFunction.execute(value);
        }
    };

    //from 0 to 1
    public static Function sigmoidFunction = new Function() {
        final String name = "sigmoidFunction";
        @Override
        public double execute(double input) {
            double output = 1/(1+Math.exp(-input));
            if(Double.isNaN(output)){
                System.out.println("sigmoid function NaN");
            }
            return output;
        }

        @Override
        public double execute(double value, double[] values) {
            //System.out.println("Wrong use of sigmoid function!");
            return sigmoidFunction.execute(value);
        }
    };

    //e average -> only for array
    public static Function softMaxFunction = new Function() {
        final String name = "softMaxFunction";
        @Override
        public double execute(double input) {
            System.out.println("Wrong use of softMax function!");
            return 0;
        }

        @Override
        public double execute(double value, double[] values) {
            double total = Arrays.stream(values).map(Math::exp).sum();
            double output = Math.exp(value)/total;

            if(output<0||Double.isNaN(output)){
                System.out.println("soft max function NaN");
            }

            return output;
        }
    };

    //e average(more precise) -> only for array
    public static Function logSoftMaxFunction = new Function() {
        final String name = "logSoftMaxFunction";
        @Override
        public double execute(double input) {
            System.out.println("Wrong use of logSoftMax function!");
            return 0;
        }

        @Override
        public double execute(double value, double[] values) {
            double total = Arrays.stream(values).map(Math::exp).sum();
            double output = Math.log(Math.exp(value)/total);
            if(Double.isNaN(output)){
                System.out.println("sigmoid function NaN");
            }
            return output;
        }
    };

    //max(0, value)
    public static Function ReLUFunction = new Function() {
        final String name = "ReLUFunction";
        @Override
        public double execute(double input) {
            return Math.max(0, input);
        }

        @Override
        public double execute(double value, double[] values) {
            //System.out.println("Wrong use of ReLU function!");
            return ReLUFunction.execute(value);
        }
    };
}

