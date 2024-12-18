class ComplexNumber(object):

    def __init__(self, real=0, imaginary=0):
        self._real = real
        self._imaginary = imaginary

    @property
    def real(self):
        return self._real

    @real.setter
    def real(self, new_value):
        self._real = new_value

    @property
    def imaginary(self):
        return self._imaginary

    @imaginary.setter
    def imaginary(self, new_value):
        self._imaginary = new_value

    def __str__(self):
        return str(self._real) +  " + " + str(self._imaginary) + "i"

if __name__ == '__main__':
    n1 = ComplexNumber(1, 2)
    n2 = ComplexNumber(-1, 5)
    print(n1)


