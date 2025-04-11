package org.example.viewSpotFinder;

import org.example.viewSpotFinder.MeshData.Element;
import org.example.viewSpotFinder.MeshData.Value;

import java.util.*;
import java.util.stream.Collectors;

public class ViewSpotFinder {
    public List<Value> find(MeshData meshData, int limit) {
        List<Value> values = meshData.getValues().stream()
                .sorted(Comparator.comparingDouble((Value v) -> v.value).reversed())
                .toList();

        Map<Integer, Element> elements = meshData.getElements().stream()
                .collect(Collectors.toMap(Element::getId, e -> e));

        return find(values, elements, limit);
    }

    private List<Value> find(List<Value> values, Map<Integer, Element> elements, int limit) {
        List<Value> viewSpots = new ArrayList<>();
        Set<Integer> visitedNodes = new HashSet<>();

        for (Value value : values) {
            Element element = elements.get(value.elementId);
            int[] nodeIds = element.nodeIds;

            boolean visited = Arrays.stream(nodeIds).anyMatch(visitedNodes::contains);

            if (!visited) {
                viewSpots.add(value);
                if (viewSpots.size() == limit) break;
            }

            Arrays.stream(nodeIds).forEach(visitedNodes::add);
        }

        return viewSpots;
    }
}
