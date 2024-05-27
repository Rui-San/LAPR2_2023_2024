package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class CancelEntryAgendaController {

    private AgendaRepository agendaRepository;

    public CancelEntryAgendaController() {
        getAgendaRepository();
    }

    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    public List<AgendaTaskDTO> getAgenda() {
        List<Task> agenda = agendaRepository.getAgenda();
        return AgendaMapper.toDTOlist(agenda);
    }

    public Optional<Task> cancelTaskAgenda(AgendaTaskDTO agendaTaskDTO) {

        return agendaRepository.updateTaskToCanceled(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status);
    }
}
