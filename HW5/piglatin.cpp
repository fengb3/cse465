// piglatin.cpp

#include <iostream>
#include <cstring>

using namespace std;

const int MAX = 43;

char* ToPigLatin(char* word);

int main()
{
   // creation of 5 character strings, each length MAX
   char word[5][MAX];
   int i;				// loop counter

   cout << "Input 5 words: ";
   for (i = 0; i < 5; i++)
       cin >> word[i];

   cout << "\nPig Latin version of the 5 words:\n";
   for (i = 0; i < 3; i++)
   {
      ToPigLatin(word[i]);
      cout << word[i] << ' ';
   }
   // Note that the above outputs illustrate that the original
   //  string itself has been converted.  
   
   // The outputs below illustrate
   //  that a pointer to this string is also being returned from the 
   //  function.
   cout << ToPigLatin(word[3]) << ' '
        << ToPigLatin(word[4]) << '\n';

   return 0;
}


/**
 * 1. A word is a consecutive sequence of letters (a-z, A-Z) or apostrophes.
 *    You may assume that the input to the function will only be a single "word". 
 *    Examples:Zebra, doesn't, apple.
 * 2. If a word starts with a vowel, 
 *    the Pig Latin version is the original word with "way" added to the end.
 * 3. If a word starts with a consonant, or a series of consecutive consonants, 
 *    the Pig Latin version transfers all consonants up to the first vowel to theendof the word, 
 *    and adds "ay" to the end.
 * 4. The letter 'y' and the letter 'w'should be treated as consonantsif theyare the first lettersof a word, 
 *    but treated as vowelsotherwise.
 * 5. If the original word is capitalized, 
 *    the new Pig Latin version of the word should be capitalized in the first letter 
 *    (i.e. the previous capital letter may not be capitalized any more). 
 */
char* ToPigLatin(char* word){
   // i dont know what to do here
   &word += '2';
}
