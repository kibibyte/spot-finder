package org.example.viewSpotFinder;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MeshData {

    public List<Node> nodes;
    public List<Element> elements;
    public List<Value> values;

    @AllArgsConstructor
    public static class Node {
        public int id;
        public double x;
        public double y;
    }

    @AllArgsConstructor
    public static class Element {
        public int id;
        public int[] nodeIds;
    }

    @AllArgsConstructor
    public static class Value {
        public int elementId;
        public double value;
    }
}
