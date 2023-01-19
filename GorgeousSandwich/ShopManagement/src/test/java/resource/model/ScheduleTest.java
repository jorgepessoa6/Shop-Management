package resource.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduleTest {

    private final Schedule schedule = new Schedule();

    @Test
    void getId() {
        UUID id = new UUID(1, 1);
        schedule.setId(id);
        assertEquals(id, schedule.getId());
    }

    @Test
    void getOpeningHours() {
        schedule.setOpeningHours("12:00");
        assertEquals("12:00", schedule.getOpeningHours());
    }

    @Test
    void getClosingHours() {
        schedule.setClosingHours("24:00");
        assertEquals("24:00", schedule.getClosingHours());
    }

    @Test
    void getDayOfTheWeek() {
        schedule.setDayOfTheWeek(DAYOFTHEWEEK.FRIDAY);
        assertEquals(DAYOFTHEWEEK.FRIDAY, schedule.getDayOfTheWeek());
    }

    @Test
    void setId() {
        UUID id = new UUID(1, 1);
        schedule.setId(id);
        assertEquals(id, schedule.getId());
    }

    @Test
    void setOpeningHours() {
        schedule.setOpeningHours("12:00");
        assertEquals("12:00", schedule.getOpeningHours());
    }

    @Test
    void setClosingHours() {
        schedule.setClosingHours("24:00");
        assertEquals("24:00", schedule.getClosingHours());
    }

    @Test
    void setDayOfTheWeek() {
        schedule.setDayOfTheWeek(DAYOFTHEWEEK.FRIDAY);
        assertEquals(DAYOFTHEWEEK.FRIDAY, schedule.getDayOfTheWeek());
    }
}