> **Note: Language Context**
>
> Please note that this project was written in **German** as part of my `Ausbildung` (German vocational training). Therefore, all variable names, comments, and console outputs (`System.out.println`) within the source code are in German.

---

# Java Number Guessing Game (v1) & AI Solver (v2)

This repository shows the evolution of a simple number-guessing game into a complex AI-powered solver. Both versions are in the same repository to demonstrate the progression in logic and algorithmic complexity.

---

## [v1 - The Game Menu](./v1/)

The first version is a user-friendly, menu-based application with three distinct game modes:

* **Level 1 (Higher/Lower):** The classic game. The player gets 9 attempts to guess a number, receiving only "higher" or "lower" feedback.
* **Level 2 (Hot/Cold):** A game with more detailed feedback (e.g., "Fast da!", "Relativ nahe").
* **Level 3 (Player vs. Simple AI):** The player competes against a simple AI. This AI uses a "smart random" guess, picking a random number within the currently known valid range (`pcmin` to `pcmax`).

---

## [v2 - The AI Solver](./v2/)

The second version is **not** a simple game; it is an **AI Algorithm Demo** that shows how a computer can solve the "hot/cold" guessing game with maximum efficiency.

### Core Concept: AI-Powered "Pruning"

This program simulates a turn-based game (Player vs. AI) where the "brain" is the real star.

1.  **Possibility List:** The AI maintains a "list of all possible numbers" (`mooegliche_Zahlen`, e.g., 0-100).
2.  **Guessing Strategy (Binary Search):** The AI (`kiTipp`) doesn't guess randomly. It always picks the most efficient guess: the **middle element** of the *remaining* possibility list (`mooegliche_Zahlen.get(size / 2)`).
3.  **AI "Brain" (`uptade_List`):** This is the core algorithm. After *every* guess (the player's or its own), it takes the "hot/cold" feedback:
    * It parses the feedback (e.g., "4-10 away").
    * It **"prunes" (budar)** the possibility list by removing all numbers that are now impossible (`removeRange`).
4.  **Visualization (`print`):** The program includes a unique print function that visualizes the AI's "brain," printing `*` for numbers that are still possible and `.` for numbers that have been eliminated.
