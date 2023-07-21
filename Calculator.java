import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Calculator extends JFrame{
    Container containerBox;
    String primaryColor = "#212525", secondaryColor = "#FFFFFF", errorColor = "#ff3333", validColor = "#4BB543";
    static int numberOfMember, numberOfTeam;
    Timer textAnimation;
    
    JLabel headingText, lovePercentage, heartWrapper1, heartWrapper2, percentageBackground;
    JTextField firstName, secondName;
    JButton generateBtn, backBtn;
    ImageIcon heart;

    ArrayList<String[]> memorization = new ArrayList<>();

    Calculator(){
        containerBox = this.getContentPane();
        containerBox.setLayout(null);
        setTitle("Love Calculator");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setResizable(false);

        
        Image favIcon = Toolkit.getDefaultToolkit().getImage("images/favIcon.png");
        setIconImage(favIcon);
        
        containerBox.setBackground(Color.decode(primaryColor));

        heart = new ImageIcon("images/heart.png");
        heartWrapper1 = new JLabel(heart);
        heartWrapper2 = new JLabel(heart);
        heartWrapper1.setBounds(10, 65, heart.getIconWidth(), heart.getIconHeight());
        heartWrapper2.setBounds(365, 65, heart.getIconWidth(), heart.getIconHeight());


        Cursor pointerCursor = new Cursor(Cursor.HAND_CURSOR);

        headingText = new JLabel("<html><center>Love<br>Calculator</center></html>", JLabel.CENTER);
        headingText.setBounds(0, 45, 500, 135);
        headingText.setFont(new Font("Poppins", Font.BOLD, 40));
        headingText.setForeground(Color.decode(primaryColor));
        headingText.setBackground(Color.decode(secondaryColor));
        headingText.setOpaque(true);

        firstName = new JTextField();
        firstName.setForeground(Color.decode(primaryColor));
        firstName.setBackground(Color.decode(secondaryColor));
        firstName.setBounds(87, 224, 311, 50);
        firstName.setFont(new Font("Poppins", Font.PLAIN, 18));
        firstName.setBorder(new LineBorder(Color.decode(secondaryColor), 15));
        
        secondName = new JTextField(); 
        secondName.setForeground(Color.decode(primaryColor));
        secondName.setBackground(Color.decode(secondaryColor));
        secondName.setBounds(87, 425, 311, 50);
        secondName.setFont(new Font("Poppins", Font.PLAIN, 18));
        secondName.setBorder(new LineBorder(Color.decode(secondaryColor), 15));

        lovePercentage = new JLabel("0%", JLabel.CENTER);
        lovePercentage.setBounds(87, 280, 311, 137);
        lovePercentage.setOpaque(true);
        lovePercentage.setForeground(Color.decode(secondaryColor));
        lovePercentage.setBackground(Color.decode(primaryColor));
        lovePercentage.setFont(new Font("Poppins", Font.BOLD, 100));
        
        percentageBackground = new JLabel();
        percentageBackground.setBounds(0, 640, 0, 30);
        percentageBackground.setOpaque(true);
        percentageBackground.setBackground(Color.decode(secondaryColor));
        

        generateBtn = new JButton("Generate");
        generateBtn.setBounds(87, 483, 311, 50);
        generateBtn.setForeground(Color.decode(primaryColor));
        generateBtn.setBackground(Color.decode(secondaryColor));
        generateBtn.setCursor(pointerCursor);
        generateBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        generateBtn.setBorder(null);
        
        
        backBtn = new JButton("Back");
        backBtn.setBounds(87, 540, 311, 50);
        backBtn.setForeground(Color.decode(primaryColor));
        backBtn.setBackground(Color.decode(secondaryColor));
        backBtn.setCursor(pointerCursor);
        backBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        backBtn.setBorder(null);


        firstName.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                normalColor(firstName);
            }
        });
        secondName.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                normalColor(secondName);
            }
        });

        generateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int percent = 0;
                Boolean isOrNot = true;

                String firstNameData = firstName.getText().trim().toLowerCase();
                String secondNameData = secondName.getText().trim().toLowerCase();

                if(firstNameData.equals(secondNameData) && firstNameData.equals("")){
                    errorColor(firstName);
                    errorColor(secondName);
                    isOrNot = false;
                }else if(firstNameData.equals("")){
                    errorColor(firstName);
                    isOrNot = false;
                }else if(secondName.equals("")){
                    errorColor(secondName);
                    isOrNot = false;
                }else if(firstNameData.equals(secondNameData)){
                    percent = 100;
                    isOrNot = false;
                    normalColor(firstName);
                    normalColor(secondName);
                }else{
                    for(int i=0; i<memorization.size(); i++){
                        Boolean temp1 = firstNameData.equals(memorization.get(i)[0]) && secondNameData.equals(memorization.get(i)[1]),
                        temp2 = firstNameData.equals(memorization.get(i)[1]) && secondNameData.equals(memorization.get(i)[0]);
                        if(temp1 || temp2){
                            percent = Integer.parseInt(memorization.get(i)[2]);
                            isOrNot = false;
                            break;
                        }
                    }
                    normalColor(firstName);
                    normalColor(secondName);
                }
                if(isOrNot){
                    percent = (int)Math.round(Math.random()*100);
                    String[] data = {firstNameData, secondNameData, percent+""};
                    memorization.add(data);
                }
                percentageBackground.setSize(500*percent/100, 30);
                lovePercentage.setText(percent+"%");
            }
        });


        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                GenerateTeam generateTeam = new GenerateTeam();
                dispose();
            }
        });
        
        add(heartWrapper1);
        add(heartWrapper2);
        add(headingText);
        add(firstName);
        add(secondName);
        add(lovePercentage);
        add(generateBtn);
        add(backBtn);
        add(percentageBackground);
        setVisible(true);
    }  

    void errorColor(JTextField element){
        element.setForeground(Color.decode(secondaryColor));
        element.setBackground(Color.decode(errorColor));
        element.setBorder(new LineBorder(Color.decode(errorColor), 15));
    }
    void normalColor(JTextField element){
        element.setForeground(Color.decode(primaryColor));
        element.setBackground(Color.decode(secondaryColor));
        element.setBorder(new LineBorder(Color.decode(secondaryColor), 15));
    }

    public static void main(String[] args) {
        Calculator obj = new Calculator();
    }
}
