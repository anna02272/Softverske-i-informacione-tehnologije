using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vezbe02.Models;
using Vezbe02.MyExceptions;

namespace Vezbe02
{
    class Program
    {
        public static void ShowProfessors()
        {
            var professors = Data.Instance.ProfessorService.GetActiveProfessors();
            ShowProfessors(professors);
        }

        private static void ShowProfessors(List<Professor> professors)
        {
            foreach (Professor professor in professors)
            {
                Console.WriteLine(professor);
            }
        }

        public static void AddProfessor()
        {
            Address adresa = new Address
            {
                StreetNumber = "2",
                Country = "Drzava 2",
                City = "Grad 2",
                Street = "Ulica 2",
                Id = 1
            };

            Console.WriteLine("Enter email:");
            string email = Console.ReadLine();

            Console.WriteLine("Enter password:");
            string password = Console.ReadLine();

            Console.WriteLine("Enter first name:");
            string fistName = Console.ReadLine();

            Console.WriteLine("Enter last name:");
            string lastName = Console.ReadLine();

            Console.WriteLine("Enter JMBG:");
            string jmbg = Console.ReadLine();

            Console.WriteLine("Enter gender:(MALE/FEMALE/OTHER)");
            Enum.TryParse(Console.ReadLine(), out EGender gender);

            User user = new User
            {
                Address = adresa,
                Email = email,
                FirstName = fistName,
                LastName = lastName,
                JMBG = jmbg,
                Password = password,
                Gender = gender,
                UserType = EUserType.PROFESSOR,
                IsActive = true
            };

            Data.Instance.ProfessorService.Add(user);

            Console.WriteLine("Professor added successfuly");

        }

        public static void UpadteProfessor()
        {
            Console.WriteLine("Enter email:");
            string email = Console.ReadLine();

            var professor = Data.Instance.ProfessorService.GetById(email);

            if (professor != null)
            {
                Console.WriteLine("Enter FirstName:"); //moze se menjati koji god atribut
                string newFirstName = Console.ReadLine();

                professor.User.FirstName = newFirstName;

                Data.Instance.ProfessorService.Update(email, professor);

                Console.WriteLine("Professor updated successfuly.");
            }
            else
            {
                Console.WriteLine("There are no professor with the given email");
            }
        }

        private static void DeleteProfessor()
        {
            Console.WriteLine("Enter email:");
            string email = Console.ReadLine();
            try
            {
                Data.Instance.ProfessorService.Delete(email);
            }
            catch (UserNotFoundException e)
            {
                Console.WriteLine("User with the given email doesn't exsist");
            }
        }

        private static void SearchByEmail()
        {
            Console.WriteLine("Enter email:");
            string email = Console.ReadLine();

            var professors = Data.Instance.ProfessorService.GetActiveProfessorsByEmail(email);
            ShowProfessors(professors);
        }

        private static void SortByEmail()
        {
            var professors = Data.Instance.ProfessorService.GetActiveProfessorsOrderedByEmail();
            ShowProfessors(professors);
        }

        static void Main(string[] args)
        {
            //inicijalno kreiranje podataka i punjenje fajlova
            
            //Data.Instance.Initialize();

            //citanje iz tekstualnih fajlova
            Data.Instance.LoadData();

            string option;
           
            do
            {
                Console.WriteLine("Menu");
                Console.WriteLine("Option 1: Prikaz svih profesora");
                Console.WriteLine("Option 2: Unos novog profesora");
                Console.WriteLine("Option 3: Modifikacija profesora");
                Console.WriteLine("Option 4: Brisanje profesora");
                Console.WriteLine("Option 5: Pretraga po email adresi");
                Console.WriteLine("Option 6: Sortiranje po email adresi");
                Console.WriteLine("Option X: Kraj");
                Console.WriteLine("Option>>>");
                option = Console.ReadLine();

                switch (option)
                {
                    case "1":
                        ShowProfessors();
                        break;
                    case "2":
                        AddProfessor();
                        break;
                    case "3":
                        UpadteProfessor();
                        break;
                    case "4":
                        DeleteProfessor();
                        break;
                    case "5":
                        SearchByEmail();
                        break;
                    case "6":
                        SortByEmail();
                        break;
                    case "X":
                        break;
                    default:
                        Console.WriteLine("Molimo ponovite unos opcije!");
                        break;
                }

            } while (!option.Equals("X"));
        }
    }
}
