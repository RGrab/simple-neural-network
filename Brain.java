import javax.swing.JFrame;
import java.util.ArrayList;

public class Brain {

  //define JFrame window size.
  public static int frameWidth = 600;
  public static int frameHeight = 400;

  //Mnist data is a 28x28 pixel image.
  private static int MnistWidth = 28;
  private static int MnistHeight = 28;

  // number of input, hidden, and output nodes.
  private int inputNodes = 784;
  private int hiddenNodes = 387;
  private int outputNodes = 10;
  private double learningRate = 0.3;

  //creating instance of neural network.
  NeuralNetwork neuralNetwork = new NeuralNetwork(inputNodes, hiddenNodes, outputNodes, learningRate);

  private static PaintPanel paintPannel;

  public Brain(){
    MnistData mnistTrainingData = new MnistData("./mnist-data/mnist_train.csv");
    MnistData mnistTestingData = new MnistData("./mnist-data/mnist_test.csv");

    //setting up JFrame options.
    paintPannel = new PaintPanel(mnistTrainingData.getDataByIndex(1000));
    JFrame frame = new JFrame("Sample-Neural-Network");
    frame.setSize(frameWidth,frameHeight);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.add(paintPannel);
    frame.setVisible(true);

    Double[] test= new Double[785];
    test = convertInput(mnistTrainingData.getDataByIndex(1000));
    test = scaleInput(test);
    for(int i = 0; i < test.length; i++){
      System.out.println(test[i]);
    }
  }

  // scales data betweeen .01 and 1
  private Double[] scaleInput(Double[] mnistDataDouble){

    //value at index 0 contains target output and should not be changed.
    for(int i = 1; i < mnistDataDouble.length; i++){
      //scaled output = (value - min)/(max - min)
      mnistDataDouble[i] = (mnistDataDouble[i] - 0)/(258 - 0) + .01;
    }

    return mnistDataDouble;
  }

  //convert csv string into array of doubles.
  private Double[] convertInput(String mnistDataString){

    String[] splitMnistData = mnistDataString.split(",");
    ArrayList<Double> mnistDataDouble = new ArrayList<Double>();

    for(int i = 0; i < splitMnistData.length; i++){
      mnistDataDouble.add(Double.parseDouble(splitMnistData[i]));
    }

    //converting ArrayList to an array.
    Double[] mnistDataDoubleArray = new Double[mnistDataDouble.size()];
    mnistDataDoubleArray = mnistDataDouble.toArray(mnistDataDoubleArray);

    return mnistDataDoubleArray;

  }
}
