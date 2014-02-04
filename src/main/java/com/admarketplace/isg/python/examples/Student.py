class Student:
    def __init__(self, name, age, major):
        self.name = name
        self.age = age
        self.major = major

    def is_old(self):
        return self.age > 100

s = Student('John', 88, None)

old = s.is_old()

if old==True:
    age = "old"
else:
    age = "young"


if old==False:
    age = "old"
else:
    age = "young"

print ("name of student is " + s.name + "he is old or not : "+age)


# make a list of class Person(s)
stdList = []
stdList.append(Student("Payne N. Diaz", 51,"Math"))
stdList.append(Student("Payne N. Diaz1", 53,"Math2"))
stdList.append(Student("Payne N. Diaz2", 54,"Math3"))
stdList.append(Student("Payne N. Diaz3", 55,"Math4"))

print ("Show one particular item:")
print (stdList[0].name)



