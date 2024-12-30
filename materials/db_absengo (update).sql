-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2024 at 10:37 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_absengo`
--

-- --------------------------------------------------------

--
-- Table structure for table `absensi`
--

CREATE TABLE `absensi` (
  `id_absensi` bigint(20) NOT NULL,
  `tanggal` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `absensi`
--

INSERT INTO `absensi` (`id_absensi`, `tanggal`) VALUES
(1, '2024-09-01'),
(2, '2024-09-02'),
(3, '2024-09-03'),
(4, '2024-09-04'),
(5, '2024-09-05'),
(6, '2024-09-06');

-- --------------------------------------------------------

--
-- Table structure for table `agenda`
--

CREATE TABLE `agenda` (
  `id_agenda` bigint(20) NOT NULL,
  `agenda` varchar(255) DEFAULT NULL,
  `judul_agenda` varchar(255) DEFAULT NULL,
  `tahun_ajaran` varchar(255) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `agenda`
--

INSERT INTO `agenda` (`id_agenda`, `agenda`, `judul_agenda`, `tahun_ajaran`, `id_user`) VALUES
(1, 'Seminar Teknologi Informasi', 'Seminar IT 2024', '2024/2025', 1),
(2, 'Pelatihan Magang Industri', 'Magang untuk Mahasiswa', '2024/2025', 2),
(3, 'Workshop Pemrograman', 'Workshop Python & Java', '2024/2025', 3),
(4, 'Kegiatan Studi Independen', 'Studi Independen di Luar Negeri', '2024/2025', 4),
(5, 'Pekan Kerja Praktik', 'Pameran Kerja Praktik Mahasiswa', '2024/2025', 5),
(6, 'Pelatihan Keamanan Jaringan', 'Keamanan Informasi 2024', '2024/2025', 6);

-- --------------------------------------------------------

--
-- Table structure for table `bidang`
--

CREATE TABLE `bidang` (
  `id_bidang` bigint(20) NOT NULL,
  `nama_bidang` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bidang`
--

INSERT INTO `bidang` (`id_bidang`, `nama_bidang`) VALUES
(1, 'Pemrograman Dasar'),
(2, 'Struktur Data'),
(3, 'Basis Data'),
(4, 'Algoritma dan Pemrograman'),
(5, 'Jaringan Komputer'),
(6, 'Sistem Operasi'),
(7, 'Rekayasa Perangkat Lunak'),
(8, 'Kecerdasan Buatan'),
(9, 'Keamanan Informasi'),
(10, 'Analisis dan Perancangan Sistem'),
(11, NULL),
(12, NULL),
(13, NULL),
(14, NULL),
(15, NULL),
(16, NULL),
(17, 'Struktur Data');

-- --------------------------------------------------------

--
-- Table structure for table `detail_absensi`
--

