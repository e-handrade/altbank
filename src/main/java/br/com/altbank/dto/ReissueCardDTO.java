package br.com.altbank.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class ReissueCardDTO {
    @Schema(description = "Reason for reissuing the card", example = "LOST,STOLEN,DAMAGED,OTHER")
    private String reason;
}
