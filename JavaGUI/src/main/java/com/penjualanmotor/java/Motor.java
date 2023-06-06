/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.penjualanmotor.java;

import java.math.BigDecimal;

/**
 *
 * @author rifky
 */
public class Motor {
	String namaPembeli,merkMotor,jenisMotor;
	BigDecimal hargaMotor = new BigDecimal(0);
	BigDecimal diskon = new BigDecimal(0);
	BigDecimal totalBayar = new BigDecimal(0);

	public String getNamaPembeli() {
		return namaPembeli;
	}

	public void setNamaPembeli(String namaPembeli) {
		this.namaPembeli = namaPembeli;
	}


	public void setMerkMotor(String merkMotor) {
		this.merkMotor = merkMotor;
	}

	public String getJenisMotor() {
		return jenisMotor;
	}

	public void setJenisMotor(String jenisMotor) {
		this.jenisMotor = jenisMotor;
		
	}
	

	public BigDecimal getHargaMotor() {
		return hargaMotor;
	}

	public void setHargaMotor() {
		if(merkMotor.equalsIgnoreCase("Honda")){
			if(jenisMotor.equalsIgnoreCase("Bebek")){
				BigDecimal harga= new BigDecimal(10000000);
				this.hargaMotor = harga;
			}
			else if(jenisMotor.equalsIgnoreCase("Matic")){
				BigDecimal harga= new BigDecimal(13000000);
				this.hargaMotor = harga;
			}

			else if(jenisMotor.equalsIgnoreCase("Sport")){
				BigDecimal harga= new BigDecimal(16000000);
				this.hargaMotor = harga;
			}
		}
		else if(merkMotor.equalsIgnoreCase("Yamaha")){
			if(jenisMotor.equalsIgnoreCase("Bebek")){
				BigDecimal harga= new BigDecimal(12000000);
				this.hargaMotor = harga;
			}
			else if(jenisMotor.equalsIgnoreCase("Matic")){
				BigDecimal harga= new BigDecimal(15000000);
				this.hargaMotor = harga;
			}

			else if(jenisMotor.equalsIgnoreCase("Sport")){
				BigDecimal harga= new BigDecimal(18000000);
				this.hargaMotor = harga;
			}

		}
		else{
				BigDecimal harga= new BigDecimal(0);
				this.hargaMotor = harga;
		}
			
	}

	public int getDiskon() {
		int diskonInt = diskon.intValue();
		return diskonInt;
	}

	public void setDiskon() {
		if(jenisMotor.equalsIgnoreCase("bebek")){
			BigDecimal diskonPersen = new BigDecimal(0.05);
			diskon = hargaMotor.multiply(diskonPersen);
		}
		else if(jenisMotor.equalsIgnoreCase("matic")){
			BigDecimal diskonPersen = new BigDecimal(0.03);
			diskon = hargaMotor.multiply(diskonPersen);
		}
		else if(jenisMotor.equalsIgnoreCase("sport")){
			BigDecimal diskonPersen = new BigDecimal(0.01);
			diskon = hargaMotor.multiply(diskonPersen);
		}
		else {
			diskon = new BigDecimal(0);

		}
			
	}

	public int getTotalBayar() {
		int totalBayarInt = totalBayar.intValue();
		return totalBayarInt;
	}

	public void setTotalBayar() {
		if(hargaMotor.equals(BigDecimal.ZERO) && diskon.equals(BigDecimal.ZERO)){
			totalBayar = new BigDecimal(0);
		}
		else{

			totalBayar = hargaMotor.subtract(diskon);
		}

	}

	
}
