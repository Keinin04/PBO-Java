/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rifky
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.time.LocalDate;
import javax.swing.JFrame;


// packege untuk listen list table
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

public class Form_kembalikan_buku extends javax.swing.JFrame {

	/**
	 * Creates new form Form_kembalikan_buku
	 */
	public Form_kembalikan_buku() {
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		refershBukuPeminjaman();



		pengembalian_off();

		table_peminjamanBukuEventListener();

	}

	private void pengembalian_off(){
		pengembalian.setEnabled(false);
	}

	private void pengembalian_on(){
		pengembalian.setEnabled(true);
	}

	private void denda(){
		JOptionPane.showMessageDialog(null, "Anda Terkena denda!", "Warning", JOptionPane.OK_OPTION);
		
		dendaDialog.pack();
		dendaDialog.setLocationRelativeTo(null);
		dendaDialog.setVisible(true);
		bayar.requestFocus();

	}

	private int hitungHargaDenda(){
		Long hariIni = LocalDate.now().toEpochDay();
		Long hariKelewat = LocalDate.parse(tanggalKembali.getText()).toEpochDay();
		int hari = (int) (hariKelewat - hariIni);
		int harga= 2000 * hari;
		this.hargaDenda.setText(String.valueOf(harga));
		return harga;
	}


	private void bayarDenda(){
		int hargaBayar = Integer.parseInt(uangBayar.getText());

		if(hargaBayar >= hitungHargaDenda()){
			int uangKembali = hargaBayar - hitungHargaDenda();

			// menampilkan uang kembali
			if(hargaBayar > 0){
				JOptionPane.showMessageDialog(rootPane, "Uang kembali : "+uangKembali);
			}


			menghapusDataPeminjaman();
			updateDataBuku();
			refershBukuPeminjaman();


			
		}
		else {
			JOptionPane.showMessageDialog(null, "Uangnya Kurang!", "Warning", JOptionPane.OK_OPTION);
			uangBayar.setText("");
		}

		
	}

