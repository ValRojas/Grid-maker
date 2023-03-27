import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIgridMaker extends JFrame{
    private JPanel background;
    private JButton ImportButton;
    private JLabel imageLabel;
    private JTextField RowsValue, ColValue;
    private int rows, cols;
    private JButton gridITButton;
    private BufferedImage image;
    private float imageAspectRatio;

    public UIgridMaker(){
        super("Grid app"); //title
        setContentPane(background);//set content panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app when close

        this.imageLabel.setVerticalAlignment(JLabel.CENTER);
        this.imageLabel.setHorizontalAlignment(JLabel.CENTER);

        ImportButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg");
            chooser.setFileFilter(filter);

            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) { //user has selected an image

                File selectedFile = chooser.getSelectedFile();
                ImportImage importer = new ImportImage(selectedFile);
                image = importer.importImage();
                imageAspectRatio = (float) image.getWidth() / image.getHeight();

                GridOverlay overlay = new GridOverlay(image, 5,Math.round(imageAspectRatio * 5));
                BufferedImage overlayImage = overlay.createOverlay();

                ImageIcon icon = new ImageIcon(overlayImage);
                imageLabel.setIcon(icon); //sets when input empty
            }
        });

        /* S: Handle grid overlay maker*/
        RowsValue.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleValueChange(true);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                handleValueChange(true);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                handleValueChange(true);
            }
        });
        ColValue.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleValueChange(false);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                handleValueChange(false);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                handleValueChange(false);
            }
        });
    }

    private void handleValueChange(boolean isRow) {
        int rows;
        int cols;
        try {
            if (isRow) {
                rows = Integer.parseInt(RowsValue.getText());
                cols = Math.round(((float) image.getWidth() / image.getHeight()) * rows);
            } else {
                cols = Integer.parseInt(ColValue.getText());
                rows = Math.round(((float) image.getHeight() / image.getWidth()) * cols);
            }
        } catch (NumberFormatException ex) {
            rows = 5;
            cols = Math.round(((float) image.getWidth() / image.getHeight()) * rows);
            System.out.println("Input error: " + ex);
        }
        updateOverlay(rows, cols);
    }

    /* E: Handle grid overlay maker*/

    /* S: Return*/
    private void updateOverlay(int rows, int cols) {
        GridOverlay overlay = new GridOverlay(image, rows, cols);
        BufferedImage overlayImage = overlay.createOverlay();
        ImageIcon icon = new ImageIcon(overlayImage);
        imageLabel.setIcon(icon);
    }
    /* E: Return*/
}
