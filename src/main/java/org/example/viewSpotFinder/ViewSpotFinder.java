package org.example.viewSpotFinder;

import java.util.*;
import java.util.stream.Collectors;

public class ViewSpotFinder {
    public List<MeshData.Value> find(MeshData meshData, int N) {
        List<MeshData.Value> values = meshData.getValues();
        Map<Integer, Double> valueMap = new HashMap<>();
        for (MeshData.Value value : values) {
            valueMap.put(value.elementId, value.value);
        }

        List<MeshData.Element> elements = meshData.getElements();
        Map<MeshData.Element, Set<MeshData.Element>> neighboursMap = new HashMap<>();
        for (MeshData.Element element : elements) {
            Set<MeshData.Element> neighbours = getNeighbours(element, elements);
            neighboursMap.put(element, neighbours);
        }

        List<MeshData.Element> viewSpots = new ArrayList<>();
        for (MeshData.Element element : elements) {
            Set<MeshData.Element> neighbours = neighboursMap.get(element);
            boolean isViewSpot = true;
            for (MeshData.Element neighbour : neighbours) {
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

        return viewSpots.stream()
                .map(viewSpot -> new MeshData.Value(viewSpot.getId(), valueMap.get(viewSpot.getId())))
                .sorted(((s1, s2) -> Double.compare(
                        valueMap.get(s2.elementId),
                        valueMap.get(s1.elementId)
                )))
                .limit(N)
                .toList();
    }

    private Set<MeshData.Element> getNeighbours(MeshData.Element element, List<MeshData.Element> elements) {
        return elements.stream().filter(e -> {
            if (e.equals(element)) return false; // exclude "me" element from set
            for (int num1 : e.nodeIds) {
                for (int num2 : element.nodeIds) {
                    if (num1 == num2) {
                        return true;
                    }
                }
            }
            return false;
        }).collect(Collectors.toSet());
    }
}
