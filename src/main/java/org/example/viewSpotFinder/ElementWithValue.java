package org.example.viewSpotFinder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ElementWithValue {
    private final int id;
    private final int[] nodeIds;
    private final double value;
}
