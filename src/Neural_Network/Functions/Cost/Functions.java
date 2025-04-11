package Neural_Network.Functions.Cost;



public class Functions {

    //categorical cross entropy
    public static Function cceFunction = new Function() {
        String name = "categorical cross entropy";
        @Override
        public double execute(double[] neuron_values, double[] expected_values) {
            double cost = 0;
            for(int i = 0; i < neuron_values.length; i++){
                cost += expected_values[i]*Math.log(neuron_values[i]);
            }
            return -cost;
        }
    };

    //binary cross entropy
    public static Function bceFunction = new Function() {
        String name = "binary cross entropy";
        @Override
        public double execute(double[] neuron_values, double[] expected_values) {
            double cost = 0;
            for(int i = 0; i < neuron_values.length; i++){
                cost += expected_values[i]*Math.log(neuron_values[i])+(1-expected_values[i])*Math.log(1-neuron_values[i]);
            }
            return -cost;
        }
    };

    //mean squared error
    public static Function mseFunction = new Function() {
        String name = "mean squared error";
        @Override
        public double execute(double[] neuron_values, double[] expected_values) {
            double cost = 0;
            for(int i = 0; i < neuron_values.length; i++){
                cost += Math.pow(expected_values[i]-neuron_values[i], 2);
            }
            return cost / neuron_values.length;
            }
    };




}
