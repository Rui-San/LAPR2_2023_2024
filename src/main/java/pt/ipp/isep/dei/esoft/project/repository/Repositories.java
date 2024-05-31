package pt.ipp.isep.dei.esoft.project.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class responsible for providing access to various repositories.
 */
public class Repositories implements Serializable{
    /**
     * The singleton instance of Repositories.
     */
    private static Repositories instance;
    /**
     * Repository for collaborators.
     */
    private final CollaboratorRepository collaboratorRepository;
    /**
     * Repository for jobs.
     */
    private final JobRepository jobRepository;
    /**
     * Repository for skills.
     */
    private final SkillRepository skillRepository;
    /**
     * Repository for checkups.
     */
    private final CheckupRepository checkupRepository;
    /**
     * Repository for vehicles.
     */
    private final VehicleRepository vehicleRepository;
    /**
     * Repository for teams.
     */
    private final TeamRepository teamRepository;
    /**
     * Repository for authentication.
     */
    private transient AuthenticationRepository authenticationRepository;

    private final GreenSpaceRepository greenSpaceRepository;

    private final ToDoRepository toDoRepository;

    private final AgendaRepository agendaRepository;


    /**
     * Private constructor to prevent external instantiation.
     */
    private Repositories() {
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        skillRepository = new SkillRepository();
        checkupRepository = new CheckupRepository();
        vehicleRepository = new VehicleRepository();
        teamRepository = new TeamRepository();
        authenticationRepository = new AuthenticationRepository();
        greenSpaceRepository = new GreenSpaceRepository();
        toDoRepository = new ToDoRepository();
        agendaRepository = new AgendaRepository();
    }

    /**
     * Returns the singleton instance of Repositories.
     *
     * @return The singleton instance.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Retrieves the CollaboratorRepository instance.
     *
     * @return The CollaboratorRepository instance.
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    /**
     * Retrieves the instance of JobRepository.
     *
     * @return The JobRepository instance.
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    /**
     * Retrieves the SkillRepository instance.
     *
     * @return The SkillRepository instance.
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    /**
     * Retrieves the CheckupRepository instance.
     *
     * @return The CheckupRepository instance.
     */
    public CheckupRepository getCheckupRepository() {
        return checkupRepository;
    }

    /**
     * Retrieves the VehicleRepository instance.
     *
     * @return The VehicleRepository instance.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Retrieves the TeamRepository instance.
     *
     * @return The TeamRepository instance.
     */
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     *
     * @return The AuthenticationRepository instance.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    public ToDoRepository getToDoRepository() {
        return toDoRepository;
    }

    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }


    public static void save() {
        try {
            String directoryPath = System.getProperty("user.dir") + File.separator + "data";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = directoryPath + File.separator + "data.bin";
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(Repositories.getInstance());
            outStream.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void load() {
        try {
            String directoryPath = System.getProperty("user.dir") + File.separator + "data";
            String filePath = directoryPath + File.separator + "data.bin";
            File file = new File(filePath);

            if (file.exists()) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                try {
                    instance = (Repositories) in.readObject();
                } finally {
                    in.close();
                }
            } else {
                instance = new Repositories();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            instance = new Repositories();
        }
    }
}


