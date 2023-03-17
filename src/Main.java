import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new UIgridMaker(); //sets UIgridMaker as frame

                //display UIgridMaker = frame class
                frame.setSize(550, 500); //window
                frame.setVisible(true);
            }
        });
    }
}