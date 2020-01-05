import javax.swing.*;
import java.awt.*;

public class LifeGameTester {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Life Game");// set frame
        LifeGamePanel lifeGamePanel = new LifeGamePanel(new LifeMatrix(10)); // initialize panel with square life matrix of size 10*10

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(lifeGamePanel);//adding panel to the frame
        frame.setVisible(true);
        double size =  Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.2; // to set dimension of the frame
        frame.setSize((int) size,(int) size);
        frame.setLocationRelativeTo(null);//centering the frame in the screen
        while (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"go to next generation?")) // while the user choose to go to the next generation
        {
            lifeGamePanel.getLifeMatrix().goToNextGen(); // update the matrix
            lifeGamePanel.repaint();
            /* there is threads issues without this sleep that causes the panel to not update sometimes the GUI as needed*/
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.revalidate();
        }
    }
}
