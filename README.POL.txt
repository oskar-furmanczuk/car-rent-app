1) Uruchomienie

Do uruchominia aplikacji niezbędne będzie:
- zainstalowana i skofigurowana Java 11 
- skonfigurowana baza PostgreSQL (zgodnie z informacjami z pliku "application.properties")

By uruchomić należy otworzyć command-line i znajdując się w katologu z plikem pom.xml należy wywołać:
java -jar .\target\car-rent-app-0.0.1-SNAPSHOT.jar


2) Opis aplikacji

Aplikacja umożliwia zarządzanie wyporzyczalnią sadomochodową. Przez zakładkę "Customers List" możemy edytować, dodawać i usuwać klientów.
Również przez tą zakładkę dostarczona jest funkcjonalnośc wyporzyczenia samochodu. Na jednego klienta przysługuję maksymalnie jeden samochód.
Zakładka "Cars List" umożliwia dodawanie, edycje, usuwanie samochodów.

Aplikacja jest również dostępna przez:
https://car-rent-app-furmanczuk.herokuapp.com/