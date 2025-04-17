package org.example.viewSpotFinder;

import org.example.viewSpotFinder.MeshData.Element;
import org.example.viewSpotFinder.MeshData.Value;

import java.util.*;
import java.util.stream.Collectors;

public class ViewSpotFinder_solution3 {
    public List<Value> find(MeshData meshData, int limit) {
        List<Value> values = meshData.getValues().stream()
                .sorted(Comparator.comparingDouble(Value::getValue).reversed())
                .toList();

        Map<Integer, Element> elements = meshData.getElements().stream()
                .collect(Collectors.toMap(Element::getId, e -> e));

        Map<Integer, Set<Integer>> neighbours = getNeighbors(elements);

        return find(values, neighbours, limit);
    }

    private static List<Value> find(List<Value> values, Map<Integer, Set<Integer>> neighbours, int limit) {
        Map<Integer, Double> valueMap = values.stream()
                .collect(Collectors.toMap(Value::getElementId, Value::getValue));
        List<Value> viewSpots = new ArrayList<>();

        for (Value value : values) {
            int elementId = value.getElementId();
            double currentValue = value.getValue();

            boolean isHotSpot = true;
            for (int neighborId : neighbours.get(elementId)) {
                if (valueMap.get(neighborId) > currentValue ||
                        (valueMap.get(neighborId) == currentValue && neighborId < elementId)) {
                    isHotSpot = false;
                    break;
                }
            }

            if (isHotSpot) {
                viewSpots.add(value);
                if (viewSpots.size() == limit) break;
            }
        }
        return viewSpots;
    }

    private static Map<Integer, Set<Integer>> getNeighbors(Map<Integer, Element> elements) {
        Map<Integer, Set<Integer>> nodeToElements = new HashMap<>();
        for (Element element : elements.values()) {
            for (int nodeId : element.getNodeIds()) {
                nodeToElements.computeIfAbsent(nodeId, k -> new HashSet<>()).add(element.getId());
            }
        }

        Map<Integer, Set<Integer>> elementNeighbors = new HashMap<>();
        for (Element element : elements.values()) {
            Set<Integer> neighbors = new HashSet<>();
            for (int nodeId : element.getNodeIds()) {
                neighbors.addAll(nodeToElements.get(nodeId));
            }
            neighbors.remove(element.getId()); // Remove "me"
            elementNeighbors.put(element.getId(), neighbors);
        }

        return elementNeighbors;
    }
}
