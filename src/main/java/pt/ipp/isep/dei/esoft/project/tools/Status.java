package pt.ipp.isep.dei.esoft.project.tools;

import java.io.Serializable;

public enum Status implements Serializable {
    PENDING,
    PROCESSED,
    PLANNED,
    POSTPONED,
    CANCELED,
    DONE
}
