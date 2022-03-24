package processor

import java.util.Scanner
import kotlin.system.exitProcess

fun printMenu() {
    println()
    println("1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. Transpose matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit")
    print("Your choice: ")
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. Transpose matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit")
    print("Your choice: ")
    while (true) {
        when (scanner.nextLine()) {
            "0" -> {
                exitProcess(0)
            }
            "1" -> {
                print("Enter size of first matrix: ")
                val matrixA = Matrix(scanner.nextInt(), scanner.nextInt())
                println("Enter first matrix:")
                for (i in 0 until matrixA.rows) {
                    for (j in 0 until matrixA.columns) {
                        matrixA[i][j] = scanner.nextDouble()
                    }
                }
                print("Enter size of second matrix: ")
                val matrixB = Matrix(scanner.nextInt(), scanner.nextInt())
                println("Enter second matrix:")
                for (i in 0 until matrixB.rows) {
                    for (j in 0 until matrixB.columns) {
                        matrixB[i][j] = scanner.nextDouble()
                    }
                }
                if (matrixA.rows == matrixB.rows && matrixA.columns == matrixB.columns) {
                    println("The result is:")
                    (matrixA + matrixB).printMatrix()
                    printMenu()
                } else {
                    println("The operation cannot be performed.")
                    printMenu()
                }
            }
            "2" -> {
                print("Enter size of matrix: ")
                val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
                println("Enter matrix:")
                for (i in 0 until matrix.rows) {
                    for (j in 0 until matrix.columns) {
                        matrix[i][j] = scanner.nextDouble()
                    }
                }
                print("Enter constant: ")
                val number = scanner.nextDouble()
                println("The result is:")
                (matrix * number).printMatrix()
                printMenu()
            }
            "3" -> {
                print("Enter size of first matrix: ")
                val matrixA = Matrix(scanner.nextInt(), scanner.nextInt())
                println("Enter first matrix:")
                for (i in 0 until matrixA.rows) {
                    for (j in 0 until matrixA.columns) {
                        matrixA[i][j] = scanner.nextDouble()
                    }
                }
                print("Enter size of second matrix: ")
                val matrixB = Matrix(scanner.nextInt(), scanner.nextInt())
                println("Enter second matrix:")
                for (i in 0 until matrixB.rows) {
                    for (j in 0 until matrixB.columns) {
                        matrixB[i][j] = scanner.nextDouble()
                    }
                }
                if (matrixA.columns == matrixB.rows) {
                    println("The result is:")
                    (matrixA * matrixB).printMatrix()
                    printMenu()
                } else {
                    println("The operation cannot be performed.")
                    printMenu()
                }
            }
            "4" -> {
                println("1. Main diagonal\n2. Side diagonal\n3. Vertical line\n4.Horizontal line")
                print("Your choice: ")
                when (scanner.nextLine()) {
                    "1" -> {
                        print("Enter matrix size: ")
                        val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
                        println("Enter matrix:")
                        for (i in 0 until matrix.rows) {
                            for (j in 0 until matrix.columns) {
                                matrix[i][j] = scanner.nextDouble()
                            }
                        }
                        println("The result is:")
                        (matrix.transposeByMainDiagonal()).printMatrix()
                        printMenu()
                    }
                    "2" -> {
                        print("Enter matrix size: ")
                        val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
                        println("Enter matrix:")
                        for (i in 0 until matrix.rows) {
                            for (j in 0 until matrix.columns) {
                                matrix[i][j] = scanner.nextDouble()
                            }
                        }
                        println("The result is:")
                        (matrix.transposeBySideDiagonal()).printMatrix()
                        printMenu()
                    }
                    "3" -> {
                        print("Enter matrix size: ")
                        val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
                        println("Enter matrix:")
                        for (i in 0 until matrix.rows) {
                            for (j in 0 until matrix.columns) {
                                matrix[i][j] = scanner.nextDouble()
                            }
                        }
                        println("The result is:")
                        (matrix.transposeByVerticalLine()).printMatrix()
                        printMenu()
                    }
                    "4" -> {
                        print("Enter matrix size: ")
                        val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
                        println("Enter matrix:")
                        for (i in 0 until matrix.rows) {
                            for (j in 0 until matrix.columns) {
                                matrix[i][j] = scanner.nextDouble()
                            }
                        }
                        println("The result is:")
                        (matrix.transposeByHorizontalLine()).printMatrix()
                        printMenu()
                    }
                }
            }
            "5" -> {
                print("Enter matrix size: ")
                val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
                println("Enter matrix:")
                for (i in 0 until matrix.rows) {
                    for (j in 0 until matrix.columns) {
                        matrix[i][j] = scanner.nextDouble()
                    }
                }
                println("The result is:")
                println(matrix.determinant)
                printMenu()
            }
            "6" -> {
                print("Enter matrix size: ")
                val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
                if (matrix.rows < 3 || matrix.columns < 3) {
                    println("This matrix doesn't have an inverse.")
                    printMenu()
                } else {
                    println("Enter matrix:")
                    for (i in 0 until matrix.rows) {
                        for (j in 0 until matrix.columns) {
                            matrix[i][j] = scanner.nextDouble()
                        }
                    }
                    println("The result is:")
                    matrix.inverse().printMatrix()
                    printMenu()
                }
            }
        }
    }
}
