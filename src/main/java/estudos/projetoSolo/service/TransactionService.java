package estudos.projetoSolo.service;

import estudos.projetoSolo.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction createTransaction(double valor, OffsetDateTime dataHora){
        if(valor<0){
            throw new IllegalArgumentException("Valor não pode ser negativo!");

        }
        Transaction transaction = new Transaction(valor, dataHora);
        transactions.add(transaction);
        return transaction;

    }

    public List<Transaction> getAllTransactions(){
        return Collections.unmodifiableList(transactions);
    }
}
