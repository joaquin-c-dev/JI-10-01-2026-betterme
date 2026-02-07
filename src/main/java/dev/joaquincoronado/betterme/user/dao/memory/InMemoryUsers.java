package dev.joaquincoronado.betterme.user.dao.memory;

import dev.joaquincoronado.betterme.user.model.BettermeUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryUsers {
    public static List<BettermeUser> users = new ArrayList<>(Arrays.asList(
        BettermeUser.builder()
            .id(1L)
            .email("admin@betterme.app")
            .name("Admin Principal")
            .password("admin123")
            .role(BettermeUser.Role.ROLE_ADMIN)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(30))
            .updatedAt(LocalDateTime.now().minusDays(1))
            .build(),

        BettermeUser.builder()
            .id(2L)
            .email("juan.perez@betterme.app")
            .name("Juan Pérez")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(20))
            .updatedAt(LocalDateTime.now().minusDays(2))
            .build(),

        BettermeUser.builder()
            .id(3L)
            .email("ana.lopez@betterme.app")
            .name("Ana López")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(18))
            .updatedAt(LocalDateTime.now().minusDays(3))
            .build(),

        BettermeUser.builder()
            .id(4L)
            .email("carlos.mendez@betterme.app")
            .name("Carlos Méndez")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(false)
            .createdAt(LocalDateTime.now().minusDays(25))
            .updatedAt(LocalDateTime.now().minusDays(10))
            .build(),

        BettermeUser.builder()
            .id(5L)
            .email("laura.gomez@betterme.app")
            .name("Laura Gómez")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(12))
            .updatedAt(LocalDateTime.now().minusDays(1))
            .build(),

        BettermeUser.builder()
            .id(6L)
            .email("miguel.hernandez@betterme.app")
            .name("Miguel Hernández")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(8))
            .updatedAt(LocalDateTime.now())
            .build(),

        BettermeUser.builder()
            .id(7L)
            .email("sofia.ramirez@betterme.app")
            .name("Sofía Ramírez")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(15))
            .updatedAt(LocalDateTime.now().minusDays(5))
            .build(),

        BettermeUser.builder()
            .id(8L)
            .email("daniel.torres@betterme.app")
            .name("Daniel Torres")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(false)
            .createdAt(LocalDateTime.now().minusDays(40))
            .updatedAt(LocalDateTime.now().minusDays(20))
            .build(),

        BettermeUser.builder()
            .id(9L)
            .email("paola.martinez@betterme.app")
            .name("Paola Martínez")
            .password("password123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(6))
            .updatedAt(LocalDateTime.now().minusDays(1))
            .build(),

        BettermeUser.builder()
            .id(10L)
            .email("tester@betterme.app")
            .name("Usuario Tester")
            .password("test123")
            .role(BettermeUser.Role.ROLE_USER)
            .active(true)
            .createdAt(LocalDateTime.now().minusDays(2))
            .updatedAt(LocalDateTime.now())
            .build()
    ));
}
