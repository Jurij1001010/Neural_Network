package Neural_Network;

import Neural_Network.Functions.Functions;

import java.io.Serializable;

public class Layer implements Serializable{
    boolean last_layer = false;

    public int neuron_before_number;
    public int neuron_number; //how many neurons are in this layer
    public int neuron_next_number; //how many neurons are in next layer if there are 0 it means it is output layer

    Functions functions;

    public Neuron[] neurons;
    public Neuron[] neurons_next;


    public Layer(int neuron_before_number, Neuron[] neurons, int neuron_next_number, Functions functions){
        this.neuron_before_number = neuron_before_number;
        this.neuron_number = neurons.length;
        this.neurons = neurons;
        this.functions = functions;

        this.neuron_next_number = neuron_next_number;
        if(neuron_next_number ==0){
            this.neuron_next_number = neuron_number;
            last_layer=true;
        }

        neurons_next = new Neuron[this.neuron_next_number];

        setUpNeurons();
        setUpNextNeurons();
    }

    public void calculateNextNeurons(){
        double[] new_neuron_next_values = new double[neuron_next_number];
        for (int i = 0; i < neuron_next_number; i++){
            new_neuron_next_values[i] = 0;

            for (int j = 0; j < neuron_number; j++){
                new_neuron_next_values[i] += neurons[j].neuron_value_output *neurons[j].weights[i];
            }

            new_neuron_next_values[i] += neurons_next[i].bias;

        }
        for(int i = 0; i < neuron_next_number; i++){
            //we pass all calculated neuron values for softmax and functions like that (they calculate average/sum...)
            neurons_next[i].setNeuron_value(new_neuron_next_values[i], new_neuron_next_values);
            if(neurons_next[i].neuron_value > 1000 || neurons_next[i].neuron_value < -1000 || neurons_next[i].neuron_value_output > 1000 || neurons_next[i].neuron_value_output< -1000 ){
                //System.out.println("neuron values to big!!");
            }
            if((neurons_next[i].neuron_value_output < 0.0005 && neurons_next[i].neuron_value_output > 0) || (neurons_next[i].neuron_value < 0.0005 && neurons_next[i].neuron_value >0)){
                //System.out.println("neuron values to low!! POSITIVE");
            }
            if((neurons_next[i].neuron_value_output > -0.0001 && neurons_next[i].neuron_value_output < 0) || (neurons_next[i].neuron_value > -0.0001 && neurons_next[i].neuron_value < 0)){
                //System.out.println("neuron values to low!! NEGATIVE");
            }

        }

    }


    public void calculateDeltas(double batch_size){
        double[] neuron_values = getNeuron_values();

        if(last_layer){
            for (int i = 0;i < neuron_number;i++) {
                /*
                double da = neurons[i].getActivationDerivative(neuron_values);//output neuron on activationFunction
                //output and correct layer have the same number of neurons
                double dc = neurons[i].getCostDerivative(neurons_next[i].neuron_value);//activationFunction on cost
                neurons[i].delta += (da * dc)/batch_size; //we add to delta and don't reset it!! -> for batches
                 */
                neurons[i].delta = neurons[i].neuron_value_output - neurons_next[i].neuron_value;


                if (neurons[i].delta>1000 || neurons[i].delta<-1000){
                    System.out.println("as");
                }
            }
        }else {
            for (int i = 0;i < neuron_number;i++) {

                double da = neurons[i].getActivationDerivative(neuron_values);// neuron on activationFunction
                for (int k = 0; k < neurons_next.length; k++) {
                    neurons[i].delta += da * neurons[i].weights[k] * neurons_next[k].delta;
                }


                if (neurons[i].delta>1000|| neurons[i].delta<-1000){
                    //System.out.println("as");
                }
            }
        }
    }


    public void calculateWeightsBiases(double learn_rate){
        for (Neuron neuron : neurons) {
            if(!last_layer){
                for (int i = 0; i < neurons_next.length; i++) {
                    neuron.weights[i] -= learn_rate * (neurons_next[i].delta * neuron.neuron_value_output);
                }
            }
            neuron.bias -= learn_rate * neuron.delta;
            if(neuron.bias>1000 || neuron.bias<-1000){
                //System.out.println("a");
            }
        }
        for(Neuron neuron_next : neurons_next){
            neuron_next.delta = 0; // resets delta after using it
        }
    }
    public double calculateCost(double[] expected_values){
        double cost = 0.0;
        if(!last_layer) return cost;

        return functions.costFunction.execute(getNeuron_values_output(), expected_values);
    }

    public void setUpNeurons(){
        for(int i = 0; i<neuron_number;i++){
            neurons[i] = new Neuron(neuron_before_number, neuron_next_number);
            neurons[i].setFunctions(functions, last_layer);
        }
    }
    public void setUpNextNeurons(){
        for(int i = 0; i<neuron_next_number;i++){
            neurons_next[i] = new Neuron(neuron_before_number,  0);
        }
    }

    public void setNeurons(double[] neuron_values){
        for(int i = 0; i<neuron_number;i++){
            //sets unactivated and activated neuron value
            neurons[i].setNeuron_value(neuron_values[i]);

        }
    }
    public void setNextNeurons(double[] neuron_next_values){
        //for setting correct output
        for(int i = 0; i<neuron_next_number;i++){
            neurons_next[i].setNeuron_value(neuron_next_values[i]);
        }
    }

    public double[] getNeuron_values_output(){
        double[] neuron_values_a=new double[neuron_number];
        for(int i = 0; i<neuron_number;i++){
            neuron_values_a[i] = neurons[i].neuron_value_output;
        }
        return neuron_values_a;
    }
    public double[] getNeuron_values(){
        double[] neuron_values=new double[neuron_number];
        for(int i = 0; i<neuron_number;i++){
            neuron_values[i] = neurons[i].neuron_value;
        }
        return neuron_values;
    }
}
