# Photo Management Program

A Java-based photo management system that organizes and retrieves images using tag-based conditions, built with custom-implemented data structures (Linked List and Binary Search Tree). Done for CSC212 - Data Structures at King Saud University.

## Overview

The system lets users create albums that automatically group photos matching a set of tag conditions (e.g. `"beach AND sunset"`). Two approaches are implemented and compared:

- **Linear approach (`Album`):** scans the full photo list and checks each photo's tags against the given conditions.
- **Inverted index approach (`InvAlbum`):** uses a Binary Search Tree keyed by tag, where each tag points to a linked list of photos containing it, allowing faster lookups by intersecting per-tag photo lists instead of scanning everything.

Both linked lists and BSTs are implemented from scratch as generic ADTs, rather than using Java's built-in collections.

## Core Components

- **`Node` / `LinkedList<T>`** — generic singly linked list with standard ADT operations (`insert`, `remove`, `retrieve`, `findFirst`, `findNext`, etc.)
- **`BSTNode` / `BST<T>`** — generic binary search tree keyed by string, supporting `insert`, `findKey`, `removeKey`, and traversal
- **`Photo`** — stores a file path and a list of tags
- **`PhotoManager`** — manages a flat linked list of photos (add/delete/exists checks)
- **`InvIndexPhotoManager`** — manages both a photo list and a BST-based inverted index from tag to photo list, keeping the two in sync on add/delete
- **`Album`** — retrieves photos matching a condition string by filtering the full photo list
- **`InvAlbum`** — retrieves photos matching a condition string by looking up each tag in the inverted index and intersecting the resulting photo lists

## Design

Class relationships and method signatures are documented via UML-style diagrams in the report, covering `List<T>`, `LinkedList<T>`, `Node<T>`, `BSTNode<T>`, `BST<T>`, `Photo`, `PhotoManager`, `InvIndexPhotoManager`, `Album`, and `InvAlbum`.

## Performance Analysis

| Method | Time Complexity | Variables |
|---|---|---|
| `Album.getPhotos()` | O(n·c·t) | n = photo list size, c = number of condition tags, t = tags per photo |
| `InvAlbum.getPhotos()` | O(t·log(K) + n1·n2) | t = condition tags, K = total unique tags in BST, n1/n2 = sizes of intersected photo lists |

For larger datasets with many unique tags, `InvAlbum.getPhotos()` (inverted index) outperforms the linear `Album.getPhotos()` approach, since it avoids scanning the entire photo collection for every query.

## Conclusion

The project compares a naive linear search against a BST-backed inverted index for tag-based photo retrieval. The inverted index approach is significantly more efficient for larger collections, though its performance depends on the BST remaining reasonably balanced — an unbalanced tree can degrade lookup performance back toward linear time.

## Requirements

- Java (JDK 8+)

## Repository Structure

```
.
├── src/
│   ├── Node.java
│   ├── LinkedList.java
│   ├── BSTNode.java
│   ├── BST.java
│   ├── Photo.java
│   ├── PhotoManager.java
│   ├── InvIndexPhotoManager.java
│   ├── Album.java
│   └── InvAlbum.java
├── report/             # full project report (PDF)
└── README.md
```

## Authors

- Maha AL Dakhil — PhotoManager & InvIndexPhotoManager classes
- Amina Abdelkareem — Node, LinkedList, BSTNode, BST, Photo classes
- Raghad Alessa — Album & InvAlbum classes

King Saud University, CSC212, 2nd Semester 2024-2025.
