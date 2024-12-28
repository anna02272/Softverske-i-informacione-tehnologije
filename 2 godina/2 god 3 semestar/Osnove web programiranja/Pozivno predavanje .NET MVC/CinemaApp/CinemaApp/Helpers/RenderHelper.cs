using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CinemaApp.Helpers
{
    public static class RenderHelper
    {
        public static class Language
        {
            public const string SerbianTwoLetters = "sr";
            public const string SerbianLatin = "sr-Latn-RS";
        }
        public static string GetLanguageCode(string lang) 
            => lang == Language.SerbianTwoLetters 
                ? Language.SerbianLatin 
                : lang;
    }
}