# 🚌 Brianza Bus — Backend

API REST per la gestione digitale del trasporto pubblico locale nella zona Brianza.

## 📋 Descrizione
Brianza Bus è una piattaforma che centralizza la gestione di linee, fermate e corse,
offrendo agli utenti informazioni in tempo reale e geolocalizzazione GPS.

## 👥 Attori del sistema
- **Portal User** (anonimo) — consulta orari, fermate e posizione dei bus in tempo reale
- **Bigliettaio** — crea e stampa biglietti a bordo
- **Amministratore** — gestione completa di linee, fermate e corse

## ⚙️ Funzionalità principali
- Autenticazione sicura tramite JWT Token
- Gestione linee e fermate con coordinate GPS
- Pianificazione corse per stagione (Estate/Inverno) e tipo di giorno (Feriale/Festivo)
- Geolocalizzazione con Google Geocoding API e formula di Haversine
- Monitoraggio corse attive in tempo reale

## 🛠️ Tecnologie utilizzate
- Java / Spring Boot
- Spring Security + JWT
- Database utulizzato MySQL

## 📊 Data Model
Le entità principali sono:
- **Line** — definisce una linea di trasporto
- **Stop** — fermate ordinate per posizione, con coordinate GPS
- **Trip** — corse associate a una linea, differenziate per stagione e tipo di giorno
- **PortalUser** — utenti con ruoli Admin / Bigliettaio

## 👨‍💻 Team
**Backend**
- Veronica Perrella
- CarloMaria Lanna
- Claudiu Chiticaru
- **Frontend**
- Bryton Junior Kengne Kamte
- Antonio Carauddo
## 🔗 Repository correlato
- [Frontend - busf](https://github.com/brytonkamte-max/busf)
