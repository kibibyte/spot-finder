package org.example.viewSpotFinder;

import org.example.viewSpotFinder.MeshData.Element;

import java.util.*;
import java.util.stream.Collectors;

public class ViewSpotFinder {
    public List<MeshData.Value> find(MeshData meshData, int N) {
        List<Element> elements = meshData.getElements();

        Map<Integer, Double> valueMap = new HashMap<>(); //TODO: best if we can store value in element struct
        for (MeshData.Value value : meshData.getValues()) {
            valueMap.put(value.elementId, value.value);
        }

        Map<Element, List<Element>> neighbours = getNeighbours(elements);
        List<Element> viewSpots = getViewSpots(elements, neighbours, valueMap);

        return viewSpots.stream()
                .map(viewSpot -> new MeshData.Value(viewSpot.getId(), valueMap.get(viewSpot.getId())))
                .sorted(((s1, s2) -> Double.compare(
                        valueMap.get(s2.elementId),
                        valueMap.get(s1.elementId)
                )))
                .limit(N)
                .toList();
    }

    private static List<Element> getViewSpots(List<Element> elements, Map<Element, List<Element>> neighbourMap, Map<Integer, Double> valueMap) {
        List<Element> viewSpots = new ArrayList<>();
        for (Element element : elements) {
            List<Element> neighbours = neighbourMap.get(element);
            boolean isViewSpot = true;
            for (Element neighbour : neighbours) {
                if (valueMap.get(neighbour.id) > valueMap.get(element.id)) {
                    isViewSpot = false;
                    break;
                } else if (Objects.equals(valueMap.get(neighbour.id), valueMap.get(element.id))) {
                    if (neighbour.id > element.id) {
                        isViewSpot = false;
                        break;
                    }
                }
            }
            if (isViewSpot) {
                viewSpots.add(element);
            }
        }
        
        return viewSpots;
    }

    private Map<Element, List<Element>> getNeighbours(List<Element> elements) {
        Map<Element, List<Element>> neighboursMap = new HashMap<>();
        for (Element element : elements) {
            List<Element> neighbours = elements.stream().filter(e -> {
                if (e.equals(element)) return false; // exclude "me" element from set
                for (int num1 : e.nodeIds) {
                    for (int num2 : element.nodeIds) {
                        if (num1 == num2) {
                            return true;
                        }
                    }
                }
                return false;
            }).collect(Collectors.toList());
            neighboursMap.put(element, neighbours);
        }

        return neighboursMap;
    }
}
