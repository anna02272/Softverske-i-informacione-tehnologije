using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace WpfApp2.Validacije
{
    public class EmailValidacijaca : ValidationRule
    {
        public override ValidationResult Validate(object value, CultureInfo cultureInfo)
        {
            if (value.ToString().Contains('@'))
            {
                return new ValidationResult(true, "");
            }
            return new ValidationResult(false, "Email treba da sadrzi @");
        }
    }
}
