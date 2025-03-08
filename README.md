# Fitts’ Law Project Summary
## Introduction
For my GitHub Pages site, I developed a project demonstrating Fitts’ Law, a fundamental principle in human-computer interaction. Fitts’ Law predicts the time required to move to a target based on its size and distance, making it a critical concept in UI/UX design, gaming, and accessibility. My goal was to create an interactive demonstration that visualizes how target size and distance affect movement efficiency.

## Project Overview
The project consists of a small interactive program where users can click on targets of varying sizes and distances. By measuring the time taken to reach each target, I can apply Fitts’ Law to analyze the relationship between target parameters and movement time. This data is then visualized to show clear trends that align with the mathematical model.

To achieve this, I implemented:

A graphical interface that displays multiple targets at different distances and sizes.
A timer function that tracks the time taken to move and click on a target.
A data collection module that records user interactions for analysis.
A mathematical validation step that compares the collected data with Fitts’ Law predictions.
Implementation Details
For coding, I used JavaScript to make the interface interactive and HTML/CSS for structuring the display. The project calculates the Index of Difficulty (ID) using the Fitts’ Law formula

MT = a + b * log₂(D / W + 1)


where:

MT is movement time,
D is the distance to the target,
W is the width of the target,
a and b are empirically determined constants.
By recording user test results, I could compare real-world performance with theoretical expectations.

## Results & Applications
My implementation successfully demonstrated that smaller and more distant targets take longer to click, aligning with Fitts’ Law. This reinforces the importance of designing user interfaces with larger, easily reachable buttons to enhance usability.

This project helped me deepen my understanding of human movement principles, user interaction analysis, and interface design best practices. Moving forward, I may expand it with dynamic difficulty adjustments, more user metrics, or applications in accessibility testing.
