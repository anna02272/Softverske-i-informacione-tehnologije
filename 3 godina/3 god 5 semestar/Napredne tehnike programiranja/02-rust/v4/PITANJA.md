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

2.  Pojednostavi kreiranje instance `user2` iz instance `user1` upotrebom sintakse za ažuriranje. Da li se `user1` može koristiti nakon instanciranja `user2`? Zašto?
    -User 1 nece imati vrednosti za acitve i username zato sto se radi premestanje,
    da zelimo da radi trebalo bi kloniranje da uradimo

        ```rust
        let user2 = User {
              active: user1.active,
              username: user1.username,
              email: String::from("another@example.com"),
              sign_in_count: user1.sign_in_count,
        };
        ```

3.  Da li su `black` i `origin` isti tip?
    -Nisu isti tip zato sto su razlicite strukture

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

6.  Implementiraj gorenavedeni zadatak upotrebom metode umesto asocirane funkcije.

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
   -Nad bilo kojom strukturom

8. Koja je razlika između `self` i `Self`?
   -Self pokazuje na tip impl bloka, zamenica za strukturu ,
   ako se refaktorise ne moramo svuda menjati nego samo ime impl bloka
   -self je instanca

9. Da li metoda uzima vlasništvo nad `self`?
   -Da

10. Kada bi metoda trebala da uzme vlasništvo nad `self`?
    -Kad nam instanca koju pozivamo metodu vise ne treba

11. Implementiraj asociranu funkciju nad `Rectangle` koja se zove `square` i koja kreira instancu `Rectangle` koja predstavlja kvadrat (dimenzije obe stranice su iste). Primer upotrebe funkcije je: `let sq = Rectangle::square(3)`;

````rust
 let sq = Rectangle::square(13);
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
    enum IpAddrKind {
        V4(string),
        V6(string),
    }

  let home =
  let loopback =

  }
  ```

15. Da li sledeći kod proizvodi grešku? Ukoliko da, ispravi je.

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
-five: Some(5,), six: Some(6,), none: None

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
        _ => println!("Svaki drugi broj");
    }

    fn add_fancy_hat() {}
    fn remove_fancy_hat() {}
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
-if let trazi jedno podudaranje tj jednu varijantu iz enuma

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

````
