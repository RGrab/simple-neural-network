# simple-neural-network
## General Information
This is a Java implementation of a simple 3-layer neural network that interprets handwritten digits.

## Getting Started

### Git LFS
Before cloning this project, be sure to install Git Large File Storage:

1. Go to https://git-lfs.github.com/ and install Git LFS.
2. If you cloned the project first, then downloaded LFS, run `git lfs pull` in the project to pull the large files.

### JAMA
This project uses the Java Matrix package known as [JAMA](http://math.nist.gov/javanumerics/jama/). In order to run this program, JAMA needs to be installed.

1. Download Jama-1.0.3.jar (http://math.nist.gov/javanumerics/jama/Jama-1.0.3.jar)
2. Make sure that you have read permissions, and take note of the file path which will be used later.

## Running the Project

### Compile the project:
* Run `javac -cp <path to JAMA> *.java`

### Run the project:
**Note:** After running the command below, it may take a few minutes to load because the neural network is training itself to recognize the numbers.

* For Linux:
  * Run `java -cp <path to JAMA> main`

* For Windows:
  * Run `java -cp <path to JAMA>; main` 

### disclaimer
Although the this program is about 95% accurate, with the testing data in its current iteration it is significantly less accurate with user drawn numbers, and is still very much a work in progress when it comes to improving the accuracy of this feature.

## Tools Used
* Git LFS
* JAMA - (http://math.nist.gov/javanumerics/jama/)
* MNIST data - (https://en.wikipedia.org/wiki/MNIST_database)

