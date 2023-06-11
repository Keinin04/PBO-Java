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
	int hargaPaket,hargaMinuman,jumlahMinuman,jumlahPaketMakanan,totalBayar;
	double hargaTarif,uangBayar,uangKembali,total;

	String pelayanan;
	double tarif;


	
//	

	public int getHargaPaket() {
		return hargaPaket;
	}

	// 
	public void setHargaPaket(String paket) {
		if(paket.equalsIgnoreCase(("PAKET 1"))){
			this.hargaPaket = 10000;

		}
		else if(paket.equalsIgnoreCase(("PAKET 2"))){
			this.hargaPaket = 15000;

		}
		else if(paket.equalsIgnoreCase(("PAKET 3"))){
			this.hargaPaket = 18000;
		}
		else {
			this.hargaPaket = 0;
		}
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

	public void setHargaMinuman(String minuman) {
		if(minuman.equalsIgnoreCase(("Air Mineral"))){
			this.hargaMinuman = 10000;

		}
		else if(minuman.equalsIgnoreCase(("Juice"))){
			this.hargaMinuman = 13000;

		}
		else {
			this.hargaMinuman = 0;
		}

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
		this.totalBayar = (hargaPaket * jumlahPaketMakanan) + (hargaMinuman * jumlahMinuman); 
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
