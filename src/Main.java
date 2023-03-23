import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // UPDATE recommendation > lambda
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new UIgridMaker(); //sets UIgridMaker

            //display UIgridMaker = frame class
            frame.setSize(550, 600);
            frame.setVisible(true);
        });
    }
}