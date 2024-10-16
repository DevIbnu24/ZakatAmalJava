package hello;

import java.util.Scanner;

public class Hello {

    public static void main(String[] args) {
      
        String nama, kelas;
        int nim, nilai;
        double harta, zakat;
        try (
                Scanner keyboard = new Scanner(System.in)) {
            System.out.println("-Biodata Mahasiswa Teknik Informatika UMPO-");
            System.out.print("Nama: ");
            nama = keyboard.nextLine();
            System.out.print("Kelas: ");
            kelas = keyboard.nextLine();
            System.out.print("NIM: ");
            nim = keyboard.nextInt();
            System.out.print("Nilai: ");
            nilai = keyboard.nextInt();

            System.out.println("\n-Menghitung Zakat Maal-");
            System.out.print("Masukkan jumlah harta (dalam bentuk desimal): ");
            harta = keyboard.nextDouble();
            zakat = harta * 2.5;
            System.out.println("\n-Data Sudah Berhasil Disimpan-");
            System.out.println("Nama: " + nama);
            System.out.println("NIM: " + nim);
            System.out.println("Kelas: " + kelas);
            System.out.println("Nilai: " + nilai);
            System.out.printf("Jumlah zakat yang harus dibayarkan: %.2f\n", zakat);
        }
    }
}
