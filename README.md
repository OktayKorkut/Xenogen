# XenoGen

A Java command-line application that simulates alien genetics by generating random DNA sequences, assigning gender and health metrics, and allowing various mating operations.

---

##  Features

- **Random DNA Generation:** Creates DNA sequences of fixed length (128) for a user-defined alien population.
- **Gender & Health:** Determines and displays each alien's gender and health status based on their DNA.
- **Mating Options:**
  - **Pairwise Mating:** Mate two selected aliens to produce offspring.
  - **Random Mating:** Randomly select pairs for reproduction.
  - **Batch Mating:** Mate all aliens in the population.
  - **Conditional Mating:** Mate only aliens above a health threshold.
- **Statistics:** View overall population statistics (counts, mating success rates, etc.).
- **Interactive CLI:** Menu-driven interface for seamless user interaction.

---

##  Requirements

- **Java SE 8** or higher.
- A terminal or command-line environment.

---

##  Installation & Usage

1. **Clone the repository:**
   ```sh
   git clone https://github.com/oktaykorkut/XenoGen.git
   cd XenoGen
   ```

2. **Compile the source file:**
   ```sh
   javac Xenogen.java
   ```

3. **Run the application:**
   ```sh
   java Xenogen
   ```

4. **Follow on-screen prompts** to:
   - Enter initial alien population size.
   - Choose mating and statistics options.
   - View simulation results and offspring generation.

---

##  Project Structure

```
XenoGen/
└── Xenogen.java    # Main class containing simulation logic and CLI
```
