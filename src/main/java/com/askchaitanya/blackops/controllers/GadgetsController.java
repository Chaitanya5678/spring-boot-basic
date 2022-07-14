package com.askchaitanya.blackops.controllers;

import com.askchaitanya.blackops.models.Gadget;
import com.askchaitanya.blackops.services.GadgetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/gadgets")
public class GadgetsController {

    @Autowired
    GadgetsService gadgetsService;

    @RequestMapping("/{id}")
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
}
