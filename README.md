## Code Breaker Project

### Overview
The Code Breaker Project, developed in Java, is a software tool designed for decrypting encrypted texts through statistical frequency analysis of letter occurrences. It utilizes a known frequency map of a language obtained from a `training.txt` file to decode texts encoded with a substitution cipher found in `decodethis.txt`. The program counts and sorts letter frequencies in both training and coded texts and hypothesizes probable substitutions to decode the ciphered text.

### Technical Description
The `CodeBreaker.java` application employs arrays to perform dynamic manipulations, crucial for sorting and substituting letters based on their frequencies. The program reads the training and coded texts, calculates the frequency of each letter, and sorts these frequencies to align with a typical language distribution. These operations facilitate the construction of a cipher key, which is then used to replace letters in the coded text, rendering an understandable output.

## Program Execution Example 

**Program Execution Example:**
- Suppose `training.txt` includes the text "HELLO WORLD" where the frequency of 'L' is notably high.
- In `decodethis.txt`, the cipher text "TXXF OEPPE" may have 'X' replacing 'L' based on frequency alignment.
- The program decodes "TXXF OEPPE" back to "HELLO WORLD" by aligning the letter frequencies from both files, demonstrating how it effectively unciphers the text.

### Files Included
- `CodeBreaker.java`: Main application source code.
- `training.txt`: Text file containing normal language text for frequency analysis.
- `decodethis.txt`: Encrypted text file using a substitution cipher method.
- `Makefile`: Simplifies the compilation and running of the program.

### How to Run the Program
1. **Compile the Program**
   ```bash
   make compile
   ```
2.	**Run the Program**
   ```bash
   make run
   ```
