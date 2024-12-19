1. Konfiguracija http klijenta (za ocene 6 i vise)
    U nasem primeru konfiguracija se nalazi u main.go fajlu news-aggregator servisa.
    Kako nas servis ne bi nekontrolisano trosio resurse, sto ga moze usporiti ili dovesti do prestanka rada,
    ogranicavamo broj aktivnih i konekcija u pool-u za svaki servis sa kojim cemo komunicirati (bbc, cnn, nyt).
2. Postavljanje timeout-a na nivou zahteva kojim pozivamo neki drugi servis (za ocene 6 i vise)
    Metoda searchWithoutGlobalTimeout u ArticleHandler-u (news-aggregator servis) dodaje
    timeout od 5s u context koji se prosledjuje metodama koje pozivaju upstream servise (bbc, cnn, nyt).
    Te metode kreiraju http zahtev sa context-om koji im je prosledjen. Nakon toga, implementacija
    hhtp klijenta vodi racuna o tome da prekine izvrsavanje zahteva ako je timeout istekao.
3. Fallback logika kada servis koji pozivamo ne vrati odgovor (za ocene 6 i vise)
    Kada metoda searchWithoutGlobalTimeout u ArticleHandler-u (news-aggregator) dobije gresku
    prilikom poziva nekog servisa (recimo istekao je timeout), ona mora da odluci kakav ce odgovor vratiti
    korisniku. U ovom slucaju, odlucili smo se da samo zanemarimo clanke tog izvora i vratimo clanke
    onih izvora koji su nam uspesno vratili odgovor. Druga opcija bi bila da na neki nacin klijentu naznacimo
    da nismo uspeli da dobijemo odgovor od tog izvora i da pokusa ponovo da posalje zahtev. U projektu, za svaki
    zahtev treba da odaberete strategiju koja vam ima najvise smisla i obrazlozite zasto ste bas nju primenili.
4. Circuit breaker (za ocene 7 i vise)
    Konfiguracija breaker instanci se nalazi u main.go fajlu news-aggregator servisa.
    Upotrebljeni su prilikom poziva svakog izvora (bbc, cnn, nyt) - Search metoda klijentskih struktura.
    Ako iskljucimo recimo bbc servis i pozovemo search nekoliko puta, videcemo kako circuit breaker za taj servis
    menja stanja i prelazi iz zatvorenog u otvoreno, pa u poluotvoreno i nakon toga opet u otvoreno (otvoreno i
    poluotvoreno stanje ce se smenjivati sve dok je bbc servis iskljucen).
5. Retry mehanizam (za ocene 9 i vise)
    Primenjen u Search metodama CNN i NYT klijentskih struktura. Kako bismo testirali retry, u enpoint-u za pretragu
    CNN servisa simulirali smo nedostupnost 20% vremena. Vodite racuna o tome da ako primenjujete retry, endpoint
    koji pozivate mora biti idempotentan (isti zahtev poslat jednom ili vise puta dovodi pozvani servis u isto stanje).
    Kada posaljemo search zahtev nekoliko puta, mozemo videti da je cnn servis nedosupan svaki peti poziv i da se u tom
    trenutku aktivira retry mehanizam.
6. Eksplicitno postavljen timeout za vracanje odgovora korisniku (za ocene 10 i vise)
     Metoda searchWithGlobalTimeout u ArticleHandler-u (news-aggregator servis) vodi racuna o tome koliko je
     vremena preostalo do trenutka kada smo "obecali" da cemo vratiti odgovor korisniku. Ako taj timeout istekne,
     a nismo prikupili clanke sa svih izvora, mozemo vratiti ili do tad dobavljene clanke ili status greske kojim
     bismo naznacili da se desio timeout.
7. Upstream servis odustaje od obrade zahteva ako je istekao timeout (dodatni materijal)
    NYT servis implementira ovu logiku. Kada mu saljemo zahtev, unutar 'Timeout' header-a treba da naznacimo
    koliki timeout dozvoljavamo za obradu zahteva. Ako taj timeout istekne, a nyt servis nije formirao odgovor,
    on treba da odustane od dalje obrade. Ako postavimo timeout na 1500ms, necemo sacekati odgovor cnn servisa,
    ali mozemo videti da on obradjuje zahtev dok ne prodje kroz sve clanke koje ima. Medjutim, ako timeout postavimo
    na 2500ms, videcemo da nyt servis zaustavlja pretragu clanaka cim timeout koji smo mu dali istekne.
