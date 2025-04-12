package org.example.viewSpotFinder;

import org.example.viewSpotFinder.MeshData.Element;
import org.example.viewSpotFinder.MeshData.Node;
import org.example.viewSpotFinder.MeshData.Value;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewSpotFinderTest {
    @Test
    public void testFindTopViewSpots() {
        //given
        MeshData mesh = getMeshData();

        //when
        List<Value> viewSpots = new ViewSpotFinder().find(mesh, 2);

        //then
        assertEquals(2, viewSpots.size());

        assertEquals(7, viewSpots.get(0).getElementId());
        assertEquals(300.0, viewSpots.get(0).getValue(), 0.0001);

        assertEquals(2, viewSpots.get(1).getElementId());
        assertEquals(200.0, viewSpots.get(1).getValue(), 0.0001);
    }

    private static MeshData getMeshData() {
        List<Node> nodes = Arrays.asList(
                new Node(1, 0, 0),
                new Node(2, 1, 0),
                new Node(3, 0, 1),
                new Node(4, 1, 1),
                new Node(5, 2, 1),
                new Node(6, 1, 1),
                new Node(7, 2, 1),
                new Node(8, 2, 1),
                new Node(9, 2, 1)
        );

        List<Element> elements = Arrays.asList(
                new Element(1, new int[]{1, 2, 3}),
                new Element(2, new int[]{2, 4, 5}),
                new Element(3, new int[]{2, 3, 5}),
                new Element(4, new int[]{2, 4, 5}),
                new Element(5, new int[]{5, 7, 8}),
                new Element(6, new int[]{5, 6, 8}),
                new Element(7, new int[]{7, 8, 9})
        );

        List<Value> values = Arrays.asList(
                new Value(1, 100),
                new Value(2, 200),
                new Value(3, 200),
                new Value(4, 50),
                new Value(5, 40),
                new Value(6, 60),
                new Value(7, 300)
        );

        return new MeshData(nodes, elements, values);
    }
}
