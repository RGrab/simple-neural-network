
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PaintPanel extends JPanel{

  String mnistData;

  //default constructor.
  public PaintPanel(){
    repaint();
  }

  //constructor with mnistData string.
  public PaintPanel(String mnistData){
    this.mnistData = mnistData;
    repaint();
  }


  public void paintComponent(Graphics g){
    //paint panel White
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, Brain.frameWidth, Brain.frameHeight);

    //if image is available draw it.
    if(mnistData != null){
      //split csv string into diffrent elements.
      String[] splitMnistData = this.mnistData.split(",");

      // loop through each pixel of the image (28x28).
      for(int i = 0; i < 28; i++){
        for(int j = 0; j < 28; j++){

          // get saturation of current pixel.
          int saturation = 255 - Integer.parseInt(splitMnistData[i * 28 + j + 1]);

          //apply saturation
          Color current = new Color(saturation, saturation, saturation);
          g.setColor(current);
          g.fillRect(j*10, i*10, 10, 10);
        }
      }
    }
  }

}
