class Calculator:
    def setdata(self, a, b):
        self.a = a
        self.b = b

cal1 = Calculator()
cal1.setdata(10, 20)
print(cal1.a, cal1.b)

cal2 = Calculator()
Calculator.setdata(cal2, 20, 30)
print(cal2.a, cal2.b)

cal3 = Calculator()
print(id(cal1), id(cal3))
