import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserDraw extends JPanel implements MouseMotionListener {

    final static int scale = 10;
    final static int pxCount = 28;
    final static int width = pxCount * scale;
    final static int height = pxCount * scale;
    BufferedImage img;

    UserDraw() {

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
        setPreferredSize(new Dimension(width, height));
        this.addMouseMotionListener(this);

      }

    @Override
    public void mouseDragged(MouseEvent e) {
      Graphics g = img.getGraphics();

      g.setColor(Color.black);
      Point p = e.getPoint();
      g.fillOval(p.x, p.y, 15, 15);

      g.dispose();
      repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, null);
    }

    /**
    * set whole image to white
    */
    public void clear(){
      Graphics g = img.getGraphics();
      g.setColor(Color.white);
      g.fillRect(0, 0, img.getWidth(null), img.getHeight(null));
      g.dispose();
      repaint();
    }

    /**
    * converts img into a string that resembles mnist data.
    *
    *@return String : converted image.
    */
    public String getImageString(){
      String mnistString = "";
      for(int i = 0; i < this.width; i += scale){
        for(int j = 0; j < this.height; j += scale){

          mnistString += ",";
          mnistString += String.valueOf(scalePixel(j, i));

      }
    }
      return mnistString.substring(1);
  }

    /**
    * scales the img down according to scale value.
    * scaled pixels are the average darkness of the pixels combined.
    * this method creates one pixel at a time.
    *
    *@param int startHeight :
    *@param int startWidth :
    *@param int : darkness of scaled pixel (225 - white, 0 - black)
    */
    private int scalePixel(int startHeight, int startWidth){


    int totalRed = 0;
    int totalGreen = 0;
    int totalBlue = 0 ;

    for(int i = startWidth; i < startWidth + this.scale; i++){
      for(int j = startHeight; j < startHeight + this.scale; j++){

        Color color =  new Color(img.getRGB(j, i));
        totalRed += color.getRed();
        totalGreen += color.getGreen();
        totalBlue += color.getBlue();

      }
    }
    return (Math.abs((((totalRed + totalGreen + totalBlue)/100)/3) - 255));
  }
}
