/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rumahmakan;

/**
 *
 * @author rifky
 */
public class Menu {
	int hargaPaket,air,juice,hargaMinuman,jumlahMinuman,jumlahPaketMakanan,totalBayar;
	double hargaTarif,uangBayar,uangKembali,total;

	String pelayanan;
	String namaPaket;
	double tarif;


	
//	

	public int getHargaPaket() {
		return hargaPaket;
	}

	// 
	public void setHargaPaket(String paket) {
		if(paket.equalsIgnoreCase(("PAKET 1"))){
			this.hargaPaket = 10000;
			this.namaPaket = "Nasi padang teriyaki";

		}
		else if(paket.equalsIgnoreCase(("PAKET 2"))){
			this.hargaPaket = 15000;
			this.namaPaket = "Nasi padang sushi";

		}
		else if(paket.equalsIgnoreCase(("PAKET 3"))){
			this.hargaPaket = 18000;
			this.namaPaket = "Nasi goreng teriyaki ";
		}
		else {
			this.hargaPaket = 0;
		}
	}

	public int getAir() {
		return air;
	}

	public void setAir(int air) {

		this.air = 10000 * air;
	}

	public int getJuice() {
		return juice;
	}

	public void setJuice(int juice) {
		this.juice = 13000 * juice;
	}
	

	public int getJumlahPaketMakanan() {
		return jumlahPaketMakanan;
	}

	public void setJumlahPaketMakanan(int jumlahPaketMakanan) {
		this.jumlahPaketMakanan = jumlahPaketMakanan;
	}
	//

	public int getHargaMinuman() {
		return hargaMinuman;
	}


	public void setHargaMinuman() {
		hargaMinuman = getAir() + getJuice();

	}

	public int getJumlahMinuman() {
		return jumlahMinuman;
	}

	public void setJumlahMinuman(int jumlahMinuman) {
		this.jumlahMinuman = jumlahMinuman;
	}


	public int getTotalBayar() {
		return totalBayar;
	}

	public void setTotalBayar() {
		this.totalBayar = (hargaPaket * jumlahPaketMakanan) + (getAir() + getJuice()); 
	}

	public String getPelayanan() {
		if(this.tarif == 0){
			return pelayanan = "0";
		}
		else{
			return pelayanan = "10%";
		}
	}

	public void setPelayanan(String pelayanan) {
		if(pelayanan.equalsIgnoreCase("Delivery")){

			this.tarif = 0.1;
		}
		else{
			this.tarif = 0;
		}
			
	}


	public double getHargaTarif() {
		
		return hargaTarif;
	}

	public void setHargaTarif() {
		
		this.hargaTarif = totalBayar * tarif;

	}
	public double getTotal() {
		return total;
	}

	// total
	public void setTotal() {
		this.total = getTotalBayar() + getHargaTarif();
	}


	public double getUangBayar() {
		return uangBayar;
	}

	public void setUangBayar(double uangBayar) {
		this.uangBayar = uangBayar;
	}

	public double getUangKembali() {
		return uangKembali;
	}

	public void setUangKembali() {
		this.uangKembali =  this.uangBayar - this.total; 
	}
	
}
