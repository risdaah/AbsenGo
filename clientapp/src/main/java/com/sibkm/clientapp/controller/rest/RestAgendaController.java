package com.sibkm.clientapp.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sibkm.clientapp.entity.Agenda;
import com.sibkm.clientapp.service.AgendaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RestAgendaController {
    private AgendaService agendaService;

    // Get All
    @GetMapping
    public List<Agenda> getAll() {
        return agendaService.getAll();
    }

    // Get By Id
    @GetMapping("/{id}")
    public Agenda getByid(@PathVariable Long id) { // Change Integer to Long
        return agendaService.getByid(id);
    }

    // Create
    @PostMapping
    public Agenda create(@RequestBody Agenda agenda) {
        return agendaService.create(agenda);
    }

    // Update
    @PutMapping("/{id}")
    public Agenda update(@PathVariable Long id, @RequestBody Agenda agenda) { // Change Integer to Long
        return agendaService.update(id, agenda);
    }

    // Delete
    @DeleteMapping("/{id}")
    public Agenda delete(@PathVariable Long id) { // Change Integer to Long
        return agendaService.delete(id);
    }
}