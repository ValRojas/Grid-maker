import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIgridMaker extends JFrame{
    private JPanel background;
    private JButton ImportButton;
    private JLabel imageLabel;
    private JTextField RowsValue;
    private JTextField ColValue;
    private int rows;
    private int cols;
    private JButton gridITButton;

    public UIgridMaker(){
        super("Grid app"); //title
        setContentPane(background);//set content panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app when close

        // UPDATE recommendation > lambda
        ImportButton.addActionListener(e -> {

            //allows the user to select an *image* file
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg");
            chooser.setFileFilter(filter);

            // allows select an image and waits for the user to select a file
            int result = chooser.showOpenDialog(null);

            //result = store the return value of the showOpenDialog method
            if (result == JFileChooser.APPROVE_OPTION) { //user has selected an image

                File selectedFile = chooser.getSelectedFile();
                // Pass the selected file to the ImageImporter class

                ImportImage importer = new ImportImage(selectedFile, imageLabel);
                BufferedImage image = importer.importImage(); //sets

                //note: inside DocumentListener

                if (RowsValue.getText().length() > 0) {
                    rows = Integer.parseInt(RowsValue.getText());
                    cols = Math.round(((float) image.getWidth() / image.getHeight()) * rows);
                }
                else if (ColValue.getText().length() > 0) {
                    cols = Integer.parseInt(ColValue.getText());
                    rows = Math.round(((float) image.getHeight() / image.getWidth()) * cols);
                }
                else {
                    rows = 5;
                    cols = Math.round(((float) image.getWidth() / image.getHeight()) * rows);
                }

                //note: inside DocumentListener

                GridOverlay overlay = new GridOverlay(image, rows, cols);
                BufferedImage overlayImage = overlay.createOverlay();

                ImageIcon icon = new ImageIcon(overlayImage);
                imageLabel.setIcon(icon);
            }
        });
    }
}
