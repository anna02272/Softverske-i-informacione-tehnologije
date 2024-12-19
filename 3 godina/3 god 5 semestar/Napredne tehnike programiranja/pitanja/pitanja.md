NAPREDNE TEHNIKE PROGRAMIRANJA

PYTHON

Vežba 1

1. Šta je to script jezik? Navesti nekoliko primera.

- Script jezik je jezik programiranja čije se komande izvršavaju redom po redosledu kako su napisane u skripti.
  Primeri: Python, JavaScript, Ruby

2. Kada je nastao Python programski jezik? Ko je njegov tvorac?

- 1989. , Guido van Rossum

3. Da li je Python statički ili dinamički tipiziran programski jezik?

- Dinamički 

4. Da li Python ima tipove podataka?

- Da (integer, float, string, list, tuple, dict )

5. Navesti nekoliko najznačajnijih Python “implementacija”.

- CPython, Jython, IronPython, PyPy

6. Ukratko objasniti pojam Just-In-Time kompajliranja?

- Tehnika kompajliranja koja se izvršava neposredno pre izvršavanja programa.

7. Koja je referentna implementacija Python programskog jezik?

- CPython

8. Da li je Python (CPython) interpretiran ili kompajliran programski jezik? Objasniti.

- Interpretiran. Prevodi izvorni kod u bytecode koji se zatim izvršava pomoću virtuelne mašine.

9. Šta je programska paradigma?

- Pristup organizaciji i strukturi programa

10. Šta znači da je Python multi-paradigmatski programski jezik?

- Podržava više različitih programskih paradigmi

11. Koje sve paradigme Python podržava?

- Proceduralna, objektno-orijentisana, funkcionalna paradigma, itd

12. Šta su i čemu služe magične metode? Navesti nekoliko primera.

- Metode koje imaju dvostruki donji podvlak i koriste se za definisanje operatora i posebnih funkcionalnosti.
  Primeri: **init**, **str**, **add**.

13. Šta je iterator protokol (u kontekstu programskog jezika Python)? Navesti primer.

- Omogućava iteraciju kroz sekvencijalne podatke. Primer: iter() i next()

14. Koja je razlika između iteratora i generatora? Kada se koristi jedan, a kada drugi?

- Iterator je objekat koji implementira **iter**() vraća sam iterator objekat i **next**() vraća sledeću vrednost iz sekvence i često zahtevaju eksplicitno održavanje stanja.
- kada je potrebno dodatno prilagođavanje iteracije i kada radite sa složenim strukturama podataka

- Generator je posebna vrsta iteratora koja se kreira pomoću funkcije sa yield izrazom i automatski pamti svoje stanje između poziva
- za jednostavne iteracije kroz skupove podataka

15. Koja je razlika između **getattribute** i **getattr**?

- getattribute je magična metoda koja se poziva prilikom pokušaja pristupa atributu objekta
- getattr funkcija omogućava pristup atributu sa mogućnošću postavljanja podrazumevane vrednosti.

16. Implementirati klasu PrirodniBrojevi čiji je zadatak da omogući iteraciju kroz skup prirodnih  
    brojeva. Implementirati ekvivalentni generator.

-

```python
class PrirodniBrojevi:
    def __init__(self):
        self.current = 1

    def __iter__(self):
        return self

    def __next__(self):
        result = self.current
        self.current += 1
        return result


def prirodni_brojevi_generator():
    current = 1
    while True:
        yield current
        current += 1
```

17. Šta predstavlja **dict**?

- Rečnik, mapiranje ključeva na vrednosti

18. Da li u programskom jeziku Python postoje modifikatori pristupa (engl. access modifiers)?

- Nema kao drugi prog. jezici ali se koristi \_ za označavanje privatnih atributa i metoda

19. Koje su dve prednosti deskriptora u odnosu na properties? Navesti primer.

- Dodatna kontrola pristupa: preciznija kontrola kako se čitaju i postavljaju vrednosti atributa.
- Reusability: mogu se definisati jednom i koristiti za više atributa različitih klasa

```python
class DescriptorExample:
    def __get__(self, instance, owner):
        print("Getting the value")
        return instance._value

    def __set__(self, instance, value):
        print("Setting the value")
        instance._value = value

class MyClass:
    descriptor = DescriptorExample()

    def __init__(self, value):
        self._value = value

obj = MyClass(42)
obj.descriptor
obj.descriptor = 99

```

20. Da li su funkcije objekti u programskom jeziku Python?

- Da

21. Da li su funkcije first-class objekti u programskom jeziku Python? Objasniti.

- Da, to znači da se mogu dodeljivati promenljivama, prosleđivati kao argumenti funkcijama i vraćati kao vrednosti funkcija.

22. Šta su to unutrašnje (ugnježdene) funkcije?

- Funkcije definisane unutar drugih funkcija, imaju pristup promenljivama roditeljske funkcije

23. Šta je leksičko zatvorenje?

- Koncept koji omogućava unutrašnjim funkcijama pristup promenljivama roditeljske funkcije čak i nakon što je roditeljska funkcija završila izvršavanje.

24. Šta su dekoratori? Navesti nekoliko primera.

- Funkcije koje primaju drugu funkciju kao argument i proširuju njeno ponašanje

```python
def my_decorator(func):
    def wrapper():
        print("Something is happening before the function is called.")
        func()
        print("Something is happening after the function is called.")
    return wrapper

@my_decorator
def say_hello():
    print("Hello!")

say_hello()
```

25. Da li dekoratori mogu da imaju parametre? Ukoliko mogu, navesti primer.

- Da

```python
def repeat(n):
    def decorator(func):
        def wrapper(*args, **kwargs):
            for _ in range(n):
                func(*args, **kwargs)
        return wrapper
    return decorator

@repeat(3)
def say_hello():
    print("Hello!")

say_hello()

```

26. Šta su to lamda funkcije, a šta predikatske funkcije?

- Lambda funkcije su anonimne funkcije definisane pomoću ključne reči lambda.
- Predikatske funkcije su funkcije koje vraćaju istinitosnu vrednost.

27. Koja dva modula u Python programskom jeziku omogućavaju paradigmu funkcionalnog programiranja?

- map i filter.

28. Šta je to lamda calculus? Ko je njegov tvorac?

- Formalni sistem matematičke logike koji koristi lambda izraze za predstavljanje funkcija
- Alonzo Church

29. Koji je zadatak @total_ordering dekoratora? Navesti primer.

- Automatski generiše sve metode poređenja (npr. <, <=, >, >=) na osnovu definisane jednakosti i jednog od odnosa među objektima

```python
from functools import total_ordering

@total_ordering
class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __lt__(self, other):
        return self.x < other.x and self.y < other.y

p1 = Point(1, 2)
p2 = Point(3, 4)
print(p1 <= p2)  # Generisano automatski
```

30. Čemu služi @wraps dekorator? Navesti primer.
- Za očuvanje metapodataka originalne funkcije prilikom dekoracije

```python
from functools import wraps

def my_decorator(f):
    @wraps(f)
    def wrapper(*args, **kwds):
        print("Calling decorated function")
        return f(*args, **kwds)
    return wrapper

@my_decorator
def example():
    """Docstring"""
    print("Called example function")

print(example.__name__)  # Čuva originalno ime funkcije
print(example.__doc__)   # Čuva originalnu docstring

```

31. U jednom iskazu potrebno je odrediti zbir kvadrata prvih 100 prirodnih brojeva.
    Napomena: Nije dozvoljena upotreba funkcije sum.

- ```python
  result = 0
  for x in range(1, 101):
       result += x*x

  ```

32. U jednom iskazu potrebno je odrediti zbir prvih 100 parnih prirodnih brojeva.
    Napomena: Nije dozvoljena upotreba funkcije sum.

- ```python
  result = 0
  for x in range(2, 201, 2):
      result += x

  ```

33. Funkcija faktorijel1 prima jedan parametar n, koji predstavlja prirodan broj. Napisati ovu funkciju  
    tako da koristi samo jedan iskaz.
    -

    ```python
    import math
    faktorijel1 = lambda n: math.factorial(n)

    ```

34. Funkcija levi_faktorijel2 prima jedan parametar n, koji predstavlja prirodan broj. Napisati ovu  
    funkciju tako da sadrži samo jedan iskaz. Potrebno je iskoristiti funkciju faktorijel iz prethodnog  
    zadatka. Napomena: Nije dozvoljena upotreba funkcije sum.

    -

    ```python
    import math
    levi_faktorijel2 = lambda n: math.factorial(n) if n == 1 else math.factorial(n) + levi_faktorijel2(n - 1)

    ```

Vežba 2

1. U čemu je razlika između kolekcije OrderDict i dict?

- OrderedDict čuva redosled umetanja elemenata
- dict ne garantuje nikakav redosled.

2. Šta defaultdict prima kao parametar prilikom instanciranja?

- Prima funkciju koja će biti korišćena za generisanje podrazumevane vrednosti za nepostojeće ključeve.

3. Koja kolekcija dosta podseća na C-ovsku strukturu? Navesti primer njene upotrebe.

- array.array

```python
import array

arr = array.array('i', [1, 2, 3, 4])
```

4. Funkcija most_common_words prima 2 parametra:
   •text – tekst (string) u kome prebrojava reči
   •n – prirodan broj.
   Ova funkcija treba da u jednom iskazu odredi n reči koje se najčešće javljaju u tekstu. Funkcija  
   treba da vrati listu reči, ali ne i koliko puta se svaka od njih pojavljuje.
   Napomena: Iskazi u kojima se import-uju bublioteke koje dolaze sa standardnom Python  
   implementacijom se ne računaju.

-

```python
def most_common_words(text, n):
    words = text.split()
    word_counts = {}

    for word in words:
        if word in word_counts:
            word_counts[word] += 1
        else:
            word_counts[word] = 1

    sorted_words = sorted(word_counts, key=word_counts.get, reverse=True)
    return sorted_words[:n]
```

5. Šta je Big O notacija?

- Koristi se za analizu performansi algoritama. Odnosi se na gornju granicu rasta vremena izvršavanja ili potrošnje resursa u odnosu na veličinu ulaznih podataka.

6. Koja je vremenska kompleksnost dodavanja elementa na početak liste, a kolika na početak
   kolekcije deque? Obratiti pažnju na funkciju deque_primer.

- Dodavanje na početak liste ima vremensku složenost O(n), dok dodavanje na početak deque ima vremensku složenost O(1).

7. Šta je Diamond problem u kontekstu višestrukog nasleđivanja?
   -Javlja se kada jedna klasa nasleđuje od dve klase koje opet nasleđuju od iste osnovne klase. Ovo može dovesti do konflikata u nasleđivanju.

8. Kako se Diamond problem rešava u Python programskom jeziku?
   -Koristi linearni redosled nasleđivanja: MRO
9. Navesti dva pravila MRO-a?

- Left-to-right rule
- Depth-first rule

10. Šta predstavlja super u programskom jeziku Python?

- Koristi se za pristup metodi iz nadklase: super().metoda().

11. Da li programski jezik Java nativno podržava višestruko nasleđivanje? Kako se ono realizuje?

- Da, putem interfejsa

12. Na osnovu dolenavedenog isečka koda odrediti MRO lanac koji odgovara klasi F.
    1                11
    2 class A:       12 class D(B, C):
    3 pass           13 pass
    4 14
    5 class B(A):    15 class E(B, C):
    6 pass           16 pass
    7                17
    8 class C(A):    18 class F(E, D):
    9 pass           19 pass 
    10 20

- F: F, E, B, C, A, D

13. Da li su klase objekti u programskom jeziku Python?

- Da i imaju tip type

14. Ko je zadužen za kreiranje klasa?

- Korišćenjem ključne reči class

15. Za šta se sve koristi type u Python programskom jeziku? Navesti primere.

- za dobijanje tipa objekta, ili za dinamičko kreiranje klasa.

```python
# Primer za dobijanje tipa objekta
x = 5
print(type(x))  # Output: <class 'int'>

# Primer za dinamičko kreiranje klasa
MyClass = type('MyClass', (), {'x': 5, 'y': 10})

obj = MyClass()
print(obj.x)  # Output: 5
print(obj.y)  # Output: 10

```

16. Za šta je class lepša sintaksa (engl. syntactic sugar)? Navesti primer.

- Dekoratori pružaju lepšu sintaksu prilikom definisanja klasa i metoda. Primer: @staticmethod i @classmethod.

17. Šta su callback funkcije?

- Callback funkcije su funkcije koje se prosleđuju drugim funkcijama kao argumenti i pozivaju se nakon završetka određenih događaja.

18. Koja je razlika između preemptive i non-preemptive multitasking-a?

- Preemptive multitasking dozvoljava operativnom sistemu da prekine izvršavanje jednog zadatka kako bi prešao na izvršavanje drugog.
- Non-preemptive ne dozvoljava takvo prekidanje.

19. Šta su korutine (u programskom jeziku Python)?

- Funkcije koje omogućavaju suspenziju izvršavanja i kasnije nastavak od tačno određene tačke.

20. Šta je “pumpa događaja” (engl. event loop)?

- Mehanizam koji čeka i šalje događaje ili poruke u programu.

21. Šta je asinhrono programiranje?

- Asinhrono programiranje se odnosi na programski model gde zadaci mogu raditi nezavisno, bez čekanja na završetak jedan drugog.

22. Šta je konkurentno programiranje?

- Konkurentno programiranje se odnosi na izvršavanje više zadatka istovremeno, ali ne nužno paralelno.

23. Šta je paralelno programiranje?

- Paralelno programiranje se odnosi na izvršavanje više zadatka istovremeno, koristeći fizičke resurse.

24. Koja je razlika između konkurentnog i paralelnog programiranja?

- Konkurentno programiranje se odnosi na organizaciju, dok paralelno programiranje na izvršavanje.

25. Šta je proces? 

- Proces je instanca programa koji se izvršava nezavisno sa drugim procesima.

26. Šta je nit?
    -Nit je najmanja jedinica izvršavanja unutar procesa.

27. Koja je razlika između procesa i niti?

- Procesi imaju sopstvenu kopiju podataka, dok niti dele isti prostor podataka.

28. Kako komuniciraju niti, a kako procesi?

- Niti dele isti prostor podataka, pa komuniciraju direktno.
- Procesi komuniciraju preko mehanizama kao što su interprocesna komunikacija (IPC).

29. Koja je razlika između generatora i korutine?

- Generatori se koriste za lenjo generisanje vrednosti, dok korutine omogućavaju suspenziju i nastavak izvršavanja.

30. Da li se generatori u programskom jeziku Python mogu ulančavati? Ukoliko mogu, navesti
    primer, u suprotnom navesti razlog zašto ne.

- Da, generatori u Pythonu se mogu ulančavati korišćenjem izraza poput (expression for item in iterable).

31. Šta je profajler (engl. profiler)?

- Alat koji analizira performanse programa, identifikujući delove koji troše najviše resursa.

32. Šta je GIL u programskom jeziku Python?

- GIL (Global Interpreter Lock) ograničava izvršavanje Python niti kako bi se izbegli problemi sa deljenjem podataka.

33. Koliko niti može istovremeno da izvršava kod u jednom Python procesu? Zašto?

- Samo jedna nit zbog GIL-a.

34. Šta je race condition? Navesti primer.

- Race condition je situacija u kojoj ponašanje programa zavisi od toga koji deo koda izvršava prvi. To se obično dešava kada više niti ili procesa pokušavaju istovremeno pristupiti i menjati deljeni podatak.

