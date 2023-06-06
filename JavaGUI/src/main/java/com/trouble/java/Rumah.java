/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trouble.java;

import java.text.NumberFormat;
/**
 *
 * @author rifky
 */
public class Rumah {
	public String lokasi, jog, swim, gym, cara;
    public int hrg = 0, hrg_j = 0, hrg_s = 0, hrg_g = 0, biaya = 0, total = 0;



   

    public String setFormat(int num){
	NumberFormat numberFormat = NumberFormat.getNumberInstance();
	String uang = numberFormat.format(num);
	return uang;
    }
    public void setLokasi(String val) {
        lokasi = val;
    }

    public String getLokasi() {
        return lokasi;
    }

    public int getHrg() {
        return hrg;
    }

    public String setHrg(){
	NumberFormat numberFormat = NumberFormat.getNumberInstance();
	String formattedNumber = numberFormat.format(hrg);
	return formattedNumber;
    } 


    public void setSeleksiLok() {
        if (lokasi.equalsIgnoreCase("BLOK A")) {
            hrg = 20000000;
        } else if (lokasi.equalsIgnoreCase("BLOK B")) {
            hrg = 30000000;
        } else if (lokasi.equalsIgnoreCase("BLOK C")) {
            hrg = 40000000;
        } else {
            hrg = 0;
        }
    }

    public void setJog(String val) {
        jog = val;
    }

    public int getJog() {
        if (jog.equalsIgnoreCase("Jogging track")) {
            hrg_j = 100000;
        } else {
            hrg_j = 0;
        }
        return hrg_j;
    }

    public void setSwim(String val) {
        swim = val;
    }

    public int getSwim() {
        if (swim.equalsIgnoreCase("Swimming pool")) {
            hrg_s = 200000;
        } else {
            hrg_s = 0;
        }
        return hrg_s;
    }

    public void setGym(String val) {
        gym = val;
    }

    public int getGym() {
        if (gym.equalsIgnoreCase("Gymnasium")) {
            hrg_g = 300000;
        } else {
            hrg_g = 0;
        }
        return hrg_g;
    }

    public void setCara(String val) {
        cara = val;
    }

    public String getCara() {
        return cara;
    }

    public String getBiaya() {
	 return setFormat(biaya);
    }

    public void setSeleksiCara() {
        if (cara.equalsIgnoreCase("BTN")) {
            biaya = 10000;
        } else if (cara.equalsIgnoreCase("Other Bank")) {
            biaya = 5000;
        } else {
            biaya = 0;
        }
    }

    public String getTotal() {
        total = hrg + hrg_j + hrg_s + hrg_g + biaya;
	return setFormat(total);

	
    }
}

