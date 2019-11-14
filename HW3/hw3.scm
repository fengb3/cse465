; Helper function - do not change
(define (mydisplay value)
	(display value)
	(newline)
	#t
)
; Helper function
(define (inlist? val lst)
	(if (null? lst) #f
		(if 
			(eq? (car lst) val) #t
			(inlist? val (cdr lst))
		)
	)
)
; Returns the intersection of two sets. The inputs are flat lists
; of atoms. The result is a flat list containing all the elements
; that appear in both of the two lists. No duplicates 
; should be present in the result. Order is not important.
; You can use the built-in function member.
; (intersection '(a b c) '(1 2 a b c)) -> (a b c)
; (intersection '(a b c) '(1 2 a b c 0)) -> (a b c)
; (intersection '(a b c) '(1 2)) -> ()
(define (intersection lst1 lst2)
	(if (null? lst1)
		'() 
		(if (null? lst2)
			'()
			(if (inlist? (car lst1) lst2)
				(cons (car lst1) (intersection (cdr lst1) lst2))
				(intersection (cdr lst1) lst2)
			)
		)
	)
)

; Return a list containing the original list's values multiplied by c.
; lst is a list of integers and c is an integer
(define (multiplyIt c lst)
; TODO: Fill this in
	(if (null? lst)
		'()
		(map (lambda (x) (* x c)) lst))
	) 	; stub -- modify it accordingly
)


; Returns a list with only the positive items in lst
; For example (posOnly '(-1 1 2 0 3 4 -4 5)) should return
; (1 2 3 4 5)
(define (posOnly lst)
	(cond 
		((null? lst) '())
		((<= (car lst) 0) (posOnly (cdr lst)))
		(else
			(cons (car lst) (posOnly (cdr lst)))
		)
	)
)

; helper method to find the number of zero in lst
(define (zeroNum lst) 
	(cond
		((null? lst) 0)
		(else 
			(+ (if (= (car lst) 0) 1 0) (zeroNum (cdr lst)))
		)
	)
)
; helper method to find the number of negative numbers in lst
(define (negNum lst) 
	(cond
		((null? lst) 0)
		(else 
			(+ (if (< (car lst) 0) 1 0) (negNum (cdr lst)))
		)
	)
)
; helper method to find the number of postive numbers in lst
(define (posNum lst) 
	(cond
		((null? lst) 0)
		(else 
			(+ (if (> (car lst) 0) 1 0) (posNum (cdr lst)))
		)
	)
)

; Returns a list of three numbers (numZero numPos numNeg),
; where these numbers correspond to the number of zeros,
; number of positive numbers, and the number of negative numbers
; respectively.
; For example (zeroPosNeg '(-9 2 3 0 -2 -8 0)) should return
; (2 2 3). Approximately, 20% of this problem's points will be
; awarded for doing this with just one pass through the list.
; lst -- flat list containing numeric values, and length is >= 1.
(define (zeroPosNeg lst)
	(list (zeroNum lst) (posNum lst) (negNum lst))
)


; Removes all occurrences of v from lst,
; where v is an integer and lst is a list of integers.
; The function must use tail recursion!
; No points if tail recursion is not used!
(define (removeTail v lst)
	(cond
		((null? lst) '())
		((not (inlist? v lst)) lst)
		((= v (car lst)) (removeTail v (cdr lst)))
		(else (cons (car lst) (removeTail v (cdr lst))))
	)
)

; sales.scm contains all the company's sales.
; You should not modify this file. Your code
; should work for other instances of this file
; such as salesBig.scm 
(load "sales.scm")
;(load "salesBig.scm")

; Returns the profit information out of a given record for a sale.
; (getProfit '(3 ("10/13/2010" "10/20/2010") (261.54 0.04 -213.25 38.94) ("Regular Air" "Nunavut") "Eldon Base for stackable storage shelf, platinum")) -> -213.25
(define (getProfit sale)
	(cadr(cdaddr sale))
)

; Returns the province information out of a given record for a sale.
; (getProv '(3 ("10/13/2010" "10/20/2010") (261.54 0.04 -213.25 38.94) ("Regular Air" "Nunavut") "Eldon Base for stackable storage shelf, platinum")) -> Nunavut
(define (getProv sale)
	(cadr(cadddr sale))
)

; stackoverflow tells me to add this to make sure contains function works 
(define contains member)

; Returns the average profit (per items sold) for a given province. 
; Returned orders are not included in this calculation.
(define (averageProfitProv prov sales returns)
    (averageProfitProvHelper prov sales returns 0 0)
)

(define (averageProfitProvHelper prov sales returns num1 num2)
        (if (null? sales) (/ num1 num2)
            (if (equal? (getProv (car sales)) prov)
                    (if (contains (getOrderID sales) returns)
                            (averageProfitProvHelper prov (cdr sales) returns num1 num2)
                            (averageProfitProvHelper prov (cdr sales) returns (+ num1 (getProfit (car sales))) (+ num2 1))
							)
                 (averageProfitProvHelper prov (cdr sales) returns num1 num2)
			)         
        )
)

; helper method returns the first date attribute in sales list
(define (getDate sales)
	(caadr (car sales))
)
; helper method returns the first order num in sales list
(define (getOrderID sales)
	(car (car sales))
)

; Returns the set (i.e., list with no duplicates) of order numbers
; that were placed on a given date.
(define (getOrdersPlacedOn date sales)
	(if (null? sales)
		'()
	;else
		(if (equal? (getDate sales) date)
			(cons (getOrderID sales) (getOrdersPlacedOn date (cdr sales)))
		;else
			(getOrdersPlacedOn date (cdr sales))
		)
	)
)

; Returns the set (i.e., list with no duplicates) of order numbers
; with multiple items being ordered.
; Note that the records in sales are ordered by order number.
(define (multipleItems sales)
	(if (null? sales)
		'()
		(if (null? (cdr sales))
			'()
			(if (null? (cddr sales))
				'()
				(if (equal? (car (makeOrder sales)) (makeseOrder sales))
					(cons (car (makeOrder sales)) (multipleItems (cddr sales)))
					(if (equal? (car (makeOrder sales)) (makefiOrder sales))
						(cons (car (makeOrder sales)) (multipleItems (cdr sales)))
						(multipleItems (cdr sales))
					)
				)
			)
		)
	)
)

; helper mehthod the find the a order 
(define (makeseOrder sales)
	(car (cddr (makeOrder sales)))
)
; helper method that find a order
(define (makefiOrder sales)
	(car (cdr (makeOrder sales)))
)

(define (makeOrder sales)
	(if (null? sales)
		'()
		(cons (car (car sales)) (makeOrder (cdr sales)))
	)
)




; Returns the list of provinces to which a given item was sold and the
; numbers of such items sold in that province.
; The expected result is a list of the form
; '((prov1 numItem1) (prov2 numItem2) ... (provn numItemn) 
; where numItemi is the number of items of the type given as an input sold in provi, 
; and prov1, prov2, ... provn are unique (no duplicates)
(define (getProvincesForItem item sales)
; TODO: Fill this in
	'((prov1 numItem1) (prov2 numItem2)) 	; stub -- modify it accordingly
)

; Do not modify the following line
(load "hw3tests.scm")

,exit
