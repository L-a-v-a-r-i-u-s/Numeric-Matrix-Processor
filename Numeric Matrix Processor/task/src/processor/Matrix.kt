package processor

import kotlin.math.pow

class Matrix(val rows: Int, val columns: Int) {
    private val matrix = Array(rows) { DoubleArray(columns) }

    val determinant: Double
        get() {
            return getDeterminant(this)
        }

    private fun getDeterminant(m: Matrix): Double {
        if (m.rows == 1) {
            return m[0][0]
        }
        if (m.rows == 2) {
            return m[0][0] * m[1][1] - m[1][0] * m[0][1]
        }

        var det = 0.0
        val n = m.rows
        val subMatrix = Matrix(n - 1, n - 1)
        for (x in 0 until n) {
            for ((subi, i) in (1 until n).withIndex()) {
                var subj = 0
                for (j in 0 until n) {
                    if (j == x) continue
                    subMatrix[subi][subj] = m[i][j]
                    subj++
                }
            }
            det += (-1.0).pow(x.toDouble()) * m[0][x] * getDeterminant(subMatrix)
        }

        return det
    }

    fun printMatrix() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                print("${matrix[i][j]} ")
            }
            println()
        }
    }

    private fun cofactorMatrix(): Matrix {
        val size = this.rows
        val cofactorMatrix = Matrix(size, size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                cofactorMatrix[i][j] = getDeterminant(getSubMatrix(i, j))
            }
        }
        return cofactorMatrix
    }

    private fun getSubMatrix(x: Int, y: Int): Matrix {
        val size = this.rows
        val subsize = this.rows - 1
        val sub = Matrix(subsize, subsize)

        var subi = 0
        var subj = 0
        for (i in 0 until size) {
            if (i == x) continue
            for (j in 0 until size) {
                if (j == y) continue
                sub[subi][subj] = if (((i + 1) + (j + 1)) % 2 == 0) this[i][j] else -this[i][j]
                subj++
            }
            subj = 0
            if (subi + 1 == subsize) {
                subi = 0
            } else {
                subi++
            }
        }

        return sub
    }

    fun inverse(): Matrix {
        return cofactorMatrix().transposeByMainDiagonal() * (1 / determinant)
    }

    operator fun times(number: Double): Matrix {
        val newMatrix = Matrix(rows, columns)
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                newMatrix[i][j] = matrix[i][j] * number
            }
        }
        return newMatrix
    }

    operator fun get(i: Int): DoubleArray = matrix[i]
    operator fun plus(secondMatrix: Matrix): Matrix {
        return if (rows == secondMatrix.rows && columns == secondMatrix.columns) {
            val newMatrix = Matrix(rows, columns)
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    newMatrix[i][j] = matrix[i][j] + secondMatrix[i][j]
                }
            }
            newMatrix
        } else {
            throw Exception("Rows and columns of matrices are not equal!")
        }
    }

    operator fun times(secondMatrix: Matrix): Matrix {
        return if (columns == secondMatrix.rows) {
            val newMatrix = Matrix(rows, secondMatrix.columns)
            for (i in 0 until newMatrix.rows) {
                for (j in 0 until newMatrix.columns) {
                    val rowMatrix1 = this[i]
                    val colMatrix2 = DoubleArray(columns)
                    for (r in 0 until secondMatrix.rows) {
                        colMatrix2[r] = secondMatrix[r][j]
                    }
                    var sum = 0.0
                    for (l in rowMatrix1.indices) {
                        sum += rowMatrix1[l] * colMatrix2[l]
                    }
                    newMatrix[i][j] = sum
                }
            }
            newMatrix
        } else {
            throw Exception("The number of columns of the first matrix is not equal to the number of rows of the second matrix!")
        }
    }

    fun transposeByMainDiagonal(): Matrix {
        val result = Matrix(rows = columns, columns = rows)
        for (i in 0 until this.rows) {
            for (j in 0 until this.columns) {
                result[j][i] = this[i][j]
            }
        }
        return result
    }

    fun transposeBySideDiagonal(): Matrix {
        return this.transposeByMainDiagonal().transposeByVerticalLine().transposeByHorizontalLine()
    }

    fun transposeByVerticalLine(): Matrix {
        val result = Matrix(rows, columns)
        for (i in 0 until rows) {
            for (j in columns - 1 downTo 0) {
                result[i][columns - 1 - j] = this[i][j]
            }
        }
        return result
    }

    fun transposeByHorizontalLine(): Matrix {
        val result = Matrix(rows, columns)
        for (i in rows - 1 downTo 0) {
            for (j in 0 until columns) {
                result[rows - 1 - i][j] = this[i][j]
            }
        }
        return result
    }
}
