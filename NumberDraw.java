import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JPanel;

public class NumberDraw extends JPanel{

    final static int scale = 10;
    final static int pxCount = 28;
    final static int width = pxCount * scale;
    final static int height = pxCount * scale;
    private int fontSize = 256;
    BufferedImage img;


    public NumberDraw() {

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
         g.drawImage(img, 0, 0, null);
    }
    /**
    * displays number to img.
    *
    *@param int number : number to display.
    */
    public void paintNumber(int number){
      Graphics g = img.getGraphics();

      g.setFont(new Font("TimesRoman", Font.PLAIN, this.fontSize));
      g.setColor(Color.black);

      String stringNumber = String.valueOf(number);
      FontMetrics fontMetrics = g.getFontMetrics(g.getFont());

      //center string in image.
      int x = (width - fontMetrics.stringWidth(stringNumber)) / 2;
      int y = ((height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

      g.drawString(stringNumber, x, y);

      repaint();
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
}
