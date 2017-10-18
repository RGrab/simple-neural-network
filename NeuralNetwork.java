import java.util.Random;
import java.lang.Math;
import Jama.Matrix;

public class NeuralNetwork{

  int inputNodes,
    hiddenNodes,
    outputNodes;

  // Controlls how much the newtwork can chainge between iterations.
  double learningRate;

  Random randGen = new Random();

  // Holds the weights inbetween layers.
  Matrix inputWeights,hiddenWeights;

  // Used to initialize the neural network size, and weights.
  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes,double learningRate){
    // Initialize size.
    this.inputNodes = new Integer(inputNodes);
    this.hiddenNodes = new Integer(hiddenNodes);
    this.outputNodes = new Integer(outputNodes);
    this.learningRate = new Double(learningRate);

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

  // Train the neural network.
  public void train(Matrix inputs, Matrix targets){

    // apply weights to inputs using matrix multiplication.
    Matrix inputSignal = inputWeights.times(inputs);

    //normalize values for inputSignal.
    for(int i = 0; i < inputSignal.getRowDimension(); i++){
      inputSignal.set(i,0,sigmoid(inputSignal.get(i,0)));
    }

    // apply weights to hiddenSignal using matrix multiplication.
    Matrix hiddenSignal = hiddenWeights.times(inputSignal);

    //normalize values for inputSignal.
    for(int i = 0; i < hiddenSignal.getRowDimension(); i++){
      hiddenSignal.set(i,0,sigmoid(inputSignal.get(i,0)));
    }

    // calculate the error.
    // error is (target - actuall).
    Matrix outputError = hiddenSignal.minus(hiddenSignal);

    //hidden error is outputError distributed by weights.
    Matrix hiddenError = hiddenWeights.times(outputError);


    //update the weights for the links between the hidden and output layers.
    //calulates and applys the change in weight values according to error and weight.
    hiddenWeights.plusEquals(
      outputError.arrayTimes(hiddenSignal)
      .arrayTimes(oneMinus(hiddenSignal))
      .times(inputSignal.transpose())
      .times(learningRate)
    );

    //update the weights for the links between the input and hidden layers.
    //calulates and applys the change in weight according to error and weight.
    inputWeights.plusEquals(
      hiddenError.arrayTimes(inputSignal)
      .arrayTimes(oneMinus(inputSignal))
      .times(inputs.transpose())
      .times(learningRate)
    );

  }

  // Generates a guess of what digit was drawn.
  public Matrix generateGuess(Matrix inputs){
      // apply weights to inputs using matrix multiplication.
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

  //returns a Matrix that is eaqual to 1 - input.
  private Matrix oneMinus(Matrix input){

    for(int i = 0 ; i < input.getRowDimension() ; i++){
      for(int j = 0 ; j < input.getColumnDimension() ; j++){
        input.set(i,j,1 - input.get(i,j));
      }
    }

    return input;
  }

}
