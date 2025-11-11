package javadatabase;

public class mahasiswa {
    private int id;
    private String nim;
    private String nama;
    private int tahunMasuk;

    // Constructor dengan ID
    public mahasiswa(int id, String nim, String nama, int tahunMasuk) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.tahunMasuk = tahunMasuk;
    }

    // Constructor tanpa ID
    public mahasiswa(String nim, String nama, int tahunMasuk) {
        this.nim = nim;
        this.nama = nama;
        this.tahunMasuk = tahunMasuk;
    }

    // Constructor default
    public mahasiswa() {}

    // Getters
    public int getId() {
        return id;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public int getTahunMasuk() {
        return tahunMasuk;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTahunMasuk(int tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }
}