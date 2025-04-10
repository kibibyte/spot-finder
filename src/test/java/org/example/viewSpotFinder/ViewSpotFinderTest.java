package org.example.viewSpotFinder;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewSpotFinderTest {
    @Test
    public void testFindTopViewSpots() {
        //given
        List<MeshData.Node> nodes = Arrays.asList(
                new MeshData.Node(1, 0, 0),
                new MeshData.Node(2, 1, 0),
                new MeshData.Node(3, 0, 1),
                new MeshData.Node(4, 1, 1),
                new MeshData.Node(5, 2, 1),
                new MeshData.Node(6, 1, 1),
                new MeshData.Node(7, 2, 1),
                new MeshData.Node(8, 2, 1),
                new MeshData.Node(9, 2, 1)
        );

        List<MeshData.Element> elements = Arrays.asList(
                new MeshData.Element(1, new int[]{1, 2, 3}),
                new MeshData.Element(2, new int[]{2, 4, 5}),
                new MeshData.Element(3, new int[]{2, 3, 5}),
                new MeshData.Element(4, new int[]{2, 4, 5}),
                new MeshData.Element(5, new int[]{5, 7, 8}),
                new MeshData.Element(6, new int[]{5, 6, 8}),
                new MeshData.Element(7, new int[]{7, 8, 9})
        );

        List<MeshData.Value> values = Arrays.asList(
                new MeshData.Value(1, 100),
                new MeshData.Value(2, 200),
                new MeshData.Value(3, 200),
                new MeshData.Value(4, 50),
                new MeshData.Value(5, 40),
                new MeshData.Value(6, 60),
                new MeshData.Value(7, 300)
        );

        MeshData mesh = new MeshData(nodes, elements, values);

        //when
        List<MeshData.Value> viewSpots = new ViewSpotFinder().find(mesh, 2);

        //then
        assertEquals(2, viewSpots.size());

        assertEquals(7, viewSpots.get(0).elementId);
        assertEquals(300.0, viewSpots.get(0).value, 0.0001);

        assertEquals(3, viewSpots.get(1).elementId);
        assertEquals(200.0, viewSpots.get(1).value, 0.0001);
    }
}
