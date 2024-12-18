using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Controls;
using System.Text.RegularExpressions;

namespace ValidationTest
{
    public class EMailValidationRule: ValidationRule
    {
        Regex regex = new Regex(@"\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b", 
            RegexOptions.IgnoreCase);

        public override ValidationResult Validate(object value, System.Globalization.CultureInfo cultureInfo)
        {
            String v = value as string;    
            if (v != null && regex.Match(v).Success)
                return new ValidationResult(true, null);
            else
                return new ValidationResult(false, "Neispravan format e-mail adrese");            
        }
    }
}
