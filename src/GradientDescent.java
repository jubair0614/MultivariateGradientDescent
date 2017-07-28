import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jubair on 7/25/17.
 */
public class GradientDescent {
    private double[][] trainingData;

    public double[] parameters;
    private double learningRate;

    public GradientDescent(){
    }

    public void initializeParameters() {
        int numOfFeatures = this.trainingData[0].length;    // number of features in training data
        this.parameters = new double[numOfFeatures];    // number of parameters is one more than number of features
    }

    public double predict(double[] inp) {
        return predict(inp, this.parameters);
    }
    private double predict(double[] inp, double[] parameters){
        double[] features = concatenate(new double[]{1}, inp);

        double prediction = 0;
        for(int j = 0; j < features.length; j++) {
            prediction += parameters[j] * features[j];
        }

        return prediction;
    }

    public void train(){
        readjustLearningRate();

        double costFunctionDelta = Math.abs(costFunction() - costFunction(iterateGradient()));

        while(costFunctionDelta > 0.0000000001) {
            /*System.out.println("Old cost function : " + costFunction());
            System.out.println("New cost function : " + costFunction(iterateGradient()));
            System.out.println("Delta: " + costFunctionDelta);*/

            parameters = iterateGradient();
            costFunctionDelta = Math.abs(costFunction() - costFunction(iterateGradient()));
            readjustLearningRate();
        }
    }

    private double[] iterateGradient() {
        double[] nextParameters = new double[parameters.length];
        // Calculate parameters for the next iteration
        for(int r = 0; r < parameters.length; r++) {
            nextParameters[r] = parameters[r] - learningRate * partialDerivative(r);
        }

        return nextParameters;
    }
    private double partialDerivative(int index) {
        double sum = 0;
        for(int i = 0; i < trainingData.length; i++) {
            int indexOfResult = trainingData[i].length - 1;
            double[] input = Arrays.copyOfRange(trainingData[i], 0, indexOfResult);
            sum += ((predict(input) - trainingData[i][indexOfResult]) * trainingData[i][index]);
        }

        return sum/trainingData.length ;
    }
    private void readjustLearningRate() {

        while(costFunction(iterateGradient()) > costFunction()) {
            // If the cost function of the new parameters is higher that the current cost function
            // it means the gradient is diverging and we have to adjust the learning rate
            // and recalculate new parameters
            //System.out.print("Learning rate: " + learningRate + " is too big, readjusted to: ");
            learningRate = learningRate/2;
            //System.out.println(learningRate);
        }
        // otherwise we are taking small enough steps, we have the right learning rate
    }

    public double[][] getTrainingData() {
        return this.trainingData;
    }
    public void setTrainingData(double[][] data) {
        this.trainingData = data;
    }

    public double[] getParameters() {
        return parameters;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    /**              1      m           i     i  2
     *   J(theta) = ----- * SUM( h     (x ) - y  )
     *               2*m    i=1   theta
     */
    public double costFunction() {
        return costFunction(this.parameters);
    }
    private double costFunction(double[] parameters) {
        int m = trainingData.length;
        double sum = 0;

        for(int i = 0; i < m; i++) {
            int indexOfResult = trainingData[i].length - 1;
            double[] input = Arrays.copyOfRange(trainingData[i], 0, indexOfResult);
            sum += Math.pow(predict(input, parameters) - trainingData[i][indexOfResult], 2);
        }

        double factor = 1D/(2*m);
        return factor * sum;
    }

    private double[] concatenate(double[] a, double[] b) {
        int size = a.length + b.length;

        double[] concatArray = new double[size];
        int index = 0;

        for(double d : a) {
            concatArray[index++] = d;
        }
        for(double d : b) {
            concatArray[index++] = d;
        }

        return concatArray;
    }

}
