package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceMapper {

    public GreenSpaceMapper() {

    }

    public static List<GreenSpaceDTO> toDTO(List<GreenSpace> greenSpaceList) {
        List<GreenSpaceDTO> greenSpaceDTOS = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            greenSpaceDTOS.add(toDTO(greenSpace));
        }
        return greenSpaceDTOS;
    }


    public static GreenSpaceDTO toDTO(GreenSpace greenSpace) {


        return new GreenSpaceDTO(greenSpace.getName());


    }


}
