# Flashcards

Desktopowa aplikacja do nauki z wykorzystaniem fiszek, oferująca 3 tryby:
 - nauka z wykorzystaniem fiszek
 - test sprawdzający wiedzę
 - tryb nauki, w którym użytkownik ręcznie wpisuje odpowiedź

Projekt będzie korzystał z następujących wzorców projektowych:
 1. Singleton - zapis fiszek do bazy danych, ustawienia aplikacji
 2. Metoda wytwórcza - tworzenie różnych fiszek (z obrazkiem, tekstem czy dźwiękiem)
 3. Fasada - stworzenie uproszczonego interfejsu w celu tworzenia testów i innych trybów nauki
 4. Pełnomocnik - cachowanie danych z bazy
 5. Iterator - przejście po kolei przez kolekcje fiszek
 6. Polecenie - funkcja dodawania, usuwania i edytowania fiszki
 7. Pamiątka - zapamiętanie stanu ostatnio usuniętej fiszki w celu cofnięcia usunięcia
 8. Stan - oflagowanie fiszki jako ważnej, aby wyświetlać ją na początku oraz zwiększyć jej prawdopodobieństwo wystąpienia w trybie nauki
