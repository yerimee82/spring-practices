package com.poscodx.container.config.videosystem;

import com.poscodx.container.videosystem.Avengers;
import com.poscodx.container.videosystem.DVDPlayer;
import com.poscodx.container.videosystem.DigitalVideoDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DVDPlayerConfig {
    @Bean
    public DigitalVideoDisc avengers() {
        return new Avengers();
    }

    // 주입(Injection)하기 1
    // Bean 생성 메소드를 직접 호출하는 방법
    // 생성자 주입
    @Bean("dvdPlayer01")
    public DVDPlayer dvdPlayer1() {
        return new DVDPlayer(avengers());
    }

    // 주입(Injection)하기 2
    // Parameter로 bean을 전달하는 방법
    // 생성자 주입
    @Bean
    public DVDPlayer dvdPlayer2(DigitalVideoDisc dvd) {
        return new DVDPlayer(dvd);
    }

    // 주입(Injection)하기 3
    // Parameter로 bean을 전달하는 방법
    // setter 주입
    @Bean("dvdPlayer03")
    public DVDPlayer dvdPlayer3(DigitalVideoDisc dvd) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        dvdPlayer.setDvd(dvd);

        return dvdPlayer;
    }
}