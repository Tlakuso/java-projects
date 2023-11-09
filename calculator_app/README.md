This is my first program aftre the lecture introduced java swing. It is a Java program for a simple calculator application implemented as a graphical user interface (GUI) using Java Swing. Here's a brief explanation of the code:

The code defines a Java class named "calculator" that extends the javax.swing.JFrame class. This class represents the calculator application's main window.

The GUI elements of the calculator are created and initialized within the constructor of the "calculator" class. These elements include text fields, buttons for digits (0-9), arithmetic operators (+, -, *, /), a clear button (C), a delete button (DEL), and an equal button (=).

Radio buttons for turning the calculator on (ON) and off (OFF) are provided, which enable or disable the calculator functionalities.

Action listeners are attached to the buttons, which respond to user interactions with the GUI elements. For example, when a digit button is clicked, the corresponding digit is appended to the text field, and when an operator button is clicked, the operation is performed.

The calculator logic is implemented using variables like num, calculation, and ans. The calculation variable is used to determine which arithmetic operation (addition, subtraction, multiplication, or division) to perform when the equal button is clicked.

The airithmetic_operation() method is used to perform the arithmetic operations based on the selected operator.

The enable() and disable() methods are used to enable or disable the GUI elements based on the state of the radio buttons (ON or OFF).

The main method creates an instance of the "calculator" class and displays the calculator window when the application is run.

Overall, this code creates a basic calculator application with a graphical user interface using Java Swing components, and it allows users to perform simple arithmetic calculations. The calculator can be turned on and off using the provided radio buttons.
