from graphics import *
import random

width= int(raw_input("How wide do you want your window?"))
height= int(raw_input("How high do you want your window?"))

number = int(raw_input("How many points do you want to plot?"))

fred = GraphWin("Chaos", width, height)

x1 = 10
y1 = height - 10
x2 = width/2
y2 = 10
x3 = width - 10
y3 = height - 10

fred.plot(x1,y1)
fred.plot(x2,y2)
fred.plot(x3,y3)

mx = width/2
my = height/2

for index in range(number):
    pick = random.randint(1,3)
    if pick == 1:
        mx = ( x1 + mx) /2
        my = ( y1 + my) /2
        fred.plot(mx, my, "red")
        
    if pick == 2:
        mx = ( x2 + mx) /2
        my = ( y2 + my) /2
        fred.plot(mx, my, "black")
        
    if pick == 3:
        mx = ( x3 + mx) /2
        my = ( y3 + my) /2
        fred.plot(mx, my, "purple")
              

fred.getMouse()
fred.close()