```python
shared_variable = 0

def increment_shared_variable():
    global shared_variable
    for _ in range(1000000):
        shared_variable += 1

thread1 = threading.Thread(target=increment_shared_variable)
thread2 = threading.Thread(target=increment_shared_variable)

thread1.start()
thread2.start()

thread1.join()
thread2.join()

print(shared_variable)
```

35. Šta je trka do podataka (engl. data race)? Navesti primer.

- Specifičan tip race condition-a gde više niti ili procesa pokušava istovremeno pristupiti i menjati deljeni podatak.

```python
shared_list = []

def append_to_shared_list(item):
    shared_list.append(item)

thread1 = threading.Thread(target=append_to_shared_list, args=('A',))
thread2 = threading.Thread(target=append_to_shared_list, args=('B',))

thread1.start()
thread2.start()

thread1.join()
thread2.join()

print(shared_list)
```

36. Šta je štetno preplitanje? Navesti primer.

- Štetno preplitanje se javlja kada se zadaci međusobno ometaju, što može dovesti do neispravnog ponašanja programa.

```python
shared_variable = 0

def increment_shared_variable():
    global shared_variable
    shared_variable += 1

def decrement_shared_variable():
    global shared_variable
    shared_variable -= 1

thread1 = threading.Thread(target=increment_shared_variable)
thread2 = threading.Thread(target=decrement_shared_variable)

thread1.start()
thread2.start()

thread1.join()
thread2.join()

print(shared_variable)
```

37. Šta je deadlock? Navesti primer.

- Deadlock se javlja kada dva ili više zadatka čekaju resurse koji su zauzeti od strane drugih, čime se stvara ciklus čekanja i blokira napredak.

```python
import threading

lock1 = threading.Lock()
lock2 = threading.Lock()

def task1():
    with lock1:
        with lock2:
            print("Task 1")

def task2():
    with lock2:
        with lock1:
            print("Task 2")

thread1 = threading.Thread(target=task1)
thread2 = threading.Thread(target=task2)

thread1.start()
thread2.start()

thread1.join()
thread2.join()
```

38. Da li se kod asinhronog programiranja javlja problem štetnog preplitanja? Zašto?

- Da, ako niti ne komuniciraju ispravno sa event loop-om. Na primer, nepravilna upotreba deljenih resursa može dovesti do neželjenih rezultata.

39. U čemu je razlika između I/O Bound i CPU Bound programa?

- I/O Bound programi troše više vremena na čekanje na ulaz/izlaz (npr. čitanje/pisanje sa diska ili mreže)
- CPU Bound programi troše više vremena na računskim operacijama.

40. Na koje je sve načine moguće optimizovati I/O Bound programe u programskom jeziku Python?
    Koji je od njih najbolji i zašto?

- Optimizacija uključuje korišćenje asinhronog I/O, više niti ili korutina kako bi se izbeglo čekanje.
- Asinhrono programiranje može povećati efikasnost kada se čeka na I/O operacije.

41. Na koje sve načine je moguće optimizovati CPU Bound programe u programskom jeziku Python?
    Koji je od njih najbolji i zašto?

- Optimizacija uključuje paralelizaciju pomoću više niti(threading) ili procesa(multiprocessing).
- Korišćenje multiprocessing za paralelizaciju često se smatra najboljim pristupom, zbog GIL-a koji ograničava efikasnost više niti. Međutim, gde GIL nije prepreka, korišćenje threading može biti jednostavnije rešenje

42. Šta će se desiti ukoliko se prilikom asinhronog programiranja koriste zadaci koji nisu kooperativni (ne komuniciraju sa event loop-om)?

- Moguće je da jedan zadatak zadrži kontrolu i blokira napredak drugih zadataka ili event loop-a.

43. Šta je jako, a šta slabo skaliranje?

- Jako skaliranje znači da sistem održava proporcionalno povećanje performansi sa povećanjem resursa.
- Slabo skaliranje ukazuje na neefikasnost u povećanju performansi sa dodatnim resursima.

44. Prilikom paralelizacije CPU Bound programa multiprocessing bibliotekom, na koji način se bira broj procesa? Šta sve treba uzeti u obzir prilikom razmatranja?

- Broj procesa treba birati u skladu sa brojem fizičkih jezgara na računaru kako bi se izbeglo nepotrebno preklapanje i poboljšala paralelizacija. Takođe, treba uzeti u obzir i resurse dostupne na sistemu.

45. Dat je sledeći isečak koda.  
    (a) Da li je navedeni program I/O Bound ili CPU Bound?
    (b) Označiti neoptimalan deo koda.
    (c) Na koje se sve načine dati programski kod može optimizovati?
    (d) Implementirati optimalan ekvivalentni programski kod.

- (a) I/O Bound program
- (b) Neoptimalan deo koda: Čekanje na I/O operaciju u session.get(url)
- (c) Optimizacija: Korišćenje asinhronog I/O, više niti ili korutina.
- (d) Optimalan ekvivalentan kod: Implementacija korišćenjem asinhronog I/O.

```python
import time
import requests

def download_site(url, session):
    with session.get(url) as response:
        print(f"Read {len(response.content)} from {url}")

def download_all_sites(sites):
    with requests.Session() as session:
        for url in sites:
            download_site(url, session)

if __name__ == "__main__":
    sites = [
                "http://www.jython.org",
                "http://olympus.realpython.org/dice",
            ] * 80
    start_time = time.time()
    download_all_sites(sites)
    duration = time.time() - start_time
    print(f"Downloaded {len(sites)} in {duration} seconds")
```

46. Dat je sledeći isečak koda.
    (a) Da li je navedeni program I/O Bound ili CPU Bound?
    (b) Označiti neoptimalan deo koda.
    (c) Na koje se sve načine dati programski kod može optimizovati?
    (d) Implementirati optimalan ekvivalentni programski kod.

- (a) CPU Bound program
- (b) Neoptimalan deo koda: Računanje sume kvadrata u find_sum
- (c) Optimizacija: Paralelizacija računanja kvadrata.
- (d) Optimalan ekvivalentan kod: Implementacija korišćenjem multiprocessing za paralelno računanje.

```python

import time

def find_sum(number):
    return sum(i * i for i in range(number))

def find_sums(numbers):
    for number in numbers:
        find_sum(number)

if __name__ == "__main__":
    numbers = [5_000_000 + x for x in range(20)]
    start_time = time.time()
    find_sums(numbers)
    duration = time.time() - start_time
    print(f"Duration {duration} seconds")
```

RUST

Vežba 1

## Početak

1. Koji su nazivi paketa koda u Rust programskom jeziku?

- sanduk (Engl. crate)

2. Gde se postavlja izvorni kod u sanduku (Engl. Crate)?

- u direktorijumu src unutar sanduka

3. Gde Cargo postavlja izvršive fajlove?

- u direktorijum target/debug ili target/release, u zavisnosti od toga da li se izrađuje u debug ili release režimu.

4. Koja je svrha `Cargo.toml` i `Cargo.lock` fajlova?

- Cargo.toml sadrži informacije o projektu, zavisnostima i konfiguracijama
- Cargo.lock fiksira verzije zavisnosti kako bi se obezbedila doslednost između izradnji na različitim sistemima.

## Igra pogađanja brojeva

1. Šta je uvodni skup (Engl. prelude set)?

- Uvodni skup se automatski učitava i obuhvata osnovne stvari poput osnovnih tipova podataka, operatora i osnovnih funkcija.

2. Da li su varijable podrazumevano promenljive ili nepromenjive? Da li isto važi i za reference?

- Nepromenjive (immutable), ali se mogu deklarisati sa mut ključnom rečju kako bi postale promenljive.
- Reference isto

3. Šta predstavlja asocirana funkcija? Navedi par primera.

- Asocirana funkcija je povezana sa tipom, ali nije vezana za instancu tog tipa.
- Primeri uključuju String::from gde from nije vezano za određeni objekat već za sam tip String.

4. Šta je varijacija (variant)?

- Koristi se u kontekstu enumeracije i predstavlja jedan od mogućih stanja koje ta enumeracija može imati.

5. Koja dva tipa sanduka (Engl. crate) postoje? Navedi glavnu razliku izmedju njih.

- bin i lib.
- bin sanduk generiše izvršni fajl
- lib sanduk generiše biblioteku koja se može koristiti od strane drugih programa.

6. Koji je unapred podešeni registar za open source sanduke?

- Crates.io.

7. Kako `Cargo.lock` fajl omogućava ponovljivost izradnje?

- Fiksira verzije zavisnosti, osiguravajući da se prilikom ponovne izgradnje projekta koriste iste verzije zavisnih paketa.

8. Kog tipa je `std::cmp::Ordering` i koje vrednosti može uzeti?

- enumeracija
- Less, Equal ili Greater.

9. Od čega se sastoji `match` izraz?
   -Od niza grana (engl. arms), gde svaka grana definiše moguće uslove i akcije koje treba preduzeti u zavisnosti od zadovoljenog uslova.

10. Pri definisanju celog broja, koji je uobičajni tip koji Rust jezik koristi?

- i32

11. Da li je senčenje (Engl. Shadowing) promenljivih dozvoljeno u istom opsegu (Engl. scope)?

- Da, senčenje promenljivih je dozvoljeno u istom opsegu, gde se nova promenljiva sa istim imenom može deklarisati unutar istog opsega, senčenjem prethodne promenljive.

Vežba 2

## Osnovni programski koncepti

1. Da li su varijable podrazumevano promenljive?  
   -Ne

2. Popravi greške pri kompajliranju sledećeg koda:

```rust

fn main() {

    let x = 5;

    println!("The value of x is: {x}");

    x = 6;

    println!("The value of x is: {x}");

}

```

```rust

fn main() {

    let mut x = 5;

    println!("The value of x is: {x}");

    x = 6;

    println!("The value of x is: {x}");

}

```

3. Koje su glavne razlike između konstanti i nepromenljivih varijabli?
   -Nepromenljivu mozemo da proglasimo promenljivom i menjamo njenu vrednost a konstantu ne mozemo menjati i ne mozemo dodeliti varijablu konstanti

4. Šta predstavlja životni opseg konstante?
   -Do kraja bloka ako se nalazi u njemu a do kraja programa ako se nalazi izvan

5. Odredi rezultat izvršavanja sledećeg koda:

```rust

fn main() {

    let x = 5;
    let x = x + 1;
    {
        let x = x * 2;
        println!("The value of x in the inner scope is: {x}");
    }
    println!("The value of x is: {x}");

}

```

- The value of x in the inner scope is: 12  
  The value of x is: 6

6. Koja je razlika između senčenja promenljivih (Engl. Shadowing) i promenljivosti?
   -Sencenje izbrise varijablu i redeklarise sa novom vrednoscu a promenljivost menja vrednost
   Mozemo imati razlicite tipove a u promenljivosti ne mozemo

7. Da li je Rust statički ili dinamički tipiziran jezik?
   -Staticki

8. Da li sledeći kod proizvodi grešku? Ukoliko da, ispravi je.

```rust

let guess = "42".parse().expect("Not a number!");

```

```rust

let guess: i32 = "42".parse().expect("Not a number!");

```

9. Koja je veličina `isize` i `usize` tipova?
   -Zavisi od arhitekrure racunara, 32 ili 64 bita

10. Da li je `57u8` validna vrednost?
    -Jeste
    u- pozitivni brojevi (u8 - brojevi od 0 do 255)
    i- negativni brojevi (i8 - -128 do 127)

