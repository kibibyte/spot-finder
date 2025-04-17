package org.example.viewSpotFinder;

import org.example.viewSpotFinder.MeshData.Element;
import org.example.viewSpotFinder.MeshData.Value;

import java.util.*;
import java.util.stream.Collectors;

public class ViewSpotFinder_solution2 {
    public List<Value> find(MeshData meshData, int limit) {
        List<Element> elements = meshData.getElements();

        Map<Integer, Double> valueMap = meshData.getValues().stream()
                .collect(Collectors.toMap(Value::getElementId, Value::getValue));

        Map<Element, List<Element>> neighbours = getNeighbours(elements);
        List<Element> viewSpots = find(elements, neighbours, valueMap);

        return viewSpots.stream()
                .map(viewSpot -> new Value(viewSpot.getId(), valueMap.get(viewSpot.getId())))
                .sorted(Comparator.comparingDouble(Value::getValue).reversed())
                .limit(limit)
                .toList();
    }

    private List<Element> find(List<Element> elements,
                               Map<Element, List<Element>> neighbourMap,
                               Map<Integer, Double> valueMap) {
        List<Element> viewSpots = new ArrayList<>();
        for (Element element : elements) {
            List<Element> neighbours = neighbourMap.get(element);
            boolean isViewSpot = true;
            for (Element neighbour : neighbours) {
                if (valueMap.get(neighbour.getId()) > valueMap.get(element.getId())) {
                    isViewSpot = false;
                    break;
                } else if (valueMap.get(neighbour.getId()).equals(valueMap.get(element.getId()))) {
                    if (neighbour.getId() < element.getId()) {
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
                for (int num1 : e.getNodeIds()) {
                    for (int num2 : element.getNodeIds()) {
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
