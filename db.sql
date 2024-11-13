CREATE DATABASE QuanLyPhongTro;
USE QuanLyPhongTro;

CREATE TABLE PaymentMethod (
    paymentMethodID INT PRIMARY KEY AUTO_INCREMENT,
    paymentMethodName VARCHAR(50) NOT NULL
);

CREATE TABLE RentalRoom (
    roomID INT PRIMARY KEY AUTO_INCREMENT,
    tenantName VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,
    rentalStartDate DATE NOT NULL,
    paymentMethodID INT,
    notes TEXT,
    FOREIGN KEY (paymentMethodID) REFERENCES PaymentMethod(paymentMethodID)
);