package estudos.projetoSolo.controller;


import estudos.projetoSolo.dto.TransactionRequest;
import estudos.projetoSolo.service.TransactionService;
import jakarta.validation.Valid;
import estudos.projetoSolo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @Valid
            @RequestBody
            TransactionRequest request){
        Transaction transaction =
                transactionService.
                        createTransaction(request.getValor(),
                                OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
