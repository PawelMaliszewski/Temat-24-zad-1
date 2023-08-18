package com.temat24zad1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class OperationController {

    private final TransactionDao transactionDao;

    public OperationController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping("/")
    String home() {
        return "index";
    }

    @GetMapping("/add")
    String addTransaction(Model model) {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "/add";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "/confirmation";
    }

    @GetMapping("/list")
    public String update(@RequestParam("type") TransactionType type, Model model) {
        List<Transaction> transactions = transactionDao.getTransactionsByType(type);
        model.addAttribute("transactions", transactions);
        return "/list";
    }

    @GetMapping("/update")
    public String update() {
        return "/update";
    }

    @PostMapping("/save")
    String saveTransaction(@ModelAttribute Transaction transaction) {
        int add = transactionDao.add(transaction);
        return (add > 0) ? "redirect:/confirmation" : "redirect:/error";
    }

    @GetMapping("/updateById")
    ModelAndView saveTransaction(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("add");
        Optional<Transaction> transaction = transactionDao.findById((long) id);
        transaction.ifPresent(value -> modelAndView.addObject("transaction", transaction.get()));
        transaction.ifPresent(value -> modelAndView.addObject("localDate", value.getDate()));
        if (transaction.isEmpty()) {
            modelAndView.addObject("no_id_found", "No id found");
        }
        return modelAndView;
    }

    @GetMapping("/delete")
    public String delete() {
        return "/delete";
    }

    @GetMapping("/deletebyid")
    String deleteById(@RequestParam int id) {
        int delete = transactionDao.delete(id);
        return (delete > 0) ? "redirect:/confirmation" : "redirect:/error";
    }
}
