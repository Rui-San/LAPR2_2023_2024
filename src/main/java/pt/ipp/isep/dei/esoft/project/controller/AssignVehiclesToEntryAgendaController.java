package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignVehiclesToEntryAgendaController {

    /**
     * The Agenda Repository.
     */
    private AgendaRepository agendaRepository;

    /**
     * The Vehicle Repository.
     */
    private VehicleRepository vehicleRepository;

    /**
     * Instantiates a new Assign Vehicles to Entry Agenda Controller.
     */
    public AssignVehiclesToEntryAgendaController() {
        getAgendaRepository();
        getVehicleRepository();
    }

    /**
     * Gets the Vehicle Repository.
     * @return Vehicle Repository
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            vehicleRepository = Repositories.getInstance().getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Gets the Agenda Repository.
     * @return Agenda Repository
     */
    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            agendaRepository = Repositories.getInstance().getAgendaRepository();
        }
        return agendaRepository;
    }

    /**
     * Gets the list of TasksDTO of a manager.
     * @return list of TasksDTO of a manager
     */
    public List<AgendaTaskDTO> getAgendaTaskDTOManagerList() {
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getManagerSpecificAgenda(managerEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {
            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask,
                    TeamMapper.toDTO(CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers())),
                    VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned())));
        }
        return managerSpecificAgendaDTO;
    }

    /**
     * Gets the list of VehiclesDTO.
     * @return list of VehiclesDTO
     */
    public List<VehicleDTO> getVehicles(){
        List<VehicleDTO> vehicleListDTO = new ArrayList<>();

        for(Vehicle vehicle : vehicleRepository.getVehicleList()){
            vehicleListDTO.add(VehicleMapper.toDTO(vehicle));
        }
        return vehicleListDTO;
    }

    /**
     * Gets the list of VehiclesDTO assigned to a Task.
     * @param agendaTaskDTO TaskDTO
     * @param selectedVehiclesDTO list of VehiclesDTO
     * @return
     */
    public Optional<Task> assignVehiclesToTaskAgenda(AgendaTaskDTO agendaTaskDTO, List<VehicleDTO> selectedVehiclesDTO) {
        List<Vehicle> selectedVehicles = VehicleMapper.toDomainList(selectedVehiclesDTO);
        return agendaRepository.assignVehiclesToTaskAgenda(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status, selectedVehicles);
    }
}