CREATE TABLE `detail_absensi` (
  `id_absensi` bigint(20) NOT NULL,
  `jam` varchar(255) DEFAULT NULL,
  `keterangan` varchar(255) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_absensi`
--

INSERT INTO `detail_absensi` (`id_absensi`, `jam`, `keterangan`, `id_user`) VALUES
(1, '08:00:00', 'Hadir', 1),
(2, '09:00:00', 'Terlambat', 3),
(3, '10:00:00', 'Izin', 5),
(4, '08:30:00', 'Hadir', 6),
(5, '07:45:00', 'Terlambat', 1),
(6, '09:15:00', 'Hadir', 2);

-- --------------------------------------------------------

--
-- Table structure for table `detail_mbkm`
--

CREATE TABLE `detail_mbkm` (
  `id_mbkm` bigint(20) NOT NULL,
  `jenis` varchar(255) DEFAULT NULL,
  `jurusan` varchar(255) DEFAULT NULL,
  `kampus` varchar(255) DEFAULT NULL,
  `id_bidang` bigint(20) DEFAULT NULL,
  `id_detail_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_mbkm`
--

INSERT INTO `detail_mbkm` (`id_mbkm`, `jenis`, `jurusan`, `kampus`, `id_bidang`, `id_detail_user`) VALUES
(1, 'Magang', 'Informatika', 'Universitas A', 1, NULL),
(2, 'Magang', 'Sistem Informasi', 'Universitas B', 2, NULL),
(3, 'Magang', 'Teknik Komputer', 'Universitas C', 3, NULL),
(4, 'Study Independent', 'Informatika', 'Universitas A', 4, NULL),
(5, 'Study Independent', 'Sistem Informasi', 'Universitas B', 5, NULL),
(6, 'Study Independent', 'Teknik Komputer', 'Universitas C', 6, NULL),
(7, 'Magang', 'Informatika', 'Universitas D', 7, NULL),
(8, 'Magang', 'Sistem Informasi', 'Universitas E', 8, NULL),
(9, 'Study Independent', 'Informatika', 'Universitas D', 9, NULL),
(10, 'Study Independent', 'Sistem Informasi', 'Universitas E', 10, NULL),
(12, 'Magang', 'Informatika', 'UPNV Jatim', NULL, NULL),
(13, 'Magang', 'Informatika', 'UPNV Jatim', NULL, NULL),
(14, 'Magang', 'Informatika', 'UPNV Jatim', NULL, NULL),
(15, 'Magang', 'Informatika', 'UPNV Jatim', NULL, NULL),
(16, 'Magang', 'Informatika', 'UPNV Jatim', NULL, NULL),
(17, 'Magang', 'Informatika', 'UPNV Jatim', 16, NULL),
(18, 'Magang', 'Informatika', 'UPNV Jatim', 17, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `detail_user`
--

CREATE TABLE `detail_user` (
  `id_detail_user` bigint(20) NOT NULL,
  `nama_belakang` varchar(255) DEFAULT NULL,
  `nama_depan` varchar(255) DEFAULT NULL,
  `id_mbkm` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_user`
--

INSERT INTO `detail_user` (`id_detail_user`, `nama_belakang`, `nama_depan`, `id_mbkm`, `id_user`) VALUES
(1, 'Doe', 'John', 1, 1),
(2, 'Smith', 'Jane', 2, 2),
(3, 'Jones', 'Michael', 3, 3),
(4, 'Davis', 'Emily', 4, 4),
(5, 'Wilson', 'David', 5, 5),
(6, 'Miller', 'Susan', 6, 6),
(7, 'Hadid', 'Belia', NULL, NULL),
(8, 'Wahyu', 'Raden', NULL, NULL),
(9, 'Bodo', 'Jaka', NULL, NULL),
(10, 'mas', 'kang', NULL, NULL),
(11, 'banu', 'sam', NULL, NULL),
(12, 'broo', 'damn', 17, NULL),
(13, 'sirr', 'yess', 18, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `priviledge`
--

CREATE TABLE `priviledge` (
  `id_priviledge` bigint(20) NOT NULL,
  `priviledge` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `priviledge`
--

INSERT INTO `priviledge` (`id_priviledge`, `priviledge`) VALUES
(1, 'Create'),
(2, 'Read'),
(3, 'Update'),
(4, 'Delete');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id_role` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `role`) VALUES
(1, 'Mentor'),
(2, 'Peserta');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_role_priviledge`
--

CREATE TABLE `tb_tr_role_priviledge` (
  `role_id` bigint(20) NOT NULL,
  `priviledge_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_tr_role_priviledge`
--

INSERT INTO `tb_tr_role_priviledge` (`role_id`, `priviledge_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_user_role`
--

CREATE TABLE `tb_tr_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_tr_user_role`
--

INSERT INTO `tb_tr_user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 2),
(12, 2),
(13, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `email`, `password`, `username`) VALUES
(1, 'john.doe@example.com', '$2a$12$hFlyvBmGre9pzTqDHiSNLudGK3WsPp8Qs/P3ETqeWNm9Mz9uL8CTW', 'johndoe'),
(2, 'jane.smith@example.com', 'password123', 'janesmith'),
(3, 'michael.jones@example.com', 'password123', 'michaeljones'),
(4, 'emily.davis@example.com', 'password123', 'emilydavis'),
(5, 'david.wilson@example.com', 'password123', 'davidwilson'),
(6, 'susan.miller@example.com', 'password123', 'susanmiller'),
(7, NULL, '$2a$10$yNBJEFrtEXcnDCply3czIuwZAIgXvQcIYj9G09Ai5E/nUE3WU4pk2', 'belia'),
(8, NULL, '$2a$10$thc1pSSozOzLr6J0iYytLOmUshiUPIbG1x7sl/WpA7TBGotayjAiq', 'wahyu'),
(9, NULL, '$2a$10$2mx4TxG2Q7ZCxEgLKOMXnOUCggvigT/qEj7nsIiQOJROuTXR8IIYS', 'jaka'),
(10, NULL, '$2a$10$5JmfreSrDYTfKHmJIYc1/eVIFkSa8DybBvQdHTY8hdNQAzCSoBuOS', 'mas'),
(11, NULL, '$2a$10$1DHkoVQDiaDwaL9dZM6dnOWaJClrwD/PKe7QfPjqe37txKdjjYsWu', 'sam'),
(12, NULL, '$2a$10$rul4vobyIbGDUwvygXhQa.NZXdzgureFoyfmmSw9BLnPTO/GV5LIG', 'damn'),
(13, NULL, '$2a$10$udsVX.FEX3LP.H3DufeMK.wXt15BkCN0PkCZsAATicyRbnSvnyily', 'yess');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absensi`
--
ALTER TABLE `absensi`
  ADD PRIMARY KEY (`id_absensi`);

--
-- Indexes for table `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id_agenda`),
  ADD KEY `FK61m93iyccij5890sijnv3sgtq` (`id_user`);

--
-- Indexes for table `bidang`
--
ALTER TABLE `bidang`
  ADD PRIMARY KEY (`id_bidang`);

--
-- Indexes for table `detail_absensi`
--
ALTER TABLE `detail_absensi`
  ADD PRIMARY KEY (`id_absensi`),
  ADD KEY `FK3mgn4b1c6e6la8y2w3jhfrh8e` (`id_user`);

--
-- Indexes for table `detail_mbkm`
--
ALTER TABLE `detail_mbkm`
  ADD PRIMARY KEY (`id_mbkm`),
  ADD UNIQUE KEY `UKpn5ua6fn45jelxpiwjrx4ro4w` (`id_detail_user`),
  ADD KEY `FKkw9wyphf1t5ej6x2yvj83cg5u` (`id_bidang`);

--
-- Indexes for table `detail_user`
--
ALTER TABLE `detail_user`
  ADD PRIMARY KEY (`id_detail_user`),
  ADD UNIQUE KEY `UKiar1n4g91metdjhs8ivbbmcqy` (`id_mbkm`),
  ADD UNIQUE KEY `UK6mmyqeo8pxjte72850un1ppot` (`id_user`);

--
-- Indexes for table `priviledge`
--
ALTER TABLE `priviledge`
  ADD PRIMARY KEY (`id_priviledge`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `tb_tr_role_priviledge`
--
ALTER TABLE `tb_tr_role_priviledge`
  ADD KEY `FKjvgd57h9bi34rkmej88c0qk9r` (`priviledge_id`),
  ADD KEY `FKlbxa240w9f8dfq4msfh0cvcax` (`role_id`);

--
-- Indexes for table `tb_tr_user_role`
--
ALTER TABLE `tb_tr_user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FK1iapnckyx3hhgmpvr98vhvby` (`role_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absensi`
--
ALTER TABLE `absensi`
  MODIFY `id_absensi` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id_agenda` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `bidang`
--
ALTER TABLE `bidang`
  MODIFY `id_bidang` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `detail_absensi`
--
ALTER TABLE `detail_absensi`
  MODIFY `id_absensi` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `detail_mbkm`
--
ALTER TABLE `detail_mbkm`
  MODIFY `id_mbkm` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `detail_user`
--
ALTER TABLE `detail_user`
  MODIFY `id_detail_user` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `priviledge`
--
ALTER TABLE `priviledge`
  MODIFY `id_priviledge` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `FK61m93iyccij5890sijnv3sgtq` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `detail_absensi`
--
ALTER TABLE `detail_absensi`
  ADD CONSTRAINT `FK3mgn4b1c6e6la8y2w3jhfrh8e` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `FKr5p4791nldp0mwxjtv2tdiflb` FOREIGN KEY (`id_absensi`) REFERENCES `absensi` (`id_absensi`);

--
-- Constraints for table `detail_mbkm`
--
ALTER TABLE `detail_mbkm`
  ADD CONSTRAINT `FKkw9wyphf1t5ej6x2yvj83cg5u` FOREIGN KEY (`id_bidang`) REFERENCES `bidang` (`id_bidang`),
  ADD CONSTRAINT `FKp6nentqgpg4vk35cbj0bat2nx` FOREIGN KEY (`id_detail_user`) REFERENCES `detail_user` (`id_detail_user`);

--
-- Constraints for table `detail_user`
--
ALTER TABLE `detail_user`
  ADD CONSTRAINT `FKd5ovje8obusj36lyhw202yi80` FOREIGN KEY (`id_mbkm`) REFERENCES `detail_mbkm` (`id_mbkm`),
  ADD CONSTRAINT `FKk7rusoeagj2gqk5wtvjbpuebj` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `tb_tr_role_priviledge`
--
ALTER TABLE `tb_tr_role_priviledge`
  ADD CONSTRAINT `FKjvgd57h9bi34rkmej88c0qk9r` FOREIGN KEY (`priviledge_id`) REFERENCES `priviledge` (`id_priviledge`),
  ADD CONSTRAINT `FKlbxa240w9f8dfq4msfh0cvcax` FOREIGN KEY (`role_id`) REFERENCES `role` (`id_role`);

--
-- Constraints for table `tb_tr_user_role`
--
ALTER TABLE `tb_tr_user_role`
  ADD CONSTRAINT `FK1iapnckyx3hhgmpvr98vhvby` FOREIGN KEY (`role_id`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `FKe61u4fme28uvp99mnxbbe5tue` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
