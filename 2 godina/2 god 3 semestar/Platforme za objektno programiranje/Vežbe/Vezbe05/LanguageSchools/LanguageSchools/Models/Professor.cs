using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LanguageSchools.Models
{
    [Serializable]
    class Professor
    {
        [NonSerialized]
        private User user;

        public User User { get => user; set => user = value; }
        public string UserId { get; set; }

        public override string ToString()
        {
            return $"[Professor] {User.FirstName} {User.LastName}, {User.Email}";
        }
    }
}
