
/**
 * Created by jubair on 7/26/17.
 */
public class Main {
    public static void main(String[] args) {
        // for single feature gradient descent test
        FileReader fileReader = new FileReader("input_roomSize_price.txt");

        GradientDescent gradientDescent = new GradientDescent();

        gradientDescent.setTrainingData(fileReader.getData());

        gradientDescent.initializeParameters();
        gradientDescent.setLearningRate(0.01);

        gradientDescent.train();

        double[] parameters = gradientDescent.getParameters();
        for (int i=0; i<parameters.length; i++) {
            System.out.println("Parameter " + i + ": " + parameters[i]);
        }

        System.out.println("PREDICTION: " + gradientDescent.predict(new double[]{1650}));


        // for multiple feature gradient descent test
        fileReader = new FileReader("trainingData.txt");

        gradientDescent = new GradientDescent();

        gradientDescent.setTrainingData(fileReader.getData());

        gradientDescent.initializeParameters();
        gradientDescent.setLearningRate(0.01);

        gradientDescent.train();

        parameters = gradientDescent.getParameters();
        for (int i=0; i<parameters.length; i++) {
            System.out.println("Parameter " + i + ": " + parameters[i]);
        }

        System.out.println("PREDICTION: " + gradientDescent.predict(new double[]{1350, 4}));
    }
}
