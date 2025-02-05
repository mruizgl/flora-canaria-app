package es.iespuerto.mr.flora.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FavoriteTest {

    @Test
    void defaultConstructorInitializesFieldsCorrectly() {
        Favorite favorite = new Favorite();

        assertEquals(0, favorite.getId());
        assertEquals(0, favorite.getUserId());
        assertEquals(0, favorite.getPlantId());
    }

    @Test
    void constructorWithUserIdAndPlantIdInitializesFieldsCorrectly() {
        Favorite favorite = new Favorite(1, 2);

        assertEquals(1, favorite.getUserId());
        assertEquals(2, favorite.getPlantId());
    }

    @Test
    void setIdUpdatesIdCorrectly() {
        Favorite favorite = new Favorite();
        favorite.setId(1);

        assertEquals(1, favorite.getId());
    }

    @Test
    void setUserIdUpdatesUserIdCorrectly() {
        Favorite favorite = new Favorite();
        favorite.setUserId(1);

        assertEquals(1, favorite.getUserId());
    }

    @Test
    void setPlantIdUpdatesPlantIdCorrectly() {
        Favorite favorite = new Favorite();
        favorite.setPlantId(1);

        assertEquals(1, favorite.getPlantId());
    }

    @Test
    void toStringReturnsCorrectStringRepresentation() {
        Favorite favorite = new Favorite(1, 2);
        favorite.setId(3);

        assertEquals("FavoritePlant [id=3, userId=1, plantId=2]", favorite.toString());
    }
}