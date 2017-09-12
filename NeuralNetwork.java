import java.util.Random;
import java.lang.Math;
import Jama.Matrix;

public class NeuralNetwork{

  int inputNodes,
    hiddenNodes,
    outputNodes;

  float learningRate;

  Random randGen = new Random();

  Matrix inputWeights, hiddenWeights;

  //used to initialize the neural network size, and weights.
  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes,float learningRate){
    //initialize size.
    this.inputNodes = new Integer(inputNodes);
    this.hiddenNodes = new Integer(hiddenNodes);
    this.outputNodes = new Integer(outputNodes);
    this.learningRate = new Float(learningRate);

    //initialize weight matrix.
    inputWeights = new Matrix(hiddenNodes,inputNodes);
    hiddenWeights =  new Matrix(outputNodes,hiddenNodes);

    //generate random weights for inputWeights
    for(int i = 0; i < hiddenNodes; i++){
      for(int j =  0; j < inputNodes; j++){
        inputWeights.set(i,j,randGen.nextGaussian()*Math.pow(hiddenNodes, -0.5));
        System.out.print(inputWeights.get(i,j) + ", ");
      }
      System.out.print("\n ");
    }

    System.out.print("\n ");

    // generate random weights for hiddenWeights
    for(int i = 0; i < outputNodes; i++){
      for(int j = 0; j < hiddenNodes; j++){
        hiddenWeights.set(i,j,randGen.nextGaussian()*Math.pow(outputNodes, -0.5));
        System.out.print(hiddenWeights.get(i,j) + ", ");
      }
      System.out.print("\n ");
    }


  }
//used to train the nural network
  public void train(){

  }
//used to generate a photo from the trained neural network.
  public void generate_guess(){

  }
}
