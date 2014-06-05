package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.ProcessBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;


    // Position and size of the window frame
    private static final int FRAME_X_POSITION = 200, FRAME_Y_POSITION = 100,
            FRAME_WIDTH = 400, FRAME_HEIGHT = 300;


    private JLabel text; // Tells user what to do
    private String DESCRIPTION = "OS Detected: " + System.getProperty("os.name");


    // Position and size of the description label
    private static final int LABEL_TOP_LEFT_X = 30, // pixels from left edge of window
            LABEL_TOP_LEFT_Y = 0, // pixels from top edge of window
            LABEL_WIDTH = FRAME_WIDTH - 2 * 30, // pixels wide
            LABEL_HEIGHT = 30;   // pixels high



    /**
     * RectangleGUI default constructor
     */
    public GUI() {
        super("Java Path Tool by Naquadah");
        setUpWindow();
    }

    /**
     * setUpWindow - add all the doo-dads we need in the window.
     */
    private void setUpWindow() {
        setBounds(FRAME_X_POSITION, FRAME_Y_POSITION, FRAME_WIDTH, FRAME_HEIGHT);

        // Without the next line, the program is still running even if the window is "closed"
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setLayout(new java.awt.GridLayout(3, 1));


        text = new JLabel(DESCRIPTION);
        text.setBounds(LABEL_TOP_LEFT_X, LABEL_TOP_LEFT_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(text);


        JButton pathButton = new JButton("Set Java Path");

        pathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String processOutput = runProcess("where java");

                //Print out the process's output (DEBUG)
                String os = System.getProperty("os.name");

                System.out.println(processOutput);
                System.out.println(os);

                //Alert OS name + process's output (DEBUG, sorta?)
                if(os.equals("Mac OS X")){
                    javax.swing.JOptionPane.showMessageDialog(null, "OS X y u do dis");
                }

                if(!processOutput.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, ("You already have Java: " + processOutput));
                }

                if(os.equals("Windows 8")){
                    javax.swing.JOptionPane.showMessageDialog(null, "Windows 8, lel");
                }

                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "Path is not set yet, setting...");

                }
            }
        });


        add(pathButton);
    }

    public String runProcess(String command){

        //Split the command into separate strings, as required by ProcessBuilder
        String[] commands = command.split(" ");

        //Create the processbuilder, run "Which java" for now, will change to OS-specific later
        ProcessBuilder pb = new ProcessBuilder(commands);
        //TODO: Linux

        Process p;
        String processOutput = "";

        //Do the BufferedReader stuff to save the output into a string, result
        try {
            p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ( (line = br.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            processOutput = builder.toString();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println(processOutput);
        return processOutput;
    }

    public void actionPerformed(ActionEvent arg0) {

    }

    public void paint(java.awt.Graphics graphics) {
        super.paint(graphics);
    }


    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}