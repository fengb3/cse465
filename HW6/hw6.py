def adder(*argv):
	sum = 0
	for value in argv:
		sum += value
	return sum

print("Sum 1 is", adder(1,2,3,4,5,6))
print("Sum 2 is", adder(1,5))
print("Sum 3 is", adder())
