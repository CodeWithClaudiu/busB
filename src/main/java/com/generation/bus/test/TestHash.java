package com.generation.bus.test;

import com.generation.bus.security.PasswordHasher;

public class TestHash {
        public static void main(String[] args) {

        PasswordHasher hasher = new PasswordHasher();

        System.out.println(hasher.toMD5("Vero")); 
    }
}
