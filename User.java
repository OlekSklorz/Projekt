package game;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
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
                String nick = getUsername();
                char[] tempPassword = getPassword();
                char[] tempPasswordConfirmation = getPasswordConfirmation();
                if(!nick.equals("") && tempPassword.length != 0 && tempPasswordConfirmation.length != 0 && Arrays.equals(tempPassword, tempPasswordConfirmation))
                {
                    if(!isExists(nick)){
                        writeToFile(nick, tempPassword);
                        dialog.setVisible(false);
                    }else{
                        username.setText("Użytkownik istnieje!");
                    }
                }
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
    public String getUsername(){
        return username.getText();
    }
    public char[] getPassword(){
        return password.getPassword();
    }
    public char[] getPasswordConfirmation(){
        return passwordConfirmation.getPassword();
    }
    private boolean isExists(String nick){
        File directory = new File("C:\\Game");
        if(directory.exists()){
            BufferedReader file = null;
            try{
                file = new BufferedReader(new FileReader("C:\\Game\\Users.txt"));
                String line = file.readLine();
                line = file.readLine();
                String name = "";
                while(line != null){
                    name = line.substring(0, line.indexOf(" "));
                    if(name.equals(nick))
                        return true;
                    line = file.readLine();
                }
                return false;
            }catch(IOException e){
            }finally{
                if(file != null){
                    try{
                        file.close();
                    }catch(IOException ex){}
                }
            }
        }
        return false;
    }
    public void writeToFile(String name, char[] p){
        File directory = new File("C:\\Game");
        boolean existenceDirectory = directory.exists();
        Double shift = 0.0;
        if(!existenceDirectory){
            directory.mkdir();
            Random x = new Random();
            shift = (double)(x.nextInt(500) + 1);
        }else{
            BufferedReader fileR = null;
            try{
                fileR = new BufferedReader(new FileReader("C:\\Game\\Users.txt"));
                String hashcode = fileR.readLine();
                int i = 1;
                do
                {
                    shift = (double)i;
                    i++;
                }while(shift.hashCode() != Integer.parseInt(hashcode));
            }catch(IOException e){
            }finally{
                if(fileR != null){
                    try{
                        fileR.close();
                    }catch(IOException ex){}
                }
            }
        }
        String allowedSigns = "";
        for(int i = 0; i < 94; i++){
            allowedSigns += (char)(i + 32);
        }
        for(int i = 0; i < p.length; i++){
            int index = allowedSigns.indexOf(p[i]);
            p[i] = (char)((allowedSigns.charAt((index + (189 % 93)) % allowedSigns.length())));
        }
        PrintWriter fileW = null;
        try{
            fileW = new PrintWriter(new FileWriter("C:\\Game\\Users.txt", true));
            if(!existenceDirectory){
                fileW.println(shift.hashCode());
            }
            fileW.println(name + " " + String.valueOf(p));
        }catch(IOException e){
        }finally{
            if(fileW != null)
                fileW.close();
        }
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
