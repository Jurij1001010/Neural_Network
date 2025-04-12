package Datasets.Functions;

public interface Function {
    int output_neuron_number = 2;
    boolean execute(double x, double y);
    double execute(double x);//returns y on function (to get points)
}
