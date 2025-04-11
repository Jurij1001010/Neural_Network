package Neural_Network.Functions.Cost;

public interface Derivative {
    double execute(double neuron_value, double expected_value);
    double[] execute(double[] neuron_values, double[] expected_values);
}
