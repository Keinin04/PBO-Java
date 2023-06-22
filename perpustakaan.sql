-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2023 at 03:12 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_buku`
--

CREATE TABLE `data_buku` (
  `kode_buku` int(10) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(30) NOT NULL,
  `tahun_terbit` date NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_buku`
--

INSERT INTO `data_buku` (`kode_buku`, `judul_buku`, `pengarang`, `penerbit`, `tahun_terbit`, `status`) VALUES
(1001, 'Mantappu Jiwa Buku Latihan Soal', 'Jerome Polin Sijabat', 'Gramedia Pustaka Utama', '2019-08-19', 'Tersedia'),
(1002, 'Logika Pemrograman Java', 'Abdul Kadir', 'Elex Media Komputindo', '2023-05-10', 'Tersedia'),
(1003, 'Rumus & Fungsi Terapan pada Microsoft Excel', 'Madcoms', 'Andi Publisher', '2023-05-12', 'Tersedia'),
(1005, 'Panduan Lengkap Google Sheets untuk Pemula', 'Yudhy Wicaksono & Solusi Kanto', 'Elex Media Komputindo', '2023-06-16', 'Tersedia'),
(1006, 'Pintar Editing Video dengan Adobe Premiere Pro', 'Su Rahman', 'Elex Media Komputindo', '2023-06-16', 'Tersedia'),
(1007, 'Analisis Bibliometrik dan Penilaian Ahli dalam Model Smart-Dry', 'Puji Rahayu', 'Deepublish', '2023-06-16', 'Tersedia'),
(1008, 'Praktis Membuat Website Sendiri dengan Wordpress', 'Arista Prasetyo Adi', 'Elex Media Komputindo', '2023-05-25', 'Tersedia'),
(1009, '7 Materi Pemrograman Web Modern', 'Rohi Abdulloh', 'Elex Media Komputindo', '2023-05-25', 'Tersedia'),
(1010, 'PEMROGRAMAN DASAR', 'LILIK NUR AINI, dkk', 'Cipta Pustaka Utama', '2023-05-22', 'Tersedia'),
(1011, 'Cara cepat menguasai aplikasi adobe photoshop', 'Johnie Rogers Swanda Pasaribu, M.Kom', 'JEJAK PUSTAKA', '2023-05-09', 'Tersedia'),
(1012, 'Analisis dan Desain Sistem Informasi : Pendekatan Pengembangan System', 'Achmad Jauhari', 'MNC Publishing', '2023-04-11', 'Tersedia'),
(1013, 'Manajemen Proyek Dalam Rekayasa Perangkat Lunak (RPL)', 'Dewi Khairani', 'Deepublish', '2023-04-18', 'Tersedia'),
(1014, 'Belajar Dasar Pemrograman Api : Application Programmable Interface', 'Ari Basuki', 'MNC Publishing', '2023-04-19', 'Tersedia'),
(1015, 'Membuat Dashboard dan Visualisasi Data dengan MS Excel 2021', 'Jubilee Enterprise', 'Elex Media Komputindo', '2023-04-12', 'Tersedia'),
(1016, 'Creators Take Control', 'Edward Lee', 'Harper Collins', '2023-03-26', 'Tersedia'),
(1017, 'Kumpulan Latihan VB.Net', 'Eri Mardiani, Nur Hayati', 'Elex Media Komputindo', '2023-02-22', 'Tersedia'),
(1018, 'Analisis Rangkaian Digital dengan Electronic Workbench 5.12', 'Liany Rompis, ST', 'andi Publisher', '2023-02-15', 'Tersedia'),
(1019, 'PERSPEKTIF KOMUNIKASI, MEDIA DIGITAL, DAN DINAMIKA BUDAYA', 'Suradi, M.Si.', 'Prenada Media', '2023-02-21', 'Tersedia'),
(1020, 'FlashBook: Menciptakan Company Profile dengan Adobe Flash', 'Eko Hadi Wibowo', 'Andi Publisher', '2023-02-15', 'Tersedia'),
(1021, 'Konsep dan Penerapan Algoritma Genetika', 'Vivi Nur Wijayaningrum', 'Syiah Kuala University Press', '2023-02-13', 'Tersedia'),
(1022, 'Modifikasi frekuensi atraktor berbasis gelombang bunyi piknet', 'Nurul Rosana', 'Scopindo Media Pustaka', '2023-02-09', 'Tersedia'),
(1023, 'Konsep dasar ilmu listrik & aplikasinya', 'Marthen Paloboran', 'Scopindo Media Pustaka', '2023-02-09', 'Tersedia'),
(1024, 'Kimia di sekitar kita : praktikum kimia dari rumah', 'Abdul Halim', 'Scopindo Media Pustaka', '2023-02-09', 'Tersedia'),
(1025, 'Konsep dan Penerapan IP Versi 6 dalam Membangun Jaringan Komputer', 'Mulyadi', 'Andi Publisher', '2023-02-02', 'Tersedia'),
(1026, 'Pemrograman Visual', 'Nizwardi Jalinus', 'PT RajaGrafindo Persada', '2023-01-25', 'Tersedia'),
(1027, 'Bermain-main dengan Registry Windows: Windows 11', 'Tri Amperiyanto', 'Elex Media Komputindo', '2022-12-28', 'Tersedia'),
(1028, 'Pemrograman Jaring Nirkabel dengan Network Simulator', 'Nurhayati, Ph.D.', 'PT RajaGrafindo Persada', '2022-11-07', 'Tersedia'),
(1029, 'Membuat Game Scratch Pertamaku', 'Triana Afriani H.E, S.Kom', 'Bhuana Ilmu Populer', '2022-10-19', 'Tersedia'),
(1030, 'Mudah Belajar Desain Printed Circuit Board', 'Fahmizal', 'Deepublish', '2022-10-24', 'Tersedia'),
(1031, 'Pemrograman Basis Data Menggunakan Mysql', 'Rahimi Fitri', 'Deepublish', '2022-08-25', 'Tersedia'),
(1032, 'Dasar Pemrograman Dalam Bahasa C', 'Bernadus Anggo Seno Aji', 'Deepublish', '2022-08-25', 'Tersedia'),
(1033, 'Pemrograman Internet', 'Muhammad Hendra Sunarya', 'Deepublish', '2023-08-25', 'Tersedia'),
(1034, 'Mobile Apps And Organic Waste', 'Hidayatullah', 'Deepublish', '2022-08-24', 'Tersedia'),
(1035, 'SEO Pamungkas', 'Su Rahman', 'Elex Media Komputindo', '2022-04-06', 'Tersedia'),
(1036, 'PEMROGRAMAN WEB UNTUK PEMULA', 'Adi Sulistyo Nugroho', 'Stiletto Book', '2022-07-18', 'Tersedia'),
(1037, 'Algoritma Pemrograman', 'Sigit Susanto Putro', 'MNC Publishing', '2023-06-16', 'Tersedia'),
(1038, 'Pengantar Teknologi Web', 'Drs. Afrizal Zein M.Kom', 'CV. Adanu Abimata', '2022-06-08', 'Tersedia'),
(1039, 'Pemrosesan Sinyal Digital', 'Nemuel Daniel Pah', 'MNC Publishing', '2022-04-26', 'Tersedia'),
(1040, 'Logika Informatika Dan Digital', 'Rosida Vivin Nahari', 'MNC Publishing', '2022-04-26', 'Tersedia');

-- --------------------------------------------------------

--
-- Table structure for table `data_peminjaman`
--

CREATE TABLE `data_peminjaman` (
  `nim` int(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `kode_buku` int(10) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `tanggal_pinjam` date NOT NULL,
  `tanggal_kembali` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` int(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`, `password`) VALUES
(1, 'Wijaya Brata Legiman', '123'),
(2, 'Setiawan Haadza', '123'),
(3, 'Niko Ghani Annafi', '123'),
(4, 'Januar Zamzam', '123'),
(5, 'Rifqy Pratama', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_buku`
--
ALTER TABLE `data_buku`
  ADD PRIMARY KEY (`kode_buku`);

--
-- Indexes for table `data_peminjaman`
--
ALTER TABLE `data_peminjaman`
  ADD PRIMARY KEY (`kode_buku`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_buku`
--
ALTER TABLE `data_buku`
  MODIFY `kode_buku` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1041;

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `nim` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
