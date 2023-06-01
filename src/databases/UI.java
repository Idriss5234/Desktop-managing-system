package databases;

import java.awt.*;

import java.awt.event.*;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class UI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        
     // create the username and password text fields
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField user_admin = new JTextField (20);

        // create the login and register buttons
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        
        JToggleButton passwordbtn = new JToggleButton("Show");

        // apply some styling to the components
        Font font = new Font("SansSerif", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(120, 40);
        Dimension buttonSize2 = new Dimension(30, 15);
        
        usernameField.setFont(font);
        passwordField.setFont(font);
        user_admin.setFont(font);
        loginButton.setFont(font);
        loginButton.setPreferredSize(buttonSize);
        registerButton.setFont(font);
        registerButton.setPreferredSize(buttonSize);
        passwordbtn.setFont(font);
        passwordbtn.setPreferredSize(buttonSize2);
        

        // create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));    //(rows,columns,horizantal gap, vertical gap)
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel()); // dummy label to fill the  cell
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(passwordbtn);
        panel.add(new JLabel("User Or Admin:"));
        panel.add(user_admin);
        panel.add(new JLabel()); // dummy label to fill the  cell
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(new JLabel()); // dummy label to fill the  cell
        
        

        // add the panel to the frame
        frame.add(panel);
        
        passwordbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // toggle the password field visibility
                if (passwordbtn.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    passwordbtn.setText("Hide");
                } else {
                    passwordField.setEchoChar('*');
                    passwordbtn.setText("Show");
                }}
            });
        
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String useradmin = user_admin.getText();
                
                // check if the username already exists in the database
                boolean exists = false;
                try {
                	// coonection to the database
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projet","idriss","password");
                    
                    PreparedStatement stmt=con.prepareStatement("select * from auth where username=?"); //an sql query
                    stmt.setString(1, username); // sets the first parameter in the prepared statement to the value of username
                    ResultSet rs=stmt.executeQuery();
                    exists = rs.next(); //checks if the ResultSet contains any rows, which would indicate that the username already exists in the database. The result is stored in the exists variable.
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                
                // if the username already exists, show an error message
                if (exists) {
                    JOptionPane.showMessageDialog(frame, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // otherwise, proceed with the registration
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projet","idriss","password");
                        PreparedStatement stmt=con.prepareStatement("insert into auth(username,password,user_admin) values (?,?,?)");
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        stmt.setString(3, useradmin);
                        stmt.executeUpdate();
                        con.close();
                        JOptionPane.showMessageDialog(frame, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        System.out.println(ex)
                        ;
                    }
                }
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String username = usernameField.getText();
	             String password = new String(passwordField.getPassword());
	             String useradmin = user_admin.getText();
	             try {
	            	 Class.forName("com.mysql.cj.jdbc.Driver");
                     Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projet","idriss","password");
                     PreparedStatement stmt=con.prepareStatement("select * from auth where username=? and password=? and user_admin=?");
                     stmt.setString(1, username);
                     stmt.setString(2, password);
                     stmt.setString(3, useradmin);
                     ResultSet rs = stmt.executeQuery();
                     // if a row is returned, the login is successful
                     if (rs.next()) {
                         JOptionPane.showMessageDialog(frame, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                         
                        
                         //code to move me to a different pannel if the login is succesful
                         
                         JFrame frame2 = new JFrame();
                         frame2.setSize(100, 1000);
                         frame2.setTitle("Hello "+username+",how can i help you today?");
                         frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                         frame2.setVisible(true);
                         
                         JButton btn0 = new JButton("Delete user (ADMINS ONLY)");
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
                                 
                                 if (useradmin.equals("admin")) {
                                     // Code to execute if the user is admin
                                     JFrame frame8 = new JFrame();
                                     frame8.setSize(60, 30);
                                     frame8.setTitle("Delete user");
                                     frame8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                     JTextField nom_user = new JTextField(20);
                                     JButton botona = new JButton("Supprimer");

                                     // Apply some styling to the components
                                     Font font = new Font("SansSerif", Font.PLAIN, 16);
                                     Dimension buttonSize = new Dimension(60, 30);

                                     nom_user.setFont(font);
                                     botona.setFont(font);
                                     botona.setPreferredSize(buttonSize);

                                     // Create a panel to hold the components
                                     JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
                                     panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

                                     panel.add(new JLabel("Nom Utilisateur:"));
                                     panel.add(nom_user);

                                     panel.add(botona);
                                     panel.add(new JLabel()); // dummy label to fill the cell

                                     // Add the panel to the frame
                                     frame8.add(panel);

                                     // Pack and show the frame
                                     frame8.pack();
                                     frame8.setVisible(true);

                                     botona.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             String usernamebye = nom_user.getText();

                                             // Connect to the database
                                             try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "idriss", "password")) {
                                                 // Create the SQL statement to delete the user
                                                 String sql = "DELETE FROM auth WHERE username = ?";

                                                 // Create a prepared statement
                                                 PreparedStatement statement = connection.prepareStatement(sql);
                                                 statement.setString(1, usernamebye);

                                                 // Execute the statement
                                                 int rowsAffected = statement.executeUpdate();

                                                 if (rowsAffected > 0) {
                                                     JOptionPane.showMessageDialog(frame8, "User deleted successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                 } else {
                                                     JOptionPane.showMessageDialog(frame8, "User not found", "Error", JOptionPane.INFORMATION_MESSAGE);
                                                 }

                                             } catch (SQLException ex) {
                                                 ex.printStackTrace();
                                             }
                                         }
                                     });
                                 } else {
                                     // Code to execute if the user is not admin
                                     JOptionPane.showMessageDialog(frame, "YOU ARE NOT AN ADMIN!", "NO NO NO", JOptionPane.INFORMATION_MESSAGE);
                                 }
                             }
                         });

                         
                         btn1.addActionListener(new ActionListener() {     // show desktop start
							
							@Override
							public void actionPerformed(ActionEvent e) {
							    JFrame frame3 = new JFrame("Dossier Data");
							    frame3.setSize(500, 500);
							    frame3.setTitle("My Data");
							    frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

							    try {
							        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "idriss", "password");
							        Statement stmt = con.createStatement();
							        ResultSet rs = stmt.executeQuery("SELECT name_d, name_f FROM auth where username='" + username + "'");

							        String[] columnNames = {"Your dossiers","Taille_D", "Your fichiers","Taille_F"};
							        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
							        
							        Random random = new Random();


							        while (rs.next()) {
							            String name_d = rs.getString("name_d");
							            String name_f = rs.getString("name_f");
							            
							            if (name_d != null) {
							                String[] names = name_d.split(",");
							                for (String name : names) {
							                    int taille = random.nextInt(1005) + 20; 
							                    String tailleFormatted = taille + " Ko";
							                    model.addRow(new Object[]{name.trim(), tailleFormatted,"",""});
							                }
							            }

							            if (name_f != null) {
							                String[] names2 = name_f.split(",");
							                for (String name : names2) {
							                    int taille = random.nextInt(1005) + 20; 
							                    String tailleFormatted = taille + " Ko";
							                    model.addRow(new Object[]{"","", name.trim(), tailleFormatted});
							                }
							            }
							        }


							        JTable table = new JTable(model);
							        JScrollPane scrollPane = new JScrollPane(table);
							        frame3.add(scrollPane);
							        
							    } catch (SQLException ex) {
							        ex.printStackTrace();
							    }

							    frame3.setVisible(true);
							}


		                   
		                         
						});  // show desktop end
                         
                         
                        btn2.addActionListener(new ActionListener() {    //start ajouter dossier
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								 JFrame frame4 = new JFrame("Dossier Ajout");
								 frame4.setSize(250, 500);
								 frame4.setTitle("Ajouter dossier");
								 frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								 
								 JTextField nom_d = new JTextField(20);
								 JButton ajouter = new JButton("Ajouter"); 
								 
								 // apply some styling to the components
							        Font font = new Font("SansSerif", Font.PLAIN, 16);
							        Dimension buttonSize = new Dimension(60, 30);
							        
							        
							        
							        nom_d.setFont(font);
							        ajouter.setFont(font);
							        ajouter.setPreferredSize(buttonSize);
							        
							     // create a panel to hold the components
							        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));    //(rows,columns,horizantal gap, vertical gap)
							        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
							        
							        panel.add(new JLabel("Nom dossier:"));
							        panel.add(nom_d);

							        panel.add(ajouter);
							        panel.add(new JLabel()); // dummy label to fill the  cell
							        
							        

							        // add the panel to the frame
							        frame4.add(panel);
							        
							        //pack and show the frame
			                         frame4.pack();
			                         frame4.setVisible(true);
			                         
			                         ajouter.addActionListener(new ActionListener() {
			                        	    @Override
			                        	    public void actionPerformed(ActionEvent e) {
			                        	        try {
			                        	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "idriss", "password");
			                        	            String updateQuery;
			                        	            PreparedStatement stmt;
			                        	            
			                        	            String nomdossier = nom_d.getText();
			                        	            
			                        	            // Check if nom_d contains a comma
			                        	            if (nomdossier.contains(",")) {
			                        	                JOptionPane.showMessageDialog(null, "Dossier name should not contain a comma (,)", "Error", JOptionPane.ERROR_MESSAGE);
			                        	                nom_d.setText("");

			                        	                return; // Exit the actionPerformed method to prevent further execution
			                        	            }
			                        	            
			                        	            // Check if the name_d field in the auth table is empty or not
			                        	            String checkEmptyQuery = "SELECT name_d FROM auth WHERE username = ?";
			                        	            PreparedStatement checkEmptyStmt = con.prepareStatement(checkEmptyQuery);
			                        	            checkEmptyStmt.setString(1, username);
			                        	            
			                        	            ResultSet checkEmptyResult = checkEmptyStmt.executeQuery();
			                        	            checkEmptyResult.next();
			                        	        //    String existingNameD = checkEmptyResult.getString(1);
			                        	            
			                        	            if (checkEmptyResult.getString(1)==null) {
			                        	                // If the name_d field is empty, update it directly with nom_d
			                        	                updateQuery = "UPDATE auth SET name_d = ? WHERE username = ?";
			                        	                stmt = con.prepareStatement(updateQuery);
			                        	                stmt.setString(1, nomdossier+',');
			                        	                stmt.setString(2, username);
			                        	            } else {
			                        	                // If the name_d field is not empty, concatenate nom_d to the existing value
			                        	                updateQuery = "UPDATE auth SET name_d = CONCAT(name_d, ?, ',') WHERE username = ?";
			                        	                stmt = con.prepareStatement(updateQuery);
			                        	                stmt.setString(1, nomdossier);
			                        	                stmt.setString(2, username);
			                        	            }
			                        	            
			                        	            // Check if the dossier name already exists
			                        	            String checkQuery = "SELECT COUNT(*) FROM auth WHERE CONCAT(',', name_d, ',') LIKE CONCAT('%,', ?, ',%') AND username = ?";
			                        	            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
			                        	            checkStmt.setString(1, nomdossier);
			                        	            checkStmt.setString(2, username);
			                        	            
			                        	            ResultSet checkResult = checkStmt.executeQuery();
			                        	            checkResult.next();
			                        	            int count = checkResult.getInt(1);
			                        	            
			                        	            if (count > 0) {
			                        	                // Dossier name already exists, show error message
			                        	                JOptionPane.showMessageDialog(null, "Dossier name already used! Try another name:", "Error", JOptionPane.ERROR_MESSAGE);
			                        	                // Reset the name dossier field
			                        	                nom_d.setText("");
			                        	            } else {
			                        	                // Dossier name is unique, insert the new entry
			                        	                int rowsUpdated = stmt.executeUpdate();
			                        	                
			                        	                if (rowsUpdated > 0) {
			                        	                    JOptionPane.showMessageDialog(frame, "Ajout successful", "Success", JOptionPane.INFORMATION_MESSAGE);
			                        	                    nom_d.setText("");
			                        	                } else {
			                        	                    JOptionPane.showMessageDialog(frame, "Failed to add dossier!", "Error", JOptionPane.ERROR_MESSAGE);
			                        	                    nom_d.setText("");
			                        	                }
			                        	            }
			                        	            
			                        	            con.close(); // Close the database connection
			                        	        } catch (SQLException ex) {
			                        	            ex.printStackTrace();
			                        	        }
			                        	    }
			                        	});

							        

								
							}
						}) ;  //fin ajouter dossier
                        
                        
                btn3.addActionListener(new ActionListener() {    //start ajouter fichier
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								 JFrame frame5 = new JFrame("Fichier Ajout");
								 frame5.setSize(250, 500);
								 frame5.setTitle("Ajouter fichier");
								 frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								 
								 JTextField nom_f = new JTextField(20);
								 JButton ajouter = new JButton("Ajouter"); 
								 
								 // apply some styling to the components
							        Font font = new Font("SansSerif", Font.PLAIN, 16);
							        Dimension buttonSize = new Dimension(60, 30);
							        
							        
							        
							        nom_f.setFont(font);
							        ajouter.setFont(font);
							        ajouter.setPreferredSize(buttonSize);
							        
							     // create a panel to hold the components
							        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));    //(rows,columns,horizantal gap, vertical gap)
							        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
							        
							        panel.add(new JLabel("Nom fichier:"));
							        panel.add(nom_f);

							        panel.add(ajouter);
							        panel.add(new JLabel()); // dummy label to fill the  cell
							        
							        

							        // add the panel to the frame
							        frame5.add(panel);
							        
							        //pack and show the frame
			                         frame5.pack();
			                         frame5.setVisible(true);
			                         
			                         ajouter.addActionListener(new ActionListener() {
			                        	    @Override
			                        	    public void actionPerformed(ActionEvent e) {
			                        	        try {
			                        	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "idriss", "password");
			                        	            String updateQuery;
			                        	            PreparedStatement stmt;
			                        	            
			                        	            String nomfichier = nom_f.getText();
			                        	            
			                        	            // Check if nom_d contains a comma
			                        	            if (nomfichier.contains(",")) {
			                        	                JOptionPane.showMessageDialog(null, "Dossier name should not contain a comma (,)", "Error", JOptionPane.ERROR_MESSAGE);
			                        	                nom_f.setText("");

			                        	                return; // Exit the actionPerformed method to prevent further execution
			                        	            }
			                        	            
			                        	            // Check if the name_d field in the auth table is empty or not
			                        	            String checkEmptyQuery = "SELECT name_f FROM auth WHERE username = ?";
			                        	            PreparedStatement checkEmptyStmt = con.prepareStatement(checkEmptyQuery);
			                        	            checkEmptyStmt.setString(1, username);
			                        	            
			                        	            ResultSet checkEmptyResult = checkEmptyStmt.executeQuery();
			                        	            checkEmptyResult.next();
			                        	        //    String existingNameD = checkEmptyResult.getString(1);
			                        	            
			                        	            if (checkEmptyResult.getString(1)==null) {
			                        	                // If the name_d field is empty, update it directly with nom_d
			                        	                updateQuery = "UPDATE auth SET name_f = ? WHERE username = ?";
			                        	                stmt = con.prepareStatement(updateQuery);
			                        	                stmt.setString(1, nomfichier+',');
			                        	                stmt.setString(2, username);
			                        	            } else {
			                        	                // If the name_d field is not empty, concatenate nom_f to the existing value
			                        	                updateQuery = "UPDATE auth SET name_f = CONCAT(name_f, ?, ',') WHERE username = ?";
			                        	                stmt = con.prepareStatement(updateQuery);
			                        	                stmt.setString(1, nomfichier);
			                        	                stmt.setString(2, username);
			                        	            }
			                        	            
			                        	            // Check if the dossier name already exists
			                        	            String checkQuery = "SELECT COUNT(*) FROM auth WHERE CONCAT(',', name_f, ',') LIKE CONCAT('%,', ?, ',%') AND username = ?";
			                        	            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
			                        	            checkStmt.setString(1, nomfichier);
			                        	            checkStmt.setString(2, username);
			                        	            
			                        	            ResultSet checkResult = checkStmt.executeQuery();
			                        	            checkResult.next();
			                        	            int count = checkResult.getInt(1);
			                        	            
			                        	            if (count > 0) {
			                        	                // Dossier name already exists, show error message
			                        	                JOptionPane.showMessageDialog(null, "Fichier name already used! Try another name:", "Error", JOptionPane.ERROR_MESSAGE);
			                        	                // Reset the name dossier field
			                        	                nom_f.setText("");
			                        	            } else {
			                        	                // Dossier name is unique, insert the new entry
			                        	                int rowsUpdated = stmt.executeUpdate();
			                        	                
			                        	                if (rowsUpdated > 0) {
			                        	                    JOptionPane.showMessageDialog(frame, "Ajout successful", "Success", JOptionPane.INFORMATION_MESSAGE);
			                        	                    nom_f.setText("");
			                        	                } else {
			                        	                    JOptionPane.showMessageDialog(frame, "Failed to add fichier!", "Error", JOptionPane.ERROR_MESSAGE);
			                        	                    nom_f.setText("");
			                        	                }
			                        	            }
			                        	            
			                        	            con.close(); // Close the database connection
			                        	        } catch (SQLException ex) {
			                        	            ex.printStackTrace();
			                        	        }
			                        	    }
			                        	});

							        

								
							}
						}) ;  //fin ajouter fichier
                
                btn4.addActionListener(new ActionListener() {    //start supp dossier
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 JFrame frame6 = new JFrame("Dossier Supprime");
						 frame6.setSize(250, 500);
						 frame6.setTitle("Supprimer dossier");
						 frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 
						 JTextField nom_d = new JTextField(20);
						 JButton supprimer = new JButton("Supprimer"); 
						 
						 // apply some styling to the components
					        Font font = new Font("SansSerif", Font.PLAIN, 16);
					        Dimension buttonSize = new Dimension(60, 30);
					        
					        
					        
					        nom_d.setFont(font);
					        supprimer.setFont(font);
					        supprimer.setPreferredSize(buttonSize);
					        
					     // create a panel to hold the components
					        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));    //(rows,columns,horizantal gap, vertical gap)
					        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
					        
					        panel.add(new JLabel("Nom dossier:"));
					        panel.add(nom_d);

					        panel.add(supprimer);
					        panel.add(new JLabel()); // dummy label to fill the  cell
					        
					        

					        // add the panel to the frame
					        frame6.add(panel);
					        
					        //pack and show the frame
	                         frame6.pack();
	                         frame6.setVisible(true);
	                         
	                         supprimer.addActionListener(new ActionListener() {
	                        	    @Override
	                        	    public void actionPerformed(ActionEvent e) {
	                                    try {
	                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "idriss", "password");
	                                        String nomdossier = nom_d.getText();
	                                        
	                                        // Check if nom_d contains a comma
	                        	            if (nomdossier.contains(",")) {
	                        	                JOptionPane.showMessageDialog(null, "Dossier name should not contain a comma (,)", "Error", JOptionPane.ERROR_MESSAGE);
	                        	                nom_d.setText("");

	                        	                return; // Exit the actionPerformed method to prevent further execution
	                        	            }
	                                        
	                                        // Check if the dossier name exists for the user
	                                        String checkQuery = "SELECT name_d FROM auth WHERE username = ?";
	                                        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
	                                        checkStmt.setString(1, username);
	                                        
	                                        ResultSet checkResult = checkStmt.executeQuery();
	                                        checkResult.next();
	                                        String existingNames = checkResult.getString("name_d");
	                                        
	                                        if (existingNames != null && existingNames.contains(nomdossier)) {
	                                            // Dossier name exists, delete the entry
	                                        	String updatedNames = existingNames.replace(nomdossier + ",", "").replace(",,", ",");
	                                            
	                                            // Update the column with the modified names
	                                            String updateQuery = "UPDATE auth SET name_d = ? WHERE username = ?";
	                                            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
	                                            updateStmt.setString(1, updatedNames);
	                                            updateStmt.setString(2, username);
	                                            
	                                            int rowsUpdated = updateStmt.executeUpdate();
	                                            
	                                            if (rowsUpdated > 0) {
	                                                JOptionPane.showMessageDialog(frame6, "Suppression réussie", "Success", JOptionPane.INFORMATION_MESSAGE);
	                                                nom_d.setText("");
	                                            } else {
	                                                JOptionPane.showMessageDialog(frame6, "Échec de suppression du dossier!", "Error", JOptionPane.ERROR_MESSAGE);
	                                                nom_d.setText("");
	                                            }
	                                        } else {
	                                            // Dossier name does not exist, show error message
	                                            JOptionPane.showMessageDialog(frame6, "Le nom de dossier n'existe pas pour cet utilisateur!", "Error", JOptionPane.ERROR_MESSAGE);
	                                            nom_d.setText("");
	                                        }
	                                        
	                                        con.close(); // Close the database connection
	                                    } catch (SQLException ex) {
	                                        ex.printStackTrace();
	                                    }
	                                }
	                            });
						
					}
				}) ;  //fin supp dossier
                
                
     btn5.addActionListener(new ActionListener() {    //start supp fich
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 JFrame frame7 = new JFrame("Fichier Supprime");
						 frame7.setSize(250, 500);
						 frame7.setTitle("Supprimer fichier");
						 frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						 JTextField nom_f = new JTextField(20);
						 JButton supprimer = new JButton("Supprimer"); 
						 
						 // apply some styling to the components
					        Font font = new Font("SansSerif", Font.PLAIN, 16);
					        Dimension buttonSize = new Dimension(60, 30);
					        
					        
					        
					        nom_f.setFont(font);
					        supprimer.setFont(font);
					        supprimer.setPreferredSize(buttonSize);
					        
					     // create a panel to hold the components
					        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));    //(rows,columns,horizantal gap, vertical gap)
					        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
					        
					        panel.add(new JLabel("Nom fichier:"));
					        panel.add(nom_f);

					        panel.add(supprimer);
					        panel.add(new JLabel()); // dummy label to fill the  cell
					        
					        

					        // add the panel to the frame
					        frame7.add(panel);
					        
					        //pack and show the frame
	                         frame7.pack();
	                         frame7.setVisible(true);
	                         
	                         supprimer.addActionListener(new ActionListener() {
	                        	    @Override
	                        	    public void actionPerformed(ActionEvent e) {
	                                    try {
	                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "idriss", "password");
	                                        String nomfichier = nom_f.getText();
	                                        
	                                        // Check if nom_d contains a comma
	                        	            if (nomfichier.contains(",")) {
	                        	                JOptionPane.showMessageDialog(null, "Dossier name should not contain a comma (,)", "Error", JOptionPane.ERROR_MESSAGE);
	                        	                nom_f.setText("");

	                        	                return; // Exit the actionPerformed method to prevent further execution
	                        	            }
	                                        
	                                        // Check if the dossier name exists for the user
	                                        String checkQuery = "SELECT name_f FROM auth WHERE username = ?";
	                                        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
	                                        checkStmt.setString(1, username);
	                                        
	                                        ResultSet checkResult = checkStmt.executeQuery();
	                                        checkResult.next();
	                                        String existingNames = checkResult.getString("name_f");
	                                        
	                                        if (existingNames != null && existingNames.contains(nomfichier)) {
	                                            // Dossier name exists, delete the entry
	                                        	String updatedNames = existingNames.replace(nomfichier + ",", "").replace(",,", ",");
	                                            
	                                            // Update the column with the modified names
	                                            String updateQuery = "UPDATE auth SET name_f = ? WHERE username = ?";
	                                            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
	                                            updateStmt.setString(1, updatedNames);
	                                            updateStmt.setString(2, username);
	                                            
	                                            int rowsUpdated = updateStmt.executeUpdate();
	                                            
	                                            if (rowsUpdated > 0) {
	                                                JOptionPane.showMessageDialog(frame7, "Suppression réussie", "Success", JOptionPane.INFORMATION_MESSAGE);
	                                                nom_f.setText("");
	                                            } else {
	                                                JOptionPane.showMessageDialog(frame7, "Échec de suppression du fichier!", "Error", JOptionPane.ERROR_MESSAGE);
	                                                nom_f.setText("");
	                                            }
	                                        } else {
	                                            // Dossier name does not exist, show error message
	                                            JOptionPane.showMessageDialog(frame7, "Le nom de fichier n'existe pas pour cet utilisateur!", "Error", JOptionPane.ERROR_MESSAGE);
	                                            nom_f.setText("");
	                                        }
	                                        
	                                        con.close(); // Close the database connection
	                                    } catch (SQLException ex) {
	                                        ex.printStackTrace();
	                                    }
	                                }
	                            });
						
					}
				}) ;  //fin supp fich
                
                         
                         
                         

                    //if username or pssword wrong     
                     } else {
                         JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                     }

                     // close the connection
                     con.close();
                 } catch (Exception ex) {
                     System.out.println(ex);
                 }
             }
			
		
         });
      


        // pack and show the frame
        frame.pack();
        frame.setVisible(true);
    }
}