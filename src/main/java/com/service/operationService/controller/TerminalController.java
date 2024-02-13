package com.service.operationService.controller;

import com.service.operationService.entity.TerminalEntity;
import com.service.operationService.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping("/createTerminal")
    @ResponseBody
    public ResponseEntity<TerminalEntity> createTerminal(@RequestBody TerminalEntity terminal, @RequestParam(name = "merchantId") Long merchantId) {
        TerminalEntity createdTerminal = terminalService.createTerminal(terminal, merchantId);
        return new ResponseEntity<>(createdTerminal, HttpStatus.CREATED);
    }

    @GetMapping("/listTerminals")
    @ResponseBody
    public ResponseEntity<List<TerminalEntity>> getAllTerminals() {
        List<TerminalEntity> terminals = terminalService.getAllTerminals();
        return new ResponseEntity<>(terminals, HttpStatus.OK);
    }

    @GetMapping("/getByMerchantId")
    @ResponseBody
    public ResponseEntity<List<TerminalEntity>> getTerminalsByMerchant(@RequestParam(name = "merchantId") Long merchantId) {
        List<TerminalEntity> terminals = terminalService.getTerminalsByMerchantId(merchantId);
        return new ResponseEntity<>(terminals, HttpStatus.OK);
    }
}
