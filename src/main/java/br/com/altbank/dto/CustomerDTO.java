package br.com.altbank.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String document;
    private AddressDTO deliveryAddress;
}
