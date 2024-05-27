package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class AgendaController {

    public List<AgendaTaskDTO> getAgendaTaskDTOList() {
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getAgenda();
        return AgendaMapper.toDTOlist(agendaTaskList);
    }

}
