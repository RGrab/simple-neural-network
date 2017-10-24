import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Arrays;
import Jama.Matrix;

public class Brain {

  //define JFrame window size.
  public static int frameWidth = 600;
  public static int frameHeight = 400;

  //Mnist data is a 28x28 pixel image.
  private static int MnistWidth = 28;
  private static int MnistHeight = 28;

  // number of input, hidden, and output nodes.
  private int inputNodes = 784;
  private int hiddenNodes = 150;
  private int outputNodes = 10;

  //how quickly the network can change.
  private double learningRate = 0.2;

  //creating instance of neural network
  NeuralNetwork neuralNetwork = new NeuralNetwork(inputNodes, hiddenNodes, outputNodes, learningRate);

  private static PaintPanel paintPannel;

  private double accuracy;

  //constructor method.
  public Brain(){

    //load training and testing data
    MnistData mnistTrainingData = new MnistData("./mnist-data/mnist_train.csv");
    MnistData mnistTestingData = new MnistData("./mnist-data/mnist_test.csv");

    //train neural network
    for(int i = 0; i < mnistTrainingData.dataSize(); i++){
      //converted and normalized input values
      double[] doubleInputs = scaleInput(convertInput(mnistTrainingData.getDataByIndex(i)));

      //create one dementional matrix
      Matrix inputs = new Matrix(Arrays.copyOfRange(doubleInputs, 1, doubleInputs.length), doubleInputs.length - 1 );

      //train network
      neuralNetwork.train(inputs, createTargetMatrix(doubleInputs[0]));

    }

    //test neural network
    accuracy = testNeural(mnistTestingData);
    System.out.println("Accuracy : " + accuracy);

    //setting up JFrame options.
    paintPannel = new PaintPanel(mnistTrainingData.getDataByIndex(0));
    JFrame frame = new JFrame("Sample-Neural-Network");
    frame.setSize(frameWidth,frameHeight);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.add(paintPannel);
    frame.setVisible(true);
  }

  private double testNeural(MnistData mnistTestingData){

    int totalCorrect = 0;

    for(int i = 0; i < mnistTestingData.dataSize(); i++){
      //converted and normalized input values
      double[] doubleInputs = scaleInput(convertInput(mnistTestingData.getDataByIndex(i)));

      //create one dementional matrix
      Matrix inputs = new Matrix(Arrays.copyOfRange(doubleInputs, 1, doubleInputs.length), doubleInputs.length - 1 );

      //train network
      Matrix guessMatrix = neuralNetwork.generateGuess(inputs);
        if(guess(guessMatrix) == (int) doubleInputs[0]){
          totalCorrect += 1;
        }
    }

    return (double) totalCorrect/(mnistTestingData.dataSize()-1);
  }

  // scales data betweeen .01 and 1
  private double[] scaleInput(double[] mnistDataDouble){

    //value at index 0 contains target output and should not be changed.
    for(int i = 1; i < mnistDataDouble.length; i++){
      //scaled output = (value - min)/(max - min) + offset
      mnistDataDouble[i] = (mnistDataDouble[i] - 0)/(258 - 0) + .01;
    }

    return mnistDataDouble;
  }

  //convert csv string into array of doubles.
  private double[] convertInput(String mnistDataString){

    String[] splitMnistData = mnistDataString.split(",");
    double[] mnistDataDouble = new double[splitMnistData.length];

    for(int i = 0; i < splitMnistData.length; i++){
      mnistDataDouble[i] = Double.parseDouble(splitMnistData[i]);
    }

    return mnistDataDouble;

  }

  //returns target matrix.
  private Matrix createTargetMatrix(double target){
    //create an array of length ouputNodes initialized to .01
    double[] targetArray = new double[this.outputNodes];
    Arrays.fill(targetArray, .01);

    //index of target value = to .99
    targetArray[(int) target] = .99;

    return new Matrix(targetArray, targetArray.length);
  }

  private int guess(Matrix guessMatrix){
    //convert matrix into 1d array
    double[] guessArray = guessMatrix.getRowPackedCopy();
    double currentMax = guessArray[0];
    int guess = 0;
    for(int i = 1; i < guessArray.length; i++){
      if(guessArray[i] >= currentMax){
        guess = i;
        currentMax = guessArray[i];
      }
    }
    return guess;
  }
}
