import java.util.Random;
import java.lang.Math;
import Jama.Matrix;

public class NeuralNetwork{

  //nodes at each layer.
  private final int inputNodes,
    hiddenNodes,
    outputNodes;

  //how much the network can change during training.
  private double learningRate;

  private Matrix weightInputHidden,
    weightHiddenOutput;

  private Random random = new Random();
  /**
  * creates NeuralNetwork.
  *
  *@param int inputNodes
  *@param int hiddenNodes
  *@param int outputNodes
  *@param double learningRate
  */
  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes, double learningRate){
    this.inputNodes = inputNodes;
    this.hiddenNodes = hiddenNodes;
    this.outputNodes = outputNodes;
    this.learningRate = learningRate;

    weightInputHidden = randomWeightMatrix(hiddenNodes, inputNodes);
    weightHiddenOutput = randomWeightMatrix(outputNodes, hiddenNodes);

  }

  /**
  * trains NeuralNetwork.
  *
  *@param Matrix inputs : one line of data.
  *@param Matrix targets : target matrix (what output should look like).
  */
  public void train(Matrix inputs, Matrix targets){

    Matrix hiddenOutput = generateHiddenOutput(inputs);

    Matrix outputOutput = generateOutputOutput(hiddenOutput);

    //calulate output error. (targets - actuall)
    Matrix outputError = targets.minus(outputOutput);
    // apply weightHiddenOutput to output error.
    Matrix hiddenError = weightHiddenOutput.transpose().times(outputError);

    backwardPropagation(weightHiddenOutput, outputError, outputOutput, hiddenOutput);
    backwardPropagation(weightInputHidden, hiddenError, hiddenOutput, inputs);
  }


  /**
  * feeds errors through layers backward to update layer weights.
  *
  *@param Matrix weightMatrix : Matrix to be updated.
  *@param Matrix error
  *@param Matrix from : starting layer.
  *@param Matrix to : ending layer.
  */
  private void backwardPropagation(Matrix weightMatrix, Matrix error, Matrix from, Matrix to){
    weightMatrix.plusEquals(
      (
        error.arrayTimes(from)
        .arrayTimes(
          new Matrix(error.getRowDimension(), error.getColumnDimension(), 1)
          .minus(from))
      )
      .times(to.transpose())
      .times(this.learningRate)
    );
  }

  /**
  * feeds inputs through hidden and output layers to get guess.
  *
  *@param Matrix inputs
  *@return Matrix
  */
  public Matrix generateGuess(Matrix inputs){

    Matrix hiddenOutput = generateHiddenOutput(inputs);

      return generateOutputOutput(hiddenOutput);

  }

  /**
  * calculate outputs of output layer.
  *
  *@param input : input to be normalized
  *@return Matrix.
  */
  private Matrix generateHiddenOutput(Matrix inputs){
    //feed inputs into hidden layer.
    Matrix hiddenInput = weightInputHidden.times(inputs);

      return activationFunction(hiddenInput);

  }

  /**
  * calculate outputs of output layer.
  *
  *@param input : input to be normalized
  *@return Matrix
  */
  private Matrix generateOutputOutput(Matrix hiddenOutput){
    //feed hidden layer outputs into output layer.
    Matrix outputInput = weightHiddenOutput.times(hiddenOutput);

      return activationFunction(outputInput);

  }

  /**
  * activationFunction (sigmoid)
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
  * randomWeightMatrix
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
