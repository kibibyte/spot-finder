package org.example.viewSpotFinder;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "id")
public class ElementWithValue {
    int id;
    int[] nodeIds;
    double value;
}
