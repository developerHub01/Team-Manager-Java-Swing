import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class GenerateTeam extends JFrame{
    Container containerBox;
    static String primaryColor = "#212525", secondaryColor = "#FFFFFF", errorColor = "#ff3333";
    ArrayList<String> teamNames = new ArrayList<String>();
    ArrayList<String> allMemberList = AddMember.allMemberList;

    int eachTeamMaxMember, extraTeamMember;

    JLabel currentTime;
    JButton reGenerateBtn, reStartBtn, closeBtn, calculator;
    JTable table;
    JScrollPane scrollPane;

    GenerateTeam(){
        containerBox = this.getContentPane();
        containerBox.setLayout(null);
        setTitle("Results");
        setSize(550, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        
        Image favIcon = Toolkit.getDefaultToolkit().getImage("images/favIcon.png");
        setIconImage(favIcon);
        
        containerBox.setBackground(Color.decode(primaryColor));

        Cursor pointerCursor = new Cursor(Cursor.HAND_CURSOR);
        
        
        generateTableMain();

        
        reGenerateBtn = new JButton("Re-generate");
        reGenerateBtn.setBounds(47, 384, 220, 60);
        reGenerateBtn.setForeground(Color.decode(primaryColor));
        reGenerateBtn.setBackground(Color.decode(secondaryColor));
        reGenerateBtn.setCursor(pointerCursor);
        reGenerateBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        reGenerateBtn.setBorder(null);
        
        reStartBtn = new JButton("Re-start");
        reStartBtn.setBounds(282, 384, 220, 60);
        reStartBtn.setForeground(Color.decode(primaryColor));
        reStartBtn.setBackground(Color.decode(secondaryColor));
        reStartBtn.setCursor(pointerCursor);
        reStartBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        reStartBtn.setBorder(null);
        
        closeBtn = new JButton("Close");
        closeBtn.setBounds(47, 455, 455, 60);
        closeBtn.setForeground(Color.decode(primaryColor));
        closeBtn.setBackground(Color.decode(secondaryColor));
        closeBtn.setCursor(pointerCursor);
        closeBtn.setFont(new Font("Poppins", Font.BOLD, 30));
        closeBtn.setBorder(null);

        currentTime = new JLabel("", JLabel.CENTER);
        currentTime.setBounds(47, 535, 455, 30);
        currentTime.setFont(new Font("Poppins", Font.BOLD, 25));
        currentTime.setForeground(Color.decode(secondaryColor));
                
        TeamManager.getCurrentTime(currentTime);
        
        calculator = new JButton("Calculator");
        calculator.setBounds(47, 583, 455, 60);
        calculator.setForeground(Color.decode(primaryColor));
        calculator.setBackground(Color.decode(secondaryColor));
        calculator.setCursor(pointerCursor);
        calculator.setFont(new Font("Poppins", Font.BOLD, 30));
        calculator.setBorder(null);

        
        
        reGenerateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                generateTableMain();
            }
        });
        
        reStartBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                AddMember.allMemberList.clear();
                TeamManager teamManager = new TeamManager();
                dispose();
            }
        });
        
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                AddMember.allMemberList.clear();
                System.exit(0);
            }
        });

        calculator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Calculator calculator = new Calculator();
                dispose();
            }
        });

        add(reGenerateBtn);
        add(reStartBtn);
        add(closeBtn);
        add(currentTime);
        add(calculator);
        setVisible(true);
    }


    void getFullTeamNameList(int eachTeamMemberNo){
        int numberOfMember = TeamManager.numberOfMember;
        teamNames.clear();
        int temp = extraTeamMember,
        number = 1,
        passedMember = 0;
        for(int i=0; i<temp; i++){
            for(int j=0; j<(eachTeamMemberNo+1); j++){
                teamNames.add("Team no #"+number);
                passedMember++;
            }
            number++;
        }
        for(int i=passedMember; i<numberOfMember; i++){
            teamNames.add("Team no #"+number);
            if(((i-passedMember)+1)%eachTeamMemberNo == 0){
                number++;
            }
        }
    }

    void shuffleList(){
        Collections.shuffle(allMemberList);
    }

    void teamMaxMemberCalculator(){
        extraTeamMember = (TeamManager.numberOfMember)%(TeamManager.numberOfTeam);
        eachTeamMaxMember = (TeamManager.numberOfMember)/(TeamManager.numberOfTeam);
    }

    void generateTableMain(){
        shuffleList();
        teamMaxMemberCalculator();
        getFullTeamNameList(eachTeamMaxMember);
        
        ArrayList<String[]> tableDataList = new ArrayList<String[]>();
        for(int i=0; i<TeamManager.numberOfMember; i++){
            String[] temp = {teamNames.get(i), allMemberList.get(i)};
            tableDataList.add(temp);
        }
        
        String[] modelHeader = {"Team Name", "Member Name"};
        String[][] tableData = tableDataList.toArray(new String[tableDataList.size()][2]);

        generateTable(tableData, modelHeader);
    }

    void generateTable(String[][] row, String[] col){
        table = new JTable(row, col);
        scrollPane = new JScrollPane(table);
        remove(scrollPane);

        table.setEnabled(false);
        table.setRowHeight(35);
        table.setBounds(47, 56, 450, 50);
        table.setFont(new Font("Poppins", Font.PLAIN, 18));
        scrollPane.setBounds(47, 56, 450, 310);

        add(scrollPane);
    }

    public static void main(String[] args) {
        GenerateTeam obj = new GenerateTeam();
    }
}