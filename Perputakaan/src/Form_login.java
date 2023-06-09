/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rifky
 */

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Form_login extends javax.swing.JFrame {

	/**
	 * Creates new form Form_login
	 */
	public Form_login() {
		initComponents();
		koneksi_db.openConnection();

		this.setLocationRelativeTo(null);
	}

	


	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                username_Textfield = new javax.swing.JTextField();
                login_button = new javax.swing.JButton();
                password_Textfield = new javax.swing.JPasswordField();
                jPanel1 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                loginExit = new javax.swing.JButton();
                jcpassword = new javax.swing.JCheckBox();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel1.setText("Username:");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel3.setText("Password :");

                username_Textfield.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                username_TextfieldActionPerformed(evt);
                        }
                });
                username_Textfield.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                username_TextfieldKeyPressed(evt);
                        }
                });

                login_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                login_button.setText("LOGIN");
                login_button.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                login_buttonActionPerformed(evt);
                        }
                });

                password_Textfield.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                password_TextfieldKeyPressed(evt);
                        }
                });

                jPanel1.setBackground(new java.awt.Color(102, 102, 102));
                jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setText("LOGIN PERPUSTAKAAN");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(jLabel4)
                                .addContainerGap(183, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(57, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(45, 45, 45))
                );

                loginExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                loginExit.setText("EXIT");
                loginExit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                loginExitActionPerformed(evt);
                        }
                });

                jcpassword.setText("Show Password");
                jcpassword.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jcpasswordActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(178, 178, 178)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(password_Textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(username_Textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(jLabel3))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jcpassword))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(160, 160, 160)
                                                .addComponent(login_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(66, 66, 66)
                                                .addComponent(loginExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(username_Textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(password_Textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jcpassword))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(login_button)
                                        .addComponent(loginExit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(120, Short.MAX_VALUE))
                );

                layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {loginExit, login_button});

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void username_TextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_TextfieldActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_username_TextfieldActionPerformed

        private void login_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_buttonActionPerformed
		try {
			// TODO add your handling code here:
			Statement statement = (Statement) koneksi_db.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM `mahasiswa` where"+" nim = '"+ username_Textfield.getText()+ "'");
			if(result.next()){
				String pass = result.getString("password");
				if(password_Textfield.getText().equals(result.getString("password"))){

					
					Form_utama.setUsername(result.getString("nim"));
					Form_utama.setNama(result.getString("nama"));

					result.close();
					statement.close();

					new Form_utama().show();
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Password salah");
					password_Textfield.setText("");
					password_Textfield.requestFocus();

					result.close();
					statement.close();
				}
			}
			else{
				JOptionPane.showMessageDialog(rootPane, "User tidak ditemukan");
				username_Textfield.setText("");
				password_Textfield.setText("");
				username_Textfield.requestFocus();

				result.close();
				statement.close();
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(Form_login.class.getName()).log(Level.SEVERE, null, ex);
		}
        }//GEN-LAST:event_login_buttonActionPerformed

        private void loginExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginExitActionPerformed
                // TODO add your handling code here:
                System.exit(0);
        }//GEN-LAST:event_loginExitActionPerformed

        private void jcpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcpasswordActionPerformed
                // TODO add your handling code here:
                if(jcpassword.isSelected()){
                        password_Textfield.setEchoChar((char)0);
                }else{
                        password_Textfield.setEchoChar('*');
                }
        }//GEN-LAST:event_jcpasswordActionPerformed

        private void username_TextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_username_TextfieldKeyPressed
                // TODO add your handling code here:
		if(evt.getKeyCode() == KeyEvent.VK_ENTER){
			password_Textfield.requestFocus();
		}
        }//GEN-LAST:event_username_TextfieldKeyPressed

        private void password_TextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password_TextfieldKeyPressed
                // TODO add your handling code here:
		if(evt.getKeyCode() == KeyEvent.VK_ENTER){
			login_button.grabFocus();
			login_button.doClick();
			this.dispose();
		}
        }//GEN-LAST:event_password_TextfieldKeyPressed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Form_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Form_login().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JCheckBox jcpassword;
        private javax.swing.JButton loginExit;
        private javax.swing.JButton login_button;
        private javax.swing.JPasswordField password_Textfield;
        private javax.swing.JTextField username_Textfield;
        // End of variables declaration//GEN-END:variables
}
