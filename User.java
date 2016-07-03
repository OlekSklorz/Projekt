package game;
// DOKOŃCZĘ POTEM 
public class User { // tą klasę zastosuję w MainMenuFrame w przyciskach logowania i rejestracji 
    private String nick = "Anonim";
    public void signIn(){
        // Tu wyświetla ramkę z pytaniem o użytkownika i hasło
        // sprawdza czy taki użytkownik istnieje w pliku i czy hasło poprawne
        // jeśli tak - loguje i zmienia nick, jeśli nie - bład i pozostaje anonimowy 
    }
    public String signUp(){
        // Tu wyświetla ramkę z pytaniem o użytkownika, hasło i potwierdzenie hasła 
        // sprawdza czy takiego użytkownika jeszcze nie ma. W między czasie działa też szyfrowanie
        // jeśli nikogo takiego nie ma - zapisuje do pliku, inaczej bład 
    }
}