11. Napredno pitanje: Kako se Rust ponaša u slučaju prelivanja vrednosti broja (Engl. Integer Overflow) [Integer Overflow](https://en.wikipedia.org/wiki/Integer_overflow)?
    -Ako prelijemo vrednost (error literal out of range)  
    U produkciji cemo dobiti vrednost koja nije ono sta ocekivamo

12. Koja je veličina `bool` tipa? Koja je veličina `char` tipa?
    bool 1 bajt
    char 4 bajta

13. Koji su primitivni složeni (Engl. compound) tipovi?
    -niz, torka(tuple)

14. Gde se nizovi alociraju u memoriji?
    -Na steku(primitivni tipovi takodje)

15. Šta su elementi iskaza `let a = [3; 5];`? Šta predstavljaju `3` i `5`?
- 3 element niza(default vrednost)
  5 velicina niza
  [3, 3, 3, 3, 3]

16. Šta je ulazna tačka za Rust program?
    -Main

17. Šta znači kada se za Rust tvrdi da je jezik baziran na izrazima (Engl. Expression-based)?
    Iskaz(Statement) ima tacku zarez i ne vraca vrednost
    Izraz(Expression) nema tacku zarez i vraca vrednost

18. Da li opseg predstavlja izraz? Upotpuni odgovor sa primerom.
    -Da, zato sto ima {}
    - Izraz u Rustu je deo koda koji se ocenjuje kao jedna vrednost. Opseg (scope) sam po sebi nije izraz, ali može sadržavati izraze.

```rust
fn main() {
    let x = {
        let y = 5;
        y * 2 // Ovo je izraz koji čini celokupni opseg
    };

    println!("The value of x is: {}", x);
}
```

19. Za šta služi `;`?
- Znak za završetak izraza, da izraz pretvori u iskaz

20. Da li je sledeća definicija validna?

```rust

fn five() -> i32 {
  5
}

```

-Jeste

21. Šta je rezultat izvršavanja sledećeg koda?

```rust

fn main() {

    let x = plus_one(5);
    println!("The value of x is: {x}");
}

fn plus_one(x: i32) -> i32 {
    x + 1;  //treba bez ; da bi funkcija vratila vrednost, treba koristiti return:
}

```
-Gresku u x + 1

22. Šta su linijski komentari u Rust-u?
    //jednolinijski
    /*viselinijski*/ 

23. Šta predstavljaju "ruke" (Engl. arms) u Rust-u?
    -Kao switch case, mozemo imati vise koda
    -Odnose se na različite grane (arm) u match izrazu. match izraz u Rustu se koristi za upoređivanje vrednosti sa različitim mogućim obrascima i izvršavanje odgovarajućeg bloka koda na osnovu podudaranja.

24. Šta je rezultat izvršavanja sledećeg koda?
```rust

fn main() {

    let number = 3;
    if number { //boolean vrednost treba number == 3

        println!("number was three");
    }
}

```
-ocekivan bool a dobijen integer i zato je error

25. Da li ključna reč `if` prestavlja izraz u Rust-u?
    -Da ako vraca vrednost a inace predstavlja izjavu

26. Šta je rezultat ivršavanja sledećeg koda?

```rust

fn main() {
    let condition = true;
    let number = if condition { 5 } else { "six" };
    println!("The value of number is: {number}");
}

```

-Ocekivan integer a dobijen string i zato je error

27. Šta je rezultat izvršavanja sledećeg koda?

```rust

fn main() {
    let mut counter = 0;

    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;
        }
    };
    println!("The result is {result}");
}

```
-The result is 20

28. Za svaki `break` iskaz navedi koja je sledeća linija u toku izvršavanja.

```rust

fn main() {

    let mut count = 0;
    'counting_up: loop {
        println!("count = {count}"); //0 ,1, 2
        let mut remaining = 10;
        loop {
            println!("remaining = {remaining}"); //10,9 // 10,9 //10
            if remaining == 9 {
                break;
            }
            if count == 2 {

                break 'counting_up;
            }
            remaining -= 1;
        }
        count += 1;

    }
    println!("End count = {count}"); //2
}

```
-
count = 0
remaining = 10
remaining = 9
count = 1
remaining = 10
remaining = 9
count = 2
remaining = 10
End count = 2

VEZBA 3

# V3 - Programski jezik Rust

## Vlasništvo i pozajmljivanje

1. Šta predstavlja vlasništvo u programskom jeziku Rust?
- Skup pravila za upravljanje memorijom(Garbage collector u javi)

2. Koja su tri pravila vlasništva?
- Pravilo jednog vlasnika (Rule of Ownership): Svaka vrednost u Rustu ima jednog vlasnika
- Pravilo pozajmljivanja (Borrowing Rule): Ako postoji mutabilna pozajmica, ne može se istovremeno imati ni jedna druga pozajmica tog dela podataka
- Pravilo životnog veka (Lifetime Rule): Referencama mora biti garantovano da će živeti dok se koriste, 
na kraju bloka se brisu

3. Koji je opseg važenja varijable?
   -Do kraja bloka

4. Da li je string literal (`str`) podrazumevano promenljiv ili nepromenjiv?
   -Nepromenljiv

5. Da li su varijable tipa `String` promenljive ili nepromenjive?
   -Promenljive

6. U kom trenutku se navedeni String briše iz memorije?

   ```rust
   {
       let s = String::from("hello"); // s is valid from this point forward

       // do stuff with s
   } //ovde
   ```
   - Kada promenljiva "s" izađe iz opsega

7. Za šta služi ugrađena funkcija `drop` u Rust-u?
   -Brisanje promenljive pre kraja bloka
   fn main() {
   // resource će automatski biti dealociran kada izađe iz opsega,
  // poziva se drop funkcija, što će ispisati poruku.
  }
    // Kraj opsega

8. Koja je interna organizacija `String` objekta? Šta se čuva na steku, a šta se čuva na hipu?
   - Stack- vrednost, niz charova //dodati odgovor
   - String u Rustu organizuje tekst na hipu, dok se metadata (dužina, kapacitet itd.) smešta na stek

9. Koja je razlika između plitkog kopiranja (Eng. shallow-copy) i dubokog kopiranja (Eng. deep-copy)?
- Plitko kopiranje kopira samo reference
- Duboko kopiranje kopira i sve podatke, uključujući i ugnježdene objekte

10. Pri dodeli `let s2 = s1`, da li programski jezik Rust obavlja plitko kopiranje, duboko kopiranje ili nešto treće?
    -Vlasnistvo

11. U kontekstu vlasništva, šta predstavlja pojam premeštanja varijabli?
    - s1 se prebaci u 2 i s1 se brise na steku
    - prenos vlasništva nad podacima iz jedne promenljive u drugu

12. Odredi rezultat izvršavanja sledećeg koda:

    ```rust
    fn main() {
        let s1 = String::from("hello");
        let s2 = s1;

        println!("{}, world!", s1);
      }
    ```

    - s1 vise ne postoji, preneseno je

13. Koja je uloga `Copy` osobine (Eng. trait)?
- Omogućava tipovima da budu kopirani umesto premeštani prilikom dodele vrednosti

14. Koji tipovi mogu da implementiraju `Copy` osobinu? Upotpuni odgovor sa primerom.
- bilo koji ali podrazumevano primitivni
```rust
#[derive(Debug, Copy, Clone)]
struct Point {
    x: f64,
    y: f64,
}

fn main() {
    let p1 = Point { x: 1.0, y: 2.0 };
    let p2 = p1; // Kopiranje, ne premeštanje
    println!("p1: {:?}", p1);
    println!("p2: {:?}", p2);
}
```

15. Da li referenca ima vlasništvo nad objektom koji pokazuje?
    -Nema

16. Šta predstavlja pojam pozajmljivanja?
- Napravi se pozajmica od strane vlasnika ka drugoj varijabli, privremeni prenos prava korišćenja vlasništva nad nekim podacima

17. Da li su reference podrazumevano nepromenjive?
    -Da

18. Da li referenca može da bude promenljiva?
    -Da ako stavimo mut

19. Koliko promenljivih referenci na isti objekat može da postoji u istom opsegu? Zašto?
    -Jedna, zato sto time sprecavamo trku do podataka

20. Pod kojim uslovima se dešava trka do podataka?
    -Ako imamo dve mutabilne reference ili jednu mutabilnu i jednu nemutabilnu

21. Da li mogu da postoje promenljiva i nepromenjiva referenca u istom opsegu u isto vreme?
    -Ne

22. Da li je naredni isečak koda ispravan?

        ```rust
        fn main() {
          let mut s = String::from("hello");

          let r1 = &s;
          let r2 = &s;
          println!("{} and {}", r1, r2);

          let r3 = &mut s;
          println!("{}", r3);
        }
        ```

- Da

23. Koji je opseg (vreme života) reference?
- Do njenog poslednjeg koriscenja

24. Šta predstavlja viseći pokazivač (referenca) (Eng. dangling pointer)?
- Predstavlja referencu koja pokazuje na memorijsku lokaciju koja više nije validna

25. Ispravi grešku u sledećem kodu:

    ```rust
    fn main() {
      let reference_to_nothing = dangle();
    }

    fn dangle() -> &String { 
        let s = String::from("hello");

        &s
    }
    ```

    ```rust
    fn main() {
    let reference_to_nothing = dangle();
    }   

    fn dangle() -> String { 
    let s = String::from("hello");
    
    s
    } //pokušavlo je vratiti referencu na String (&String), 
    //ali to bi dovelo do problema sa vlasništvom jer je String lokalna promenljiva unutar funkcije
    ```

26. Da li je sledeća tvrdnja tačna? _U svakom trenutku možemo imati ili jednu promenjivu ili proizvoljan broj nepromenjivih referenci_
- Jeste

27. Šta predstavlja isečak (Eng. slice)?
- Nepromenljivi deo nekog niza odnoso skupa karaktera ili elemenata
- referenca koja sadrži deo kolekcije

28. Na koji način kreiramo string isečak?
- String isečak se kreira pomoću &str tipa, koji predstavlja referencu na deo stringa ili na ceo string

29. Odredi rezultat izvršavanja sledećeg koda:

    ```rust
    fn first_word(s: &String) -> &str {
      let bytes = s.as_bytes();

      for (i, &item) in bytes.iter().enumerate() {
          if item == b' ' {
              return &s[0..i];
          }
      }

      &s[..]
    }

    fn main() {
        let mut s = String::from("hello world");

        let word = first_word(&s);

        s.clear();

        println!("the first word is: {}", word);
    }
    ```

-Greska zato sto je referenca i mutable    
//first_word funkcija vraća referencu na deo stringa (&s[0..i]), a zatim se string s menja pozivom s.clear(), što uzrokuje da referenca postane nevažeća jer se originalni string modifikuje. Kada se kasnije pokuša koristiti referenca (word) za ispisivanje, doći će do panic-a zbog pokušaja pristupa nevažećoj referenci.

30. Da li string literali predstavljaju string isečke? Ukoliko jesu, da li su promenljivi ili nepromenjivi?
    -Da, nepromenljivi

31. Odredi tip isečka u sledećem kodu:

    ```rust
    let a = [1, 2, 3, 4, 5];

    let slice = &a[1..3];
    ```
- &[i32]

32. Odredi rezultat izvršavanja sledećeg koda:

    ```rust
    fn first_word(s: &str) -> &str {
      let bytes = s.as_bytes();

      for (i, &item) in bytes.iter().enumerate() {
          if item == b' ' {
              return &s[0..i];
          }
      }

      &s[..]
    }

    fn main() {
        let my_string = String::from("hello world");

        let word = first_word(&my_string[0..6]); // "hello"
        let word = first_word(&my_string[..]);  // "hello world"

        let word = first_word(&my_string); // "hello world"

        let my_string_literal = "hello world";

        let word = first_word(&my_string_literal[0..6]); // "hello"
        let word = first_word(&my_string_literal[..]);  // "hello world"

        let word = first_word(my_string_literal);  // "hello world"
    }
    ```

33. Ispravi greške u sledećem kodu:

    ```rust
    fn main() {
      let s = String::from("hello");  // s comes into scope

      takes_ownership(s);
      println!("{}", s);

      let x = 5;

      makes_copy(x);

      println!("{}", x)
    }

    fn takes_ownership(some_string: String) {
        println!("{}", some_string);
    }
    fn makes_copy(some_integer: i32) {
        println!("{}", some_integer);
    }
    ```
- 

    ```rust
    fn main() {
    let s = String::from("hello");  // s comes into scope

    takes_ownership(s.clone()); //Da bi se izbeglo premeštanje vlasništva vrednosti s možemo koristiti metodu clone() kako bismo napravili duboku kopiju stringa s
    println!("{}", s);

    let x = 5;

    makes_copy(x);

    println!("{}", x)
    }

    fn takes_ownership(some_string: String) {
        println!("{}", some_string);
    }
    fn makes_copy(some_integer: i32) {
        println!("{}", some_integer);
    }

    ```

34. Odredi rezultat izvršavanja sledećeg koda:

    ```rust
    fn main() {
      let s1 = gives_ownership();

      let s2 = String::from("hello");

      let s3 = takes_and_gives_back(s2);
    }

    fn gives_ownership() -> String {
        let some_string = String::from("yours");

        some_string
    }

    fn takes_and_gives_back(a_string: String) -> String {
        a_string
    }
    ```
- Rezultat izvršavanja koda neće izazvati grešku, a vlasništvo stringa s2 će biti premešteno u funkciju takes_and_gives_back, ali će ona vratiti vlasništvo natrag u main funkciju, gde će s3 imati vlasništvo nad stringom. Nakon završetka main funkcije, svi stringovi će biti dealocirani jer izlazimo iz opsega u kojem su vlasnici.

VEZBA 4

# V4 - Programski jezik Rust

## Strukture, enumeracije i podudaranje obrazaca

1.  Pojednostavi instanciranje strukture `User` u funkciji `build_user`:

    ```rust
    struct User {
      active: bool,
      username: String,
      email: String,
      sign_in_count: u64,
    }

    fn build_user(email: String, username: String) -> User {
        User {
            active: true,
            username: username,
            email: email,
            sign_in_count: 1,
        }
    }
    ```
    ```rust
    fn build_user(email: String, username: String) -> User {
    User {
        active: true,
        username,
        email,
        sign_in_count: 1,
    }
    }
    ```

2.  Pojednostavi kreiranje instance `user2` iz instance `user1` upotrebom sintakse za ažuriranje. Da li se `user1` može koristiti nakon instanciranja `user2`? Zašto?
- User 1 nece imati vrednosti za acitve i username zato sto se radi premestanje, da zelimo da radi trebalo bi kloniranje da uradimo

        ```rust
        let user2 = User {
              active: user1.active,
              username: user1.username,
              email: String::from("another@example.com"),
              sign_in_count: user1.sign_in_count,
        };
        ```
-

        ```rust
        let user2 = User {
        email: String::from("another@example.com"),
         ..user1   //ovo kopira
        };
        ```
- 2 pr Da, user1 se može koristiti nakon instanciranja user2. Ovo je zato što se vlasništvo podataka, poput String za username, prenosi kroz vlasničke reference, a ne kroz vlasničke vrednosti, prilikom korišćenja sintakse za ažuriranje strukture. Dakle, user2 koristi vlasničke reference na podatke iz user1.

3.  Da li su `black` i `origin` isti tip?
- Nisu isti tip zato sto su razlicite strukture
-  Struktura definiše tip, pa čak i ako dve strukture imaju iste unutrašnje tipove, smatraju se različitim tipovima.
     
        ```rust
        struct Color(i32, i32, i32);
        struct Point(i32, i32, i32);

        fn main() {
            let black = Color(0, 0, 0);
            let origin = Point(0, 0, 0);
        }
        ```

4.  Da li je naredni isečak koda ispravan?
    -Jeste

        ```rust
        struct AlwaysEqual;

        fn main() {
            let subject = AlwaysEqual;
        }
        ```

5.  Kreiraj strukturu `Rectangle` sa dva polja: `height` i `weight`. Nakon toga, kreiraj funkciju koja izračunava površinu pravougaonika na osnovu instance pravougaonika. Ispisati instancu strukture `Rectangle` upotrebom #[derive] atributa.

    ```rust

     #[derive(Debug)]
    struct Rectangle {
     height: u32,
     width: u32
    }

     fn area(rectangle: &Rectangle) -> u32 {
         rectangle.width * rectangle.height
     }

    fn main(){
     let rect1 = Rectangle{height: 1, width: 2};
     let result = area(&rect1);
     println!("{rect1:?}");
      println!("{result}");
    }

    ```

6.  Implementiraj gore navedeni zadatak upotrebom metode umesto asocirane funkcije.

```rust
    #[derive(Debug)]
  struct Rectangle {
   height: u32,
   width: u32
  }

  impl Rectangle {
       fn area(rectangle: &Rectangle) -> u32 {
           rectangle.width * rectangle.height
       }
       fn area_method(&self) -> u32 {   //&mut self - mutable referenca na self = mozemo menjati
           self.width * self.height
       }
       fn new_width(&self, width: u35) -> Rectangle {
           Rectangle{
               width,
               ..self
           }
       }
  }

  fn main(){
 //  let s1 = String::from("test"); // ovako se pozivaju pridruzene funkcije
   let rect1 = Rectangle{height: 1, width: 2};
   let result = Rectangle::area(&rect1);
   let result_method = rect1.area_method();
   let rect2 = rect1.new_width(10);
   println!("{rect1:?}");
   println!("{result}");
   println!("{result_method}");
  }

```

7. Nad kojim sve tipovima se može definisati metoda?
   - Nad bilo kojom strukturom
   - strukture, enumeracije, ili tipovi koji implementiraju određene interfejse

8. Koja je razlika između `self` i `Self`?
   -Self pokazuje na tip impl bloka, zamenica za strukturu , koristi za dinamičko referenciranje na tip implementacije.
   ako se refaktorise ne moramo svuda menjati nego samo ime impl bloka
   -self je konkretna instanca

9. Da li metoda uzima vlasništvo nad `self`?
   -Da

10. Kada bi metoda trebala da uzme vlasništvo nad `self`?
    - Kad nam instanca kojom pozivamo metodu vise ne treba, tj kada želi preuzeti kontrolu nad resursima koje poseduje instanca (objekat) tog tipa i kada želi biti odgovorna za oslobađanje tih resursa

11. Implementiraj asociranu funkciju nad `Rectangle` koja se zove `square` i koja kreira instancu `Rectangle` koja predstavlja kvadrat (dimenzije obe stranice su iste). Primer upotrebe funkcije je: `let sq = Rectangle::square(3)`;

````rust
 let sq = Rectangle::square(13);
  ```

  -
  ```rust
  #[derive(Debug)]
struct Rectangle {
    height: u32,
    width: u32,
}

impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }

    fn square(size: u32) -> Rectangle {
        Rectangle {
            height: size,
            width: size,
        }
    }
}

