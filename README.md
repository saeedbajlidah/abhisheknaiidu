# Design and Analysis of Algorithms - Java Implementation 🚀

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Course](https://img.shields.io/badge/Course-CSEN3302%2FCSE353-blue.svg)](#)
[![University](https://img.shields.io/badge/University-Hafr%20Al--Batin-maroon.svg)](https://www.uhb.edu.sa)

A Java CLI application developed for the *Design and Analysis of Algorithms (CSEN 3302 / CSE 353)** course at the University of Hafr Al Batin.

> Primary Author Focus: 
I was fully responsible for the end-to-end design, implementation, index bounds validation, and integration testing of Algorithm 1: Merging Two Sorted Sub-arrays.


 Main Highlight: Merging Two Sorted Sub-arrays (Algorithm 4)

*Developed & Implemented by Saeed Bagulidah

 Overview
Combines two contiguous, individually pre-sorted sub-arrays $A[p..q]$ and $A[q+1..r]$ into a single fully sorted sub-array $A[p..r]$ in linear time without re-sorting the array from scratch.

 How It Works
1. Uses two pointers to walk through copied left and right partitions ($L$ and $R$ created via `Arrays.copyOfRange`).
2. Compares current elements from both sub-arrays and copies the smaller element back into the primary array $A$
3. Features robust input boundary validation ($0 \le p \le q < r < m$) to guarantee zero runtime bounds errors.



 Other Algorithms in the Application (Brief Overview)

- Algorithm 7: Selection Problem (k-th Smallest): 
  Determines the $k$-th smallest value in an unsorted array using the deterministic Median-of-Medians strategy paired with 3-way partitioning  
  *(Time: Worst-case $\Theta(n)$)

- Algorithm 15: Kruskal's Minimum Spanning Tree (MST): 
  Constructs a Minimum Spanning Tree for weighted undirected graphs using Disjoint-Set Union (DSU) with Path Compression  
  (Time: $O(E \log E)$)


 Team & Course Information

- Primary Contributor (Algorithm 1) Saeed Bagulidah
- Team Members : Abdulmalik Alshammari, Faisal Al-Suhaymi
- nstructor:** Dr. Muhammad Akhlaq


 Repository Structure

```text
.
├── ProjectMain.java                  # Java CLI driver containing all source code
├── PROJECT REPORT — Group 4.docx     # Academic project report
├── presentationGroup-4.pptx          # Project presentation slides
└── README.md                         # Project documentation
