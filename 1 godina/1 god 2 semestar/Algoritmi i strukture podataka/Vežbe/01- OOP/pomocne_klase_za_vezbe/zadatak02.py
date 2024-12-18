class Student(object):

    def __init__(self, first_name, last_name):
        self._first_name = first_name
        self._last_name = last_name

    @property
    def first_name(self):
        return self._first_name

    @first_name.setter
    def first_name(self, new_value):
        self._first_name = new_value

    @property
    def last_name(self):
        return self._last_name

    @last_name.setter
    def last_name(self, new_value):
        self._last_name = new_value

    def introduce_yourself(self):
        print("My name is " + self._first_name + " " + self._last_name + ".")

if __name__ == '__main__':
    s1 = Student("Pera", "Peric")
    s1.introduce_yourself()