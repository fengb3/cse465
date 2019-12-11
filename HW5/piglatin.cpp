// piglatin.cpp

#include <iostream>
#include <cstring>
#include <cctype>

using namespace std;

/* some globale variables*/
const int MAX = 43;
bool isSwitched = false;
   // 2 char[] contains vowel letter for different purposes
const char vList1[] = "AEIOUaeiou";
const char vList2[] = "AEIOUWYaeiouwy";

/* declariations for functions*/
char* ToPigLatin(char* word);
bool firstVowel(char *word);
void switchChar(char *str);
bool inList1(char *c);
bool inList2(char *c);
char* append(char * a, char * b);

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

bool inList1(char * c){
   for(auto vowel: vList1){
      if (c[0] == vowel){
         return true;
      }
   }
   return false;
}

bool inList2(char * c){
   for(auto vowel: vList2){
      if (c[0] == vowel){
         return true;
      }
   }
   return false;
}

bool firstVowel(char *word){
   if(inList1(word)){
      return true;
   }
   else if(inList2(word)){
      return isSwitched;
   }
   else{
      return false;
   }
}


/**
 * this method moves the first letter in an char to the end
 * set isSwitched to true
 * @param char* str: the char to be operated 
 */
void switchChar(char * str) {
   char first = str[0];

   bool isUpper = isupper(first);

   char re[strlen(str)];

   for (int i = 1; i < strlen(str); i++){
      str[i - 1] = str[i];
   }

   str[strlen(str) - 1] = tolower(first);

   if(isUpper)
      str[0] = toupper(str[0]);

   isSwitched = true;
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
 * 
 * @param char* word the char to be operated
 */
char* ToPigLatin(char* word){
   // i dont know what to do here
   char voappend[] = "way";
   char nonvoappend[] = "ay";
   int len = strlen(word);
   if (!isSwitched && firstVowel(word)){
      word[len] = 'w';
      word[len+1] = 'a';
      word[len+2] = 'y';
      word[len+3] = '\0';
      isSwitched = false;
      return word;
   }
   else if (isSwitched && firstVowel(word)){
      word[len] = 'a';
      word[len+1] = 'y';
      word[len+2] = '\0';
      isSwitched = false;
      return word;
   }
   else{
      switchChar(word);
      return ToPigLatin(word);
   }
}