	private void menghapusDataPeminjaman(){
		try {
			// cek tanggal sekarang apakah sebelum tanggal kembali

			// Menghapus data peminjaman
			String sql = "DELETE FROM `data_peminjaman` WHERE nim ='"+Form_utama.getUsername()+"' AND kode_buku ='"+kodeBuku.getText()+"'";
			PreparedStatement statement = koneksi_db.conn.prepareStatement(sql);

			// Eksekusi pernyataan DELETE
			int rowDeleted = statement.executeUpdate();

				if(rowDeleted > 0){
					JOptionPane.showMessageDialog(rootPane, "Buku "+judulBuku.getText()+" Berhasil dikembalikan");
					refershBukuPeminjaman();
					clearTextfield();
				}
				else{
					JOptionPane.showMessageDialog(rootPane, "GAGAL di kembalikan");
			
			statement.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(Form_kembalikan_buku.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void table_peminjamanBukuEventListener(){
		// listener table 

		ListSelectionModel selectionModel = table_peminjamanBuku.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent evt){
				if(!evt.getValueIsAdjusting()){
					int selectedRow = table_peminjamanBuku.getSelectedRow();
					if(selectedRow != -1){
						// Mendapatkan nilai dari baris yang dipilih
						String kode_buku = table_peminjamanBuku.getValueAt(selectedRow, 0).toString();
						String judul_buku = table_peminjamanBuku.getValueAt(selectedRow, 1).toString();
						String tanggal_pinjam = table_peminjamanBuku.getValueAt(selectedRow, 2).toString();
						String tanggal_kembali  = table_peminjamanBuku.getValueAt(selectedRow, 3).toString();

						// Memasukannya ke textfield

						kodeBuku.setText(kode_buku);
						judulBuku.setText(judul_buku);
						tanggalPinjam.setText(tanggal_pinjam);
						tanggalKembali.setText(tanggal_kembali);

						// Menyalakan tombol pengembalian 
						pengembalian_on();

					}
				}
			}
		});

	}

	private void clearTextfield(){
		kodeBuku.setText("");
		judulBuku.setText("");
		tanggalPinjam.setText("");
		tanggalKembali.setText("");
	}

	private void refershBukuPeminjaman(){
		if(Form_utama.getUsername() != null){
			try {
				koneksi_db.openConnection();
				try (Statement statement = (Statement) koneksi_db.getConnection().createStatement()) {
					ResultSet result;
					String sql = "SELECT * FROM `data_peminjaman` WHERE nim ='"+Form_utama.getUsername()+"'";
					statement.execute(sql);
					result = statement.getResultSet();
					
					// Mendapatkan model tabel dari table_peminjamanBuku
					DefaultTableModel tableModel = (DefaultTableModel) table_peminjamanBuku.getModel();
					
					
					// menghapus semua baris yang ada dimodel table
					tableModel.setRowCount(0);
					
					// Iterasi melalui ResultSet dan tambahkan baris ke model tabel
					while (result.next()) {
						Object[] rowData = {
							result.getString("kode_buku"),
							result.getString("judul_buku"),
							result.getString("tanggal_pinjam"),
							result.getString("tanggal_kembali"),
						};
						tableModel.addRow(rowData);
					}

					
					result.close();
					statement.close();
				}
				

				

			} catch (SQLException ex) {
				Logger.getLogger(Form_pencarian_buku.class.getName()).log(Level.SEVERE, null, ex);
			}

		} 
		
	}

	private void updateDataBuku(){
		try {
				String sql = "UPDATE data_buku SET status = ? WHERE kode_buku = "+kodeBuku.getText();
				PreparedStatement statement = koneksi_db.conn.prepareStatement(sql);

				statement.setString(1, "Tersedia");

				statement.executeUpdate();
				statement.close();
				

		} catch (SQLException ex) {
			Logger.getLogger(Form_pencarian_buku.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                dendaDialog = new javax.swing.JDialog();
                jLabel6 = new javax.swing.JLabel();
                hargaDenda = new javax.swing.JTextField();
                uangBayar = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                bayar = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                jLabel8 = new javax.swing.JLabel();
                tidakBayar = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                table_peminjamanBuku = new javax.swing.JTable();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                kodeBuku = new javax.swing.JTextField();
                judulBuku = new javax.swing.JTextField();
                tanggalPinjam = new javax.swing.JTextField();
                tanggalKembali = new javax.swing.JTextField();
                pengembalian = new javax.swing.JButton();
                jMenuBar1 = new javax.swing.JMenuBar();
                Back = new javax.swing.JMenu();
                Logout = new javax.swing.JMenu();

                dendaDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jLabel6.setText("Harga Denda");

                hargaDenda.setEditable(false);

                jLabel7.setText("Masukan Uang");

                bayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                bayar.setText("BAYAR");
                bayar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                bayarActionPerformed(evt);
                        }
                });

                jPanel3.setBackground(new java.awt.Color(255, 51, 51));

                jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel8.setText("BAYAR DENDA");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addContainerGap())
                );

                tidakBayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                tidakBayar.setText("NANTI");

                javax.swing.GroupLayout dendaDialogLayout = new javax.swing.GroupLayout(dendaDialog.getContentPane());
                dendaDialog.getContentPane().setLayout(dendaDialogLayout);
                dendaDialogLayout.setHorizontalGroup(
                        dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dendaDialogLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dendaDialogLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(105, 105, 105)
                                                .addComponent(uangBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(dendaDialogLayout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(112, 112, 112)
                                                .addComponent(hargaDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(125, Short.MAX_VALUE))
                        .addGroup(dendaDialogLayout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(bayar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tidakBayar)
                                .addGap(101, 101, 101))
                );
                dendaDialogLayout.setVerticalGroup(
                        dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dendaDialogLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(hargaDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(uangBayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bayar)
                                        .addComponent(tidakBayar))
                                .addContainerGap(24, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(152, 180, 212));
                jPanel1.setPreferredSize(new java.awt.Dimension(248, 51));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel1.setText("PEMINJAMAN BUKU");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUKU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

                table_peminjamanBuku.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Kode Buku", "Judul Buku", "Tanggal Pinjam", "Tanggal Kembali"
                        }
                ));
                jScrollPane1.setViewportView(table_peminjamanBuku);

                jLabel2.setText("Kode Buku");

                jLabel3.setText("Tanggal Pinjam");

                jLabel4.setText("Judul Buku");

                jLabel5.setText("Tanggal Kembali");

                kodeBuku.setEditable(false);

                judulBuku.setEditable(false);

                tanggalPinjam.setEditable(false);

                tanggalKembali.setEditable(false);

                pengembalian.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                pengembalian.setText("PENGEMBALIAN");
                pengembalian.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                pengembalianActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel5))
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(kodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(judulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(tanggalKembali, javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(tanggalPinjam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(pengembalian)))
                                .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(kodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(judulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(tanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(tanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(pengembalian)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                Back.setText("Back");
                Back.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                BackMouseClicked(evt);
                        }
                });
                jMenuBar1.add(Back);

                Logout.setText("Logout");
                Logout.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                LogoutMouseClicked(evt);
                        }
                });
                jMenuBar1.add(Logout);

                setJMenuBar(jMenuBar1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void pengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengembalianActionPerformed
                // TODO add your handling code here:

		if(Form_utama.getUsername() != null){
			if(!kodeBuku.getText().isBlank()){
				LocalDate hariIni = LocalDate.now();
				LocalDate tanggal = LocalDate.parse(tanggalKembali.getText());

				if(hariIni.isBefore(tanggal)){
					// menghapus peminjaman
					updateDataBuku();
					menghapusDataPeminjaman();

					refershBukuPeminjaman();
				}
				else {
					// kenda denda
					denda();
				}

			}
			else {
				JOptionPane.showMessageDialog(rootPane, "Tidak Ada buku yang dikembalikan");
			}
		} 
		else {
			JOptionPane.showMessageDialog(rootPane, "Harap Login Ulang");
			new Form_login().show();
			this.dispose();
		}
        }//GEN-LAST:event_pengembalianActionPerformed

        private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
                // TODO add your handling code here:
		bayarDenda();
        }//GEN-LAST:event_bayarActionPerformed

        private void BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseClicked
                // TODO add your handling code here:
		new Form_utama().show();
		this.dispose();
        }//GEN-LAST:event_BackMouseClicked

        private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
                // TODO add your handling code here:
		new Form_login().show();
		this.dispose();
        }//GEN-LAST:event_LogoutMouseClicked

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
			java.util.logging.Logger.getLogger(Form_kembalikan_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Form_kembalikan_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Form_kembalikan_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Form_kembalikan_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Form_kembalikan_buku().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JMenu Back;
        private javax.swing.JMenu Logout;
        private javax.swing.JButton bayar;
        private javax.swing.JDialog dendaDialog;
        private javax.swing.JTextField hargaDenda;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JMenuBar jMenuBar1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextField judulBuku;
        private javax.swing.JTextField kodeBuku;
        private javax.swing.JButton pengembalian;
        private javax.swing.JTable table_peminjamanBuku;
        private javax.swing.JTextField tanggalKembali;
        private javax.swing.JTextField tanggalPinjam;
        private javax.swing.JButton tidakBayar;
        private javax.swing.JTextField uangBayar;
        // End of variables declaration//GEN-END:variables
}
