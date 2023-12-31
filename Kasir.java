import java.text.DecimalFormat; // digunakan untuk mengubah format angka menjadi tampilan yang lebih terstruktur dan mudah dibaca
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Kasir {
    private List<Obat> obatTerpilih;
    String NamaKasir;
    String IdKasir;
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Kasir() {
        obatTerpilih = new ArrayList<>();
    }

    public void tambahObat(Obat obat) {
        obatTerpilih.add(obat);
    }

    public boolean isKosong() {
        return obatTerpilih.isEmpty();
    }

    public void cetakStruk(String namaPelanggan) {
        double totalHarga = 0;
        String leftAlignFormat = "| %-20s | %-8d | %-12s | %-40s | %-20s | %-15s |%n"; // Sesuaiin aja Sama hasil output, kalo kurang set ulang ya
        
        System.out.println("\nStruk Pembelian Obat:");
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Date: " + now.format(formatter));
        System.out.format("+----------------------+----------+--------------+------------------------------------------+-------------------------+------------------+%n");
        System.out.format("| Sakit                | Stadium  | Jumlah Obat  | Dosis                                    | Nama Obat               | Harga            |%n");
        System.out.format("+----------------------+----------+--------------+------------------------------------------+-------------------------+------------------+%n");
        
    for (Obat obat : obatTerpilih) {
        if (obat != null) {
            String jenisSakit = extractSakitFromNamaObat(obat.getNama());
            System.out.format(leftAlignFormat, jenisSakit, obat.getStadium(), "1", obat.dosis(), obat.getNama(), formatHarga(obat.getHarga()));
            totalHarga += obat.getHarga();
        } else {
            System.out.println("Ada obat yang tidak teridentifikasi.");
        }
    }

    System.out.format("+----------------------+----------+--------------+------------------------------------------+-------------------------+------------------+%n");
    DecimalFormat decimalFormat = new DecimalFormat("Rp #,###,###.###");
    System.out.println("Total Harga: " + decimalFormat.format(totalHarga));

    System.out.println("\nDeskripsi Obat:");
    for (Obat obat : obatTerpilih) {
        if (obat != null) {
            System.out.println(obat.getNama() + " - " + obat.getDeskripsi());
        }
    }
}

     private String extractSakitFromNamaObat(String namaObat) {
        if (namaObat.contains("Panadol") || namaObat.contains("Bodrex") || namaObat.contains("Ibuprofen") || namaObat.contains("Aspirin")) {
            return "Sakit Kepala";
        } else if (namaObat.contains("Paracetamol") || namaObat.contains("Acetaminophen") || namaObat.contains("Ibuprofen") || namaObat.contains("Naproxen")) {
            return "Demam";
        } else if (namaObat.contains("Antiseptik") || namaObat.contains("Salep")) {
            return "Luka Luar";
        }  else if (namaObat.contains("Antibiotik") || namaObat.contains("Pain Reliever")) {
            return "Luka Dalam";
        } else if (namaObat.contains("Antistres") || namaObat.contains("Penenang")) {
            return "Sakit Hati";
        } else {
            return "Jenis Sakit Tidak Diketahui";
        }
    }

    private String formatHarga(double harga) {
        DecimalFormat decimalFormat = new DecimalFormat("Rp #,###,###.###");
        return decimalFormat.format(harga);
    }
}

