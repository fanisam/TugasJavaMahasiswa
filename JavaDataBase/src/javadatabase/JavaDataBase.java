package javadatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaDataBase {
    private static final String URL = "jdbc:postgresql://localhost:5432/JavaDataBase";
    private static final String USER = "postgres";
    private static final String PASSWORD = "fanisam2025"; // Diperbaiki typo

    // KONEKSI
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // INSERT - Mengembalikan ID yang di-generate
    public static int insert(mahasiswa m) {
        String sql = "INSERT INTO mahasiswa (nim, nama, tahunmasuk) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getNim());
            ps.setString(2, m.getNama());
            ps.setInt(3, m.getTahunMasuk());
            ps.executeUpdate();

            // Ambil ID yang di-generate
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            System.out.println("✅ Data berhasil disimpan ke database!");
        } catch (SQLException e) {
            System.err.println("❌ Error insert: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // Jika gagal
    }

    // READ
    public static List<mahasiswa> getAll() {
        List<mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa ORDER BY id ASC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                mahasiswa item = new mahasiswa(
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getInt("tahunmasuk")
                );
                list.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error read: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public static boolean update(mahasiswa m) {
        String sql = "UPDATE mahasiswa SET nama = ?, nim = ?, tahunmasuk = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNama());
            stmt.setString(2, m.getNim());
            stmt.setInt(3, m.getTahunMasuk());
            stmt.setInt(4, m.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data berhasil diperbarui.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error update: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public static boolean delete(int id) {
        String sql = "DELETE FROM mahasiswa WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data berhasil dihapus dari database!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error delete: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean insertWithId(mahasiswa m) {
    String sql = "INSERT INTO mahasiswa (id, nama, nim, tahunMasuk) VALUES (?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, m.getId());
        stmt.setString(2, m.getNama());
        stmt.setString(3, m.getNim());
        stmt.setInt(4, m.getTahunMasuk());
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error insertWithId: " + e.getMessage());
        return false;
    }
}
    public static mahasiswa getById(int id) {
    String sql = "SELECT * FROM mahasiswa WHERE id=?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            mahasiswa m = new mahasiswa(
                rs.getString("nim"),
                rs.getString("nama"),
                rs.getInt("tahunMasuk")
            );
            m.setId(rs.getInt("id"));
            return m;
        }
    } catch (Exception e) {
        System.out.println("Error getById: " + e.getMessage());
    }
    return null;
}

}