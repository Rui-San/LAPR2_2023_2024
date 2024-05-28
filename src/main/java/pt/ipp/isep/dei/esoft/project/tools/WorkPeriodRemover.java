package pt.ipp.isep.dei.esoft.project.tools;

import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;

public interface WorkPeriodRemover {

    void removeWorkPeriodIfExists(WorkPeriod taskWorkPeriod);
}
