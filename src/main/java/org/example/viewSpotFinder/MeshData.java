package org.example.viewSpotFinder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Value
public class MeshData {

    List<Node> nodes;
    List<Element> elements;
    List<Value> values;

    @lombok.Value
    public static class Node {
        int id;
        double x;
        double y;
    }

    @lombok.Value
    public static class Element {
        int id;
        @JsonProperty("nodes")
        int[] nodeIds;
    }

    @lombok.Value
    public static class Value {
        @JsonProperty("element_id")
        int elementId;
        double value;
    }
}
