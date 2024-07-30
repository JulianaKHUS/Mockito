package com.example.geo;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MessageSenderImplTest {

    @Test
    public void testSendMessage() {
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        when(geoService.getCountry("8.8.8.8")).thenReturn("United States");
        when(localizationService.getLocalization("United States")).thenReturn("en_US");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String message = messageSender.sendMessage("8.8.8.8", "Hello, World!");

        assertEquals("Hello, World!", message);
    }

    @Test
    public void testSendLocalMessage() {
        String message = "Hello";
        String country = "US";
        String expected = "Hello, welcome!";

        String actual = messageService.sendLocalMessage(message, country);

        assertEquals(expected, actual);
    }

    @Test
    public void testSendDefaultLocalMessage() {
        String message = "Hello";
        String expected = "Hello, welcome!";

        String actual = messageService.sendLocalMessage(message, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testSendUnknownCountryMessage() {
        String message = "Hello";
        String country = "ZZ";
        String expected = "Hello";

        String actual = messageService.sendLocalMessage(message, country);

        assertEquals(expected, actual);
    }