fn main() {
    let sq = Rectangle::square(13);
    println!("{:?}", sq);
    println!("Area: {}", sq.area());
}
```

12. Da li struktura može da ima više `impl` blokova?
-Moze i nezavisni su ali ne podrzava vise metoda sa istim imenom

13. Šta predstavljaju enumeracije u Rust-u?
-Predstavljaju tipove koji mogu da se sastoje od dugih tipova

14. Pojednostavi sledeći primer upotrebom samo enumeracije:

  ```rust
  fn main() {
    enum IpAddrKind {
        V4,
        V6,
    }

    struct IpAddr {
        kind: IpAddrKind,
        address: String,
    }

    let home = IpAddr {
        kind: IpAddrKind::V4,
        address: String::from("127.0.0.1"),
    };

    let loopback = IpAddr {
        kind: IpAddrKind::V6,
        address: String::from("::1"),
    };
  }
  ```

  ```rust
  fn main() {
    enum IpAddr {
        V4(string),
        V6(string),
    }

  let home = IpAddr::V4(String::from("127.0.0.1"));
  let loopback = IpAddr::V6(String::from("::1"));

  }
  ```

15. Da li sledeći kod proizvodi grešku? Ukoliko da, ispravi je.
- Ne

  ```rust
  fn main() {
    enum IpAddr {
        V4(u8, u8, u8, u8),
        V6(String),
    }

    let home = IpAddr::V4(127, 0, 0, 1);

    let loopback = IpAddr::V6(String::from("::1"));
  }
  ```

16. Da li Rust ima koncept `null` vrednosti?
-Ne ima None, tj enum OPTIONS koji ima none i some

17. Ispravi grešku u sledećem kodu:

  ```rust
  fn main() {
    let _absent_number = None;
  }
  ```


  ```rust
  fn main() {
    let _absent_number: option<i32> = None;
    //_absent_number = Some(5);
    let result = _absent_number.unwrap() + 6; //ako unwrapujemo none mozemo da dobijemo nullpointer exception
    match _absent_number {
      None => println!("Greska!"),
      Some(x) => {
          let result = x + 6;
          println!("{result}"),
      }
    }
    //println!("{_absent_number:?}")
  }
  ```

18. Koje su prednosti upotrebe `Option<T>` umesto `null`?
-Prednost je sto je to enum sa dve opcije pa vidimo gde se moze none izazvati

19. Šta je rezultat izvršavanja sledećeg koda?
-State quarter from Alaska

  ```rust
  #[derive(Debug)]
  enum UsState {
      Alabama,
      Alaska,
      // --snip--
  }

  enum Coin {
      Penny,
      Nickel,
      Dime,
      Quarter(UsState),
  }

  fn value_in_cents(coin: Coin) -> u8 {
      match coin {
          Coin::Penny => 1,
          Coin::Nickel => 5,
          Coin::Dime => 10,
          Coin::Quarter(state) => {
              println!("State quarter from {:?}!", state);
              25
          }
      }
  }

  fn main() {
      value_in_cents(Coin::Quarter(UsState::Alaska));
  }
  ```

20. Šta je rezultat izvršavanja sledećeg koda?
- five: Some(5), six: Some(6), none: None


  ```rust
  fn main() {
    fn plus_one(x: Option<i32>) -> Option<i32> {
        match x {
            None => None,
            Some(i) => Some(i + 1),
        }
    }

    let five = Some(5);
    let six = plus_one(five);
    let none = plus_one(None);

    println!("five: {:#?}, six: {:#?}, none: {:#?}", five, six, none)
  }
  ```

21. Šta je rezultat izvršavanja sledećeg koda?
-Greska zato sto moramo pokriti sve mogucnosti option enuma a ne pokrivamo none

  ```rust
  fn main() {
    fn plus_one(x: Option<i32>) -> Option<i32> {
        match x {
            Some(i) => Some(i + 1),
        }
    }

    let five = Some(5);
    let six = plus_one(five);
    let none = plus_one(None);

    println!("five: {:#?}, six: {:#?}, none: {:#?}", five, six, none)
  }
  ```

22. Ispravi grešku u sledećem kodu:

  ```rust
  fn main() {
    let dice_roll = 9;
    match dice_roll {
        3 => add_fancy_hat(),
        7 => remove_fancy_hat(),
    }

    fn add_fancy_hat() {}
    fn remove_fancy_hat() {}
  }
  ```

   ```rust
  fn main() {
    let dice_roll = 9;
    match dice_roll {
        3 => add_fancy_hat(),
        7 => remove_fancy_hat(),
        _ => {
            println!("Svaki drugi broj");
        }
    }
}

fn add_fancy_hat() {
}

fn remove_fancy_hat() {
}

  ```

23. Iskoristi `if let` umesto `match` izraza u sledećem primeru:

  ```rust
  fn main() {
    let config_max = Some(3u8);
    match config_max {
        Some(max) => println!("The maximum is configured to be {}", max),
        _ => (),
    }
  }
  ```

   ```rust
  fn main() {
    let config_max = Some(3u8);

    if let Some(local_var) = config_max{
      println!("The maximum is configured to be {}", local_var),
    }
  }
  ```

24. Koja je razlika `if let` u odnosu na `match`?
- if let trazi jedno podudaranje tj jednu varijantu iz enuma
- match opsežniji i koristi se kada je potrebno rukovati sa više različitih obrazaca

25. Iskoristi `if let` umesto `match` izraza u sledećem primeru:

  ```rust
  #[derive(Debug)]
  enum UsState {
      Alabama,
      Alaska,
      // --snip--
  }

  enum Coin {
      Penny,
      Nickel,
      Dime,
      Quarter(UsState),
  }

  fn main() {
      let coin = Coin::Penny;
      let mut count = 0;
      match coin {
          Coin::Quarter(state) => println!("State quarter from {:?}!", state),
          _ => count += 1,
      }
      println!("The count is: {}", count)
  }
  ```
- 
```rust
#[derive(Debug)]
enum UsState {
    Alabama,
    Alaska,
    // --snip--
}

enum Coin {
    Penny,
    Nickel,
    Dime,
    Quarter(UsState),
}

fn main() {
    let coin = Coin::Penny;
    let mut count = 0;
    
    if let Coin::Quarter(state) = coin {
        println!("State quarter from {:?}!", state);
    } else {
        count += 1;
    }
    
    println!("The count is: {}", count);
}
```
````

VEZBA 5

# V5 - Programski jezik Rust

## Pametni pointeri, kolekcije

1. Šta predstavlja pointer?
- Promenljivu koja sadrži memorijsku adresu

2. Koji je najčešća vrsta pointera u Rust-u?
- Referenca

3. Šta predstavlja pametni pointer (Engl. smart pointer)?
- Pokazivac koji sadrzi dodatne metapodatke tj ima jos neke infomacije pored pokazivanja
- Struktura koja ne samo da sadrži memorijsku adresu, već ima i dodatne metapodatke, kao i određenu logiku ponašanja

4. Koja je glavna razlika između pametnog pointera i reference?
- Pametni pokazivac je vlasnik podataka koje pokazuje
- Referenca nema vlasnistvo nad podacima, već samo pristupa postojećim podacima.

5. Da li su `String` i `Vec<T>` pametni pointeri?
- Da

6. Preko čega se pametni pointeri obično implementiraju?
- Strukture

7. Šta predstavlja `Box<T>`?
- Box je pametni pokazivac koji uzima vlasnistvo nad podacima smeštenim na heapu

8. U kojim situacijama se koristi `Box<T>`?
- Koristimo ga kad želimo preneti vlasništvo podataka na heapu i obezbediti da se ti podaci brišu kada je Box(deo koji ga sadrži) izvan opsega.

9. Da li sledeći kod proizvodi grešku? Zašto? Više detalja o `Cons` listi na sledećem [linku](https://doc.rust-lang.org/book/ch15-01-box.html#more-information-about-the-cons-list).

   ```rust
   enum List {
       Cons(i32, List),
       Nil,
   }

   use crate::List::{Cons, Nil};

   fn main() {
       let list = Cons(1, Cons(2, Cons(3, Nil)));
   }
   ```
- Da, ovaj kod proizvodi grešku jer List treba da sadrži tip Box<T> kako bi se rekurzija mogla koristiti sa promenljivom fiksnog veličine.

10. Ispravi grešku u prethodnom kodu upotrebom `Box<T>`.
- 
```rust
enum List {
    Cons(i32, Box<List>),
    Nil,
}

use crate::List::{Cons, Nil};

fn main() {
    let list = Cons(1, Box::new(Cons(2, Box::new(Cons(3, Box::new(Nil))))));
}
```
11. Koja je uloga `Deref` osobine?
- Omogućava pristup vrednostima unutar pametnog pokazivača pomoću dereferenciranja

12. Ispravi grešku u sledećem kodu:

    ```rust
    fn main() {
        let x = 5;
        let y = &x;

        assert_eq!(5, x);
        assert_eq!(5, y);
    }
    ```
- 
    ```rust
    fn main() {
        let x = 5;
        let y = &x;

        assert_eq!(5, x);
        assert_eq!(5, *y);
    }
    ```

13. Koja je razlika između prethodnog koda i sledećeg koda?

    ```rust
    fn main() {
        let x = 5;
        let y = Box::new(x);

        assert_eq!(5, x);
        assert_eq!(5, *y);
    }
    ```
- Razlika je u tome što Box uzima vlasništvo nad vrednošću x, pa se pristupanje vrednosti izvodi korišćenjem *y

14. Šta predstavlja `struct MyBox<T>(T);`?
- Strukturu koja omogućava kreiranje pametnog pokazivača MyBox koji sadrži vrednost tipa T.

15. Ispravi grešku u sledećem kodu implementiranjem `Deref` osobine:

    ```rust
    struct MyBox<T>(T);

    impl<T> MyBox<T> {
        fn new(x: T) -> MyBox<T> {
            MyBox(x)
        }
    }


    fn main() {
        let x = 5;
        let y = MyBox::new(x);

        assert_eq!(5, x);
        assert_eq!(5, *y);
    }
    ```
    
-
```rust
use std::ops::Deref;

struct MyBox<T>(T);

impl<T> Deref for MyBox<T> {
    type Target = T;

    fn deref(&self) -> &Self::Target {
        &self.0
    }
}

impl<T> MyBox<T> {
    fn new(x: T) -> MyBox<T> {
        MyBox(x)
    }
}

fn main() {
    let x = 5;
    let y = MyBox::new(x);

    assert_eq!(5, x);
    assert_eq!(5, *y);
}
```

16. Šta predstavlja `Deref` koercija?
- Automatsko pozivanje derefa
- Automatski konvertuje vrednosti pametnih pokazivača u referencu na ciljni tip kada se očekuje referenca

17. Pojednostavi poziv `hello` funkcije u sledećem kodu:

    ```rust
    struct MyBox<T>(T);

    impl<T> MyBox<T> {
        fn new(x: T) -> MyBox<T> {
            MyBox(x)
        }
    }

    use std::ops::Deref;

    impl<T> Deref for MyBox<T> {
        type Target = T;

        fn deref(&self) -> &Self::Target {
            &self.0
        }
    }

    fn hello(name: &str) {
        println!("Hello, {name}!");
    }

    fn main() {
        let m = MyBox::new(String::from("Rust"));
        hello(&(*m)[..]);
    }
    ```

- Zbog implementirane Deref osobine, (*m)[..] se automatski prevodi u &m[..], pa se može direktno koristiti.
```rust
fn main() {
    let m = MyBox::new(String::from("Rust"));
    hello(&m[..]);
}
```

18. Kako se `Deref` koercija odnosi prema promenljivositi (Eng. mutability)? Za više informacija pročitati sledeću [sekciju](https://doc.rust-lang.org/book/ch15-02-deref.html#how-deref-coercion-interacts-with-mutability) knjige.
- Pravila za pozajmljivanje(borrowing) automatski primenjuju na tipove koji implementiraju Deref trait.

19. Koja je uloga `Drop` osobine?
- Za definisanje ponašanja koje treba izvršiti kada vlasnički objekat izađe iz opsega
- Automatski se poziva, za resurse kao što su oslobađanje memorije, zatvaranje fajlova, ili slično

20. Odredi rezultat izvršavanja sledećeg koda:

    ```rust
    struct CustomSmartPointer {
        data: String,
    }

    impl Drop for CustomSmartPointer {
        fn drop(&mut self) {
            println!("Dropping CustomSmartPointer with data `{}`!", self.data);
        }
    }

    fn main() {
        let c = CustomSmartPointer {
            data: String::from("my stuff"),
        };
        let d = CustomSmartPointer {
            data: String::from("other stuff"),
        };
        println!("CustomSmartPointers created.");
    }
    ```
- 
Dropping CustomSmartPointer with data `my stuff`!
Dropping CustomSmartPointer with data `other stuff`!
CustomSmartPointers created.

21. Odredi rezultat izvršavanja sledećeg koda:

    ```rust
    struct CustomSmartPointer {
        data: String,
    }

    impl Drop for CustomSmartPointer {
        fn drop(&mut self) {
            println!("Dropping CustomSmartPointer with data `{}`!", self.data);
        }
    }

    fn main() {
        let c = CustomSmartPointer {
            data: String::from("my stuff"),
        };
        let d = CustomSmartPointer {
            data: String::from("other stuff"),
        };
        drop(d);
        println!("CustomSmartPointers created.");
    }
    ```
-
Dropping CustomSmartPointer with data `other stuff`!
Dropping CustomSmartPointer with data `my stuff`!
CustomSmartPointers created.

22. Da li postoji deljeno vlasništvo u Rust-u? Ako postoji, kako se realizuje?
- Da, ono se postiže pomoću pametnog pointera Rc<T> (Reference Counted).
- Rc<T> omogućava više vlasnika referenci na iste podatke na heapu

23. Da li je `Rc<T>` siguran u kontekstu niti (Engl. threadsafe)?
- Nije jer ne prati broj referenci atomično
 (Arc<T> jeste)

24. Da li `Rc::clone` obavlja duboko kopiranje vrednosti?
- Ne, samo samo povećava broj referenci na iste podatke, 
- duboko kopiranje bi zahtevalo implementaciju Clone

25. Da li `Rc<T>` dozvoljava deljenim vlasnicima da dele i promenljive (Engl. mutable) reference?
- Ne 
(RefCell unutar Rc<RefCell<T>> da)

26. Odredi rezultat izvršavanja sledećeg koda:

    ```rust
    enum List {
        Cons(i32, Rc<List>),
        Nil,
    }

    use crate::List::{Cons, Nil};
    use std::rc::Rc;

    fn main() {
        let a = Rc::new(Cons(5, Rc::new(Cons(10, Rc::new(Nil)))));
        println!("count after creating a = {}", Rc::strong_count(&a));
        let b = Cons(3, Rc::clone(&a));
        println!("count after creating b = {}", Rc::strong_count(&a));
        {
            let c = Cons(4, Rc::clone(&a));
            println!("count after creating c = {}", Rc::strong_count(&a));
        }
        println!("count after c goes out of scope = {}", Rc::strong_count(&a));
    }
    ```

- 1 ,2 , 3 , 2
count after creating a = 1
count after creating b = 2
count after creating c = 3
count after c goes out of scope = 2


27. U kom delu memorije se čuvaju ugrađeni nizovi i torke?
- Steak

28. U kom delu memorije se čuvaju elementi kolekcije, poput vektora i heš mape?
- Heap

29. Ispravi grešku u sledećem kodu:

    ```rust
    fn main() {
    let v = Vec::new();
    }
    ```
- 
    ```rust
    fn main() {
    let v: Vec<i32> = Vec::new();
    }
    ```

30. Ispravi grešku u sledećem kodu:

    ```rust
    fn main() {
    let v = Vec::new();

    v.push(5);
    v.push(6);
    v.push(7);
    v.push(8);
    }
    ```

    ```rust
    fn main() {
     let mut v: Vec<i32> = Vec::new();

    v.push(5);
    v.push(6);
    v.push(7);
    v.push(8);
    }
    ```

31. Koji je rezultat izvršavanja sledećeg koda? Zašto? Ukoliko program proizvodi grešku, izmeni kod tako da se uspešno izvrši.

    ```rust
    fn main() {
        let v = vec![1, 2, 3, 4, 5];

         let does_not_exist = &v[100];
    }
    ```
- Greska zbog pristupa elementu koji ne postoji u vektoru
````rust
  fn main() {
      let v = vec![1, 2, 3, 4, 5];

      let does_not_exist: Option<&i32> = v.get(100);

  }
```

