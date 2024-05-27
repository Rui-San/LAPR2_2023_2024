package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.matdisc.dto.GreenSpaceDTO;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceMapper {

    public GreenSpaceMapper() {

    }

    public static List<GreenSpaceDTO> toDTOlist(List<GreenSpace> greenSpaceList) {
        List<GreenSpaceDTO> greenSpaceDTOS = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            greenSpaceDTOS.add(toDTO(greenSpace));
        }
        return greenSpaceDTOS;
    }


    public static GreenSpaceDTO toDTO(GreenSpace greenSpace) {

        return new GreenSpaceDTO(
                greenSpace.getName(),
                greenSpace.getType(),
                greenSpace.getTotalArea(),
                greenSpace.getAddress().getStreet(),
                greenSpace.getAddress().getStreetNumber(),
                greenSpace.getAddress().getPostalCode(),
                greenSpace.getAddress().getCity(),
                greenSpace.getAddress().getDistrict()

        );
    }

    public static GreenSpace toGreenSpace(GreenSpaceDTO greenSpaceDTO, String managerEmail){
        return new GreenSpace(
                greenSpaceDTO.type,
                greenSpaceDTO.name,
                greenSpaceDTO.street,
                greenSpaceDTO.streetNumber,
                greenSpaceDTO.postalCode,
                greenSpaceDTO.city,
                greenSpaceDTO.district,
                greenSpaceDTO.totalArea,
                managerEmail
        );
    }


}
