package com.investment.app.server.controller.Transaction;

import java.util.List;

import com.investment.app.server.controller.BaseController;
import com.investment.app.server.entity.Transaction;
import com.investment.app.server.model.TransactionRequest;
import com.investment.app.server.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin
@RestController
@EnableWebMvc
@RequestMapping("/transaction")
public class TransactionController extends BaseController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(path = "/")
    public String getDefaultMappingResponse() {
        return "Default mapping response";
    }

    @GetMapping(path = "/getalltransactions")
    public List<Transaction> getAllTransactions(@RequestParam String user) {
        return transactionService.getAllTransactions();
    }

    @PostMapping(path = "/savetransactions")
    public String saveTransactions(@RequestBody List<TransactionRequest> transactions, @RequestParam String user) {
        return transactionService.saveTransactions(transactions, user);
    }

    @GetMapping(path = "/getcostbasis")
    public List<String> getCostBasis(@RequestParam String user) {
        return transactionService.returnCostBasis();
    }

}
