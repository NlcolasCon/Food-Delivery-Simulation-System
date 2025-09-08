# Food Delivery System (Java)

A simulation of a **food delivery platform** that models orders, cooking times, and delivery scheduling using multiple algorithms.  
The system reads orders and historical delivery data from text files, simulates kitchen and driver workflows, and reports KPIs such as on-time rate, average delay, and customer satisfaction.

---

## Project Explanation

### What it models
- **Orders** with timestamps, locations, and item mixes.
- **Menu items** with defined **cook times** (e.g., `Pitta` = 5 minutes).
- **Delivery scheduling algorithms** (three variants) to assign and route orders.
- **KPIs / Metrics** such as number of happy customers, average lateness, and per-algorithm statistics.

### Architecture (high level)
- **Domain models** (e.g., `Order`, food item classes like `Pitta`, etc.)
- **Algorithm interface** (contract for all scheduling strategies).
- **Implementations** of three delivery algorithms (plug-and-play via the interface).
- **Simulation runner** to load data, run scenarios, and print results.

> The design cleanly separates **business logic** (orders, cook times) from **strategy** (which algorithm to use), so you can add or swap algorithms without touching the core.

---

## Repository Structure
Food-Delivery-System/
┣ src/
┃ ┣ hw4/
┃ ┃ ┣ Algorithms.java 
┃ ┃ ┣ Pitta.java 
┃ ┃ ┗ ... # Order, Driver, AlgorithmA/B/C, App/Main, etc.
┣ data/
┃ ┣ orders.txt
┃ ┗ deliveries.txt
┣ LICENSE # MIT (recommended)
┗ README.md # This file

---

## Features
- Pluggable **scheduling algorithms** via a common interface
- Configurable **cook times** per item
- Reads **orders** and **deliveries** from text files
- Computes and prints **KPIs** (on-time %, avg delay, satisfied customers)
- Lightweight, **no external dependencies**

---

## Typical CLI options (example)
- algo {A|B|C}: which scheduling strategy to run
- orders <path>: path to orders file
- deliveries <path>: path to deliveries file

## Output (example KPIs)
- Happy customers: 142
- Average delay: 6.8 minutes
- On-time rate: 74%
- Breakdown by algorithm: A vs B vs C
- (Exact fields depend on your print(...) and simulation logic.)

## Technologies
- Language: Java
- Paradigm: OOP + Strategy pattern
- Build/Run: javac / java or any Java IDE (Eclipse, IntelliJ IDEA, VS Code)

## Data Files
- data/orders.txt – example input with order coordinates/timestamps/items
- data/deliveries.txt – example historical/simulated delivery logs

## Usage

### Compile
```bash
javac -d bin src/**/*.java


👤 Author
Developed by Nicolas Constantinou
2025
