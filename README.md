<p align="center">
   <a href="https://github.com/kjzieba/Flashcards">
     <img alt="FlashCards" src="src/main/resources/img/logo.svg"/>
   </a>
 </p>

![java](https://img.shields.io/badge/java-17-important)
![swing](https://img.shields.io/badge/swing-gui-blue)
![junit](https://img.shields.io/badge/jUnit-5.9.2-blue)
![build](https://img.shields.io/badge/build-passed-success)
![tests](https://img.shields.io/badge/tests-passed-success)

### Setup

1. Make sure you have `Java 17` and `Maven` installed on your machine.
2. Clone the repository.
```bash
git clone https://github.com/kjzieba/Flashcards.git
```
3. Open terminal in the project directory and run the following command:
```bash
./exec.sh
```

Desktopowa aplikacja do nauki z wykorzystaniem fiszek, oferująca 3 tryby:
 - nauka z wykorzystaniem fiszek
 - tryb nauki, w którym użytkownik zaznacza czy umie czy nie
 - test sprawdzający wiedzę, użytkownik ręcznie wpisuje odpowiedź

Projekt będzie korzystał z następujących wzorców projektowych:

### Wzorce kreacyjne:
 1. Singleton - zapis fiszek do bazy danych, ustawienia aplikacji
 2. Metoda wytwórcza - tworzenie różnych fiszek (z obrazkiem, tekstem czy dźwiękiem)
 
### Wzorce strukturalne:
 3. Fasada - stworzenie uproszczonego interfejsu w celu tworzenia testów i innych trybów nauki
 4. Pełnomocnik - cachowanie danych z bazy
 
### Wzorce behawioralne:
 5. Iterator - przejście po kolei przez kolekcje fiszek
 6. Polecenie - funkcja dodawania, usuwania i edytowania fiszki
 7. Pamiątka - zapamiętanie stanu ostatnio usuniętej fiszki w celu cofnięcia usunięcia
 8. Stan - oflagowanie fiszki jako ważnej, aby wyświetlać ją na początku oraz zwiększyć jej prawdopodobieństwo wystąpienia w trybie nauki
