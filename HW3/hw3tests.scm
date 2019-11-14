(mydisplay "1. INTERSECTION --------------------------------------")
(mydisplay (intersection '(a b c) '(1 2 a b c)))
(mydisplay (intersection '(a b c) '(1 2 a b c 0)))
(mydisplay (intersection '(a b c) '(1 2)))

(mydisplay "2. MULTIPLYIT --------------------------------------")
(mydisplay (multiplyIt 3 '(-1 1 2 3 4 -4 5)))

(mydisplay "3. POSONLY --------------------------------------")
(mydisplay (posOnly '(-1 1 2 0 3 4 -4 5)))
(mydisplay (posOnly '(-1 -1 -2 0)))
(mydisplay (posOnly '(1 2 3 4 5)))
(mydisplay (posOnly '()))

(mydisplay "4. ZEROPOSNEG --------------------------------------")
(mydisplay (zeroPosNeg '(-9 2 3 0 -2 -8 0)))
(mydisplay (zeroPosNeg '(-1 1 2 3 4 -4 5)))
(mydisplay (zeroPosNeg '(-1 0 0)))
(mydisplay (zeroPosNeg '()))

(mydisplay "5. REMOVETAIL --------------------------------------")
(mydisplay (removeTail 1 '(1 2 3)))
(mydisplay (removeTail 1 '(1 2 1 1 14 1 5 6)))
(mydisplay (removeTail 1 '()))
(mydisplay (removeTail 1 '(1)))

(mydisplay "6. GETPROFIT --------------------------------------")
(mydisplay (getProfit '(3 ("10/13/2010" "10/20/2010") (261.54 0.04 -213.25 38.94) ("Regular Air" "Nunavut") "Eldon Base for stackable storage shelf, platinum")))
(mydisplay (getProfit '(293 ("10/1/2012" "10/2/2012") (10123.02 0.07 457.81 208.16) ("Delivery Truck" "Northwest Territories") "1.7 Cubic Foot Compact Cube Office Refrigerators")))

(mydisplay "7. GETPROV --------------------------------------")
(mydisplay (getProv '(3 ("10/13/2010" "10/20/2010") (261.54 0.04 -213.25 38.94) ("Regular Air" "Nunavut") "Eldon Base for stackable storage shelf, platinum")))
(mydisplay (getProv '(293 ("10/1/2012" "10/2/2012") (10123.02 0.07 457.81 208.16) ("Delivery Truck" "Northwest Territories") "1.7 Cubic Foot Compact Cube Office Refrigerators")))

(mydisplay "8. AVEARAGEPROFITPROV --------------------------------------")
(mydisplay (averageProfitProv "Nunavut" SALES RETURNS))

(mydisplay "9. GETORDERSPLACEDON --------------------------------------")
(mydisplay (getOrdersPlacedOn "10/1/2012" SALES))

(mydisplay "10. MULTIPLEITEMS --------------------------------------")
(mydisplay (multipleItems SALES))

(mydisplay "11. GETPROVINCESFORITEM --------------------------------------")
(mydisplay (getProvincesForItem "Xerox 210" SALES))


