package estudos.projetoSolo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;


public class TransactionRequest {

    @NotNull
    @Min(0)
    private double valor;



    public double getValor() {
        return valor;
    }


}
