# MovieTheaterSeatingSystem

## Language used: Java

## Program description:
Designing and writing a seat assignment program to maximize both customer satisfaction and customer safety.
The implementation for this problem should follow below rules:
1. Customers will likely to take seats at the middle rows first, as viewing from those rows will result in better experience.
2. Customers come first will be allocated first (first comes, first serves).
3. Try to fullfill as many requests as possible.
4. Firstly, try to find one row that can fullfill all customers of one reservation.
5. Secondly, if cannot find such row, customers are assigned to empty seats, from middle.
6. If there are not enough seats left, customers will be informed about that.

## Assumption:
1. One reservation cannot be completed if there are not enough seats left or the number of customers is less than or equal to 0.
2. Due to COVID-19, the theater should require that gap between each customer is 3 seats and one rows, making that only 25 seats available.
3. The order of the reservation is the order of the input file.

## Rules to fullfill customer Satisfactions:
1. Firstly, customers of one reservation tend to seat in one row.
2. Secondly, the order of finding seat should be from the middle first, as viewing from the middle will grant better experience.

## Maximize Seats:
1. Always try allocating customers in one row first. If find such row, always start allocating from the first column.
2. If cannot find such row, customers will be allocated wherever possible (still start from middle first).
### Note: Always checking that the remaing seats are enough before trying allocating seats.

## How to run?
1. Clone this repository.
2. Go to the src folder.
3. Open cmd or terminal (based on OS) in src folder.
4. Ensure that java is installed and added to PATH.
5. Type javac Driver.java to compile Driver.java into bytecode.
6. Type java Driver "absolute path to input fil"
