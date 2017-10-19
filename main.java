import javax.swing.JFrame;

public class main {
//home/ryan/java-jar-packages/Jama-1.0.3.jar

  public static int frameWidth = 600;
  public static int frameHeight = 400;
  private static int MnistWidth = 28;
  private static int MnistHeight = 28;
  private static PaintPanel paintPannel;

  public static void main(String[] args){
    MnistData mnistData = new MnistData("mnist-data/mnist_0-9.csv");
    JFrame frame = new JFrame("Hello");
    paintPannel = new PaintPanel(mnistData.getDataByIndex(5));

    frame.setSize(frameWidth,frameHeight);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.add(paintPannel);
    frame.setVisible(true);
  }
}
