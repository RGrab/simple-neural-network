import java.util.Random;
import java.lang.Math;
import Jama.Matrix;

public class NeuralNetwork{

  int inputNodes,
    hiddenNodes,
    outputNodes;

  // Controlls how much the newtwork can chainge between iterations.
  float learningRate;

  Random randGen = new Random();

  // Holds the weights inbetween layers.
  Matrix inputWeights,hiddenWeights;

  // Used to initialize the neural network size, and weights.
  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes,float learningRate){
    // Initialize size.
    this.inputNodes = new Integer(inputNodes);
    this.hiddenNodes = new Integer(hiddenNodes);
    this.outputNodes = new Integer(outputNodes);
    this.learningRate = new Float(learningRate);

    // Initialize weight matrix.
    inputWeights = new Matrix(hiddenNodes,inputNodes);
    hiddenWeights = new Matrix(outputNodes,hiddenNodes);

    // Generate random weights for inputWeights.
    for(int i = 0; i < hiddenNodes; i++){
      for(int j =  0; j < inputNodes; j++){
        inputWeights.set(i,j,randGen.nextGaussian()*Math.pow(hiddenNodes,-0.5));
      }
    }

    // Generate random weights for hiddenWeights.
    for(int i = 0; i < outputNodes; i++){
      for(int j = 0; j < hiddenNodes; j++){
        hiddenWeights.set(i,j,randGen.nextGaussian()*Math.pow(outputNodes,-0.5));
      }
    }
  }

  // Train the nural network.
  public void train(){

  }

  // Generates a guess of what digit was drawn.
  public Matrix generateGuess(Matrix inputs){
      // apply weights to input data to be stored in inputSignal.
      Matrix inputSignal = inputWeights.times(inputs);

      //normalize values for inputSignal.
      for(int i = 0; i < inputSignal.getRowDimension(); i++){
        inputSignal.set(i,0,sigmoid(inputSignal.get(i,0)));
      }

      // apply weights to inputSignal to be stored in hiddenSignal.
      Matrix hiddenSignal = hiddenWeights.times(inputSignal);

      //normalize data in hiddenSignal.
      for(int i = 0; i < hiddenSignal.getRowDimension(); i++){
        hiddenSignal.set(i,0,sigmoid(hiddenSignal.get(i,0)));
      }

      return hiddenSignal;

  }

  // Also known as the activation function.
  private double sigmoid(double input){
    return 1/(1 + Math.exp(-input));
  }

}
