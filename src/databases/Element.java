package databases;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class Element {
	private String name;
	public abstract int getTaille();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Element [name=" + name + "]";
	}
	
}




/* else if(useradmin=="admin") {   // the admin part


JFrame frame2 = new JFrame();
frame2.setSize(100, 1000);
frame2.setTitle("Hello "+username+",how can i help you today?");
frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame2.setVisible(true);

JButton btn0 = new JButton("Delete user");
JButton btn1 = new JButton("Show my Desktop");
JButton btn2 = new JButton("Ajouter dossier");
JButton btn3 = new JButton("Ajouter fichier");
JButton btn4 = new JButton("Supprimer dossier");
JButton btn5 = new JButton("Supprimer fichier");

Font font = new Font("SansSerif", Font.PLAIN, 16);
Dimension buttonSize = new Dimension(300, 40);

btn0.setFont(font);
btn0.setPreferredSize(buttonSize);
btn1.setFont(font);
btn1.setPreferredSize(buttonSize);
btn2.setFont(font);
btn2.setPreferredSize(buttonSize);
btn3.setFont(font);
btn3.setPreferredSize(buttonSize);
btn4.setFont(font);
btn4.setPreferredSize(buttonSize);
btn5.setFont(font);
btn5.setPreferredSize(buttonSize);

JPanel panel2 = new JPanel(new GridLayout(6, 1, 10, 10));
panel2.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

panel2.add(btn0);
panel2.add(btn1);
panel2.add(btn2);
panel2.add(btn3);
panel2.add(btn4);
panel2.add(btn5);

frame2.add(panel2);

//pack and show the frame
frame2.pack();
frame2.setVisible(true);



btn0.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 JFrame frame8 = new JFrame();
        frame8.setSize(60, 30);
        frame8.setTitle("Delete user");
        frame8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextField nom_user=new JTextField(20);
        JButton botona = new JButton("Supprimer");
        
        // apply some styling to the components
        Font font = new Font("SansSerif", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(60, 30);
        
        
        
        nom_user.setFont(font);
        botona.setFont(font);
        botona.setPreferredSize(buttonSize);
        
     // create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));    //(rows,columns,horizantal gap, vertical gap)
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        panel.add(new JLabel("Nom Utilisateur:"));
        panel.add(nom_user);

        panel.add(botona);
        panel.add(new JLabel()); // dummy label to fill the  cell
        
        

        // add the panel to the frame
        frame8.add(panel);
        
        //pack and show the frame
         frame8.pack();
         frame8.setVisible(true);
         
         botona.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
               String usernamebye = nom_user.getText();
               
               // Connect to the database
               try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inpt", "root", "idkr2003")) {
                   // Create the SQL statement to delete the user
                   String sql = "DELETE FROM users WHERE username = ?";
                   
                   // Create a prepared statement
                   PreparedStatement statement = connection.prepareStatement(sql);
                   statement.setString(1, usernamebye);
                   
                   // Execute the statement
                   int rowsAffected = statement.executeUpdate();
                   
                   if (rowsAffected > 0) {
                       System.out.println("User deleted successfully.");
                   } else {
                       System.out.println("User not found.");
                   }
                   
               } catch (SQLException ex) {
                   ex.printStackTrace();
               }
           }
       });
   }
}); // fin delete user
*/