32. Da li sledeći kod proizvodi grešku? Zašto?

  ```rust
  fn main() {
  let mut v = vec![1, 2, 3, 4, 5];

  let first = &v[0];

  v.push(6);

  println!("The first element is: {first}");
  }
  ```

- Zato sto referenca zivi do njene prve upotrebe

The first element is: 1
    ```rust
  fn main() {
  let mut v = vec![1, 2, 3, 4, 5];

  let first: &i32 = &v[0];

  println!("The first element is: {first}"); 

  v.push(6);
  }
  ```

33. Da li pravila vlasništva važe i u kontekstu vektora?
- Da

34. Ispravi grešku u sledećem kodu:

  ```rust
  fn main() {
  let mut v = vec![100, 32, 57];
  for i in &v {
      i += 50;
  }
  }
  ```
-
  ```rust
  fn main() {
  let mut v = vec![100, 32, 57];
  for i in &mut v {
      *i += 50;   //* tu gde menjamo vrednost //da bismo dereferencirali referencu i izmenili vrednost elementa vektora
  }
  }
  ```

35. Da li sledeći kod proizvodi grešku? Zašto?
- Da, zato sto se pokušava mutirati vektor (v.push(55)) dok se već pozajmljuje mutabilno (&mut v) unutar iste petlje.
  ```rust
  n main() {
  let mut v = vec![100, 32, 57];
  for i in &mut v {
      *i += 50;
      v.push(55)
  }
  }
  ```

36. Da li je naredni isečak koda ispravan?
- Da

  ```rust
  fn main() {
  enum SpreadsheetCell {
      Int(i32),
      Float(f64),
      Text(String),
  }

  let row = vec![
      SpreadsheetCell::Int(3),
      SpreadsheetCell::Text(String::from("blue")),
      SpreadsheetCell::Float(10.12),
  ];
  }
  ```

37. U kom trenutku se elementi vektora oslobađaju iz memorije?
-Kad se ne koriste vise, na kraju bloka tj u poslednjem trenutku koriscenja vektora

38. Koja je razlika između stringa tipa `String` i stringa tipa `str` (ili `&str`)?
- String je pokazivac koji pokazuje na str i koji ima vlasnistvo nad str
- String se koristi za dinamičko upravljanje vlasništvom teksta - mutabilan 
- str (ili &str) predstavlja referencu na deo memorije koja sadrži niz karaktera, ali ne upravlja vlasništvom tog niza -
nemutabilan

39. Izmeni sledeći isečak koda koristeći `to_string` metodu nad string literalom:

  ```rust
  fn main() {
      let s = String::from("initial contents");
  }
  ```
- ```rust
fn main() {
    let s = "initial contents".to_string();
}
```

40. Da li `push_str` uzima vlasništvo nad parametrom? Proveriti na sledećem primeru koda:
- Ne uzima

  ```rust
  fn main() {
      let mut s1 = String::from("foo");
      let s2 = "bar";
      s1.push_str(s2);
      println!("s2 is {s2}");
  }
  ```

41. Ispravi grešku u sledećem kodu:

  ```rust
  fn main() {
      let s1 = String::from("Hello, ");
      let s2 = String::from("world!");
      let s3 = s1 + s2;
  }
  ```

    ```rust
  fn main() {
      let s1 = String::from("Hello, ");
      let s2 = String::from("world!");
      let s3 = s1 + &s2;   //ili  let s3 = format!("{}{}", s1, s2);
      println!("{s3}");
  }
  ```

42. Da li sledeći kod proizvodi grešku? Zašto?
- Da zato sto smo premestili vlasnistvo u s3, npr da je format umesto + onda ne bi

  ```rust
  fn main() {
      let s1 = String::from("Hello, ");
      let s2 = String::from("world!");
      let s3 = s1 + &s2;
      println!("{s1}, {s2}")
  }
  ```

43. Koji je rezultat izvršavanja sledećeg koda?

  ```rust
  fn main() {
      let s1 = String::from("tic");
      let s2 = String::from("tac");
      let s3 = String::from("toe");

      let s = format!("{s1}-{s2}-{s3}");
      println!("{s1}, {s2}, {s3}: {s}")
  }
  ```
- tic, tac, toe: tic-tac-toe

44. Da li sledeći kod proizvodi grešku? Zašto?
- Da, zato što pokušava indeksirati String da bi dobilo pojedinačni karakter, ali indeksiranje String-a nije dozvoljeno direktno.

  ```rust
  fn main() {
      let s1 = String::from("hello");
      let h = s1[0];                                 //  Pristup pojedinačnim karakterima kroz petlju
                                                         // for c in s1.chars() {
                                                             // println!("{}", c);
                                                        // }
  }
  ```

45. Koji je rezultat izvršavanja sledećeg koda?
-Zd zato sto cirilica pa zauzima 2 bajta (ščć isto zauzima dva bajta)

  ```rust
  #![allow(unused)]
  fn main() {
      let hello = "Здравo";
      let s = &hello[0..4];
      println!("{s}")
  }
  ```

46. Da li sledeći kod proizvodi grešku?
- Da, zato sto mu treba 2 bajta da ispise Z
  ```rust
  #![allow(unused)]
  fn main() {
      let hello = "Здравo";
      let s = &hello[0..1];
      println!("{s}")
  }
  ```

47. Koji su ispisi sledeća dva isečka koda?

  ```rust
  // Snippet 1
  #![allow(unused)]
  fn main() {
      for c in "Зд".chars() {
          println!("{c}");
      }
  }
  // Snippet 2
  #![allow(unused)]
  fn main() {
      for b in "Зд".bytes() {
          println!("{b}");
      }
  }
  ```
- 1 : 
Z
d

-2 : 
208
151
208
180

48. Koja je uloga `copied` i `unwrap_or` u sledećem kodu?
- za pristup vrednosti u HashMap
- unwrap_or se izvrsava ako nema kljuca, npr da je Green umesto Blue

  ```rust
  fn main() {
      use std::collections::HashMap;

      let mut scores = HashMap::new();

      scores.insert(String::from("Blue"), 10);
      scores.insert(String::from("Yellow"), 50);

      let team_name = String::from("Blue");
      let score = scores.get(&team_name).copied().unwrap_or(0);
  }
  ```

49. Iteriraj kroz ključeve i vrednosti sledeće heš mape:

  ```rust
  fn main() {
      use std::collections::HashMap;

      let mut scores = HashMap::new();

      scores.insert(String::from("Blue"), 10);
      scores.insert(String::from("Yellow"), 50);

      // TODO: Add code here
  }
  ```
-
```rust
for (key, value) in &scores {
        println!("Key: {}, Value: {}", key, value);
    }
```

50. Da li sledeći kod proizvodi grešku? Zašto?
- insert premesta vlasnistvo i zato ne moze da se ispise 

  ```rust
  fn main() {
      use std::collections::HashMap;

      let mut scores = HashMap::new();

      let blue = String::from("Blue");
      let ten = 10;
      let yellow = String::from("Yellow");
      let fifty = 50;

      scores.insert(blue, ten);
      scores.insert(yellow, fifty);

      println!("{blue}, {ten}; {yellow}, {fifty}")
  }
  ```

51. Odredi rezultat izvršavanja sledećeg koda:
- {"Blue": 25}
-25 zato sto insert uzima najnoviji

  ```rust
  fn main() {
      use std::collections::HashMap;

      let mut scores = HashMap::new();

      scores.insert(String::from("Blue"), 10);
      scores.insert(String::from("Blue"), 25);

      println!("{:?}", scores);
  }
  ```

52. Odredi rezultat izvršavanja sledećeg koda:
- {"Yellow": 50, "Blue": 10}
- blue 10 zato sto entry ne radi nista ako ima kljuca

  ```rust
  fn main() {
      use std::collections::HashMap;

      let mut scores = HashMap::new();
      scores.insert(String::from("Blue"), 10);

      scores.entry(String::from("Yellow")).or_insert(50);
      scores.entry(String::from("Blue")).or_insert(50);

      println!("{:?}", scores);
  }
  ```

53. U kojoj situaciji se poziva `or_insert` metoda nad `Entry` enumeracijom?
- u okviru HashMap i BTreeMap kolekcija, kada želimo dodati vrednost za određeni ključ ako taj ključ ne postoji u mapi, ili ažurirati postojeću vrednost ako ključ već postoji

54. Odredi rezultat izvršavanja sledećeg koda:
- {"hello": 1, "world": 2, "wonderful": 1}

  ```rust
  fn main() {
      use std::collections::HashMap;

      let text = "hello world wonderful world";

      let mut map = HashMap::new();

      for word in text.split_whitespace() {
          let count = map.entry(word).or_insert(0);
          *count += 1;
      }

      println!("{:?}", map);
  }
  ```
````

VEZBA 6

# V6 - Programski jezik Rust

## Paketi, moduli, obrada grešaka, testiranje

1.  Iz kojih elemenata se sastoji Rust-ov sistem za module?
    -Paketa, sanduka, modula i stavki

2.  Šta je sanduk (Engl. Crate)?
    -Minimalna jedinica koja moze da se kompajlira. Moze biti binarni i bibliotecki

3.  Šta je binarni sanduk (Engl. Binary Crate)?
    -Generise izvrsivu datoteku koja je rezlutat kompajliranja. Izvrsiv exe fajl.

4.  Šta je bibliotečki sanduk (Engl. Library Crate)?
    -Bibliotecki ne generise binary. Biblioteka nema main.

5.  Šta je koren (Engl. Root) sanduka?
    -Prva stvar koja ce se u sanduku izvrsiti

6.  Šta je paket (Engl. Package)?
    -Projekat. Moze da sadrzi vise sanduka

7.  Koliko binarnih ili bibliotečkih sanduka može da se nalazi u jednom paketu?
    -Vise binarnih, jedan biblioteckih

8.  Šta je uobičajen koren za binarni sanduk? A za bibliotečki sanduk?
    -Main rs file, lib rs file

9.  Gde se još binarni sanduci mogu nalaziti?
    -U src bin folderu

10. Koje konvencije bismo trebali da pratimo dok definišemo module (Engl. Modules) u sanducima?
    -Da ime fajla predstavlja ime modula

11. Da li su moduli uobičajeno privatni?
    -Da ali ga mozemo uciniti javinim

12. Kako formiramo putanju do stavke (metoda ili funkcija) u stablu modula (Engl. Module Tree)?
    -Apsolutno do tog fajla(od pocetka cratea do funkcije) ili relativno

13. Da li uobičajeno roditeljski modul (Engl. Parent Module) ima pristup sadržaju unutrašnjeg modula?
    -Ne

14. Pozovi funkciju `add_to_waitlist` uz pomoć apsolutne i relativne putanje na naznačenim mestima:

    ```rust
    mod front_of_house {
        mod hosting {
            fn add_to_waitlist() {}
        }
    }

    pub fn eat_at_restaurant() {
        // TODO: Apsolutna putanja

        // TODO: Relativna putanja
    }

    ```

    ```rust
    mod front_of_house {
    pub mod hosting {
    pub fn add_to_waitlist() {}
    }
    }

    pub fn eat_at_restaurant() {
    // TODO: Apsolutna putanja
    crate::front_of_house:hosting::add_to_waitlist();
    // TODO: Relativna putanja
    front_of_house:hosting::add_to_waitlist();
    }

    ```

15. Uzimajući u obzir prethodno pitanje, zašto može `eat_at_restaurant` da pristupi privatnom modulu `front_of_house`?
    -Zato sto se nalaze u istom fajlu

16. Za šta se koristi ključna reč `super`?
- Da pristupimo roditeljskom modulu. Idi gore u hijearhiji. Idi gore pa pristupi dole(kao cd ..)
  Da nisu u istom (super::front_of_house:hosting::add_to_waitlist(); )

17. Ispravi grešku u sledećem kodu:

    ```rust
    mod back_of_house {
        pub struct Breakfast {
            pub toast: String,
            seasonal_fruit: String,
        }

        impl Breakfast {
            pub fn summer(toast: &str) -> Breakfast {
                Breakfast {
                    toast: String::from(toast),
                    seasonal_fruit: String::from("peaches"),
                }
            }
        }
    }

    pub fn eat_at_restaurant() {
        // Order a breakfast in the summer with Rye toast
        let mut meal = back_of_house::Breakfast::summer("Rye");
        // Change our mind about what bread we'd like
        meal.toast = String::from("Wheat");
        println!("I'd like {} toast please", meal.toast);

        meal.seasonal_fruit = String::from("blueberries");
    }

    fn main() {
        eat_at_restaurant();
    }
    ```

    ```rust
    mod back_of_house {
        pub struct Breakfast {
            pub toast: String,
            pub seasonal_fruit: String, //Dodali smo ovde pub da mozemo da mu pristupimo
        }

        impl Breakfast {
            pub fn summer(toast: &str) -> Breakfast {
                Breakfast {
                    toast: String::from(toast),
                    seasonal_fruit: String::from("peaches"),
                }
            }
        }
    }

    pub fn eat_at_restaurant() {
        let mut meal = back_of_house::Breakfast::summer("Rye");

        meal.toast = String::from("Wheat");
        println!("I'd like {} toast please", meal.toast);

        meal.seasonal_fruit = String::from("blueberries");
    }

    fn main() {
        eat_at_restaurant();
    }
    ```

18. Zašto funkcija `summer` mora biti javna (Engl. Public)?
    -Zato sto joj ne mozemo pristupiti ako je private

19. Ukoliko je enum javan, da li su i njegove varijacije javne? A strukture (Engl. Structs)?
    -U enumu da. U strukturi ne, jos uvek su privatna ako ih ne ucinimo javna.

20. Da li sledeći kod proizvodi grešku? Zašto?
 - Modulu hosting ne mozemo pristupiti iz drugog modula. 

        ```rust
        mod front_of_house {
            pub mod hosting {
                pub fn add_to_waitlist() {}
            }
        }

        use crate::front_of_house::hosting;

        mod customer {
            pub fn eat_at_restaurant() {
                hosting::add_to_waitlist();
            }
        }
        ```
- 
         ```rust
        mod front_of_house {
            pub mod hosting {
                pub fn add_to_waitlist() {}
            }
        }

        mod customer {
            use crate::front_of_house::hosting; //import unutar
            pub fn eat_at_restaurant() {
                hosting::add_to_waitlist(); //ili dodamo super::hosting::add_to_waitlist(); a import ostaje gge jeste
            }
        }
        ```

