package pt.ipp.isep.dei.esoft.project.ui.gui;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

public class Main {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Repositories.save();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }));

        if(!Repositories.load()){
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.run();
            Repositories.save();
        }
        Bootstrap.addUsers();
        MainApp.main(args);
    }
}
