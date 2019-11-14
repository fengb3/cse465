"""
Transforms a string represented as a list of characters into its Pig Latin 
version.

Args:
	l: a list of characters representing one of the input strings
	   e.q., l = ['a', 'p', 'p', 'l', 'e'] represents the string "apple"
	
Returns:
	A list of characters representing the pig latin version of the string 
	represented by argument l. The function is also supposed to change the
	list l in place to its pig latin version.
	   e.g., for argument l = ['a', 'p', 'p', 'l', 'e'], the return should be
	   ['a', 'p', 'p', 'l', 'e', 'w', 'a', 'y'] and also the argument l should
	   be changed to value ['a', 'p', 'p', 'l', 'e', 'w', 'a', 'y'].
"""
def ToPigLatin(l):
	# TODO: implement this function
	return l
	
		
# Main -- do not change the code below
s = input('Enter 5 strings separated by one blank space: ')
myList = s.strip().split(' ')

resList = []
for i in range(3):
	l = list(myList[i])
	ToPigLatin(l)
	resList.append(''.join(l))
for i in range(3,5):
	l = list(myList[i])
	resList.append(''.join(ToPigLatin(l)))
	
print(' '.join(resList))
	