21. Navedi dva načina da se ispravi greška u sledećem isečku koda:

    ```rust
    mod front_of_house {
        pub mod hosting {
            pub fn add_to_waitlist() {}
        }
    }

    use crate::front_of_house::hosting;

    mod customer {
        pub fn eat_at_restaurant() {
            hosting::add_to_waitlist();
        }
    }

    fn main() {
        customer::eat_at_restaurant();
    }
    ```

- Kao u prethodnom primeru, ili stavimo import unutar modula ili stavimo super

22. Koji je proces ponovnog export-ovanja (Engl. Re-Export) imena?
- Importuje modul ali ga ucini javnim. -pub use 
-  Izloži imena iz drugih modula kao da su deo samog modula

23. Kako dodajemo i koristimo spoljne pakete (Engl. External Package) kao zavisnosti (Engl. Dependency) u našem Rust projektu?
-u cargo.toml dodamo [dependencies]

24. Šta je naziv sanduka standardne biblioteke (Engl. Standard Library)?
- std

25. Prepiši sledeći isečak koda da bude koncizniji:

    ```rust
    use std::cmp::Ordering;
    use std::io;
    ```

    ```rust
    use std::{io, cmp::Ordering};
    ```

26. Prepiši sledeći isečak koda da bude koncizniji:

    ```rust
    use std::io;
    use std::io::Write;
    ```

    ```rust
    use std::io::{self, Write};
    ```

27. Koji tipovi grešaka su podržani u Rust programskom jeziku i kako se ponašaju?
- Compile time and runtime greske 
- rezlutat - funkcija može vratiti grešku, moramo eksplicitno rukovati greškama 
- panic - program se obustavlja

28. Kako mozemo da izazovemo `panic` u Rust-u?
- Pomoću makroa panic!()      // panic!("Ovo je panika!");

29. Šta je odmotavanje (Engl. unwinding) i kako se sprečava?
-  Mehanizam koji se koristi za obradu izuzetaka
- Kada se desi greska npr panic pa rust pokusava da odmota stack trace a mozemo ga spreciti ako u kofiguraciji
navedemo da je panic = "abort" a ne unwinding.

30. Da li se u sledećem isečku koda događa `panic`? Ukoliko da, kako možemo pokazati celokupni povratni trag (Engl. Backtrace)?

    ```rust
    fn main() {
        let v = vec![1, 2, 3];

        v[99];
    }
    ```

- Da, rust backstrace = 1 //RUST_BACKTRACE=1 cargo run

31. Ukoliko postoji, pokušaj otvoriti `hello.txt`, u suprotnom iskoristi `panic`. Reši problem koristeći `match`.
- 
```rust
use std::fs::File;

fn main() {
    // Pokušaj otvaranja datoteke hello.txt
    let file_result = File::open("hello.txt");

    // Rukovanje rezultatom pomoću match
    match file_result {
        Ok(file) => {
            // Ako otvaranje uspe, ovde možete raditi sa datotekom 'file'
            println!("Datoteka otvorena uspešno!");
        }
        Err(error) => {
            // Ako se desi greška, prikaži poruku i završi program
            panic!("Greška prilikom otvaranja datoteke: {}", error);
        }
    }
}
```
32. Reši prethodni problem pomoću leksičkog zatvaranja (Engl. Lexical Closure).
- 
```rust
use std::fs::File;

fn main() {
    // Koristi leksičko zatvaranje (lexical closure) za rukovanje otvaranjem datoteke
    let result = || -> Result<(), &'static str> {
        let file = File::open("hello.txt")?;
        println!("Datoteka otvorena uspešno!");
        Ok(())
    };

    // Pozivanje leksičkog zatvaranja i rukovanje mogućim greškama
    if let Err(err) = result() {
        panic!("Greška prilikom otvaranja datoteke: {}", err);
    }
}
```
33. Isti problem reši koristeći `unwrap`.
-
```rust
use std::fs::File;

fn main() {
    // Pokušaj otvaranja datoteke i automatsko rukovanje greškom pomoću unwrap
    let file = File::open("hello.txt").unwrap();

    // Ako otvaranje uspe, ovde možete raditi sa datotekom 'file'
    println!("Datoteka otvorena uspešno!");
}
```
34. Isti problem reši koristeći `expect`.
-
```rust
use std::fs::File;

fn main() {
    // Pokušaj otvaranja datoteke i rukovanje greškom pomoću expect
    let file = File::open("hello.txt").expect("Greška prilikom otvaranja datoteke");

    // Ako otvaranje uspe, ovde možete raditi sa datotekom 'file'
    println!("Datoteka otvorena uspešno!");
}
```
35. Šta je bolje koristiti: `unwrap` ili `expect`?
- unwrap - korisno kada smo sigurai da se greška neće desiti ili da nije bitno pružiti dodatne informacije o grešci
- expect - orisno kada želimo pružiti dodatne informacije o grešci ili kontekst.
- Leksičko zatvaranje pruža dodatnu kontrolu, unwrap je jednostavan ali može izazvati paniku, dok expect omogućava pružanje prilagođene poruke panike.

36. Sledeći isečak koda prepiši u kraći i ekvivalentan kod koristeći `?`. Nije dozvoljeno korišćenje `fs::read_to_string`.

    ```rust
    #![allow(unused)]
    fn main() {
        use std::fs::File;
        use std::io::{self, Read};

        fn read_username_from_file() -> Result<String, io::Error> {
            let username_file_result = File::open("hello.txt");

            let mut username_file = match username_file_result {
                Ok(file) => file,
                Err(e) => return Err(e),
            };

            let mut username = String::new();

            match username_file.read_to_string(&mut username) {
                Ok(_) => Ok(username),
                Err(e) => Err(e),
            }
        }
    }
    ```
-
```rust
use std::fs;
use std::io;

fn read_username_from_file() -> Result<String, io::Error> {
    let mut username = String::new();
    fs::File::open("hello.txt")?.read_to_string(&mut username)?;  //? se koristi za propagiranje grešaka umesto          eksplicitnog rukovanja match izrazima
    Ok(username)
}
```

37. Da li sledeći kod proizvodi grešku? Zašto?

    ```rust
    use std::fs::File;

    fn main() {
        let greeting_file = File::open("hello.txt")?;
    }
    ```
- Da,zato sto operator može biti korišćen samo unutar funkcija koje imaju povratni tip Result ili Option.

38. Šta može biti povratni tip main funkcije?
- Empty () (unit tip) - ako ne vraca nikakvu vrednost,
- Result - ako funkcija vraća rezultat koji može biti greška
- i32: Ako main funkcija vraća 32-bitni celobrojni rezultat

