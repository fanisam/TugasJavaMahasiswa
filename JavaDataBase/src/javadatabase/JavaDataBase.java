package javadatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaDataBase {

    private static final String URL = "jdbc:postgresql://localhost:5432/JavaDataBase";
    private static final String USER = "postgres";
    private static final String PASSWORD = "fanisam2025";

    // KONEKSI
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ===================== INSERT =====================
    public static boolean insert(mahasiswa m, String jenisMahasiswa) {
        String sql = "INSERT INTO mahasiswa "
                + "(nim, nama, tahunmasuk, jenis_mahasiswa, jumlah_sks, biaya_kuliah) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNim());
            ps.setString(2, m.getNama());
            ps.setInt(3, m.getTahunMasuk());
            ps.setString(4, jenisMahasiswa);
            ps.setInt(5, m.getjumlahSKS());
            ps.setDouble(6, m.hitungBiayaKuliah()); // POLYMORPHISM

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error insert: " + e.getMessage());
            return false;
        }
    }

    // ===================== READ =====================
    public static List<Object[]> getAll() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa ORDER BY id ASC";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jenis_mahasiswa"),
                    rs.getInt("jumlah_sks"),
                    rs.getDouble("biaya_kuliah")
                };
                list.add(data);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error read: " + e.getMessage());
        }
        return list;
    }

    // ===================== UPDATE =====================
    public static boolean update(int id, mahasiswa m, String jenisMahasiswa) {
        String sql = "UPDATE mahasiswa SET "
                + "nama=?, nim=?, tahunmasuk=?, jenis_mahasiswa=?, jumlah_sks=?, biaya_kuliah=? "
                + "WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNama());
            ps.setString(2, m.getNim());
            ps.setInt(3, m.getTahunMasuk());
            ps.setString(4, jenisMahasiswa);
            ps.setInt(5, m.getjumlahSKS());
            ps.setDouble(6, m.hitungBiayaKuliah());
            ps.setInt(7, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error update: " + e.getMessage());
            return false;
        }
    }

    // ===================== DELETE =====================
    public static boolean delete(int id) {
        String sql = "DELETE FROM mahasiswa WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error delete: " + e.getMessage());
            return false;
        }
    }
}
