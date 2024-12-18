using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel;
using System.Windows.Controls;

namespace ValidationTest
{
    class Student: IDataErrorInfo//jedna varijanta validacije preko implementacije ovog interfejsa
    {
        public string Ime { get; set; }
        public string Prezime { get; set; }
        public int GodinaUpisa { get; set; }
        public string Email1 { get; set; }
        public string Email2 { get; set; }
        public string Email3 { get; set; }
        public string Email4 { get; set; }
        public bool NotifyViaEmail { get; set; }

        public string Error
        {
            get
            {
                return "Neispravni podaci o studentu";
            }
        }

        public string this[string propertyName]
        {
            get
            {
                switch (propertyName)
                {
                    case "Ime":
                    case "Prezime":
                    case "GodinaUpisa":
                        if (string.IsNullOrEmpty(Ime) || string.IsNullOrEmpty(Prezime))
                            return "Polje ne sme biti prazno";
                        break;
                    case "Email4":
                        EMailValidationRule validator = new EMailValidationRule();
                        //validnost email svojstva zavisi i od vrednosti svojstva NotifyViaEmail
                        //samo ako je to svojstvo postavljeno na true, email format mora biti ispravan
                        if (NotifyViaEmail && validator.Validate(Email4, null) != ValidationResult.ValidResult)
                            return "Neispravan format e-mail adrese";
                        break;
                }
                return "";
            }
        }
    }
}
