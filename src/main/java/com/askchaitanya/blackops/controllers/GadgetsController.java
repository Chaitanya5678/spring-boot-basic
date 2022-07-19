package com.askchaitanya.blackops.controllers;

import com.askchaitanya.blackops.models.Gadget;
import com.askchaitanya.blackops.services.GadgetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class GadgetsController {

    @Autowired
    GadgetsService gadgetsService;

    @GetMapping("/gadgets/{id}")
    public ResponseEntity<Gadget> getGadgetById(@PathVariable("id") String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Gadget> gadget = gadgetsService.getGadgetById(uuid);
        return gadget.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/gadgets")
    public ResponseEntity<List<Gadget>> listGadgets() {
        List<Gadget> gadgetsList = gadgetsService.listGadgets();
        return new ResponseEntity<>(gadgetsList, HttpStatus.OK);
    }

}
