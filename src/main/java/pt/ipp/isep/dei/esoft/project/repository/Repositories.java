package pt.ipp.isep.dei.esoft.project.repository;

import java.io.*;

/**
 * Singleton class responsible for providing access to various repositories.
 */
public class Repositories implements Serializable {
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
    /**
     * Repository for green spaces.
     */
    private final GreenSpaceRepository greenSpaceRepository;
    /**
     * Repository for to do tasks.
     */
    private final ToDoRepository toDoRepository;
    /**
     * Repository for agenda tasks.
     */
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
     * @return The CollaboratorRepository instance.
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    /**
     * Retrieves the instance of JobRepository.
     * @return The JobRepository instance.
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    /**
     * Retrieves the SkillRepository instance.
     * @return The SkillRepository instance.
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    /**
     * Retrieves the CheckupRepository instance.
     * @return The CheckupRepository instance.
     */
    public CheckupRepository getCheckupRepository() {
        return checkupRepository;
    }

    /**
     * Retrieves the VehicleRepository instance.
     * @return The VehicleRepository instance.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Retrieves the TeamRepository instance.
     * @return The TeamRepository instance.
     */
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     * @return The AuthenticationRepository instance.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Retrieves the GreenSpaceRepository instance.
     * @return The GreenSpaceRepository instance.
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    /**
     * Retrieves the ToDoRepository instance.
     * @return The ToDoRepository instance.
     */
    public ToDoRepository getToDoRepository(){
        return toDoRepository;
    }

    /**
     * Retrieves the AgendaRepository instance.
     * @return The AgendaRepository instance.
     */
    public AgendaRepository getAgendaRepository(){
        return agendaRepository;
    }

    /**
     * Saves the current state of the Repositories instance to a file.
     * Therefore, it serializes all the repositories.
     */
    public static void save(){
        try {
            FileOutputStream fileOut = new FileOutputStream("data.dat");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(Repositories.getInstance());
            outStream.close();
            fileOut.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Loads the state of the Repositories instance from a file. Therefore, it deserializes all the repositories.
     * @return True if the file was successfully loaded, false otherwise.
     */
    public static boolean load(){
        try{
            FileInputStream file = new FileInputStream("data.dat");
            ObjectInputStream in = new ObjectInputStream(file);

            instance = (Repositories) in.readObject();

            in.close();
            file.close();
            System.out.println("[Log] All repos have been deserialized.");

            Repositories.getInstance().authenticationRepository = new AuthenticationRepository();

            return true;
        }catch (Exception e){
            System.out.println("[Log] First time executing program, data from bootstrap has been saved.");
            return false;
        }
    }

}