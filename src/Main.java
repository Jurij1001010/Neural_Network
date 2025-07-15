import Datasets.Batch;
import Datasets.DataSet;
import Datasets.Functions.Function;
import Datasets.Inputs.Input;
import Neural_Network.Functions.Activation.Functions;
import Neural_Network.Network;
import java.util.Arrays;

import static Datasets.Functions.Functions.*;
import static Datasets.Inputs.Inputs.*;


public class Main {

    private static final Neural_Network.Functions.Functions functions = new Neural_Network.Functions.Functions(Functions.ReLUFunction, Functions.softMaxFunction, Neural_Network.Functions.Cost.Functions.cceFunction );
    public static Network n;


    public static void main(String[] args) {
        DataSet data_set = new DataSet(10000,  new Input[]{x, y, xy, xPow2, yPow2, sum, sinX, sinY}, new Function[]{racional_function, polinom, log}, 100);

        data_set.createDataSet();

        n = new Network(new int[]{data_set.input_neuron_number,10,8,8,10,  data_set.output_neuron_number}, functions);
        n.setLearn_rate(0.03);


        learn(300000, data_set);
        System.out.println();
        test(10, data_set);

    }




    public static void learn(int number_of_epoch, DataSet data_set){
        int ONEHUNDERT_count = 0;

        for (int i = 0; i < number_of_epoch; i++) {


            Batch batch = data_set.setRandomBatch();
            n.learnNetwork(batch);
            if(i%100==0) {
                if (batch.correct_predictions_count==100)ONEHUNDERT_count++;
                if(ONEHUNDERT_count==100){
                    System.out.println("I am finished!");
                    break;}
                System.out.println(i+" "+batch.cost+" "+batch.correct_predictions_count);
            }
        }

    }
    public static void test(int number_of_tests, DataSet data_set){

        for (int i = 0; i < number_of_tests; i++) {
            double[][] random_data = data_set.getRandomData();
            double[] data = random_data[0];
            double[] results = random_data[1];

            String results_s = "";
            for (int j = 0; j < results.length; j++) {
                if(results[j]==1){
                    results_s += j;
                    break;
                }
            }

            System.out.println("("+data[0]+", "+data[1]+") "+results_s+" "+Arrays.toString(n.feedNetwork(data))+" "+n.calculateCost(results));

        }
    }
}
