package pt.ipp.isep.dei.esoft.project.tools;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.List;

public class GreenSpaceSorting {

    private enum SortingAlgorithm {
        BubbleSort,
        SelectionSort,
    }

    public static List<GreenSpace> sort(List<GreenSpace> listToSort){
        SortingAlgorithm algorithm = SortingAlgorithm.BubbleSort;
        try {
            algorithm = SortingAlgorithm.valueOf(ApplicationSession.getInstance().getProperties().getProperty(ApplicationSession.SORTING_ALGORITHM));
        } catch (IllegalArgumentException e){
            System.out.println("Invalid sorting algorithm. Using default algorithm.");
        }
        switch (algorithm){
            case SelectionSort:
                return selectionSort(listToSort);
            default:
                return bubbleSort(listToSort);
        }
    }

    private static List<GreenSpace> bubbleSort(List<GreenSpace> sortedList){
        for (int i = 0; i < sortedList.size() - 1; i++){
            for (int j = 0; j < sortedList.size() - 1 - i; j++){
                Double area1 = sortedList.get(j).getTotalArea();
                Double area2 = sortedList.get(j + 1).getTotalArea();
                GreenSpace gs1 = sortedList.get(j);
                GreenSpace gs2 = sortedList.get(j + 1);
                if(area1 < area2){
                    sortedList.set(j, gs2);
                    sortedList.set(j + 1, gs1);
                }
            }
        }
        System.out.println("Bubble Sort used");
        return sortedList;
    }

    private static List<GreenSpace> selectionSort(List<GreenSpace> sortedList) {
        for(int i = 0; i < sortedList.size(); i++ ){
            int minIndex = i;
            for(int j = i + 1; j < sortedList.size(); j++){
                Double area1 = sortedList.get(minIndex).getTotalArea();
                Double area2 = sortedList.get(j).getTotalArea();
                if(area1 < area2){
                    minIndex = j;
                }
            }
            GreenSpace temp = sortedList.get(minIndex);
            sortedList.set(minIndex, sortedList.get(i));
            sortedList.set(i, temp);
        }
        System.out.println("Selection Sort used");
        return sortedList;
    }

}
