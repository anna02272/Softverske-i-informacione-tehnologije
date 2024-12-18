using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LanguageSchools.Models
{
    [Serializable]
    public class Professor: ICloneable
    {
        public int Id { get; set; }
        private User user;
        public int UserId { get; set; }

        public User User 
        { 
            get => user; 
            set {
                user = value;
                UserId = user.Id; // kada se setuje User tada se setuje i UserId, tako ne moramo kasnije da ih setujemo zasebno
            } 
        }

        public object Clone()
        {
            return new Professor
            {
                Id = Id,
                User = User.Clone() as User
            };
        }

        public override string ToString()
        {
            return $"[Professor] {User.FirstName} {User.LastName}, {User.Email}";
        }
    }
}
