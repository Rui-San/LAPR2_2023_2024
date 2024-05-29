package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignVehiclesToEntryAgendaController {

    private AgendaRepository agendaRepository;
    private AuthenticationRepository authenticationRepository;
    private VehicleRepository vehicleRepository;

    public AssignVehiclesToEntryAgendaController() {
        getAgendaRepository();
        getAuthenticationRepository();
        getVehicleRepository();

    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            vehicleRepository = Repositories.getInstance().getVehicleRepository();
        }
        return vehicleRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            agendaRepository = Repositories.getInstance().getAgendaRepository();
        }
        return agendaRepository;
    }

    public List<AgendaTaskDTO> getAgendaTaskDTOManagerList() {
        String managerEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getManagerSpecificAgenda(managerEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {
            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask,
                    TeamMapper.toDTO(CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers())),
                    VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned())));
        }
        return managerSpecificAgendaDTO;
    }

    public List<VehicleDTO> getVehicles(){
        List<VehicleDTO> vehicleListDTO = new ArrayList<>();

        for(Vehicle vehicle : vehicleRepository.getVehicleList()){
            vehicleListDTO.add(VehicleMapper.toDTO(vehicle));
        }
        return vehicleListDTO;
    }

    public Optional<Task> assignVehiclesToTaskAgenda(AgendaTaskDTO agendaTaskDTO, List<VehicleDTO> selectedVehiclesDTO) {
        List<Vehicle> selectedVehicles = VehicleMapper.toDomainList(selectedVehiclesDTO);
        return agendaRepository.assignVehiclesToTaskAgenda(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status, selectedVehicles);
    }
}
