import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class main {

  public static void main(String[] args){
    System.out.println("===============================");
    System.out.println("|~~  Simple Neural Network  ~~|");
    System.out.println("===============================");

        Brain brain = new Brain();

        // user drawing input.
        UserDraw userDraw = new UserDraw();
        // set background to white.
        userDraw.clear();

        // displays number guessed from user drawing.
        NumberDraw numberDraw = new NumberDraw();
        // set background to white.
        numberDraw.clear();


        JFrame frame = new JFrame("Simple Neural Network");

        // houses submitBtn and clearBtn.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));


        // used to submit drawings to be evaluated by neuralNetwork.
        JButton submitBtn = new JButton("Submit");
        submitBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitBtn.getMinimumSize().height));
        submitBtn.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){

                numberDraw.clear();
                numberDraw.paintNumber(brain.userTestNeural(userDraw.getImageString()));
          }
        });

        // used to clear the UserDraw and numberDraw pannels.
        JButton clearBtn = new JButton("Clear");
        clearBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, clearBtn.getMinimumSize().height));
        clearBtn.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){

            userDraw.clear();
            numberDraw.clear();
          }
        });

        //add componets to frame.
        buttonPanel.add(submitBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(clearBtn);
        buttonPanel.add(Box.createVerticalGlue());
        frame.add(userDraw , BorderLayout.LINE_START);
        frame.add(buttonPanel , BorderLayout.CENTER);
        frame.add(numberDraw , BorderLayout.LINE_END);
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //displaying frame.
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
