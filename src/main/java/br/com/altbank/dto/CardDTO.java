package br.com.altbank.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class CardDTO {
    private Long accountId;
    @Schema(description = "Type of Card requested", example = "PHYSICAL,VIRTUAL")
    private String cardType;
}
