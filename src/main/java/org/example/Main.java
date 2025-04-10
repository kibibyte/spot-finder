package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.viewSpotFinder.MeshData;
import org.example.viewSpotFinder.ViewSpotFinder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        MeshData meshData = mapper.readValue(new File(args[0]), MeshData.class);

        List<MeshData.Value> viewSpots = new ViewSpotFinder().find(meshData, Integer.parseInt(args[1]));

        System.out.println(mapper.writeValueAsString(viewSpots));
    }
}