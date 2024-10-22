package hello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class Hello extends JFrame {

    private JTextField namaField, hartaField, penghasilanField, pengeluaranField;
    private JTextField emasField, perakField, hewanTernakField;
    private JLabel zakatResultLabel, statusResultLabel;
    private double nisab = 8500000; // Nisab zakat
    private NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    private boolean running = true;

    public Hello() {
        setTitle("Aplikasi Penghitung Zakat");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menambahkan header dalam format vertikal
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        JLabel titleLabel = new JLabel("Sistem Penghitungan Zakat By M Ibnu P", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(titleLabel);

        JLabel nimLabel = new JLabel("NIM : 23533832", SwingConstants.CENTER);
        nimLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(nimLabel);

        JLabel kelasLabel = new JLabel("3A TI UMPO", SwingConstants.CENTER);
        kelasLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(kelasLabel);

        add(headerPanel, BorderLayout.NORTH);

        while (running) {
            tampilkanMenu();
        }
    }

    // Method untuk menampilkan menu utama
    private void tampilkanMenu() {
        String[] options = {"Hitung Zakat", "Keluar"};
        int pilihan = JOptionPane.showOptionDialog(null, "Pilih Opsi:", "Menu Utama",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (pilihan) {
            case 0:
                hitungZakatMenu();  // Menampilkan menu sub untuk hitung zakat
                break;
            case 1:
                running = false;
                JOptionPane.showMessageDialog(this, "Terima kasih telah menggunakan program ini. Selamat tinggal!");
                dispose();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Pilihan tidak valid. Silakan coba lagi.");
                break;
        }
    }

    // Menu untuk memilih jenis zakat
    private void hitungZakatMenu() {
        String[] options = {"Zakat Fitrah", "Zakat Emas", "Zakat Penghasilan", "Kembali"};
        int pilihan = JOptionPane.showOptionDialog(null, "Pilih Jenis Zakat:", "Menu Hitung Zakat",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (pilihan) {
            case 0:
                hitungZakatFitrah();
                break;
            case 1:
                hitungZakatEmas();
                break;
            case 2:
                hitungZakatPenghasilan();
                break;
            case 3:
                // Kembali ke menu utama
                break;
            default:
                JOptionPane.showMessageDialog(this, "Pilihan tidak valid. Silakan coba lagi.");
                break;
        }
    }

    // Form untuk menghitung Zakat Fitrah
    private void hitungZakatFitrah() {
        // Inputan untuk Zakat Fitrah
        String[] options = {"Hitung Zakat Fitrah", "Kembali"};
        int pilihan = JOptionPane.showOptionDialog(null, "Masukkan Jumlah Orang:", "Zakat Fitrah",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (pilihan == 0) {
            String input = JOptionPane.showInputDialog("Jumlah Orang:");
            if (input != null) {
                int jumlahOrang = Integer.parseInt(input);
                double zakatFitrah = jumlahOrang * 30000; // Contoh nominal zakat fitrah

                // Menampilkan hasil zakat fitrah
                JOptionPane.showMessageDialog(this, "Zakat Fitrah yang harus dibayarkan: " + rupiahFormat.format(zakatFitrah));
                // Menentukan hak untuk menerima atau membayar zakat
                if (zakatFitrah > nisab) {
                    JOptionPane.showMessageDialog(this, "Status: Anda wajib membayar zakat fitrah.");
                } else {
                    JOptionPane.showMessageDialog(this, "Status: Anda berhak menerima zakat fitrah.");
                }
            }
        }
    }

    // Form untuk menghitung Zakat Emas
    private void hitungZakatEmas() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(createLabel("Emas yang Disimpan (gram):"));
        emasField = new JTextField();
        panel.add(emasField);

        JButton calculateButton = new JButton("Hitung Zakat Emas");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double emas = Double.parseDouble(emasField.getText());
                    double nisabEmas = 85; // Nisab emas dalam gram
                    double zakatEmas = 0;

                    // Hitung zakat emas jika jumlah emas mencapai nisab
                    if (emas >= nisabEmas) {
                        zakatEmas = emas * 0.025; // 2.5% dari emas yang disimpan
                        // Menampilkan hasil zakat emas
                        JOptionPane.showMessageDialog(Hello.this, "Zakat Emas yang harus dibayarkan: " + rupiahFormat.format(zakatEmas));
                        JOptionPane.showMessageDialog(Hello.this, "Status: Anda wajib membayar zakat emas.");
                    } else {
                        JOptionPane.showMessageDialog(Hello.this, "Status: Anda tidak wajib membayar zakat emas karena jumlah emas di bawah nisab.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Hello.this, "Masukkan jumlah emas yang valid.");
                }
            }
        });
        panel.add(calculateButton);

        // Menampilkan form di dialog box
        JOptionPane.showMessageDialog(this, panel, "Zakat Emas", JOptionPane.PLAIN_MESSAGE);
    }

    // Form untuk menghitung Zakat Penghasilan
    private void hitungZakatPenghasilan() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(createLabel("Penghasilan Setahun Terakhir (Rp):"));
        penghasilanField = new JTextField();
        panel.add(penghasilanField);

        JButton calculateButton = new JButton("Hitung Zakat Penghasilan");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double penghasilan = Double.parseDouble(penghasilanField.getText());
                    double zakatPenghasilan = penghasilan * 0.025; // 2.5% dari penghasilan

                    // Menampilkan hasil zakat penghasilan
                    JOptionPane.showMessageDialog(Hello.this, "Zakat Penghasilan yang harus dibayarkan: " + rupiahFormat.format(zakatPenghasilan));
                    // Menentukan hak untuk menerima atau membayar zakat
                    if (penghasilan < nisab) {
                        JOptionPane.showMessageDialog(Hello.this, "Status: Anda berhak menerima zakat penghasilan.");
                    } else {
                        JOptionPane.showMessageDialog(Hello.this, "Status: Anda wajib membayar zakat penghasilan.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Hello.this, "Masukkan jumlah penghasilan yang valid.");
                }
            }
        });
        panel.add(calculateButton);

        // Menampilkan form di dialog box
        JOptionPane.showMessageDialog(this, panel, "Zakat Penghasilan", JOptionPane.PLAIN_MESSAGE);
    }

    // Method untuk menampilkan JLabel dengan setting default
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }

    public static void main(String[] args) {
        // Jalankan GUI di thread Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Hello().setVisible(true);
            }
        });
    }
}
