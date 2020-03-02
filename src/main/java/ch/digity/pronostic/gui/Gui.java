package ch.digity.pronostic.gui;

import static java.lang.Integer.parseInt;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import ch.digity.pronostic.Executer;

public class Gui {

    private File file;
    private JLabel fileLabel = new JLabel();
    private JTextField dayFrom = new JTextField("0");
    private JTextField dayTo = new JTextField("0");
    private JLabel statusMessage = new JLabel();

    public static void main(String[] args) {
        new Gui().launch();
    }

    private void launch() {
        JFrame frame = new JFrame("Pronostic calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(getChooseFile(frame));
        container.add(fileLabel);
        container.add(new JLabel("Extra statistics tab"));
        container.add(new JLabel("from day"));
        container.add(dayFrom);
        container.add(new JLabel(" to day"));
        container.add(dayTo);
        container.add(getCalculateButton());
        container.add(statusMessage);
        frame.setVisible(true);
    }

    private JButton getCalculateButton() {
        JButton button = new JButton("Process");
        button.addActionListener(e -> {
            if (file != null) {
                try {
                    String execute = new Executer().execute(file, parseInt(dayFrom.getText()), parseInt(dayTo.getText()));
                    statusMessage.setText("<html>" + execute.replaceAll("\n", "<br/>") + "</html>");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                fileLabel.setText("Please choose a file to process!");
            }
        });
        return button;
    }

    private JButton getChooseFile(JFrame frame) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose the ods file for the pronostics");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Open Office Sheets", "ods");
        jfc.addChoosableFileFilter(filter);

        JButton button = new JButton("Choose file");
        button.addActionListener(e -> {
            int result = jfc.showOpenDialog(frame);
            if (JFileChooser.APPROVE_OPTION == result) {
                file = jfc.getSelectedFile();
                fileLabel.setText(file.getAbsolutePath());
                System.out.println(file.getAbsolutePath());
            }
        });
        return button;
    }
}
