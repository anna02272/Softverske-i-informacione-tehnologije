class MyException(Exception):
    pass

def f():
    raise MyException("Desila se greska!")

class M(object):

    def __init__(self, p):
        self.prop = p

    def get_prop(self):
        print("Getter")
        return self._prop

    def set_prop(self, p):
        self._prop = p

    prop = property(get_prop, set_prop)

if __name__ == '__main__':
    m = M("ABC")
    print(m.prop)


