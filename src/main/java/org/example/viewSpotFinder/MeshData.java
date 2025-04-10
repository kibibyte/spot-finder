package org.example.viewSpotFinder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MeshData {

    List<Node> nodes;
    List<Element> elements;
    List<Value> values;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        public int id;
        public double x;
        public double y;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Element {
        public int id;
        @JsonProperty("nodes")
        public int[] nodeIds;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Value {
        @JsonProperty("element_id")
        public int elementId;
        public double value;
    }
}
