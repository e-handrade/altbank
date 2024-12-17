CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customerId` bigint NOT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  `createdAt` timestamp NULL DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_ibfk_1` (`customerId`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `street` varchar(255) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `zipCode` varchar(20) NOT NULL,
  `country` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `card` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accountId` bigint NOT NULL,
  `cardType` varchar(20) NOT NULL,
  `number` bigint NOT NULL,
  `cvv` varchar(3) NOT NULL,
  `expirationDate` date NOT NULL,
  `deliveryStatus` varchar(20) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  KEY `card_ibfk_1` (`accountId`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `cardReissueRequest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cardId` bigint NOT NULL,
  `reason` varchar(20) NOT NULL,
  `requestedAt` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_card` (`cardId`),
  CONSTRAINT `fk_card` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  `deliveryAddressId` bigint DEFAULT NULL,
  `document` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_delivery_address` (`deliveryAddressId`),
  CONSTRAINT `fk_delivery_address` FOREIGN KEY (`deliveryAddressId`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `cvvWebhookLog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accountId` varchar(255) NOT NULL,
  `cardId` varchar(255) NOT NULL,
  `nextCvv` int NOT NULL,
  `expirationDate` datetime NOT NULL,
  `receivedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `deliveryWebhookLog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `trackingId` varchar(255) NOT NULL,
  `deliveryStatus` varchar(50) NOT NULL,
  `deliveryDate` datetime NOT NULL,
  `deliveryReturnReason` varchar(255) DEFAULT NULL,
  `deliveryAddress` varchar(255) DEFAULT NULL,
  `receivedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci