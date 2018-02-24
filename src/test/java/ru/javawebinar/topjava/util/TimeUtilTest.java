package ru.javawebinar.topjava.util;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeUtilTest {

    @Test
    public void isBetween() {
        assertTrue(TimeUtil.isBetween(LocalTime.of(9, 0), LocalTime.of(9, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void isNotBetween() {
        assertFalse(TimeUtil.isBetween(LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0)));
    }
}