39. Podseti se igre pogađanja brojeva [na sledećem linku](https://doc.rust-lang.org/book/ch02-00-guessing-game-tutorial.html). Izmeni kod da takođe proverava da li je korisnikov unos u rasponu [1, 100]. Ovu proveru uradi tako što napišeš svoju `Guess` strukturu sa konstruktorom koji izaziva `panic` ukoliko vrednost nije u očekivanom rasponu.
```rust
use std::io;
use std::cmp::Ordering;

struct Guess {
    value: u32,
}

impl Guess {
    // Konstruktor za Guess sa proverom raspona
    fn new(value: u32) -> Guess {
        if value < 1 || value > 100 {
            panic!("Vrednost van raspona [1, 100]!");
        }
        Guess { value }
    }

    // Getter za vrednost
    fn value(&self) -> u32 {
        self.value
    }
}

fn main() {
    println!("Dobrodošli u igru pogađanja brojeva!");

    let secret_number = 42;

    loop {
        println!("Unesite vašu pretpostavku:");

        let mut guess = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let guess: u32 = match guess.trim().parse() {
            Ok(num) => num,
            Err(_) => {
                println!("Molimo vas unesite validan broj!");
                continue;
            }
        };

        let guess = Guess::new(guess);

        println!("Vaša pretpostavka: {}", guess.value());

        match guess.value().cmp(&secret_number) {
            Ordering::Less => println!("Vaša pretpostavka je premala!"),
            Ordering::Greater => println!("Vaša pretpostavka je prevelika!"),
            Ordering::Equal => {
                println!("Bravo! Tačan broj je {}", secret_number);
                break;
            }
        }
    }
}
```

VEZBA 7

# V7 - Programski jezik Rust

## Generički tipovi, osobine, životni vek

1. Umesto dve funkcije `largest_i32` i `largest_char`, napiši jednu funkciju koja podržava generičke tipove i pruža istu funkcionalnost u `main` metodi.

   ```rust
       fn largest_i32(list: &[i32]) -> &i32 {
       let mut largest = &list[0];

       for item in list {
           if item > largest {
               largest = item;
           }
       }

       largest
   }

   fn largest_char(list: &[char]) -> &char {
       let mut largest = &list[0];

       for item in list {
           if item > largest {
               largest = item;
           }
       }

       largest
   }

   fn main() {
       let number_list = vec![34, 50, 25, 100, 65];

       let result = largest_i32(&number_list);
       println!("The largest number is {}", result);
       assert_eq!(*result, 100);

       let char_list = vec!['y', 'm', 'a', 'q'];

       let result = largest_char(&char_list);
       println!("The largest char is {}", result);
       assert_eq!(*result, 'y');
   }
   ```
-
```rust
fn largest<T: std::cmp::PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];

    for item in list {
        if item > largest {
            largest = item;
        }
    }

    largest
}

fn main() {
    let number_list = vec![34, 50, 25, 100, 65];

    let result = largest(&number_list);
    println!("The largest number is {}", result);
    assert_eq!(*result, 100);

    let char_list = vec!['y', 'm', 'a', 'q'];

    let result = largest(&char_list);
    println!("The largest char is {}", result);
    assert_eq!(*result, 'y');
}
```

2. Pronađi i ispravi grešku u sledećem isečku koda.

   ```rust
   struct Point<T> {
       x: T,
       y: T,
   }

   fn main() {
       let wont_work = Point { x: 5, y: 4.0 };
   }
   ```
-
````rust
  struct Point<T, U> { //U zato sto su drugaciji tip
      x: T,
      y: U,
  }

  fn main() {
      let wont_work = Point { x: 5, y: 4.0 };
  }
  ```

3. Da li je dozvoljeno koristiti generičke tipove unutar enumeracija? Ukoliko da, navedi primer.
-Da
```rust
enum MyEnum<T> {
    Variant1(T),
    Variant2(i32),
}
```

4. Šta je rezultat izvršavanja sledećeg isečka koda na `stdout`?

  ```rust
  struct Point<X1, Y1> {
      x: X1,
      y: Y1,
  }

  impl<X1, Y1> Point<X1, Y1> {
      fn mixup<X2, Y2>(self, other: Point<X2, Y2>) -> Point<X1, Y2> {
          Point {
              x: self.x,
              y: other.y,
          }
      }
  }

  fn main() {
      let p1 = Point { x: 5, y: 10.4 };
      let p2 = Point { x: "Hello", y: 'c' };

      let p3 = p1.mixup(p2);

      println!("p3.x = {}, p3.y = {}", p3.x, p3.y);
  }
  ```
- p3.x = 5, p3.y = c

5. Da li korištenje generičkih tipova utiče na vreme izvršavanja programa (Engl. Runtime overhead)? Razjasniti.
- Korišćenje generičkih tipova nema uticaja na vreme izvršavanja programa. Rust kompajler primenjuje koncept "monomorfizma" gde generički kod biva instanciran za svaki konkretan tip tokom kompilacije, što eliminiše bilo kakav runtime overhead.

6. Koji koncept iz Rust-a je sličan intefejsima iz drugih jezika? Koje su razlike između njih?
- trait
- Razlika je u tome što trait-ovi u Rustu omogućavaju dodavanje metoda strukturi ili enumeraciji naknadno, iako oni nisu deo originalne definicije.

7. Da li je moguće implementirati `Display` osobinu (Engl. Trait) za `Vec<T>`? Zašto?
- Ne
- Display je implementiran za String jer je String tip koji se može konvertovati u string, ali Vec<T> nije direktno konvertibilan u string.

8. Da li je moguće pozivati uobičajenu (Engl. Default) implementaciju iz naknadne (Engl. Overriding) implementacije iste metode?
- Da, korišćenjem super:: praćeno imenom modula u kojem se nalazi uobičajena implementacija

9. Šta je rezultat izvršavanja sldećeg isečka koda na `stdout`?
-
New article available! Penguins win the Stanley Cup Championship!, by Iceburgh (Pittsburgh, PA, USA)
1 new tweet: (Read more from @horse_ebooks...)

  ```rust
  pub trait Summary {
      fn summarize_author(&self) -> String;

      fn summarize(&self) -> String {
          format!("(Read more from {}...)", self.summarize_author()) // (Read more from @horse_ebooks...)
      }
  }


  pub struct Tweet {
      pub username: String,
      pub content: String,
      pub reply: bool,
      pub retweet: bool,
  }

  impl Summary for Tweet {
      fn summarize_author(&self) -> String {
          format!("@{}", self.username)            // @horse_ebooks
      }
  }


  pub struct NewsArticle {
      pub headline: String,
      pub location: String,
      pub author: String,
      pub content: String,
  }

  impl Summary for NewsArticle {
      fn summarize_author(&self) -> String {
          format!("@{}", self.author)
          //Iceburgh
      } 

      fn summarize(&self) -> String {
          format!("{}, by {} ({})", self.headline, self.author, self.location) 
          //Penguins win the Stanley Cup Championship!, by Iceburgh (Pittsburgh, PA, USA)
      }
  }


  fn main() {
      let article = NewsArticle {
          headline: String::from("Penguins win the Stanley Cup Championship!"),
          location: String::from("Pittsburgh, PA, USA"),
          author: String::from("Iceburgh"),
          content: String::from(
              "The Pittsburgh Penguins once again are the best \
              hockey team in the NHL.",
          ),
      };

      println!("New article available! {}", article.summarize()); 
      //New article available! Penguins win the Stanley Cup Championship!, by Iceburgh (Pittsburgh, PA, USA)


      let tweet = Tweet {
          username: String::from("horse_ebooks"),
          content: String::from(
              "of course, as you probably already know, people",
          ),
          reply: false,
          retweet: false,
      };

      println!("1 new tweet: {}", tweet.summarize()); //1 new tweet: (Read more from @horse_ebooks...)
      }
   ```

10. Pojednostavi sledeći kod koristeći sintaksu za ograničenja kroz osobine  (Engl. Trait bounds).
  ```rust
  pub fn notify(item1: &impl Summary, item2: &impl Summary)
  ```
-
  ```rust
  
  pub fn notify<T: Summary>(item1: &T, item2: &T) {
}
```

11. Pojednostavi sledeći kod koristeći sintaksu za ograničenja kroz osobine  (Engl. Trait bounds).
  ```rust
  pub fn notify(item: &(impl Summary + Display))
  ```
-
```rust
pub fn notify<T>(item: &T)
where
    T: Summary + Display,
{
    // function body
}
```

12. Napiši ekvivalentni kod koristeći `where` klauzulu.
  ```rust
  fn some_function<T: Display + Clone, U: Clone + Debug>(t: &T, u: &U) -> i32
  ```
-
``fn some_function<T, U>(t: &T, u: &U) -> i32
where
    T: Display + Clone,
    U: Clone + Debug,
{
    // Funkcija implementacija
}

```
13. Da li sledeći kod proizvodi grešku?
- Da, if else ima nekompatibilne tipove
  ```rust
      pub trait Summary {
      fn summarize_author(&self) -> String;

      fn summarize(&self) -> String {
          format!("(Read more from {}...)", self.summarize_author())
      }
  }


  pub struct Tweet {
      pub username: String,
      pub content: String,
      pub reply: bool,
      pub retweet: bool,
  }

  impl Summary for Tweet {
      fn summarize_author(&self) -> String {
          format!("@{}", self.username)
      }
  }


  pub struct NewsArticle {
      pub headline: String,
      pub location: String,
      pub author: String,
      pub content: String,
  }

  impl Summary for NewsArticle {
      fn summarize_author(&self) -> String {
          format!("@{}", self.author)
      }

      fn summarize(&self) -> String {
          format!("{}, by {} ({})", self.headline, self.author, self.location)
      }
  }


  fn main() {
      let article = NewsArticle {
          headline: String::from("Penguins win the Stanley Cup Championship!"),
          location: String::from("Pittsburgh, PA, USA"),
          author: String::from("Iceburgh"),
          content: String::from(
              "The Pittsburgh Penguins once again are the best \
              hockey team in the NHL.",
          ),
      };

      println!("New article available! {}", article.summarize());


      let tweet = Tweet {
          username: String::from("horse_ebooks"),
          content: String::from(
              "of course, as you probably already know, people",
          ),
          reply: false,
          retweet: false,
      };

      println!("1 new tweet: {}", tweet.summarize());
  }


  fn returns_summarizable(switch: bool) -> impl Summary {
      if switch {
          NewsArticle {
              headline: String::from(
                  "Penguins win the Stanley Cup Championship!",
              ),
              location: String::from("Pittsburgh, PA, USA"),
              author: String::from("Iceburgh"),
              content: String::from(
                  "The Pittsburgh Penguins once again are the best \
                  hockey team in the NHL.",
              ),
          }
      } else {
          Tweet {
              username: String::from("horse_ebooks"),
              content: String::from(
                  "of course, as you probably already know, people",
              ),
              reply: false,
              retweet: false,
          }
      }
  }
  ```

14. Šta predstavlja `blanket` implementacija?
- Implementacija trait-a za sve tipove koji ispunjavaju određene uslove, omogućava generičkom kodu da radi sa svim tipovima koji ispunjavaju uslove navedene u blanket implementaciji.

15. Šta je životni vek reference (Engl. Reference Lifetime)?
- Period vremena tokom kojeg je referenca na neki podatak važeća

16. Da li sledeći kod proizvodi grešku? Ukoliko da, ispravi je.

  ```rust
  fn main() {
      let r: i32;
      println!("r: {}", r);
  }
  ```

- Promenljiva r je deklarisana, ali se ne inicijalizuje pre korišćenja
```rust
fn main() {
    let r: i32 = 42;
    println!("r: {}", r);
}
```

17. Da li sledeći kod prozvodi grešku? Zašto? Pomoć: *viseća referenca* (Engl. dangling pointer).

  ```rust
  fn main() {
      let r;

      {
          let x = 5;
          r = &x;
      }

      println!("r: {}", r);
  }
  ```
- Da
- Promenljiva x se definiše unutar bloka, a referenca r pokušava da zadrži referencu na x i nakon završetka bloka, što bi dovelo do dangling pointera. Ispravna referenca ne sme živeti duže od podataka koje referiše.

18. Kako funkcioniše `borrow checker` u Rust-u?
-Poredi zivotni vek referenci
- Deo kompajlera koji proverava validnost referenci u kodu kako bi obezbedio sigurnost od visećih referenci, trka podataka i drugih grešaka povezanih sa upravljanjem referencama.

19. Pronađi i ispravi greške u sledećem isečku koda.

  ```rust
  fn main() {
      let string1 = String::from("abcd");
      let string2 = "xyz";

      let result = longest(string1.as_str(), string2);
      println!("The longest string is {}", result);
  }

  fn longest(x: &str, y: &str) -> &str {
      if x.len() > y.len() {
          x
      } else {
          y
      }
  }
  ```
- Pokušava da referiše na nešto što može biti ugašeno pre nego što funkcija završi.

```rust
fn main() {
    let string1 = String::from("abcd");
    let string2 = "xyz";

    let result = longest(string1.as_str(), string2);
    println!("The longest string is {}", result);
}

fn longest<'a>(x: &'a str, y: &'a str) -> String {
    if x.len() > y.len() {
        x.to_string()
    } else {
        y.to_string()
    }
}
```

20. Da li je sledeći kod validan? Zašto?
- The longest string is long string is long
- Referenca koja se vraća (result) ima životni vek ograničen životnim vekom string1, a to je dovoljno da bi se kod uspešno kompajlirao.

  ```rust
  fn main() {
      let string1 = String::from("long string is long");

      {
          let string2 = String::from("xyz");
          let result = longest(string1.as_str(), string2.as_str());
          println!("The longest string is {}", result);
      }
  }

  fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {
      if x.len() > y.len() {
          x
      } else {
          y
      }
  }
  ```

21. Da li se sledeći kod uspešno kompajlira? Zašto?
- Ne string2 ne zivi dovoljno dugo 
- Kada se string2 dodeljuje result, životni vek result postaje isti kao životni vek string2. Međutim, po završetku bloka ({}), string2 ističe, a referenca koja se odnosi na nju postaje nevažeća. Kao rezultat toga, pokušaj pristupa result nakon bloka dovodi do pokušaja referisanja nečega što više nije u važećem stanju, što nije dozvoljeno

  ```rust
  fn main() {
      let string1 = String::from("long string is long");
      let result;
      {
          let string2 = String::from("xyz");
          result = longest(string1.as_str(), string2.as_str());
      }
      println!("The longest string is {}", result);
  }

  fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {
      if x.len() > y.len() {
          x
      } else {
          y
      }
  }
  ```

22. Popravi greške u sledećem isečku koda.

  ```rust
  fn main() {
      let string1 = String::from("abcd");
      let string2 = "xyz";

      let result = longest(string1.as_str(), string2);
      println!("The longest string is {}", result);
  }

  fn longest<'a>(x: &str, y: &str) -> &'a str {
      let result = String::from("really long string");
      result.as_str() //referenca ne bi bila važeća izvan funkcije
  }
  ```

  ```rust 
  fn main() {
    let string1 = String::from("abcd");
    let string2 = "xyz";

    let result = longest(string1.as_str(), string2);
    println!("The longest string is {}", result);
}

fn longest<'a>(x: &'a str, y: &'a str) -> &'a str { //longest da direktno vraća referencu na duži string između x i y
    if x.len() > y.len() {
        x
    } else {
        y
    }
}
```

23. Popravi greške u sledećem isečku koda.

  ```rust
  struct ImportantExcerpt {
      part: &str,
  }

  fn main() {
      let novel = String::from("Call me Ishmael. Some years ago...");
      let first_sentence = novel.split('.').next().expect("Could not find a '.'");
      let i = ImportantExcerpt {
          part: first_sentence,
      };
  }
  ```
- ImportantExcerpt struktura dobija vlastiti vektor kao polje (Vec<String>) umesto referencu
  ```rust
  struct ImportantExcerpt {
    part: Vec<String>,
}

fn main() {
    let novel = String::from("Call me Ishmael. Some years ago...");
    let first_sentence: Vec<String> = novel.split('.').map(String::from).collect();
    let i = ImportantExcerpt {
        part: first_sentence,
    };
}
```

24. Šta su ulazni i izlazni životni vekovi (Engl. Input and Output Lifetimes)?
- Izlazni su životni vekovi koji se odnose na reference koje funkcija vraća
- Ulazni su životni vekovi koji se odnose na reference koje se prenose kao argumenti funkcije

25. Šta su pravila za *eliziju životnog veka* (Engl. Lifetime Elision)?
- Skup pravila koje automatski određuju životne vekove referenci na osnovu sintakse funkcija, bez potrebe eksplicitnog navođenja životnih vekova.

26. Da li su nam potrebne eksplicitne anotacije životnog veka u sledećem primeru? Zašto?

  ```rust
  fn first_word(s: &str) -> &str {
  ```
-Nisu jer postoji elizija životnog veka, u funkcijama koje uzimaju samo jedan ulazni referentni argument, automatski  se određuje životni vek tog argumenta

27. Popravi greške u sledećem isečku koda.

  ```rust
  struct ImportantExcerpt<'a> {
      part: &'a str,
  }

  impl ImportantExcerpt {
      fn level(&self) -> i32 {
          3
      }
  }

  impl ImportantExcerpt {
      fn announce_and_return_part(&self, announcement: &str) -> &str {
          println!("Attention please: {}", announcement);
          self.part
      }
  }

  fn main() {
      let novel = String::from("Call me Ishmael. Some years ago...");
      let first_sentence = novel.split('.').next().expect("Could not find a '.'");
      let i = ImportantExcerpt {
          part: first_sentence,
      };
  }
  ```

    ```rust
  struct ImportantExcerpt<'a> {
      part: &'a str,
  }

  impl<'a> ImportantExcerpt<'a> {
      fn level(&self) -> i32 {
          3
      }
  }

  impl<'a> ImportantExcerpt<'a> {
      fn announce_and_return_part(&self, announcement: &str) -> &str {
          println!("Attention please: {}", announcement);
          self.part
      }
  }

  fn main() {
      let novel = String::from("Call me Ishmael. Some years ago...");
      let first_sentence = novel.split('.').next().expect("Could not find a '.'");
      let i = ImportantExcerpt {
          part: first_sentence,
      };
  }
  ```

28. Napisi ekvivalentan kod koji koristi eksplicitan životni vek.

  ```rust
  let s: &str = "I have a static lifetime.";
  ```
  -
  ```rust
  let s: &'static str = "I have a static lifetime.";
```

29. Koliko je dugačak životni vek string literal-a?
- Statički (static) životni vek, dostupni su tokom celog trajanja programa

// 30. Koja ja razlika između korišćenja asociranih tipova i generičkih tipova pri implementaciji osobina?
// -ovo pitanje nije toliko bitno

31. Da li sledeći kod izaziva `panic`?
- Ne

  ```rust
  use std::ops::Add;

  #[derive(Debug, Copy, Clone, PartialEq)]
  struct Point {
      x: i32,
      y: i32,
  }

  impl Add for Point {
      type Output = Point;

      fn add(self, other: Point) -> Point {
          Point {
              x: self.x + other.x,
              y: self.y + other.y,
          }
      }
  }

  fn main() {
      assert_eq!(
          Point { x: 1, y: 0 } + Point { x: 2, y: 3 },
          Point { x: 3, y: 3 }
      );
  }
  ```

// 32. Zašto sledeći primer koristi `Add<Meters>` a ne samo `Add` kao prethodni?
// -ne treba ovo pitanje

//   ```rust
//   use std::ops::Add;

//   struct Millimeters(u32);
//   struct Meters(u32);

//   impl Add<Meters> for Millimeters {
//       type Output = Millimeters;

//       fn add(self, other: Meters) -> Millimeters {
//           Millimeters(self.0 + (other.0 * 1000))
//       }
//   }
//   ```

33. Šta je rezultat ivršavanja sledećeg isečka koda?
- *waving arms furiously*

  ```rust
  trait Pilot {
      fn fly(&self);
  }

  trait Wizard {
      fn fly(&self);
  }

  struct Human;

  impl Pilot for Human {
      fn fly(&self) {
          println!("This is your captain speaking.");
      }
  }

  impl Wizard for Human {
      fn fly(&self) {
          println!("Up!");
      }
  }

  impl Human {
      fn fly(&self) {
          println!("*waving arms furiously*");
      }
  }

  fn main() {
      let person = Human;
      person.fly();
  }
  ```

34. Ponovo napiši `main` funckiju iz prethodnog primera tako da se pozovu sve 3 implementacije `fly` metode.
-
```rust
fn main() {
    let person = Human;

    // Pozivamo metodu `fly` iz trait-a `Pilot`
    Pilot::fly(&person);

    // Pozivamo metodu `fly` iz trait-a `Wizard`
    Wizard::fly(&person);

    // Pozivamo metodu `fly` iz strukture `Human`
    person.fly();
}
```
35. Šta je rezultat izvršavanja sledećeg isečka koda?
- A baby dog is called a Spot
  ```rust
  trait Animal {
      fn baby_name() -> String;
  }

  struct Dog;

  impl Dog {
      fn baby_name() -> String {
          String::from("Spot")
      }
  }

  impl Animal for Dog {
      fn baby_name() -> String {
          String::from("puppy")
      }
  }

  fn main() {
      println!("A baby dog is called a {}", Dog::baby_name());
  }
  ```

NE UCITI OD 36 NADALJE
// 36. Ponovo napiši `main` funkciju iz prethodnog primera tako da poziva obe `baby_name` metode.
// 37. Sledeći kod se ne kompajlira. Implementiraj odgovarajuću osobinu na `Point` tipu.
//   ```rust
//   use std::fmt;

//   trait OutlinePrint: fmt::Display {
//       fn outline_print(&self) {
//           let output = self.to_string();
//           let len = output.len();
//           println!("{}", "*".repeat(len + 4));
//           println!("*{}*", " ".repeat(len + 2));
//           println!("* {} *", output);
//           println!("*{}*", " ".repeat(len + 2));
//           println!("{}", "*".repeat(len + 4));
//       }
//   }

//   struct Point {
//       x: i32,
//       y: i32,
//   }

//   impl OutlinePrint for Point {}
//   ```
// 38. Implementiraj `Display` osobinu na `Vector<T>` tipu.
// 39. Šta sledeći kod prikazuje na `stdout`?

//   ```rust
//   fn main() {
//       type Kilometers = i32;
//       let x: i32 = 5;
//       let y: Kilometers = 5;
//       println!("x + y = {}", x + y);
//   }
//   ```

// 40. Šta je *never type*?
// 41. Šta su divergirajuće funkcije (Engl. Divering functions)?
// 42. Koji je tip `guess` varijable u sledećem isečku koda?

//   ```rust
//           let guess: u32 = match guess.trim().parse() {
//               Ok(num) => num,
//               Err(_) => continue,
//           };
//   ```

// 43. Šta je tip `loop` izraza u sledećem isečku koda?

//   ```rust
//       print!("forever ");

//       loop {
//           print!("and ever ");
//       }
//   ```

// 44. Da li se sledeći isečak koda uspešno kompajlira? Zašto?

//   ```rust
//   fn main() {
//       let s1: str = "Hello there!";
//       let s2: str = "How's it going?";
//   }
//   ```

// 45. Da li su osobine tipovi dinamičke veličine (Engl. Dynamically Sized)?
// 46. Koja osobina je korisna u toku rada sa tipovima dinamičke veličine (DST - Dynamically Sized Type)?
// 47. Da li generičke funkcije uobičajeno podržavaju rad sa tipovima dinamičke veličine?

VEZBA 8
# V8 - Programski jezik Rust

## Pametni pointeri (drugi deo), FP, iteratori i zatvorenja
1-6/7 nam nece trebati

// 1. Šta predstavlja obrazac interne promenjivosti?
// 2. Koja je razlika između `Box<T>` i `RefCell<T>`?
// 3. U kojoj situaciji je potrebno da se pravila pozajmljivanja proveravaju u vreme izvršavanja programa umesto u vreme kompajliranja?
// 4. Izmeni sledeći kod upotrebom `RefCell<T>` tako da se testovi uspešno izvrše:

//   ```rust
//   pub trait Messenger {
//   fn send(&self, msg: &str);
//   }

//   pub struct LimitTracker<'a, T: Messenger> {
//       messenger: &'a T,
//       value: usize,
//       max: usize,
//   }

//   impl<'a, T> LimitTracker<'a, T>
//   where
//       T: Messenger,
//   {
//       pub fn new(messenger: &'a T, max: usize) -> LimitTracker<'a, T> {
//           LimitTracker {
//               messenger,
//               value: 0,
//               max,
//           }
//       }

//       pub fn set_value(&mut self, value: usize) {
//           self.value = value;

//           let percentage_of_max = self.value as f64 / self.max as f64;

//           if percentage_of_max >= 1.0 {
//               self.messenger.send("Error: You are over your quota!");
//           } else if percentage_of_max >= 0.9 {
//               self.messenger
//                   .send("Urgent warning: You've used up over 90% of your quota!");
//           } else if percentage_of_max >= 0.75 {
//               self.messenger
//                   .send("Warning: You've used up over 75% of your quota!");
//           }
//       }
//   }


//   #[cfg(test)]
//   mod tests {
//       use super::*;

//       struct MockMessenger {
//           sent_messages: Vec<String>,
//       }

//       impl MockMessenger {
//           fn new() -> MockMessenger {
//               MockMessenger {
//                   sent_messages: vec![],
//               }
//           }
//       }

//       impl Messenger for MockMessenger {
//           fn send(&self, message: &str) {
//               self.sent_messages.push(String::from(message));
//           }
//       }

//       #[test]
//       fn it_sends_an_over_75_percent_warning_message() {
//           let mock_messenger = MockMessenger::new();
//           let mut limit_tracker = LimitTracker::new(&mock_messenger, 100);

//           limit_tracker.set_value(80);

//           assert_eq!(mock_messenger.sent_messages.len(), 1);
//       }
//   }
//   ```

// 5. Da li sledeći kod proizvodi grešku? Zašto?

//   ```rust
//   pub trait Messenger {
//   fn send(&self, msg: &str);
//   }

//   pub struct LimitTracker<'a, T: Messenger> {
//       messenger: &'a T,
//       value: usize,
//       max: usize,
//   }

//   impl<'a, T> LimitTracker<'a, T>
//   where
//       T: Messenger,
//   {
//       pub fn new(messenger: &'a T, max: usize) -> LimitTracker<'a, T> {
//           LimitTracker {
//               messenger,
//               value: 0,
//               max,
//           }
//       }

//       pub fn set_value(&mut self, value: usize) {
//           self.value = value;

//           let percentage_of_max = self.value as f64 / self.max as f64;

//           if percentage_of_max >= 1.0 {
//               self.messenger.send("Error: You are over your quota!");
//           } else if percentage_of_max >= 0.9 {
//               self.messenger
//                   .send("Urgent warning: You've used up over 90% of your quota!");
//           } else if percentage_of_max >= 0.75 {
//               self.messenger
//                   .send("Warning: You've used up over 75% of your quota!");
//           }
//       }
//   }


//   #[cfg(test)]
//   mod tests {
//       use super::*;
//       use std::cell::RefCell;

//       struct MockMessenger {
//           sent_messages: RefCell<Vec<String>>,
//       }

//       impl MockMessenger {
//           fn new() -> MockMessenger {
//               MockMessenger {
//                   sent_messages: RefCell::new(vec![]),
//               }
//           }
//       }

//       impl Messenger for MockMessenger {
//           fn send(&self, message: &str) {
//               let mut one_borrow = self.sent_messages.borrow_mut();
//               let mut two_borrow = self.sent_messages.borrow_mut();

//               one_borrow.push(String::from(message));
//               two_borrow.push(String::from(message));
//           }
//       }

//       #[test]
//       fn it_sends_an_over_75_percent_warning_message() {
//           let mock_messenger = MockMessenger::new();
//           let mut limit_tracker = LimitTracker::new(&mock_messenger, 100);

//           limit_tracker.set_value(80);

//           assert_eq!(mock_messenger.sent_messages.borrow().len(), 1);
//       }
//   }
//   ```

6. Ispravi grešku u sledećem kodu:

  ```rust
  fn main() {
      let add_one_v4 = |x|  x + 1;
  }
  ```
  -
  ```rust
 fn main() {
    let add_one_v4 = |x: i32| x + 1;
}
```

7. Da li sledeći kod proizvodi grešku? Zašto?
-Da zato sto su drugaciji tipovi, ne moze da zakljuci koji je

  ```rust
  fn main() {
      let example_closure = |x| x;

      let s = example_closure(String::from("hello"));
      let n = example_closure(5);
  }
  ```

8. Da li sledeći kod proizvodi grešku? Zašto?
//Moze biti na testu (move ili bez)
- Ne
Before defining closure: [1, 2, 3]
Before calling closure: [1, 2, 3]
From closure: [1, 2, 3]
After calling closure: [1, 2, 3]

  ```rust
      fn main() {
      let list = vec![1, 2, 3];
      println!("Before defining closure: {:?}", list);

      let only_borrows = || println!("From closure: {:?}", list);

      println!("Before calling closure: {:?}", list);
      only_borrows(); //lambda izraz only_borrows, koji samo pozajmljuje vlasništvo
      println!("After calling closure: {:?}", list);
  }
  ```

9. Da li sledeći kod proizvodi grešku? Zašto?
- Da, ne može da pozajmi `list` kao nepromenljiv jer je takođe pozajmljen kao promenljiv
  ```rust
  fn main() {
      let mut list = vec![1, 2, 3];
      println!("Before defining closure: {:?}", list);

      let mut borrows_mutably = || list.push(7);
      println!("Before calling closure: {:?}", list); //immutable borrow occurs here
      borrows_mutably(); //mutable borrow later used here
      println!("After calling closure: {:?}", list);
  }
  ```

10. Ispravi grešku u sledećem kodu:

  ```rust
  use std::thread;

  fn main() {
      let list = vec![1, 2, 3];
      println!("Before defining closure: {:?}", list);

      thread::spawn(|| println!("From thread: {:?}", list))
          .join()
          .unwrap();
  }
  ```
- ne dozvoljava korišćenje vlasničkih vrednosti unutar closure-a koji se prenose u thread::spawn. Da bi prenela vlasnička vrednost u thread, mora se koristiti move
Before defining closure: [1, 2, 3]
From thread: [1, 2, 3]

```rust
  use std::thread;

  fn main() {
      let list = vec![1, 2, 3];
      println!("Before defining closure: {:?}", list);

      thread::spawn(move || println!("From thread: {:?}", list))
          .join()
          .unwrap();
  }
  ```

11. U zavisnosti od zapamćene vrednosti u zatvorenju, koje tri `Fn` osobine zatvorenje može da implementira?
-Fn, FnOnce, FnMut
FnOnce: omogućava zatvaranju da konzumira vrednosti iz svoje okoline. Zatvaranje koje implementira FnOnce može biti pozvano samo jednom. Na primer, zatvaranje koje koristi move ključnu reč će implementirati FnOnce jer prenosi vlasništvo nad vrednostima iz okoline.

FnMut: Ova osobina omogućava zatvaranju da mutira vrednosti iz svoje okoline, ali ne konzumira ih. Zatvaranje koje menja vrednosti koje je uhvatilo, ali ih ne konzumira, implementira FnMut.

Fn: Ova osobina omogućava zatvaranju da pozivaču pruži neizmenjene referencu na vrednosti iz svoje okoline. Zatvaranja koja ne koriste move i ne mutiraju vrednosti iz okoline implementiraju Fn.

12. Koju `Fn` osobinu implementiraju svi tipovi zatvorenja?
- FnOnce 

13. Da li sledeći kod proizvodi grešku? Zašto?
- Da, zato sto se string premesta a closure se izvrsava vise od jednom
- Vlasništvo nad String vrednošću ne može biti premesteno jer String ne implementira trait Copy

  ```rust
  #[derive(Debug)]
  struct Rectangle {
      width: u32,
      height: u32,
  }

  fn main() {
      let mut list = [
          Rectangle { width: 10, height: 1 },
          Rectangle { width: 3, height: 5 },
          Rectangle { width: 7, height: 12 },
      ];

      let mut sort_operations = vec![];
      let value = String::from("by key called");

      list.sort_by_key(|r| {
          sort_operations.push(value); 
          r.width
      });
      println!("{:#?}", list);
  }
  ```

14. Da li sledeći kod proizvodi grešku? Zašto?
-Ne
[
    Rectangle {
        width: 3,
        height: 5,
    },
    Rectangle {
        width: 7,
        height: 12,
    },
    Rectangle {
        width: 10,
        height: 1,
    },
], sorted in 6 operations

  ```rust
  #[derive(Debug)]
  struct Rectangle {
      width: u32,
      height: u32,
  }

  fn main() {
      let mut list = [
          Rectangle { width: 10, height: 1 },
          Rectangle { width: 3, height: 5 },
          Rectangle { width: 7, height: 12 },
      ];

      let mut num_sort_operations = 0;
      list.sort_by_key(|r| {
          num_sort_operations += 1;
          r.width
      });
      println!("{:#?}, sorted in {num_sort_operations} operations", list);
  }
  ```

15. Da li su iteratori "lenji" u Rust-u?
-Da, nemaju efekat dok ne pozovemo metodu

16. Šta predstavlja `Iterator` osobina?
-Logiku za dobavljanje sledecih elemenata 

17. Ispravi grešku u sledećem kodu:

  ```rust
  fn main() {
      let v1 = vec![1, 2, 3];

      let v1_iter = v1.iter();  //  let mut v1_iter  Dodajemo mut kako bismo mogli da menjamo v1_iter

      assert_eq!(v1_iter.next(), Some(&1));
      assert_eq!(v1_iter.next(), Some(&2));
      assert_eq!(v1_iter.next(), Some(&3));
      assert_eq!(v1_iter.next(), None);
  }
  ```

18. Ispravi grešku u sledećem kodu:

  ```rust
  fn main() {
      let v1 = vec![1, 2, 3];

      let mut v1_iter = v1.iter();

      let mut first = v1_iter.next().unwrap();
      first += 1;
  }
  ```

   ```rust
  fn main() {
      let mut v1 = vec![1, 2, 3];

      let mut v1_iter = v1.iter_mut();

      let mut first = v1_iter.next().unwrap();
      *first += 1;         //* se koristi za dereferenciranje, tj. dobijanje vrednosti koja se nalazi iza reference
       println!("Modified value: {}", first); //2
  }
  ```

19. Šta su konzumirajući adapteri (Engl. consuming adapters)?
- Metode iteratora koje konzumiraju izvor podataka na koji iterator ukazuje, više ne možemo pristupiti originalnom iteratoru jer su svi podaci već konzumirani ili potrošeni.
-iter(), sum(), konzumirao je sebe pa ga ne mozemo vise koristiti

20. Da li sledeći kod proizvodi grešku? Zašto?
- Kod proizvodi grešku jer je sum() metoda konzumirajući adapter, što znači da troši originalni iterator (v1_iter) i ne dozvoljava dalje korišćenje istog iteratora nakon poziva. 

  ```rust
  fn main() {
      let v1 = vec![1, 2, 3];

      let v1_iter = v1.iter();

      let total: i32 = v1_iter.sum();

      assert_eq!(total, 6);

      let give_me_next_pls = v1_iter.next();
  }
  ```

21. Šta su iterator adapteri (Engl. iterator adapters)?
- Metode koje se primenjuju na iteratorima kako bi transformisale, filtrirale ili modifikovale podatke tokom iteracije, proizvodeći novi iterator (adaptirani) kao rezultat. 
-map(), filter()

22. Ispravi grešku u sledećem kodu:

```rust
  fn main() {
      let v1: Vec<i32> = vec![1, 2, 3];
      let v2 = v1.iter().map(|x| x + 1).collect();
      assert_eq!(v2, vec![2, 3, 4]);
  }
````

```rust
fn main() {
    let v1: Vec<i32> = vec![1, 2, 3];
    let v2: Vec<i32> = v1.iter().map(|x| x + 1).collect(); //tip
    assert_eq!(v2, vec![2, 3, 4]);
}
```

23. Ispravi grešku u sledećem kodu:

    ```rust
    #[derive(PartialEq, Debug)]
    struct Shoe {
        size: u32,
        style: String,
    }

    fn shoes_in_size(shoes: Vec<Shoe>, shoe_size: u32) -> Vec<Shoe> {
        shoes.iter().filter(|s| s.size == shoe_size).collect()
    }

    #[cfg(test)]
    mod tests {
        use super::*;

        #[test]
        fn filters_by_size() {
            let shoes = vec![
                Shoe {
                    size: 10,
                    style: String::from("sneaker"),
                },
                Shoe {
                    size: 13,
                    style: String::from("sandal"),
                },
                Shoe {
                    size: 10,
                    style: String::from("boot"),
                },
            ];

            let in_my_size = shoes_in_size(shoes, 10);

            assert_eq!(
                in_my_size,
                vec![
                    Shoe {
                        size: 10,
                        style: String::from("sneaker")
                    },
                    Shoe {
                        size: 10,
                        style: String::from("boot")
                    },
                ]
            );
        }
    }
    ```

    ```rust
    #[derive(PartialEq, Debug, Clone)]
    struct Shoe {
        size: u32,
        style: String,
    }

    fn shoes_in_size(shoes: &[Shoe], shoe_size: u32) -> Vec<Shoe> {
        shoes.iter().filter(|s| s.size == shoe_size).cloned().collect()
    }

    #[cfg(test)]
    mod tests {
        use super::*;

        #[test]
        fn filters_by_size() {
            let shoes = vec![
                Shoe {
                    size: 10,
                    style: String::from("sneaker"),
                },
                Shoe {
                    size: 13,
                    style: String::from("sandal"),
                },
                Shoe {
                    size: 10,
                    style: String::from("boot"),
                },
            ];

            let in_my_size = shoes_in_size(&shoes, 10);

            assert_eq!(
                in_my_size,
                vec![
                    Shoe {
                        size: 10,
                        style: String::from("sneaker")
                    },
                    Shoe {
                        size: 10,
                        style: String::from("boot")
                    },
                ]
            );
        }
    }
    ```

24. Pri dodeli drugoj varijabli, da li Rust podrazumevano obavlja kopiranje (`Copy`) varijable?
- Da ako tip implementira Copy trait. Tipovi koji su jednostavni i ne poseduju resurse, poput integera, floatova, boolean , char, tuple gde svi elementi impl copy, i nekih struktura koje takođe implementiraju Copy, će se automatski kopirati kada ih dodeljujete drugoj varijabli.

25. Da li sledeći kod proizvodi grešku? Zašto?
-Ne:  5, 6
    ```rust
    fn inc(x: i32) -> i32 {
        x + 1
    }

    fn main() {
        let x = 5;
        let y = inc(x);
        println!("{x}, {y}");
    }
    ```

26. Napiši koncizniji kod umesto sledećeg koda upotrebom iterator adaptera i `filter` metode.

    ```rust
    pub fn search<'a>(query: &str, contents: &'a str) -> Vec<&'a str> {
        let mut results = Vec::new();

        for line in contents.lines() {
            if line.contains(query) {
                results.push(line);
            }
        }

        results
    }
    ```
    ```rust
    pub fn search<'a>(query: &str, contents: &'a str) -> Vec<&'a str> {
    contents.lines().filter(|&line| line.contains(query)).collect()
    }
    ```
