package ca.mcmaster.cas.se2aa4.a3.island.CityTests;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.CityType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityTypesTest {
    @Test
    public void testCityType() {
        CityType capital = CityType.CAPITAL;
        assertEquals("Capital", capital.toString());

        CityType city = CityType.MEGACITY;
        assertEquals("City", city.toString());

        CityType hamlet = CityType.HAMLET;
        assertEquals("Hamlet", hamlet.toString());
    }

}
