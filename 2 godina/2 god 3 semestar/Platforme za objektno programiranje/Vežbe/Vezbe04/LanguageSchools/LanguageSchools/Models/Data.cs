using System;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using LanguageSchools.Services;

namespace LanguageSchools.Models
{
    sealed class Data
    {
        private static readonly Data instance = new Data();
        public IUserService UserService { get; set; }
        public IProfessorService ProfessorService { get; set; }

        static Data() { }

        private Data()
        {
            UserService = new UserService();
            ProfessorService = new ProfessorService();
        }

        public static Data Instance
        {
            get
            {
                return instance;
            }
        }

        public void Initialize()
        {
            Address address = new Address
            {
                City = "Novi Sad",
                Country = "Srbija",
                Street = "ulica1",
                StreetNumber = "22",
                Id = 1
            };

            User user1 = new User()
            {
                FirstName = "Pera",
                LastName = "Peric",
                Email = "pera@gmail.com",
                JMBG = "121346",
                Password = "peki",
                Gender = EGender.MALE,
                Address = address,
                UserType = EUserType.ADMINISTRATOR,
                IsActive = true
            };
        
            User user2 = new User
            {
                Email = "mika@gmail.com",
                FirstName = "mika",
                LastName = "Mikic",
                JMBG = "121346",
                Password = "zika",
                Gender = EGender.FEMALE,
                UserType = EUserType.PROFESSOR,
                IsActive = true,
                Address = address
            };

            UserService.Add(user1);
            ProfessorService.Add(user2);
        }

        public void LoadData()
        {
            var users = LoadUsers();
            var professors = LoadProfessors();

            foreach (var professor in professors)
            {
                var user = users.Find(u => u.Email == professor.UserId);
                professor.User = user;
            }

            UserService.Set(users);
            ProfessorService.Set(professors);
        }

        private List<User> LoadUsers()
        {
            try
            {
                IFormatter formatter = new BinaryFormatter();
                
                using (Stream stream = new FileStream(Config.usersFilePath, FileMode.Open, FileAccess.Read))
                {
                    return (List<User>)formatter.Deserialize(stream);
                }
            }
            catch(Exception e)
            {
                return new List<User>();
            }
        }

        private List<Professor> LoadProfessors()
        {
            try
            {
                IFormatter formatter = new BinaryFormatter();
                using (Stream stream = new FileStream(Config.professorsFilePath, FileMode.Open, FileAccess.Read))
                {
                    return (List<Professor>)formatter.Deserialize(stream);
                }
            }
            catch(Exception e)
            {
                return new List<Professor>();
            }
         
        }
    }
}
