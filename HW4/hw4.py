# this is Homework for CSE 465
# Author Bohan Feng

import sys
import csv

def fileOutput(filename, str):
    '''
        this method create a file output
        Args:
            filename (str): out filename
            str (str): content of outputfilne
    '''
    extname = ".txt"
    filename += extname
    f = open(filename, "w")
    f.write(str)
# end def

def modifieLine(line, list):
    '''
        this method replace the '<<key>>' to "<<value>>"
        Args: 
            line (str): a string to be modified
            list (dict): a dictionary that contains the keys and value
        Return:
            line (str): a modified string
    ''' 
    for x in list:
        line = line.replace("<<" + x + ">>", list[x])
    # end for
    return line
# end def

def getresult(filename1, filename2):
    '''
        this method reads files and get the content and make the content into specic format
        Args:
            filename1 (str): tsv file
            finename2 (str): tmp file
    '''
    with open(filename1) as tsvfile:
        reader = csv.DictReader(tsvfile, dialect='excel-tab')
        for row in reader:
            re = ""
            with open(filename2) as f:
                for line in f:
                    re += modifieLine(line, row)
                # end for
            # end with
            fileOutput(row['ID'], re)
        # end for
    # end with
# end def

# filename
filename1 = sys.argv[1]
filename2 = sys.argv[2]

# get the result
getresult(filename1, filename2)