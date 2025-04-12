package Datasets;

public class Batch {
    public int size;
    public double[][] data;
    public double[][] results;

    public double cost = 0;
    public int correct_predictions_count = 0;

    public int input_neuron_number;
    public int output_neuron_number;

    public Batch(int batch_size, int input_neuron_number, int output_neuron_number){
        this.size = batch_size;
        this.input_neuron_number = input_neuron_number;
        this.output_neuron_number = output_neuron_number;

        this.data = new double[batch_size][input_neuron_number];
        this.results = new double[batch_size][output_neuron_number];
    }

    public void setBatch(double[][] batch_data, double[][] batch_results){
        this.data = batch_data;
        this.results = batch_results;
    }
    public void setBatchI(double[] data, double[] results, int i){
        this.data[i] = data;
        this.results[i] = results;
    }

    public void checkPredictions(double[] predicted_values,int i){
        if(predicted_values[0]>predicted_values[1]){
            if(results[i][0]==1)correct_predictions_count++;
        }else if(predicted_values[0]<predicted_values[1]){
            if(results[i][0]==0)correct_predictions_count++;
        }

    }

}
