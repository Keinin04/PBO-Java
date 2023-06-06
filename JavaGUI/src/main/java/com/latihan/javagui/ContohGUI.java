/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.latihan.javagui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author rifky
 */
public class ContohGUI extends JFrame {
	JPanel pl = new JPanel();
	JLabel lbl_namatamu = new JLabel("Nama Tamu");
	JLabel lbl_kodekamar = new JLabel("Kode Kamar");
	JLabel lbl_namakamar = new JLabel("Nama Kamar"); 
	JLabel lbl_hargakamar = new JLabel("Harga Kamar"); 
	JLabel lbl_lamainap = new JLabel("Lama Inap"); 
	JLabel lbl_totalbayar= new JLabel("Total Bayar"); 

	JTextField tField_namatamu = new JTextField();
	JComboBox<String> cb_kodekamar;
	
	JTextField tField_namakamar = new JTextField();
	JTextField tField_hargakamar = new JTextField();
	JTextField tField_lamainap = new JTextField();
	JTextField tField_totalbayar = new JTextField();

	JButton b1 = new JButton("PROSES");
	JButton b2 = new JButton("HAPUS");

	// value 
	 int hargakamar = 0; 
	 int lamainap = 0; 
	 int totalbayar = 0; 


	public ContohGUI(){
		this.cb_kodekamar = new JComboBox<>();
		add(lbl_namatamu);
		add(lbl_kodekamar);
		add(lbl_namakamar);
		add(lbl_hargakamar);
		add(lbl_lamainap);
		add(lbl_totalbayar);
		
		
		add(tField_namatamu);

		cb_kodekamar.addItem("A001");
		cb_kodekamar.addItem("M002");
		cb_kodekamar.addItem("T003");
		cb_kodekamar.setVisible(true);

		add(cb_kodekamar);

		add(tField_namakamar);
		add(tField_hargakamar);
		add(tField_lamainap);
		add(tField_totalbayar);

		add(b1);
		add(b2);
		add(pl);
	
		setTitle("GUI Swing");
		setBounds(100, 100, 300, 600);

		lbl_namatamu.setBounds(15,20,80,25);//15 jarak antr pinggir
		lbl_kodekamar.setBounds (15,55,125,25);
		lbl_namakamar.setBounds(15,80,125,25);
		lbl_hargakamar.setBounds(15,110,125,25);
		lbl_lamainap.setBounds(15,150,125,25);
		lbl_totalbayar.setBounds(15,180,125,25);

		tField_namatamu.setBounds(120,20,80,25);
		cb_kodekamar.setBounds(120,50,110,25);
		tField_namakamar.setBounds(120,80,150,25);
		tField_hargakamar.setBounds(120,110,150,25);
		tField_lamainap.setBounds(120,150,150,25);
		tField_totalbayar.setBounds(120,180,150,25);

		b1.setBounds(15, 250, 100, 25);
		b2.setBounds(120,250,80,25);
		setDefaultCloseOperation (EXIT_ON_CLOSE); 


		// event listener combobox
		cb_kodekamar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String selected = (String) cb_kodekamar.getSelectedItem();
				if(selected.equals("A001")){
					tField_namakamar.setText("Anggrek");
					tField_hargakamar.setText("Rp.1.000.000");
				}
				else if(selected.equals("M002")){
					tField_namakamar.setText("Mawar");
					tField_hargakamar.setText("Rp.750.000");
				}
				else {
					tField_namakamar.setText("Tulip");
					tField_hargakamar.setText("Rp.500.000");
				}
			}
			});

		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ){

				String selected = (String) cb_kodekamar.getSelectedItem();

				if(selected.equals("A001")){
					hargakamar+=1000000;
				}
				else if(selected.equals("M002")){
					hargakamar+=750000;
				}
				else {
					tField_namakamar.setText("Tulip");
					tField_hargakamar.setText("Rp.500.000");
					hargakamar+=500000;
				}
				
				// Menghitung total bayar
					totalbayar = hargakamar*Integer.parseInt(tField_lamainap.getText());
					tField_totalbayar.setText("Rp."+totalbayar);
			}

			});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				hapusdata();}
			});


	}

	public void hapusdata(){
		tField_namatamu.setText("");
		tField_namakamar.setText("");
		tField_hargakamar.setText("");
		hargakamar = 0;
		tField_lamainap.setText("");
		tField_totalbayar.setText("");
		totalbayar = 0;
	}



	public static void main(String[] args) {
		ContohGUI demo = new ContohGUI();
		demo.setVisible(true);
	}



} 