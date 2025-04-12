import Datasets.Batch;
import Datasets.DataSet;
import Neural_Network.Functions.Activation.Functions;
import Neural_Network.Network;

import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Neural_Network.Functions.Functions functions = new Neural_Network.Functions.Functions(Functions.ReLUFunction, Functions.softMaxFunction, Neural_Network.Functions.Cost.Functions.cceFunction );
    public static Network n = new Network(new int[]{4,5,5,5,2}, functions);


    public static void main(String[] args) {

        n.setLearn_rate(0.03);

        DataSet data_set = new DataSet(100, 4, 2, 10);


        learn(10000, data_set);
        System.out.println();
        test(10, data_set);

    }




    public static void learn(int number_of_epoch, DataSet data_set){


        for (int i = 0; i < number_of_epoch; i++) {


            Batch batch = data_set.setRandomBatch();
            n.learnNetwork(batch);
            if(i%100==0) {
                System.out.println(i+" "+batch.cost+" "+batch.correct_predictions_count);
            }
        }

    }
    public static void test(int number_of_tests, DataSet data_set){

        for (int i = 0; i < number_of_tests; i++) {
            double[][] random_data = data_set.getRandomData();
            double[] data = random_data[0];
            double[] results = random_data[1];


            System.out.println(data[0]+" "+data[1]+" "+(results[0]==1?"true":"false")+" "+Arrays.toString(n.feedNetwork(data))+" "+n.calculateCost(results));

        }
    }
}
