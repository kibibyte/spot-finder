package org.example.viewSpotFinder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
@Value
public class MeshData {

    List<Node> nodes;
    List<Element> elements;
    List<Value> values;

    @AllArgsConstructor
    public static class Node {
        public int id;
        public double x;
        public double y;
    }

    @AllArgsConstructor
    @lombok.Value
    @EqualsAndHashCode(of = "id")
    public static class Element {
        public int id;
        public int[] nodeIds;
    }

    @AllArgsConstructor
    @lombok.Value
    public static class Value {
        public int elementId;
        public double value;
    }
}
