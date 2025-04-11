package Neural_Network.Functions.Activation;

public interface Derivative {
    double execute(double input);
    double execute(double value, double[] values);
}
