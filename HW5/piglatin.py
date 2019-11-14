"""
	This functio determing the first element of a list is vowel
	if the list is switched w and y are also vowel

	Args:
		a char array
	Returns:
		True: the first char is an vowel
		False: the first char is not a vowel	
"""
def firstVowel(l):
	global isSwitched
	vList2 = ['A', 'E', 'I', 'O', 'U', 'W', 'Y', 'a', 'w', 'y', 'e', 'i', 'o', 'u']
	vList1 = ['A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u']
	if l[0] in vList1:
		return True;
	# end if
	elif l[0] in vList2:
		return isSwitched
	# end elif
	else:
		return False
	# end else
# end def


"""
	this fucntion move the fist element in a list to the end of the list

	Args:
		l: a list that need to be switched
"""
def switch(l):
	global isSwitched
	first = l[0]
	isUpper = first.isupper();
	del l[0]
	l.append(first.lower())
	if isUpper:
		l[0] = l[0].upper()
	# end if
	isSwitched = True
# end def


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
	global isSwitched
	if (not isSwitched) and firstVowel(l):
		l.extend(['w', 'a', 'y'])
		isSwitched = False
		return l
	# end if
	elif isSwitched and firstVowel(l):
		l.extend(['a', 'y'])
		isSwitched = False
		return l
	# end elif
	else:
		switch(l)
		return ToPigLatin(l)
	# end else
# end def

# a globale variable that determin if a list is switched
isSwitched = False;
		
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