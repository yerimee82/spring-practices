package com.poscodx.container.soundsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"classpath:com/poscodx/container/config/soundsystem/applicationContext.xml"})
public class CDPlayerXmlConfigTest {
    @Autowired
    private CDPlayer cdPlayer;

    @Test
    public void testCDPlayNotNull() {
        assertNotNull(cdPlayer);
    }

    @Test
    public void testPlay() {
        assertEquals("Playing 붕붕 by 김하온", cdPlayer.play());
    }
}