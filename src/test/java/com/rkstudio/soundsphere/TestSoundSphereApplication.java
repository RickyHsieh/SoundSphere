package com.rkstudio.soundsphere;

import org.springframework.boot.SpringApplication;

public class TestSoundSphereApplication {

    public static void main(String[] args) {
        SpringApplication.from(SoundSphereApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
