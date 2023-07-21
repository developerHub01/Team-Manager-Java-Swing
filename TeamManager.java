import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.awt.event.KeyAdapter;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class TeamManager extends JFrame{
    Container containerBox;
    String primaryColor = "#212525", secondaryColor = "#FFFFFF", errorColor = "#ff3333", validColor = "#4BB543";
    static int numberOfMember, numberOfTeam;

    JLabel logoWrapper, headingText, memberLabel, teamLabel, errorText, currentTime;
    JTextField memberTextField, teamTextField;
    JButton createBtn;
    ImageIcon logo;
    
    TeamManager(){
        containerBox = this.getContentPane();
        containerBox.setLayout(null);
        setTitle("Welcome Page");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        Image favIcon = Toolkit.getDefaultToolkit().getImage("images/favIcon.png");
        setIconImage(favIcon);
        
        
        containerBox.setBackground(Color.decode(primaryColor));

        
        Cursor pointerCursor = new Cursor(Cursor.HAND_CURSOR);
        
        
        logo = new ImageIcon("images/logo.png");
        logoWrapper = new JLabel(logo);
        logoWrapper.setBounds(136, 30, logo.getIconWidth(), logo.getIconHeight());
        
        
        headingText = new JLabel("Welcome", JLabel.CENTER);
        headingText.setBounds(0, 135, 485, 85);
        headingText.setFont(new Font("Poppins", Font.BOLD, 55));
        headingText.setForeground(Color.decode(primaryColor));
        headingText.setBackground(Color.decode(secondaryColor));
        headingText.setOpaque(true);
        // headingText.setBorder(new LineBorder(Color.RED, 2));
        
        // Member Label start ======================
        memberLabel = new JLabel("Number of members");
        memberLabel.setBounds(87, 289, 311, 30);
        memberLabel.setFont(new Font("Poppins", Font.BOLD, 25));
        memberLabel.setForeground(Color.decode(secondaryColor));
        
        memberTextField = new JTextField(); 
        memberTextField.setForeground(Color.decode(primaryColor));
        memberTextField.setBackground(Color.decode(secondaryColor));
        memberTextField.setBounds(87, 330, 311, 50);
        memberTextField.setFont(new Font("Poppins", Font.PLAIN, 18));
        memberTextField.setBorder(new LineBorder(Color.decode(secondaryColor), 15));
        // Member Label end ======================
        
        
        // team Label start ======================
        teamLabel = new JLabel("Number of Team");
        teamLabel.setBounds(87, 400, 311, 30);
        teamLabel.setFont(new Font("Poppins", Font.BOLD, 25));
        teamLabel.setForeground(Color.decode(secondaryColor));
        
        teamTextField = new JTextField();
        teamTextField.setForeground(Color.decode(primaryColor));
        teamTextField.setBackground(Color.decode(secondaryColor));
        teamTextField.setBounds(87, 439, 311, 50);
        teamTextField.setFont(new Font("Poppins", Font.PLAIN, 18));
        teamTextField.setBorder(new LineBorder(Color.decode(secondaryColor), 15));
        // team Label end ======================
        
        
        createBtn = new JButton("Let's Create");
        createBtn.setBounds(87, 517, 311, 50);
        createBtn.setForeground(Color.decode(primaryColor));
        createBtn.setBackground(Color.decode(secondaryColor));
        createBtn.setCursor(pointerCursor);
        createBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        createBtn.setBorder(null);
        
        currentTime = new JLabel("", JLabel.CENTER);
        currentTime.setBounds(87, 600, 311, 30);
        currentTime.setFont(new Font("Poppins", Font.BOLD, 25));
        currentTime.setForeground(Color.decode(secondaryColor));
        
        errorText();

        getCurrentTime(currentTime);
        
        memberTextField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                errorText.setVisible(false);
                validColor(memberTextField);
                
                int temp;
                if(e.getKeyText(e.getKeyCode()) == "Enter"){
                    try{
                        temp = Integer.parseInt(memberTextField.getText());
                        validColor(memberTextField);
                        teamTextField.requestFocus();
                        try{
                            temp = Integer.parseInt(teamTextField.getText());
                            validColor(teamTextField);
                            createBtn.doClick();
                        }catch(Exception error){
                            teamTextField.requestFocus();
                        }
                    }catch(Exception error){
                        errorColor(memberTextField);
                    }
                }else{
                    validColor(memberTextField);
                }
            }
        });
        
        teamTextField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                errorText.setVisible(false);
                validColor(teamTextField);

                int temp;
                if(e.getKeyText(e.getKeyCode()) == "Enter"){
                    try{
                        temp = Integer.parseInt(teamTextField.getText());
                        validColor(teamTextField);
                        try{
                            temp = Integer.parseInt(memberTextField.getText());
                            validColor(memberTextField);
                            createBtn.doClick();
                        }catch(Exception error){
                            errorColor(memberTextField);
                            memberTextField.requestFocus();
                        }
                    }catch(Exception error){
                        errorColor(teamTextField);
                    }
                }else{
                    validColor(teamTextField);
                }
            }
        });

        
        // Create Button Exception handling =====================
        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    int temp = Integer.parseInt(memberTextField.getText());
                    validColor(memberTextField);
                    try{
                        temp = Integer.parseInt(teamTextField.getText());
                        validColor(teamTextField);

                        numberOfMember = Integer.parseInt(memberTextField.getText());
                        numberOfTeam = Integer.parseInt(teamTextField.getText());


                        if(teamValid()){
                            AddMember obj = new AddMember();
                            dispose();
                        }

                    }catch(Exception error){
                        errorColor(teamTextField);
                        teamTextField.requestFocus();
                    }
                }catch(Exception error){
                    errorColor(memberTextField);
                    memberTextField.requestFocus();
                }
            }
        });


        add(logoWrapper);
        add(headingText);
        add(memberLabel);
        add(memberTextField);
        add(teamLabel);
        add(teamTextField);
        add(createBtn);
        add(errorText);
        add(currentTime);
        setVisible(true);
    }
    TeamManager(String dummyText){
        // System.out.println(dummyText);
    }


    Boolean teamValid(){
        int numberOfTeam, numberOfMember;

        try{
            numberOfMember = Integer.parseInt(memberTextField.getText());
        }catch(Exception e){
            numberOfMember = 0;
        }

        try{
            numberOfTeam = Integer.parseInt(teamTextField.getText());
        }catch(Exception e){
            numberOfTeam = 0;
        }
        
        if(numberOfTeam <= numberOfMember && numberOfMember!=0 && numberOfTeam!=0){
            errorColor(memberTextField);
            errorColor(teamTextField);
            errorText.setVisible(false);
            return true;
        }else{
            validColor(memberTextField);
            validColor(teamTextField);
            errorText.setVisible(true);
            return false;
        }
    }

    void errorColor(JTextField element){
        element.setBackground(Color.decode(errorColor));
        element.setBorder(new LineBorder(Color.decode(errorColor), 15));
        element.setForeground(Color.decode(secondaryColor));
    }
    void validColor(JTextField element){
        element.setBackground(Color.decode(validColor));
        element.setBorder(new LineBorder(Color.decode(validColor), 15));
        element.setForeground(Color.decode(secondaryColor));
    }
    void errorText(String text){
        errorTextFuntionality(text);
    }
    void errorText(){
        errorTextFuntionality("Something went wrong");
    }
    void errorTextFuntionality(String dummyError){
        errorText = new JLabel(dummyError, JLabel.CENTER);
        errorText.setBounds(87, 600, 311, 40);
        errorText.setFont(new Font("Poppins", Font.BOLD, 18));
        errorText.setForeground(Color.decode(secondaryColor));
        errorText.setBackground(Color.decode(errorColor));
        errorText.setOpaque(true);
        errorText.setVisible(false);
        // add(errorText);
    }
    public static void getCurrentTime(JLabel currentTime){
        Timer getTime = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int hours, min, sec;
                String amPm = "AM";
                String fullTime;
                LocalTime time = LocalTime.now();
                hours = time.getHour();
                min = time.getMinute();
                sec = time.getSecond();
                amPm = hours>=12? "PM": "AM";
                hours = hours>12? hours-12: hours;
                hours = hours==0? 12 : hours;
                fullTime = (hours<10? "0"+hours : hours)+ " : " + (min<10? "0"+min: min)+ " : " + (sec<10? "0"+sec: sec)+ " | " + amPm;

                currentTime.setText(fullTime);
            }
        });
        getTime.start();
    }
    
    public static void main(String[] args) {
        TeamManager obj = new TeamManager();
    }
}