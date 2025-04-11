package Neural_Network;

import Neural_Network.Functions.Activation.Derivative;
import Neural_Network.Functions.Activation.Function;
import Neural_Network.Functions.Functions;

import java.util.Random;

public class Neuron{
    public double neuron_value; // value of neuron
    public double neuron_value_output; // value of activated neuron

    public int neuron_before_number;
    public int neuron_next_number; // how many weights
    public double[] weights;
    public double bias;

    public double delta = 0;

    Function activationFunction;
    Derivative activateDerivative;

    Neural_Network.Functions.Cost.Function costFunction;
    Neural_Network.Functions.Cost.Derivative costDerivative;

    //for generating new weights and biases
    private final Random rand = new Random();
    private double weight_limit = 1;
    private double bias_limit = 1;

    public Neuron(int neuron_before_number, int neuron_next_number) {
        this.neuron_before_number = neuron_before_number;
        this.neuron_next_number = neuron_next_number;

        if(neuron_next_number!=0) {
            weights = new double[neuron_next_number];

            setNewWeights();
            setNewBias();
        }
    }


    public void setNewWeights(){
        double max = Math.sqrt((double) 1 /(neuron_before_number+neuron_next_number));
        double min = -max;
        /*
        for (int i = 0; i < neuron_next_number; i++){
            double randomNum = -weight_limit + (weight_limit + weight_limit) * rand.nextDouble();
            weights[i] = randomNum;
        }
        */
        for (int i = 0; i < neuron_next_number; i++){
            double randomNum = min + ((max - min) * rand.nextDouble());
            weights[i] = randomNum;
            //System.out.println("a = "+randomNum);
        }

    }

    public void setNewBias(){
        bias = -bias_limit + (bias_limit + bias_limit) * rand.nextDouble();
    }

    public void setNeuron_value(double neuron_value) {
        this.neuron_value = neuron_value;
        if(activationFunction!=null) {
            this.neuron_value_output = activationFunction.execute(neuron_value);
        }

    }
    public void setNeuron_value(double neuron_value, double[] neuron_values) {
        this.neuron_value = neuron_value;
        //needs to get all neurons values for calculating activations that are avg. or sum ...
        this.neuron_value_output = activationFunction.execute(neuron_value, neuron_values);



    }


    public double getActivationDerivative(double[] neuron_values){
        //needs to get all neurons values for calculating activation derivatives that are avg. or sum ...
        //needs to get values with witch activation was calculated!!!
        return activateDerivative.execute(neuron_value, neuron_values);
    }
    public double getCostDerivative(double expected_value){
        //needs to get correct value
        return costDerivative.execute(neuron_value_output, expected_value);
    }


    public void setFunctions(Functions functions, boolean output_neuron){
        this.activationFunction = functions.activationFunction;
        this.activateDerivative = functions.activationDerivative;
        if(output_neuron){
            this.activationFunction = functions.activationFunctionO;
            this.activateDerivative = functions.activationDerivativeO;
            this.costFunction = functions.costFunction;
            this.costDerivative = functions.costDerivative;
        }
    }

}
