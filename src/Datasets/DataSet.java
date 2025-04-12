package Datasets;

import Datasets.Functions.Function;
import Datasets.Inputs.Input;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class DataSet {

    public int dataset_length;
    public int input_neuron_number;
    public int output_neuron_number;

    public double[][] data;
    public double[][] results;

    public int batch_size;

    public static int min = -10;
    public static int max = 10;

    public Function[] functions;
    private Input[] inputs;

    private static final Random rand = new Random();

    int function_pointer = 0;

    public DataSet(int dataset_length, Input[] inputs, Function[] functions, int batch_size){
        this.dataset_length = dataset_length;
        this.input_neuron_number = inputs.length;
        this.functions = functions;
        this.output_neuron_number = functions.length+1;
        this.batch_size = batch_size;
        this.inputs = inputs;

        data = new double[dataset_length][this.input_neuron_number];
        results = new double[dataset_length][this.output_neuron_number];
    }

    public void createDataSet(){

        for (int i = 0; i < dataset_length; i++) {
            data[i] = getData();
            results[i] = getResults(data[i]);
            function_pointer++;
        }
        shuffleDataset();
        System.out.println();
    }

    public double[] getData(){
        double[] output = getPointFxOut(functions[0]);
        if(function_pointer==output_neuron_number-1){
            for(int i = 0;i<output_neuron_number-1;i++){
                if(functions[i].execute(output[0], output[1])){
                    output = getPointFxOut(functions[i]);
                    i = -1;
                }
            }
            function_pointer = -1;
            return output;
        }

        output = getPointFxIn(functions[function_pointer]);

        return output;
    }
    public double[] getResults(double[] data){
        double[] output = new double[output_neuron_number];
        boolean in_fx = false;
        for (int i = 0; i<output_neuron_number-1;i++){
            if(functions[i].execute(data[0], data[1])){
                output[i] = 1;
                in_fx = true;
            }
        }
        if(!in_fx) output[output.length-1] = 1;
        return output;
    }
    public Batch setRandomBatch(){
        Batch batch = new Batch(batch_size, input_neuron_number, output_neuron_number);

        if (batch_size==dataset_length){
            batch.setBatch(data, results);
            return batch;
        }

        LinkedList<Integer> already_used_r = new LinkedList<Integer>();

        for (int i = 0; i < batch_size; i++) {
            int r;
            while(already_used_r.contains(r = getRanInt(0, dataset_length)));

            batch.setBatchI(data[r].clone(), results[r].clone(), i);
            already_used_r.add(r);
        }
        return batch;
    }

    public double[][] getRandomData(){
        int rand_d = getRanInt(0, dataset_length);
        return new double[][]{data[rand_d], results[rand_d]};
    }

    public double[] getPointFxIn(Function function){
        double x = getRanDouble(min, max);
        double y = function.execute(x);
        while(Double.isNaN(y)||!function.execute(x,y)||y<min||y>max){

            x = getRanDouble(min, max);
            y = function.execute(x);
        }

        return makeInput(x, y);
    }

    public double[] getPointFxOut(Function function){
        double x = getRanDouble(min, max);
        double y = getRanDouble(min, max);
        while(function.execute(x,y)){
            x = getRanDouble(min, max);
            y = getRanDouble(min, max);
        }
        return makeInput(x, y);
    }

    public void shuffleDataset() {
        for (int i = 0; i < dataset_length; i++) {
            int randomIndexToSwap = rand.nextInt(dataset_length);
            double[] temp_d = data[randomIndexToSwap];
            double[] temp_r = results[randomIndexToSwap];

            data[randomIndexToSwap] = data[i];
            results[randomIndexToSwap] = results[i];
            data[i] = temp_d;
            results[i] = temp_r;
        }
    }


    public double[] makeInput(double x, double y){
        double[] output = new double[input_neuron_number];

        for(int i = 0; i <input_neuron_number;i++){
            output[i] = inputs[i].execute(x, y);
        }
        return output;
    }


    public static int getRanInt(int min, int max){
        return min + (int)(Math.random() * ((max - min)));
    }

    public static double getRanDouble(double min, double max){
        double num = min + ((max - min) * rand.nextDouble());
        return num;
    }
}
