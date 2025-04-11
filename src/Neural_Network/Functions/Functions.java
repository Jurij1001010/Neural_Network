package Neural_Network.Functions;

import Neural_Network.Functions.Activation.Derivative;
import Neural_Network.Functions.Activation.Derivatives;
import Neural_Network.Functions.Activation.Function;

import java.util.Objects;

public class Functions {
    public Function activationFunction;
    public Derivative activationDerivative;

    //if output layer has different activation
    public Function activationFunctionO;
    public Derivative activationDerivativeO;

    public Neural_Network.Functions.Cost.Function costFunction;
    public Neural_Network.Functions.Cost.Derivative costDerivative;

    public Functions(){
        this.activationFunction = Neural_Network.Functions.Activation.Functions.tanHFunction;
        this.activationDerivative = Derivatives.tanHDerivative;

        this.activationFunctionO = Neural_Network.Functions.Activation.Functions.softMaxFunction;
        this.activationDerivativeO = Derivatives.softMaxDerivative;

        this.costFunction = Neural_Network.Functions.Cost.Functions.cceFunction;
        this.costDerivative = Neural_Network.Functions.Cost.Derivatives.ccaDerivative;


    }
    public Functions(Function activationFunction, Function activationFunctionO, Neural_Network.Functions.Cost.Function costFunction){
        //System.out.println("Which activate function is this?"+(activationFunction== Neural_Network.Functions.Activation.Functions.ReLUFunction)+" "+Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.ReLUFunction));
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.ReLUFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.ReLUDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.sigmoidFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.sigmoidDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.tanHFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.tanHDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.softMaxFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.softMaxDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.logSoftMaxFunction)){
            this.activationFunction = activationFunction;
            System.out.println("no derivative for logSoftMax!!");
            this.activationDerivative = Derivatives.softMaxDerivative;
        }else {
            System.out.println("Which activate function is this?"+(activationFunction== Neural_Network.Functions.Activation.Functions.ReLUFunction));
        }
        setActivationO(activationFunctionO);

        if(Objects.equals(costFunction, Neural_Network.Functions.Cost.Functions.cceFunction)){
            this.costFunction = costFunction;
            this.costDerivative = Neural_Network.Functions.Cost.Derivatives.ccaDerivative;
        }
        else if(Objects.equals(costFunction, Neural_Network.Functions.Cost.Functions.mseFunction)){
            this.costFunction = Neural_Network.Functions.Cost.Functions.mseFunction;
            this.costDerivative = Neural_Network.Functions.Cost.Derivatives.mseDerivative;
        }
        else if(Objects.equals(costFunction, Neural_Network.Functions.Cost.Functions.bceFunction)){
            this.costFunction = Neural_Network.Functions.Cost.Functions.bceFunction;
            this.costDerivative = Neural_Network.Functions.Cost.Derivatives.bcaDerivative;
        }
        else{
            System.out.println("Which cost function is this?");
        }
    }

    public void setActivationO(Function activationFunction){
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.ReLUFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.ReLUDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.sigmoidFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.sigmoidDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.tanHFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.tanHDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.softMaxFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.softMaxDerivative;
        }
        else if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.logSoftMaxFunction)){
            this.activationFunctionO = activationFunction;
            System.out.println("no derivative for logSoftMax!!");
            this.activationDerivativeO = Derivatives.softMaxDerivative;
        }else{
            System.out.println("Which activate function is this?");
        }
    }
}
