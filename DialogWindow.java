package game;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * Obiekt <code>DialogWindow</code> reprezentuje okno dialogowe.
 * To okno służy do rejestracji nowego użytkownika lub logowania się do programu.
 * Pobiera informacje na temat nazwy i hasła użytkownika i zapisuje/odczytuje je do/z pliku.
 */
public class DialogWindow extends JPanel{
    private JTextField username;
    private final JPasswordField password;
    private JPasswordField passwordConfirmation;
    private JDialog dialog;
    private final JButton okButton;
    private final JButton cancelButton;
    private boolean ok;
    public DialogWindow(boolean log){
        JPanel panel = new JPanel();
        if(!log)
            panel.setLayout(new GridLayout(3, 2));
        else
            panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Username:"));
        panel.add(username = new JTextField("", 20));
        panel.add(new JLabel("Password:"));
        panel.add(password = new JPasswordField(""));
        if(!log){
            panel.add(new JLabel("Confirm password: "));
            panel.add(passwordConfirmation = new JPasswordField(""));
        }
        add(panel, BorderLayout.CENTER);
        okButton = new JButton("OK");
        if(!log){
            okButton.addActionListener(ae -> {
                    String nick = getUsername();
                    char[] tempPassword = getPassword();
                    char[] tempPasswordConfirmation = getPasswordConfirmation();
                    if(!nick.matches("^[a-zA-Z0-9]*$"))
                        username.setText("Zły zakres");
                    else{
                        if(!nick.equals("") && tempPassword.length != 0 && tempPasswordConfirmation.length != 0 && Arrays.equals(tempPassword, tempPasswordConfirmation)){
                            if(!isExists(nick, null)){
                                writeToFile(nick, tempPassword);
                                dialog.setVisible(false);
                            }else{
                                username.setText("Użytkownik istnieje!");
                            }
                        }
                    }
            });
        }else{
            okButton.addActionListener(ae -> {
                   String nick = getUsername();
                   char[] tempPassword = getPassword();
                   if(isExists(nick, String.valueOf(tempPassword))){
                       dialog.setVisible(false);
                       ok = true;
                   }else
                       username.setText("Zły użytkownik lub hasło!");
            });
        }
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(ae -> dialog.setVisible(false));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Wyświetla okno dialogowe służące do rejestracji lub logowania.
     * @param parent komponent będący właścicielem okna dialogowego.
     * @param title tytuł okna dialogowego.
     */
    public boolean showDialog(Component parent, String title){
        ok = false;
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
        return ok;
    }
    
    /**
     * Pobiera nazwę użytkownika z pola username.
     * @return nazwa użytkownika
     */
    public String getUsername(){
        return username.getText();
    }
    
    /**
     * Pobiera hasło użytkownika z pola password.
     * @return hasło użytkownika
     */
    public char[] getPassword(){
        return password.getPassword();
    }
    
    /**
     * Pobiera powtórzone hasło użytkownika z pola confirm password.
     * @return powtórzone hasło użytkownika
     */
    public char[] getPasswordConfirmation(){
        return passwordConfirmation.getPassword();
    }
    
    private boolean isExists(String nick, String p){
        if(new File("C:\\Game").exists()){
            try(BufferedReader file = new BufferedReader(new FileReader("C:\\Game\\Users.txt"))){
                String line = file.readLine();
                line = file.readLine();
                String name = "";
                String tempP = "";
                while(line != null){
                    name = line.substring(0, line.indexOf(" "));
                    if(p == null){
                        if(name.equals(nick))
                            return true;
                    }else{
                        if((nick + " " + String.valueOf(encrypt(p.toCharArray(), inverseHashcode()))).equals(line))
                            return true;
                    }
                    line = file.readLine();
                }
                return false;
            }catch(IOException e){
            }
        }
        return false;
    }
    
    private char[] encrypt(char[] p, Double shift){
        String allowedSigns = "";
        for(int i = 0; i < 94; i++){
            allowedSigns += (char)(i + 32);
        }
        for(int i = 0; i < p.length; i++){
            int index = allowedSigns.indexOf(p[i]);
            p[i] = (char)((allowedSigns.charAt((index + (shift.intValue() % 93)) % allowedSigns.length())));
        }
        return p;
    }
    
    private Double inverseHashcode(){
        Double inverse = 0.0;
        try(BufferedReader fileR = new BufferedReader(new FileReader("C:\\Game\\Users.txt"))){
            String hashcode = fileR.readLine();
            int i = 1;
            do
            {
                inverse = (double)i;
                i++;
            }while(inverse.hashCode() != Integer.parseInt(hashcode));
        }catch(IOException e){
        }
        return inverse;
    }
    
    private void writeToFile(String name, char[] p){
        File directory = new File("C:\\Game");
        boolean existenceFiles = directory.exists() && new File("C:\\Game\\Users.txt").exists();
        Double shift = 0.0;
        if(!directory.exists()) directory.mkdir();
        if(!existenceFiles){
            Random x = new Random();
            shift = (double)(x.nextInt(500) + 1);
        }else{
            shift = inverseHashcode();
        }
        p = encrypt(p, shift);
        try(PrintWriter fileW = new PrintWriter(new FileWriter("C:\\Game\\Users.txt", true))){
            if(!existenceFiles){
                fileW.println(shift.hashCode());
            }
            fileW.println(name + " " + String.valueOf(p));
        }catch(IOException e){
        }
    }
}
