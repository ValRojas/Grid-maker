import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UIgridMaker extends JFrame{
    private JPanel background;
    private JButton ImportButton;
    private JLabel imageLabel;
    private JTextField RowsValue;
    private JButton gridITButton;
    private JTextField ColValue;

    public UIgridMaker(){
        super("My grid app"); //title
        setContentPane(background);//check ?
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app when close

        ImportButton.addActionListener(new ActionListener() {
            @Override // "overriding the behavior/functionality of the method in the class"

            public void actionPerformed(ActionEvent e) {

                //allows the user to select an *image* file
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png");
                chooser.setFileFilter(filter);

                // allows select an image and waits for the user to select a file
                int result = chooser.showOpenDialog(null);

                //result = store the return value of the showOpenDialog method
                if (result == JFileChooser.APPROVE_OPTION) { //user has selected an image

                    File selectedFile = chooser.getSelectedFile();
                    // Pass the selected file to the ImageImporter class
                    ImportImage importer = new ImportImage(selectedFile, imageLabel); //selectedFile is passed to the class ImportImage
                    importer.importImage(); //calls
                }
            }
        });
    }
}
