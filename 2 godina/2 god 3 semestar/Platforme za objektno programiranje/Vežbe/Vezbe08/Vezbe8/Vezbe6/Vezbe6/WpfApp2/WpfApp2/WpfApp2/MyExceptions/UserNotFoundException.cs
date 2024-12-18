using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp2.MyExceptions
{
    class UserNotFoundException : Exception
    {
        public UserNotFoundException(): base() { }

        public UserNotFoundException(string message): base(message) { }

        public UserNotFoundException(string message, Exception innerException): base(message, innerException) { }
    }
}
