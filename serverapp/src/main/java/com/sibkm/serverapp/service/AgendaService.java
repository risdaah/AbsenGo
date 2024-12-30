package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Agenda;
import com.sibkm.serverapp.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    public List<Agenda> getAllAgendas() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> getAgendaById(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda saveAgenda(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Agenda updateAgenda(Long id, Agenda agendaDetails) {
        return agendaRepository.findById(id).map(agenda -> {
            agenda.setJudul_agenda(agendaDetails.getJudul_agenda());
            agenda.setAgenda(agendaDetails.getAgenda());
            agenda.setTahun_ajaran(agendaDetails.getTahun_ajaran());
            return agendaRepository.save(agenda);
        }).orElseThrow(() -> new RuntimeException("Agenda not found with id " + id));
    }

    public void deleteAgenda(Long id) {
        agendaRepository.deleteById(id);
    }
}
