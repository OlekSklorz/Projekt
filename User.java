package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
class DialogWindow extends JPanel{
    private JTextField username;
    private JPasswordField password;
    private JPasswordField passwordConfirmation;
    private JDialog dialog;
    private JButton okButton;
    private JButton cancelButton;
    public DialogWindow(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        panel.add(username = new JTextField(""));
        panel.add(new JLabel("Password:"));
        panel.add(password = new JPasswordField(""));
        panel.add(new JLabel("Confirm password: "));
        panel.add(passwordConfirmation = new JPasswordField(""));
        add(panel, BorderLayout.CENTER);
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                dialog.setVisible(false);
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                dialog.setVisible(false);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public void showDialog(Component parent, String title){
        Frame owner = null;
        if(parent instanceof Frame) owner = (Frame) parent;
        else owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
        if(dialog == null || dialog.getOwner() != owner)
        {
            dialog = new JDialog(owner, true);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }
        dialog.setTitle(title);
        dialog.setVisible(true);
    }
}
public class User { 
    private String nick = "Anonim";
    private static DialogWindow dialog;
    /*public String signIn(){
        
    }*/ 
    public static void signUp(){
        dialog = new DialogWindow();
        dialog.showDialog(new MainMenuFrame(), "Registration");
    }
    
}
