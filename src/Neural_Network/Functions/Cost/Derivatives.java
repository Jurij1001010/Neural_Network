package Neural_Network.Functions.Cost;

public class Derivatives {

    //categorical cross entropy
    public static Derivative ccaDerivative = new Derivative() {
        String name = "categorical cross entropy";
        @Override
        public double execute(double neuron_value, double expected_value) {
            return -(expected_value)/(neuron_value);
        }

        @Override
        public double[] execute(double[] neuron_values, double[] expected_values) {
            double[] output = new double[neuron_values.length];
            for (int i = 0; i < neuron_values.length; i++) {
                output[i] = ccaDerivative.execute(neuron_values[i], expected_values[i]);
            }
            return output;
        }
    };

    //binary cross entropy
    public static Derivative bcaDerivative = new Derivative() {
        String name = "binary cross entropy";
        @Override
        public double execute(double neuron_value, double expected_value) {
            return -((expected_value/neuron_value)-(1-expected_value)/(1-neuron_value));
        }

        @Override
        public double[] execute(double[] neuron_values, double[] expected_values) {
            double[] output = new double[neuron_values.length];
            for (int i = 0; i < neuron_values.length; i++) {
                output[i] = ccaDerivative.execute(neuron_values[i], expected_values[i]);
            }
            return output;
        }
    };


    //mean squared error
    public static Derivative mseDerivative = new Derivative() {
        String name = "mean squared error";
        @Override
        public double execute(double neuron_value, double expected_value) {
            return -2*(expected_value-neuron_value);
        }

        @Override
        public double[] execute(double[] neuron_values, double[] expected_values) {
            double[] output = new double[neuron_values.length];
            for (int i = 0; i < neuron_values.length; i++) {
                output[i] = mseDerivative.execute(neuron_values[i], expected_values[i]);
            }
            return output;
        }
    };


}
