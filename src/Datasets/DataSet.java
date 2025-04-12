package Datasets;

import java.util.LinkedList;
import java.util.Random;

public class DataSet {

    public int dataset_length;
    public int input_neuron_number;
    public int output_neuron_number;

    public double[][] data;
    public double[][] results;

    public int batch_size;
    public double[][] batch_data;
    public double[][] batch_results;

    public static int min = -10;
    public static int max = 10;

    private static final Random rand = new Random();

    public DataSet(int dataset_length, int input_neurons_number, int output_neurons_number, int batch_size){
        this.dataset_length = dataset_length;
        data = new double[dataset_length][input_neurons_number];
        results = new double[dataset_length][output_neurons_number];
        this.batch_size = batch_size;

        createDataSet();
    }

    public void createDataSet(){


        for (int i = 0; i < dataset_length; i+=2) {
            data[i] = getPointFxIn();
            data[i+1] = getPointFxOut();
            //System.out.println(x+" "+y);
            results[i] = fx(data[i][0], data[i][1]);
            results[i+1] = fx(data[i+1][0], data[i+1][1]);

            //System.out.println(Arrays.toString(data[i]) +" "+ Arrays.toString(results[i]));
            //System.out.println(Arrays.toString(data[i+1]) +" "+ Arrays.toString(results[i+1]));
        }
        shuffleDataset();
        System.out.println();
    }

    public void setRandomBatch(){
        if (batch_size==dataset_length){
            batch_data = data;
            batch_results = results;
            return;
        }

        batch_data = new double[batch_size][input_neuron_number];
        batch_results = new double[batch_size][output_neuron_number];

        LinkedList<Integer> already_used_r = new LinkedList<Integer>();

        for (int i = 0; i < batch_size; i++) {
            int r;
            while(already_used_r.contains(r = getRanInt(0, dataset_length)));

            batch_data[i] = data[r].clone();
            batch_results[i] = results[r].clone();

            already_used_r.add(r);
        }

    }

    public double[][] getRandomData(){
        int rand_d = getRanInt(0, dataset_length);

        return new double[][]{data[rand_d], results[rand_d]};
    }

    public static double[] getPointFxIn(){
        double x = getRanInt(min, max);
        double y = getRanInt(min, max);
        while(fx(x,y)[0]!=1){
            x = getRanInt(min, max);
            y = getRanInt(min, max);
        }
        return new double[]{x, y, x*x, y*y};

    }
    public static double[] getPointFxOut(){
        double x = getRanInt(min, max);
        double y = getRanInt(min, max);
        while(fx(x,y)[0]!=0){
            x = getRanInt(min, max);
            y = getRanInt(min, max);
        }
        return new double[]{x, y, x*x, y*y};
    }


    public static double[] fx(double x, double y){
        //boolean f = y>=5;
        //boolean f = (2*x-5)<=y && y<=(2*x+5);

        boolean f = x*x+y*y <25; // circle

        return f?new double[]{1, 0}:new double[]{0, 1};
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
    public static int getRanInt(int min, int max){
        return min + (int)(Math.random() * ((max - min)));
    }

    public static double getRanDouble(double min, double max){
        double num = min + ((max - min) * rand.nextDouble());
        return num;
    }
}
