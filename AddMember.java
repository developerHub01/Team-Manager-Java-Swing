import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class AddMember extends JFrame{
    static ArrayList<String> allMemberList = new ArrayList<String>();

    Container containerBox;
    String primaryColor = "#212525", secondaryColor = "#FFFFFF", errorColor = "#ff3333";

    JLabel headingText, nameLabel, nameList, remainMemberNumber, currentTime;
    JTextField nameTextField;
    JButton addBtn, clearBtn, generateBtn, backBtn, popBtn;

    AddMember(){
        containerBox = this.getContentPane();
        containerBox.setLayout(null);
        setTitle("Add name of all members");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Image favIcon = Toolkit.getDefaultToolkit().getImage("images/favIcon.png");
        setIconImage(favIcon);


        containerBox.setBackground(Color.decode(primaryColor));
        
        
        Cursor pointerCursor = new Cursor(Cursor.HAND_CURSOR);


        
        headingText = new JLabel("<html><center>Add name of all <br>members</center></html>", JLabel.CENTER);
        headingText.setBounds(0, 15, 500, 105);
        headingText.setFont(new Font("Poppins", Font.BOLD, 35));
        headingText.setForeground(Color.decode(primaryColor));
        headingText.setBackground(Color.decode(secondaryColor));
        headingText.setOpaque(true);
        // headingText.setBorder(new LineBorder(Color.RED, 2));
        add(headingText);


        nameLabel = new JLabel("Enter the name of member");
        nameLabel.setBounds(87, 145, 311, 30);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        nameLabel.setForeground(Color.decode(secondaryColor));
        
        nameTextField = new JTextField(); 
        nameTextField.setForeground(Color.decode(primaryColor));
        nameTextField.setBackground(Color.decode(secondaryColor));
        nameTextField.setBounds(87, 180, 311, 50);
        nameTextField.setFont(new Font("Poppins", Font.PLAIN, 18));
        nameTextField.setEnabled(true);
        nameTextField.setBorder(new LineBorder(Color.decode(secondaryColor), 15));
        
        
        addBtn = new JButton("Add");
        addBtn.setBounds(87, 240, 150, 50);
        addBtn.setForeground(Color.decode(primaryColor));
        addBtn.setBackground(Color.decode(secondaryColor));
        addBtn.setCursor(pointerCursor);
        addBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        addBtn.setBorder(null);
        
        popBtn = new JButton("Pop");
        popBtn.setBounds(248, 240, 150, 50);
        popBtn.setForeground(Color.decode(primaryColor));
        popBtn.setBackground(Color.decode(secondaryColor));
        popBtn.setCursor(pointerCursor);
        popBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        popBtn.setBorder(null);
        
        clearBtn = new JButton("Clear");
        clearBtn.setBounds(87, 300, 311, 50);
        clearBtn.setForeground(Color.decode(primaryColor));
        clearBtn.setBackground(Color.decode(secondaryColor));
        clearBtn.setCursor(pointerCursor);
        clearBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        clearBtn.setBorder(null);
        
        
        nameList = new JLabel("");
        nameList.setBounds(87, 355, 311, 50);
        nameList.setFont(new Font("Poppins", Font.BOLD, 18));
        nameList.setForeground(Color.decode(secondaryColor));
        
        remainMemberNumber = new JLabel((TeamManager.numberOfMember - allMemberList.size())+"", JLabel.CENTER);
        remainMemberNumber.setBounds(87, 415, 311, 50);
        remainMemberNumber.setFont(new Font("Poppins", Font.BOLD, 40));
        remainMemberNumber.setForeground(Color.decode(secondaryColor));
        remainMemberNumber.setBackground(Color.RED);

        
        generateBtn = new JButton("Generate");
        generateBtn.setBounds(87, 480, 311, 50);
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

        currentTime = new JLabel("", JLabel.CENTER);
        currentTime.setBounds(87, 610, 311, 30);
        currentTime.setFont(new Font("Poppins", Font.BOLD, 25));
        currentTime.setForeground(Color.decode(secondaryColor));

        TeamManager.getCurrentTime(currentTime);

        
        nameTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(e.getKeyText(e.getKeyCode()) == "Enter"){
                    addBtn.doClick();
                }
            }
        });
        
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNameInList();
                nameTextField.requestFocus();
            }
        });
        
        popBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allMemberList.remove(allMemberList.size()-1);
                nameListUpdate();
                if(allMemberList.size() < TeamManager.numberOfMember){
                    nameTextField.setEnabled(true);
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                nameTextField.setText("");
                nameList.setText("");
                allMemberList.clear();
                nameTextField.setEnabled(true);
                nameListUpdate();
            }
        });

        generateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(allMemberList.size()>0){
                    nameTextField.setForeground(Color.decode(primaryColor));
                    nameTextField.setBackground(Color.decode(secondaryColor));
                    nameTextField.setBorder(new LineBorder(Color.decode(secondaryColor), 15));
                    GenerateTeam obj = new GenerateTeam();
                    dispose();
                }else{
                    nameTextField.setForeground(Color.decode(secondaryColor));
                    nameTextField.setBackground(Color.decode(errorColor));
                    nameTextField.setBorder(new LineBorder(Color.decode(errorColor), 15));
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                allMemberList.clear();
                TeamManager obj = new TeamManager();
                dispose();
            }
        });
        
        
        add(nameLabel);
        add(nameTextField);
        add(addBtn);
        add(popBtn);
        add(clearBtn);
        add(nameList);
        add(remainMemberNumber);
        add(generateBtn);
        add(currentTime);
        add(backBtn);
        setVisible(true);
    }
    AddMember(String dummyText){
        // System.out.println(dummyText);
    }
    void addNameInList(){
        if (nameTextField.getText().equals("")){
            nameTextField.setForeground(Color.decode(secondaryColor));
            nameTextField.setBackground(Color.decode(errorColor));
            nameTextField.setBorder(new LineBorder(Color.decode(errorColor), 15));
        }else{
            String tempStr = nameTextField.getText();
            String[] inputNames = tempStr.split(",");

            Set<String> uniquerNames = new HashSet<>(Arrays.asList(inputNames));
            inputNames = uniquerNames.toArray(new String[uniquerNames.size()]);


            int limit = TeamManager.numberOfMember - allMemberList.size();
            int cnt=0;
            for(int i=0; i<inputNames.length; i++){
                if(cnt == limit) break;
                if(allMemberList.indexOf(inputNames[i].trim()) == -1){
                    allMemberList.add(inputNames[i].trim());
                    cnt++;
                }
            }
            
            nameListUpdate();
        }
        
        if(allMemberList.size()>=(TeamManager.numberOfMember)){ // numberOfMember is static so no need to create object for TeamManager
            nameTextField.setEnabled(false);
        }
    }

    void nameListUpdate(){
        nameTextField.setText("");

        String temp = "";

        for(int i=0; i<allMemberList.size(); i++){
            if(i != allMemberList.size()-1){
                temp += (allMemberList.get(i)+ ", ");
            }else{
                temp += allMemberList.get(i);
            }
        }
        nameList.setText(temp);

        remainMemberNumber.setText(TeamManager.numberOfMember - allMemberList.size()+"");
    }

    public static void main(String[] args) {
        AddMember obj = new AddMember();
    }
}
