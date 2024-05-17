package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project._templateFiles.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {
    List<Task> agenda;



    public AgendaRepository(){
        this.agenda = new ArrayList<>();
    }

    public List<Task> getAgenda() {
        return agenda;
    }



}
