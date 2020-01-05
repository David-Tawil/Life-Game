import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LifeGamePanel extends JPanel{
    private LifeMatrix lifeMatrix; // life matrix object
    private ArrayList<JPanel> cellPanels; // array list to store panels that represent a cell

    /**
     * constructor of life matrix panel
     * @param lifeMatrix life matrix to show in the panel
     */
    public LifeGamePanel(LifeMatrix lifeMatrix) {
        int size = lifeMatrix.size();//get size of matrix to determine how many cells will be in our panel
        super.setLayout(new GridLayout(size,size,5,5));
        this.lifeMatrix = lifeMatrix;
        cellPanels = new ArrayList<>(size*size);
    }

    /**
     * @return lifeMatrix of this panel
     */
    public LifeMatrix getLifeMatrix() {
        return lifeMatrix;
    }

    /**
     * sets each panel cell in the grid according to the state of he given lifeMatrix
     */
    private void setCellPanels(){
        cellPanels.clear();// be sure there is no cell panel at start
        for (int i = 0; i<lifeMatrix.size()*lifeMatrix.size(); i++)
            cellPanels.add(new JPanel()); // adding all cell panels
        for (int row = 0; row < lifeMatrix.size(); row++)// setting each cell panel LIVE or DEAD
            for (int column = 0; column < lifeMatrix.size(); column++)
                if (lifeMatrix.getCellValue(row, column) == LifeMatrix.State.DEAD)
                    cellPanels.get(row * 10 + column).setBackground(Color.WHITE);
                else
                    cellPanels.get(row * 10 + column).setBackground(Color.BLACK);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setCellPanels();//set the cell panels according the life matrix state
        removeAll();//remove all previous cell panels
        for (JPanel cell : cellPanels)
            add(cell);
    }
}
