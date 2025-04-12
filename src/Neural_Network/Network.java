package Neural_Network;

import Datasets.Batch;

import java.io.Serializable;

public class Network implements Cloneable, Serializable {
    public Layer input_layer;
    public Layer output_layer;

    public final int input_neurons_number; //how many neurons are in input layer
    public final int output_neurons_number; //how many neurons are in output layer
    public final int hidden_layers_number; //how many hidden layers are
    public int layer_number;//how many layers
    public int[] neurons_numbers;

    public Layer[] layers;
    private double learn_rate = 0.1;

    Neural_Network.Functions.Functions functions;


    public Network(int[] neurons_numbers, Neural_Network.Functions.Functions functions){
        this.layer_number = neurons_numbers.length;
        this.input_neurons_number = neurons_numbers[0];
        this.hidden_layers_number = layer_number-2;
        this.output_neurons_number = neurons_numbers[layer_number-1];
        this.neurons_numbers = neurons_numbers;

        this.functions = functions;


        layers = new Layer[2+hidden_layers_number];
        makeLayers();
    }

    public void resetLayers(){
        for(Layer l : layers){
            for(Neuron n : l.neurons){
                n.setNewWeights();
                n.setNewBias();
            }
        }
    }
    public void setLayers(Network n){
        for(int i = 0; i<layers.length;i++){
            layers[i].neurons = n.layers[i].neurons.clone();
        }
    }

    public void makeLayers(){
        //if there is 0 hidden layers we only need to set up input and output layers

        layers[0] = new Layer(0, new Neuron[input_neurons_number], neurons_numbers[1], functions);
        for (int i = 0; i< hidden_layers_number; i++){
            layers[i+1] = new Layer(neurons_numbers[i], layers[i].neurons_next, neurons_numbers[i+2], functions);
        }
        layers[layer_number-1] = new Layer(neurons_numbers[layer_number-2], layers[layer_number-2].neurons_next, 0, functions); //set up output layer

        input_layer = layers[0];
        output_layer = layers[layers.length-1];
    }


    public double[] feedNetwork(double[] input_neuron_values){
        input_layer.setNeurons(input_neuron_values, true);

        for (int i = 0; i<layer_number;i++) {
            if(layers[i]==output_layer)break;
            layers[i].calculateNextNeurons();
        }
        return output_layer.getNeuron_values_output();
    }

    public void learnNetwork(Batch batch){
        double[][] expected_neuron_values_batch = batch.results;


        for (int i = 0; i < batch.size; i++) {
            double[] predicted_values = feedNetwork(batch.data[i]);

            batch.checkPredictions(predicted_values, i);

            output_layer.setNextNeurons(expected_neuron_values_batch[i]);//set last neurons as expected values
            output_layer.calculateDeltas(batch.size); //calculates average delta in whole batch
            batch.cost += output_layer.calculateCost(expected_neuron_values_batch[i])/batch.size; //calculates average cost in whole batch
        }
        output_layer.calculateWeightsBiases(learn_rate); //with average deltas calculates weights on output layer

        //back-propagation
        for (int i = layer_number-2; i >= 0; i--){
            layers[i].calculateDeltas(1); //calculates deltas on all others layers (batch_size 1 because not average)
            layers[i].calculateWeightsBiases(learn_rate); //
        }
    }

    public double calculateCost(double[] expected_neuron_values) {
        return output_layer.calculateCost(expected_neuron_values);
    }

    public void setLearn_rate(double learn_rate) {
        this.learn_rate = learn_rate;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

