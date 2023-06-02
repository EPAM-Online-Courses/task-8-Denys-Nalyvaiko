package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {
    Planner planner = new Planner();

    @BeforeEach
    public void setup() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "activityLevel = {0}")
    @EnumSource(ActivityLevel.class)
    void shouldCalculateDailyCaloriesDemand(ActivityLevel activityLevel) {
        // given
        User user = TestConstants.TEST_USER;
        int caloriesDemand = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        // when
        int calculateDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);
        // then
        assertEquals(caloriesDemand, calculateDemand);
    }

    @Test
    void shouldCalculateDailyIntake() {
        // given
        User user = TestConstants.TEST_USER;
        DailyIntake dailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;
        // when
        DailyIntake dailyIntakeByMethods = planner.calculateDailyIntake(user);
        // then
        assertAll(
                () -> assertEquals(dailyIntake.getCalories(), dailyIntakeByMethods.getCalories()),
                () -> assertEquals(dailyIntake.getProtein(), dailyIntakeByMethods.getProtein()),
                () -> assertEquals(dailyIntake.getFat(), dailyIntakeByMethods.getFat()),
                () -> assertEquals(dailyIntake.getCarbohydrate(), dailyIntakeByMethods.getCarbohydrate()));
    }
}
