﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using LanguageSchools.Services;

namespace LanguageSchools.Models
{
    [Serializable]
    public class Data
    {
        [NonSerialized]
        private static Data instance;

        public List<User> Users { get; set; }
        public List<Professor> Professors { get; set; }

        static Data() { }

        private Data()
        {
            Users = new List<User>();
            Professors = new List<Professor>();
        }

        public static Data Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new Data();
                }

                return instance;
            }

            private set => instance = value;
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
                Gender = EGender.FEMEALE,
                UserType = EUserType.PROFESSOR,
                IsActive = true,
                Address = address
            };

            Users.Add(user1);

            var professor = new Professor
            {
                User = user2
            };

            Professors.Add(professor);
        }

        public void Save()
        {
            IFormatter formatter = new BinaryFormatter();
            using (Stream stream = new FileStream(Config.dataFilePath, FileMode.Create, FileAccess.Write))
            {
                formatter.Serialize(stream, this);
            }
        }

        public static void Load()
        {
            try
            {
                IFormatter formatter = new BinaryFormatter();
                using (Stream stream = new FileStream(Config.dataFilePath, FileMode.Open, FileAccess.Read))
                {
                    Instance = (Data)formatter.Deserialize(stream);
                }
            }
            catch (Exception e)
            {
                Instance = new Data();
            }

        }
    }
}
