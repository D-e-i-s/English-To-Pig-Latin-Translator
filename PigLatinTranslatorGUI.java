import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class PigLatinTranslatorGUI
{
    private JPanel mainPanel;
    private JButton convertButton;
    private JButton saveButton;
    private JButton clearTopButton;
    private JButton clearBottomButton;
    private JTextArea textAreaUserInput;
    private JTextArea textAreaConvertedText;
    private JButton insertFileButton;

    // this prevents us from saving unless text has been converted.
    private boolean convertUsed = false;

    private PigLatinTranslatorGUI()
    {

        convertButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String userInput = textAreaUserInput.getText();
                if(!userInput.equals(""))
                {
                    String convertedText = PigLatinTranslator.stringToPigLatin(userInput);
                    textAreaConvertedText.setText(convertedText);
                    convertUsed = true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You should enter the text you want to convert.");
                }

            }
        });

        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!convertUsed)
                {
                    JOptionPane.showMessageDialog(null,"You wouldn't want to save an empty file would you?");
                }
                else
                {
                    String userInput = textAreaUserInput.getText();
                    String convertedText = PigLatinTranslator.stringToPigLatin(userInput);

                    Scanner userSuppliedFilename = new Scanner(System.in);
                    System.out.println("What would you like the filename to be?");
                    String fileName = userSuppliedFilename.nextLine();
                    stringToFile(convertedText, fileName);
                }
            }
        });
        clearTopButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textAreaUserInput.setText("");
            }
        });
        clearBottomButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textAreaConvertedText.setText("");
                convertUsed = false;
            }
        });
        insertFileButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
    }
    public static void main(String[]args)
    {
        JFrame frame = new JFrame("English To Pig Latin");
        frame.setContentPane(new PigLatinTranslatorGUI().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void stringToFile(String text, String fileName)
    {
        try
        {
            File file = new File(fileName);

            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile( );
            }

            FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
            BufferedWriter bw = new BufferedWriter( fw );
            bw.write( text );
            bw.close( );
            //System.out.println("Done writing to " + fileName); //For testing
        }
        catch( IOException e )
        {
            System.out.println("Error: " + e);
            e.printStackTrace( );
        }
    } //End method stringToFile
}
