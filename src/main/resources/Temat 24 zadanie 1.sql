CREATE DATABASE transactions CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;

USE transactions;

CREATE TABLE transaction (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
type ENUM ('Wydatek', 'Przychód') NOT NULL ,
description VARCHAR(1000) NOT NULL,
amount DECIMAL(10, 2) NOT NULL,
date DATE NOT NULL
);

INSERT INTO transaction (type, description, amount, date) 
	VALUES 
		('Wydatek', 'Napawa auta', '2500', '2023-01-14'),
		('Przychód', 'Zwrot za zakupy w HM', '300', '2023-02-04'),
		('Przychód', 'Wypłata', '6000', '2023-03-08'),
		('Wydatek', 'Zakupy spożywcze', '325.55', '2023-03-015'),
		('Przychód', 'Wypłata', '6000', '2023-04-01'),
		('Wydatek', 'Ubrania', '1300.97', '2023-04-29'),
		('Przychód', 'Zwrot podatku', '416.56', '2023-05-19'),
		('Wydatek', 'Telewizor', '7500', '2023-06-17');


