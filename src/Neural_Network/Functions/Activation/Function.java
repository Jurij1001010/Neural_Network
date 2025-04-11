package Neural_Network.Functions.Activation;

public interface Function {
    double execute(double input);
    double execute(double value, double[] values);//for functions that calculate average
}
