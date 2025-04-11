package Neural_Network.Functions.Cost;

public interface Function {
    double execute(double[] neuron_values, double[] expected_values);
}
