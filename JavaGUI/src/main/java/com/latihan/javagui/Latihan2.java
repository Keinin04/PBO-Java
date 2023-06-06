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
public class Latihan2 extends JFrame implements ActionListener {
	/* modifier (mengatur hak akses)
		(+) private =  hanya dapat diakses didalam kelas itu sendiri
		(+) static = dapat langsung diakses tanpa membuat objek 
		(+) final = nilai tidak dapat diubah/konstan
		(+) int = tipe data integer
	*/
	private static final int FRAME_WIDTH = 300; // 300 (Nilai untuk mengatur lebar frame) 
	private static final int FRAME_HEIGHT = 250; // 250 (Nilai untuk mengatur tinggi frame)
	private static final int FRAME_X_ORIGIN = 150; // 150 (Nilai untuk mengatur lokasi vertikal muncul nya frame pada layar)
	private static final int FRAME_Y_ORIGIN = 250; // 250 (Nilai untuk mengatur lokasi horizontal muncul nya frame pada layar)
	private static final String EMPTY_STRING = ""; // (String untuk mengosongkan isi text)
	private static final String NEWLINE = System.lineSeparator(); //  (Untuk mendapatkan karakter pemisah (\r \n pada windows))
	private JButton clearButton; // membuat modifier JButton dengan nama clearButton
	private JButton addButton; // membuat modifier JButton dengan nama addButton
	private JTextField inputLine; // membuat input 
	private JTextArea textArea; // membuat textarea

	public static void main(String[] args) {
		Latihan2 frame = new Latihan2(); // membuat objek frame
		frame.setVisible(true);
	}

	public Latihan2(){
	// setting properties frame
		Container contentPane; // dari package java.awt objek yang menampung kompenen grafis seperti JTextArea,JTextField,JButton
		setSize(FRAME_WIDTH,FRAME_HEIGHT); // mengatur lebar tinggi frame
		setResizable(false); // Tidak bisa diatur lebar tinggi semaunya
		setTitle("Program Latihan2"); // nama tittle pada atas frame
		setLocation(FRAME_X_ORIGIN,FRAME_Y_ORIGIN); // mengatur lokasi muncul nya frame pada layar
		contentPane = getContentPane(); // membuat objek 
		contentPane.setLayout(new FlowLayout()); // menempatkan komponen secara berurutan  
		textArea = new JTextArea(); // membuat objek textarea
		textArea.setColumns(22); // mengatur kolom
		textArea.setRows(8); // mengatur baris
		textArea.setBorder(BorderFactory.createLineBorder(Color.RED)); // menambah bingkai
		textArea.setEditable(false); // menjadi tidak bisa diedit
		contentPane.add(textArea); // menampilkan komponen dari objek textArea 
		inputLine = new JTextField(); // membuat objek inputLine
		inputLine.setColumns(22); // mengatur lebar kolom dari inputLine
		contentPane.add(inputLine); // menampilkan komponen dari objek inputLine
		inputLine.addActionListener(this);
	//membuat dua button ke dalam frame
		addButton = new JButton("ADD");
		contentPane.add(addButton);

		clearButton = new JButton("CLEAR");
		contentPane.add(clearButton);
	//membuat action listener untuk kedua button
		clearButton.addActionListener(this);
		addButton.addActionListener(this);
	//tutup form saat [x] diklik
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public void actionPerformed(ActionEvent event){ // membuat method actionPerformed
		if (event.getSource() instanceof JButton){ // membuat nested if listener instanceof(mengecek sumber event adalah dari kelas atau turunan tersebut) pada JButton
			JButton clickedButton = (JButton) event.getSource(); /*mengubah tipe data dari event.getsource() `Object` menjadi JButton*/
			if (clickedButton == addButton){
				addText(inputLine.getText());
			}else{
				clearText();
			}
		}else { //event untuk input
			addText(inputLine.getText());
		}
	}
	
	private void addText(String newline){
		textArea.append(newline + NEWLINE);
		inputLine.setText("");
	}

	private void clearText(){
		textArea.setText(EMPTY_STRING);
		inputLine.setText(EMPTY_STRING);
	}

}
