/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.JFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author rifky
 */
public class Form_utama2 extends javax.swing.JFrame {

	/**
	 * Creates new form Form_utama2
	 */
	public Form_utama2() {
			initComponents();
			fullLayar();


	}

	private void buttonPinjam_on(){
		buttonPinjam.setEnabled(true);
	}
	private void buttonPinjam_off(){
		buttonPinjam.setEnabled(false);
	}
	private void tidak_on(){
		buttonTidak.setEnabled(true);
	}
	private void buttonTidak_off(){
		buttonTidak.setEnabled(false);
	}

	private void buttonPengembalian_off(){
		buttonPengembalian.setEnabled(false);
	}
	private void buttonPengembalian_on(){
		buttonPengembalian.setEnabled(true);
	}

	private LocalDate getDuaMInggu(){
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusWeeks(2);
		return futureDate;
	}
	private void fullLayar(){
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	private void gantiPanel(javax.swing.JPanel panel){
		// remove panel
		Main.removeAll();
		Main.repaint();
		Main.revalidate();

		// add panel
		Main.add(panel);
		Main.repaint();
		Main.revalidate();

	}

	private void clearTextFieldPencarian(){
		textKodeBuku.setText("");
		textJudulBuku.setText("");
		textPengarang.setText("");
		textPenerbit.setText("");
		textTahunTerbit.setText("");
	}

	private void clearTextFieldPeminjaman(){
		textKodeBukuPeminjaman.setText("");
		textJudulBukuPeminjaman.setText("");
		textTanggalPinjamPeminjaman.setText("");
		textTanggalKembaliPeminjaman.setText("");
	}

	private void refershBukuPencarian(){
		try {
			koneksi_db.openConnection();
			try (Statement statement = (Statement) koneksi_db.getConnection().createStatement()) {
				ResultSet result;
				String sql = "SELECT * FROM `data_buku`";
				statement.execute(sql);
				result = statement.getResultSet();

				
				
				// Mendapatkan model tabel dari table_pencarianBuku
				DefaultTableModel tableModel = (DefaultTableModel) tablePencarianBuku.getModel();
				
				
				// menghapus semua baris yang ada dimodel table
				tableModel.setRowCount(0);
				
				// Iterasi melalui ResultSet dan tambahkan baris ke model tabel
				while (result.next()) {
					Object[] rowData = {
						result.getString("kode_buku"),
						result.getString("judul_buku"),
						result.getString("pengarang"),
						result.getString("penerbit"),
						result.getString("tahun_terbit"),
						result.getString("status")
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

	private void tablePencarianBukuEventListener(){
		// listener table 

		ListSelectionModel selectionModel = tablePencarianBuku.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent evt){
				if(!evt.getValueIsAdjusting()){
					tidak_on();



					int selectedRow = tablePencarianBuku.getSelectedRow();
					if(selectedRow != -1){
						// Mendapatkan nilai dari baris yang dipilih
						String kode_buku = tablePencarianBuku.getValueAt(selectedRow, 0).toString();
						String judul_buku = tablePencarianBuku.getValueAt(selectedRow, 1).toString();
						String pengarang = tablePencarianBuku.getValueAt(selectedRow, 2).toString();
						String penerbit  = tablePencarianBuku.getValueAt(selectedRow, 3).toString();
						String tahun_terbit = tablePencarianBuku.getValueAt(selectedRow, 4).toString();
						String status = tablePencarianBuku.getValueAt(selectedRow, 5).toString();

						// Memasukannya ke textfield

						textKodeBuku.setText(kode_buku);
						textJudulBuku.setText(judul_buku);
						textPengarang.setText(pengarang);
						textPenerbit.setText(penerbit);
						textTahunTerbit.setText(tahun_terbit);

						// cek status
						if(status.equalsIgnoreCase("Tersedia")){
							buttonPinjam_on();
						} else {
							buttonPinjam_off();
						}

						

					}
				}
			}
		});

	}

	private void updateDataBukuPencarian(){

		
		try {
				String sql = "UPDATE data_buku SET status = ? WHERE kode_buku = "+textKodeBuku.getText();
				PreparedStatement statement = koneksi_db.conn.prepareStatement(sql);

				statement.setString(1, "Dipinjam");

				statement.executeUpdate();

				statement.close();

		} catch (SQLException ex) {
			Logger.getLogger(Form_pencarian_buku.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void refershDataPeminjaman(){
		if(Form_utama.getUsername() != null){
			try {
				koneksi_db.openConnection();
				try (Statement statement = (Statement) koneksi_db.getConnection().createStatement()) {
					ResultSet result;
					String sql = "SELECT * FROM `data_peminjaman` WHERE nim ='"+Form_utama.getUsername()+"'";
					statement.execute(sql);
					result = statement.getResultSet();
					
					// Mendapatkan model tabel dari table_peminjamanBuku
					DefaultTableModel tableModel = (DefaultTableModel) tablePeminjamanBuku.getModel();
					
					
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

	private void tablePeminjamanBukuEventListener(){
		// listener table 

		ListSelectionModel selectionModel = tablePeminjamanBuku.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent evt){
				if(!evt.getValueIsAdjusting()){
					int selectedRow = tablePeminjamanBuku.getSelectedRow();
					if(selectedRow != -1){
						// Mendapatkan nilai dari baris yang dipilih
						String kode_buku = tablePeminjamanBuku.getValueAt(selectedRow, 0).toString();
						String judul_buku = tablePeminjamanBuku.getValueAt(selectedRow, 1).toString();
						String tanggal_pinjam = tablePeminjamanBuku.getValueAt(selectedRow, 2).toString();
						String tanggal_kembali  = tablePeminjamanBuku.getValueAt(selectedRow, 3).toString();

						// Memasukannya ke textfield

						textKodeBukuPeminjaman.setText(kode_buku);
						textJudulBukuPeminjaman.setText(judul_buku);
						textTanggalPinjamPeminjaman.setText(tanggal_pinjam);
						textTanggalKembaliPeminjaman.setText(tanggal_kembali);

						// Menyalakan tombol pengembalian 
						buttonPengembalian_on();

					}
				}
			}
		});

	}

	private void updateDataBukuPeminjaman(){
		try {
				String sql = "UPDATE data_buku SET status = ? WHERE kode_buku = "+ textKodeBukuPeminjaman.getText();
				PreparedStatement statement = koneksi_db.conn.prepareStatement(sql);

				statement.setString(1, "Tersedia");

				statement.executeUpdate();
				statement.close();
				

		} catch (SQLException ex) {
			Logger.getLogger(Form_pencarian_buku.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void menghapusDataPeminjaman(){
		try {
			// cek tanggal sekarang apakah sebelum tanggal kembali

			// Menghapus data peminjaman
			String sql = "DELETE FROM `data_peminjaman` WHERE nim ='"+Form_utama.getUsername()+"' AND kode_buku ='"+textKodeBukuPeminjaman.getText()+"'";
			PreparedStatement statement = koneksi_db.conn.prepareStatement(sql);

			// Eksekusi pernyataan DELETE
			int rowDeleted = statement.executeUpdate();

				if(rowDeleted > 0){
					JOptionPane.showMessageDialog(rootPane, "Buku "+textKodeBukuPeminjaman.getText()+" Berhasil dikembalikan");
					refershDataPeminjaman();
					clearTextFieldPeminjaman();
				}
				else{
					JOptionPane.showMessageDialog(rootPane, "GAGAL di kembalikan");
			
			statement.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(Form_kembalikan_buku.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void denda(){
		JOptionPane.showMessageDialog(null, "Anda Terkena denda!", "Warning", JOptionPane.OK_OPTION);
		
		dendaDialog.pack();
		dendaDialog.setLocationRelativeTo(null);
		dendaDialog.setVisible(true);
		uangBayar.requestFocus();
		bayar.setEnabled(false);
		

		hitungHargaDenda();

	}

	private int hitungHargaDenda(){
		Long hariIni = LocalDate.now().toEpochDay();
		Long hariKelewat = LocalDate.parse(textTanggalKembaliPeminjaman.getText()).toEpochDay();
		int hari = (int) (hariIni- hariKelewat);
		int harga= 2000 * hari;
		this.hargaDenda.setText(String.valueOf(harga));
		return harga;
	}


	private void bayarDenda(){
		int hargaBayar = Integer.parseInt(uangBayar.getText());

		if(hargaBayar >= hitungHargaDenda()){
			int uangKembali = hargaBayar - hitungHargaDenda();

			// menampilkan uang kembali
			if(uangKembali > 0){
				JOptionPane.showMessageDialog(rootPane, "Uang kembali : "+uangKembali);
			}


			updateDataBukuPeminjaman();
			menghapusDataPeminjaman();
			refershDataPeminjaman();

			dendaDialog.dispose();



			
		}
		else {
			JOptionPane.showMessageDialog(null, "Uangnya Kurang!", "Warning", JOptionPane.OK_OPTION);
			uangBayar.setText("");
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
                tidakBayar = new javax.swing.JButton();
                bayar = new javax.swing.JButton();
                jLabel14 = new javax.swing.JLabel();
                uangBayar = new javax.swing.JTextField();
                jLabel15 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel16 = new javax.swing.JLabel();
                hargaDenda = new javax.swing.JTextField();
                panelMenu = new javax.swing.JPanel();
                buttonCari = new javax.swing.JButton();
                Home = new javax.swing.JButton();
                buttonPeminjaman = new javax.swing.JButton();
                jLabel1 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                Main = new javax.swing.JPanel();
                panelUtama = new javax.swing.JPanel();
                panelCari = new javax.swing.JPanel();
                jPanel1 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tablePencarianBuku = new javax.swing.JTable();
                textCari = new javax.swing.JTextField();
                cari = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                textKodeBuku = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                textJudulBuku = new javax.swing.JTextField();
                jLabel6 = new javax.swing.JLabel();
                textPengarang = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                textTahunTerbit = new javax.swing.JTextField();
                buttonPinjam = new javax.swing.JButton();
                buttonTidak = new javax.swing.JButton();
                jLabel8 = new javax.swing.JLabel();
                textPenerbit = new javax.swing.JTextField();
                panelPeminjaman = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jLabel9 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                tablePeminjamanBuku = new javax.swing.JTable();
                jLabel10 = new javax.swing.JLabel();
                textKodeBukuPeminjaman = new javax.swing.JTextField();
                jLabel11 = new javax.swing.JLabel();
                textJudulBukuPeminjaman = new javax.swing.JTextField();
                jLabel12 = new javax.swing.JLabel();
                textTanggalPinjamPeminjaman = new javax.swing.JTextField();
                jLabel13 = new javax.swing.JLabel();
                textTanggalKembaliPeminjaman = new javax.swing.JTextField();
                buttonPengembalian = new javax.swing.JButton();

                tidakBayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                tidakBayar.setText("NANTI");
                tidakBayar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                tidakBayarActionPerformed(evt);
                        }
                });

                bayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                bayar.setText("BAYAR");
                bayar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                bayarActionPerformed(evt);
                        }
                });

                jLabel14.setText("Masukan Uang");

                uangBayar.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                                uangBayarKeyTyped(evt);
                        }
                });

                jLabel15.setText("Harga Denda");

                jPanel3.setBackground(new java.awt.Color(255, 51, 51));

                jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel16.setText("BAYAR DENDA");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel16)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addContainerGap())
                );

                hargaDenda.setEditable(false);

                javax.swing.GroupLayout dendaDialogLayout = new javax.swing.GroupLayout(dendaDialog.getContentPane());
                dendaDialog.getContentPane().setLayout(dendaDialogLayout);
                dendaDialogLayout.setHorizontalGroup(
                        dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dendaDialogLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dendaDialogLayout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(105, 105, 105)
                                                .addComponent(uangBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(dendaDialogLayout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(112, 112, 112)
                                                .addComponent(hargaDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(110, Short.MAX_VALUE))
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
                                        .addComponent(jLabel15)
                                        .addComponent(hargaDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(uangBayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dendaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bayar)
                                        .addComponent(tidakBayar))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                panelMenu.setBackground(new java.awt.Color(255, 255, 255));

                buttonCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                buttonCari.setForeground(new java.awt.Color(139, 114, 208));
                buttonCari.setText("Cari Buku");
                buttonCari.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonCariActionPerformed(evt);
                        }
                });

                Home.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                Home.setForeground(new java.awt.Color(139, 114, 208));
                Home.setText("Utama");
                Home.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                HomeActionPerformed(evt);
                        }
                });

                buttonPeminjaman.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                buttonPeminjaman.setForeground(new java.awt.Color(139, 114, 208));
                buttonPeminjaman.setText("Peminjaman");
                buttonPeminjaman.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonPeminjamanActionPerformed(evt);
                        }
                });

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel1.setText("Perpustakaan");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel3.setText("BSI");

                jLabel18.setIcon(new javax.swing.ImageIcon("C:\\Users\\rifky\\Documents\\NetBeansProjects\\PBO-Java\\Perputakaan\\open-book.png")); // NOI18N

                javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
                panelMenu.setLayout(panelMenuLayout);
                panelMenuLayout.setHorizontalGroup(
                        panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMenuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonPeminjaman, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelMenuLayout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel18))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                );
                panelMenuLayout.setVerticalGroup(
                        panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMenuLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(99, 99, 99)
                                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );

                panelMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Home, buttonCari, buttonPeminjaman});

                Main.setBackground(new java.awt.Color(204, 204, 204));
                Main.setLayout(new java.awt.CardLayout());

                panelUtama.setBackground(new java.awt.Color(153, 0, 0));

                javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
                panelUtama.setLayout(panelUtamaLayout);
                panelUtamaLayout.setHorizontalGroup(
                        panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1413, Short.MAX_VALUE)
                );
                panelUtamaLayout.setVerticalGroup(
                        panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 655, Short.MAX_VALUE)
                );

                Main.add(panelUtama, "card3");

                panelCari.setBackground(new java.awt.Color(243, 244, 250));

                jPanel1.setBackground(new java.awt.Color(152, 180, 212));

                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel4.setText("PENCARIAN BUKU");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(21, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(19, 19, 19))
                );

                tablePencarianBuku.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null}
                        },
                        new String [] {
                                "Kode Buku", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit", "Status"
                        }
                ));
                jScrollPane1.setViewportView(tablePencarianBuku);

                textCari.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                textCariActionPerformed(evt);
                        }
                });
                textCari.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                textCariKeyPressed(evt);
                        }
                });

                cari.setText("CARI");
                cari.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cariActionPerformed(evt);
                        }
                });

                jLabel2.setText("Kode Buku");

                textKodeBuku.setEditable(false);

                jLabel5.setText("Judul Buku");

                textJudulBuku.setEditable(false);

                jLabel6.setText("Pengarang");

                textPengarang.setEditable(false);

                jLabel7.setText("Tahun Terbit");

                textTahunTerbit.setEditable(false);

                buttonPinjam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                buttonPinjam.setText("PINJAM");
                buttonPinjam.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonPinjamActionPerformed(evt);
                        }
                });

                buttonTidak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                buttonTidak.setText("TIDAK");
                buttonTidak.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonTidakActionPerformed(evt);
                        }
                });

                jLabel8.setText("Penerbit");

                textPenerbit.setEditable(false);

                javax.swing.GroupLayout panelCariLayout = new javax.swing.GroupLayout(panelCari);
                panelCari.setLayout(panelCariLayout);
                panelCariLayout.setHorizontalGroup(
                        panelCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCariLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textPengarang)
                                        .addComponent(textPenerbit)
                                        .addGroup(panelCariLayout.createSequentialGroup()
                                                .addGroup(panelCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(textKodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(textTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8))
                                                .addGap(0, 171, Short.MAX_VALUE))
                                        .addGroup(panelCariLayout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(buttonPinjam)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonTidak)
                                                .addGap(24, 24, 24))
                                        .addComponent(textJudulBuku))
                                .addContainerGap())
                        .addGroup(panelCariLayout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cari)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelCariLayout.setVerticalGroup(
                        panelCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCariLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCariLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cari))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelCariLayout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textKodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(panelCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(buttonPinjam)
                                                        .addComponent(buttonTidak))))
                                .addGap(0, 23, Short.MAX_VALUE))
                );

                Main.add(panelCari, "card2");

                jPanel2.setBackground(new java.awt.Color(152, 180, 212));

                jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel9.setText("PEMINJAMAN BUKU");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(21, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(19, 19, 19))
                );

                tablePeminjamanBuku.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Kode Buku", "Judul Buku", "Tanggal Pinjam", "Tanggal Kembali"
                        }
                ));
                jScrollPane2.setViewportView(tablePeminjamanBuku);

                jLabel10.setText("Kode Buku");

                textKodeBukuPeminjaman.setEditable(false);

                jLabel11.setText("Judul Buku");

                textJudulBukuPeminjaman.setEditable(false);

                jLabel12.setText("Tanggal Pinjam");

                textTanggalPinjamPeminjaman.setEditable(false);

                jLabel13.setText("Tanggal Kembali");

                textTanggalKembaliPeminjaman.setEditable(false);

                buttonPengembalian.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                buttonPengembalian.setText("PENGEMBALIAN");
                buttonPengembalian.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonPengembalianActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout panelPeminjamanLayout = new javax.swing.GroupLayout(panelPeminjaman);
                panelPeminjaman.setLayout(panelPeminjamanLayout);
                panelPeminjamanLayout.setHorizontalGroup(
                        panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelPeminjamanLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPeminjamanLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel10)
                                                        .addComponent(jLabel11)
                                                        .addComponent(jLabel12)
                                                        .addComponent(jLabel13)
                                                        .addComponent(textJudulBukuPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(textTanggalKembaliPeminjaman, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                                .addComponent(textTanggalPinjamPeminjaman, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addComponent(textKodeBukuPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelPeminjamanLayout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(buttonPengembalian)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelPeminjamanLayout.setVerticalGroup(
                        panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPeminjamanLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPeminjamanLayout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textKodeBukuPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textJudulBukuPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textTanggalPinjamPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textTanggalKembaliPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(buttonPengembalian))
                                        .addGroup(panelPeminjamanLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 52, Short.MAX_VALUE))
                );

                Main.add(panelPeminjaman, "card4");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(panelMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
                // TODO add your handling code here:
		gantiPanel(panelUtama);
        }//GEN-LAST:event_HomeActionPerformed

        private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariActionPerformed
                // TODO add your handling code here:
		gantiPanel(panelCari);
		refershBukuPencarian();
		buttonPinjam_off();
		buttonTidak_off();
		tablePencarianBukuEventListener();
        }//GEN-LAST:event_buttonCariActionPerformed

        private void textCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCariActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_textCariActionPerformed

        private void buttonTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTidakActionPerformed
                // TODO add your handling code here:
		clearTextFieldPencarian();

		refershBukuPencarian();
		
		buttonPinjam_off();
		buttonTidak_off();
        }//GEN-LAST:event_buttonTidakActionPerformed

        private void buttonPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPinjamActionPerformed
                // TODO add your handling code here:
		try {
			if(Form_utama.getUsername() != null){
				String sql = "INSERT INTO data_peminjaman (nim, nama, kode_buku, judul_buku, tanggal_pinjam, tanggal_kembali) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = koneksi_db.conn.prepareStatement(sql);

				statement.setInt(1, Integer.parseInt(Form_utama.getUsername()));
				statement.setString(2, Form_utama.getNama());
				statement.setInt(3, Integer.parseInt(textKodeBuku.getText()));
				statement.setString(4, textJudulBuku.getText());
				statement.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
				statement.setDate(6,java.sql.Date.valueOf(getDuaMInggu()));

				int rowInserted = statement.executeUpdate();
				if (rowInserted > 0){
					JOptionPane.showMessageDialog(rootPane, "Buku "+textJudulBuku.getText()+ " Berhasil dipinjam.");
					updateDataBukuPencarian();
					refershBukuPencarian();
					clearTextFieldPencarian();

					statement.close();
					buttonPinjam_off();
					buttonTidak_off();
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Gagal");
					clearTextFieldPencarian();

					statement.close();
					buttonPinjam_off();
					buttonTidak_off();
				}

			}
			else {
				JOptionPane.showMessageDialog(rootPane, "Harap Login Ulang");
				new Form_login().show();
				this.dispose();
			}
			

		} catch (SQLException ex) {
			Logger.getLogger(Form_pencarian_buku.class.getName()).log(Level.SEVERE, null, ex);
		}
        }//GEN-LAST:event_buttonPinjamActionPerformed

        private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
                // TODO add your handling code here:
		try {
			koneksi_db.openConnection();
			try (Statement statement = (Statement) koneksi_db.getConnection().createStatement()) {
				ResultSet result;
				String sql = "SELECT * FROM `data_buku` WHERE kode_buku LIKE '%"+textCari.getText()+"%' OR judul_buku LIKE '%"+textCari.getText()+"%' OR pengarang LIKE '%"+textCari.getText()+"%' OR penerbit LIKE '%"+textCari.getText()+"%' OR tahun_terbit LIKE '%"+textCari.getText()+"%'";
				statement.execute(sql);
				result = statement.getResultSet();
				
				// Mendapatkan model tabel dari table_pencarianBuku
				DefaultTableModel tableModel = (DefaultTableModel) tablePencarianBuku.getModel();
				
				
				// menghapus semua baris yang ada dimodel table
				tableModel.setRowCount(0);
				
				// Iterasi melalui ResultSet dan tambahkan baris ke model tabel
				while (result.next()) {
					Object[] rowData = {
						result.getString("kode_buku"),
						result.getString("judul_buku"),
						result.getString("pengarang"),
						result.getString("penerbit"),
						result.getString("tahun_terbit"),
						result.getString("status")
					};
					tableModel.addRow(rowData);
				}
				
				result.close();
				statement.close();
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(Form_login.class.getName()).log(Level.SEVERE, null, ex);
		}
		
        }//GEN-LAST:event_cariActionPerformed

        private void textCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCariKeyPressed
                // TODO add your handling code here:
		if(evt.getKeyCode() == KeyEvent.VK_ENTER){
			cari.doClick();
		}
        }//GEN-LAST:event_textCariKeyPressed

        private void buttonPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPeminjamanActionPerformed
                // TODO add your handling code here:
		gantiPanel(panelPeminjaman);
		refershDataPeminjaman();
		tablePeminjamanBukuEventListener();
		buttonPengembalian_off();
        }//GEN-LAST:event_buttonPeminjamanActionPerformed

        private void buttonPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPengembalianActionPerformed
                // TODO add your handling code here:
		if(Form_utama.getUsername() != null){
			if(!textKodeBukuPeminjaman.getText().isBlank()){
				LocalDate hariIni = LocalDate.now();
				LocalDate tanggal = LocalDate.parse(textTanggalKembaliPeminjaman.getText());

				if(!hariIni.isAfter(tanggal)){
					// menghapus peminjaman
					updateDataBukuPeminjaman();
					menghapusDataPeminjaman();

					refershDataPeminjaman();
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
        }//GEN-LAST:event_buttonPengembalianActionPerformed

        private void tidakBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidakBayarActionPerformed
                // TODO add your handling code here:
                dendaDialog.dispose();
        }//GEN-LAST:event_tidakBayarActionPerformed

        private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
                // TODO add your handling code here:
                bayarDenda();
        }//GEN-LAST:event_bayarActionPerformed

        private void uangBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uangBayarKeyTyped
                // TODO add your handling code here:
                char c = evt.getKeyChar();
                if(!Character.isDigit(c)){
                        evt.consume();
                }

                if(uangBayar.getText().isBlank()){
                        bayar.setEnabled(false);
                }
                else{
                        bayar.setEnabled(true);
                }
        }//GEN-LAST:event_uangBayarKeyTyped

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
			java.util.logging.Logger.getLogger(Form_utama2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Form_utama2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Form_utama2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Form_utama2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Form_utama2().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton Home;
        private javax.swing.JPanel Main;
        private javax.swing.JButton bayar;
        private javax.swing.JButton buttonCari;
        private javax.swing.JButton buttonPeminjaman;
        private javax.swing.JButton buttonPengembalian;
        private javax.swing.JButton buttonPinjam;
        private javax.swing.JButton buttonTidak;
        private javax.swing.JButton cari;
        private javax.swing.JDialog dendaDialog;
        private javax.swing.JTextField hargaDenda;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel18;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JPanel panelCari;
        private javax.swing.JPanel panelMenu;
        private javax.swing.JPanel panelPeminjaman;
        private javax.swing.JPanel panelUtama;
        private javax.swing.JTable tablePeminjamanBuku;
        private javax.swing.JTable tablePencarianBuku;
        private javax.swing.JTextField textCari;
        private javax.swing.JTextField textJudulBuku;
        private javax.swing.JTextField textJudulBukuPeminjaman;
        private javax.swing.JTextField textKodeBuku;
        private javax.swing.JTextField textKodeBukuPeminjaman;
        private javax.swing.JTextField textPenerbit;
        private javax.swing.JTextField textPengarang;
        private javax.swing.JTextField textTahunTerbit;
        private javax.swing.JTextField textTanggalKembaliPeminjaman;
        private javax.swing.JTextField textTanggalPinjamPeminjaman;
        private javax.swing.JButton tidakBayar;
        private javax.swing.JTextField uangBayar;
        // End of variables declaration//GEN-END:variables
}
