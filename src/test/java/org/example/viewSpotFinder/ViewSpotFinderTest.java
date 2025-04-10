package org.example.viewSpotFinder;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewSpotFinderTest {
    @Test
    public void testFindTopViewSpots_simpleMesh() {
        List<MeshData.Node> nodes = Arrays.asList(
                new MeshData.Node(1, 0, 0),
                new MeshData.Node(2, 1, 0),
                new MeshData.Node(3, 0, 1),
                new MeshData.Node(4, 1, 1),
                new MeshData.Node(5, 2, 1)
        );

        List<MeshData.Element> elements = Arrays.asList(
                new MeshData.Element(1, new int[]{1, 2, 3}),
                new MeshData.Element(2, new int[]{2, 3, 4}),
                new MeshData.Element(3, new int[]{2, 4, 5})
        );

        List<MeshData.Value> values = Arrays.asList(
                new MeshData.Value(1, 10),
                new MeshData.Value(2, 20),
                new MeshData.Value(3, 15)
        );

        MeshData mesh = new MeshData(nodes, elements, values);

        ViewSpotFinder finder = new ViewSpotFinder();
        List<MeshData.Value> viewSpots = finder.findViewSpots1(mesh, 2);

        // Assertions
        assertEquals(1, viewSpots.size());
        assertEquals(2, viewSpots.get(0).elementId); // Element 2 has the highest local value
        assertEquals(20, viewSpots.get(0).elementId, 0.0001);
    }
}
