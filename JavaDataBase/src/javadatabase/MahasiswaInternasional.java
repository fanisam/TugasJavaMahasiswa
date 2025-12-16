/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javadatabase;

/**
 *
 * @author FANISAM
 */
public class MahasiswaInternasional extends mahasiswa {
public MahasiswaInternasional(int id, String nim, String nama, int tahunMasuk, int jumlahSKS) {
    super(
            id, 
            nim, 
            nama, 
            tahunMasuk, 
            jumlahSKS
    );
}


@Override
public double hitungBiayaKuliah() {
return (jumlahSKS * 300000) + 5000000;
}
}