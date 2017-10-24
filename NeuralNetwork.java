import java.util.Random;
import java.lang.Math;
import Jama.Matrix;

public class NeuralNetwork{

  private final int inputNodes,
    hiddenNodes,
    outputNodes;

  private double learningRate;

  private Matrix weightInputHidden,
    weightHiddenOutput;

  private Random random = new Random();

  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes, double learningRate){
    this.inputNodes = inputNodes;
    this.hiddenNodes = hiddenNodes;
    this.outputNodes = outputNodes;
    this.learningRate = learningRate;

    weightInputHidden = randomWeightMatrix(hiddenNodes, inputNodes);
    weightHiddenOutput = randomWeightMatrix(outputNodes, hiddenNodes);

  }

  public void train(Matrix inputs, Matrix targets){

    //inputs = inputs.transpose();
    //targets = targets.transpose();

    //feed inputs into hidden layer.
    Matrix hiddenInput = weightInputHidden.times(inputs);
    //calculate outputs of hidden layer.
    Matrix hiddenOutput = activationFunction(hiddenInput);

    //feed hidden layer outputs into output layer.
    Matrix outputInput = weightHiddenOutput.times(hiddenOutput);
    //calculate outputs of output layer
    Matrix outputOutput = activationFunction(outputInput);

    //calulate output error. (targets - actuall)
    Matrix outputError = targets.minus(outputOutput);
    // apply weightHiddenOutput to output error.
    Matrix hiddenError = weightHiddenOutput.transpose().times(outputError);

    //update weightHiddenOutput.
    weightHiddenOutput.plusEquals(
      (
        outputError.arrayTimes(outputOutput)
        .arrayTimes(
          new Matrix(outputError.getRowDimension(), outputError.getColumnDimension(), 1)
          .minus(outputOutput))
      )
      .times(hiddenOutput.transpose())
      .times(learningRate)
    );

    weightInputHidden.plusEquals(
      (
        hiddenError.arrayTimes(hiddenOutput)
        .arrayTimes(
          new Matrix(hiddenError.getRowDimension(), hiddenError.getColumnDimension(), 1)
          .minus(hiddenOutput))
      )
      .times(inputs.transpose())
      .times(learningRate)
    );
  }

  // Generates a guess of what digit was drawn.
  public Matrix generateGuess(Matrix inputs){
    //feed inputs into hidden layer.
    Matrix hiddenInput = weightInputHidden.times(inputs);
    //calculate outputs of hidden layer.
    Matrix hiddenOutput = activationFunction(hiddenInput);

    //feed hidden layer outputs into output layer.
    Matrix outputInput = weightHiddenOutput.times(hiddenOutput);
    //calculate outputs of output layer
    Matrix outputOutput = activationFunction(outputInput);

      return outputOutput;

  }

  /**
  *activationFunction (sigmoid)
  *
  *@param input : input to be normalized
  *@return Matrix : input normalized
  */
  private Matrix activationFunction(Matrix input){
    for(int i = 0; i < input.getRowDimension(); i++){
      for(int j = 0; j < input.getColumnDimension(); j++){
        input.set(i, j, 1/(1 + Math.exp(- input.get(i, j))));
      }
    }
    return input;
  }

  /**
  *randomWeightMatrix
  *
  *@param row
  *@param col
  *@return row by col matrix
  */
  private Matrix randomWeightMatrix(int row, int col){
    Matrix retMatrix = new Matrix(row, col);
    for(int i = 0; i < retMatrix.getRowDimension(); i++){
      for(int j = 0; j < retMatrix.getColumnDimension(); j++){
        retMatrix.set(i, j, random.nextGaussian() * Math.pow(hiddenNodes, -0.5));
      }
    }
    return retMatrix;
  }
}
