#  AI Search Algorithms – Java Implementation

This repository contains two assignments developed as part of the **Artificial Intelligence** course.  
Each assignment implements classic AI search algorithms in Java, including Uniform Cost Search, A*, and Minimax.

---

##  Assignment Overview

### 1️ Cube Stacking – State-Space Search

Simulates moving colored cubes across three floors, aiming to reach a goal configuration with the minimum cost.

- **ask1a.java**: Uniform Cost Search (UCS)
- **ask1b.java**: A* Search with custom admissible heuristic
- Custom state representation using stack positions and levels
- Movement cost includes vertical and horizontal displacement

### 2️ Card Game – Game Tree Search

Solves a deterministic 2-player card distribution game using Minimax.

- **ask2.java**: Full recursive Minimax implementation without alpha-beta pruning
- Evaluates game states and chooses optimal moves for MAX player
- Supports up to 7-card distributions with variable turn outcomes

---

##  Project Structure

```
ai-search-algorithms-java/
├── src/
│   ├── ask1a.java         # UCS for cube problem
│   ├── ask1b.java         # A* for cube problem
│   └── ask2.java          # Minimax for game tree 
└── README.md
```

---

##  How to Compile & Run

```bash
javac src/*.java
java -cp src ask1a     # or ask1b / ask2 depending on task
```

---

##  License

For educational use only.  
Please do not submit this code as your own in academic environments.

---

##  Author

**Maria Tsatsari**  
GitHub: [maytsatsari](https://github.com/maytsatsari)
