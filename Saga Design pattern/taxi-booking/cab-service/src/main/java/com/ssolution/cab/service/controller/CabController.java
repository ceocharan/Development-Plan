package com.ssolution.cab.service.controller;

import com.ssolution.cab.service.entity.Cab;
import com.ssolution.cab.service.service.CabService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cabs")
@AllArgsConstructor
public class CabController {
    CabService cabService;

    @PostMapping
    public ResponseEntity<Cab> saveCab(@RequestBody Cab cab) {
        return new ResponseEntity<>(cabService.saveCab(cab), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cab>> getAllCabs() {
        return new ResponseEntity<>(cabService.getAllCabs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cab> getCabById(@PathVariable UUID id) {
        return new ResponseEntity<>(cabService.getCabById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCabById(@PathVariable UUID id) {
        cabService.deleteCab(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
