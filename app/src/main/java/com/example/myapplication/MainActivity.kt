import java.util.Scanner

fun steadyGene(gene: String): Int {
    val n = gene.length
    val targetCount = n / 4

    // Create a mutable map to count frequencies of A, C, T, and G in the gene
    val geneCount = mutableMapOf('A' to 0, 'C' to 0, 'T' to 0, 'G' to 0)

    // Count initial frequencies of each character
    for (ch in gene) {
        geneCount[ch] = geneCount[ch]!! + 1
    }

    // If the gene is already steady, no need to replace any substring
    if (geneCount.values.all { it == targetCount }) {
        return 0
    }

    // Sliding window approach to find the minimum length of the substring to replace
    var minLength = n
    var left = 0

    for (right in gene.indices) {
        val rightChar = gene[right]
        geneCount[rightChar] = geneCount[rightChar]!! - 1

        // Check if the remaining part of the gene outside the current window is steady
        while (geneCount['A']!! <= targetCount && geneCount['C']!! <= targetCount &&
            geneCount['T']!! <= targetCount && geneCount['G']!! <= targetCount) {

            // Calculate the length of the current window
            minLength = minOf(minLength, right - left + 1)

            // Shrink the window from the left
            val leftChar = gene[left]
            geneCount[leftChar] = geneCount[leftChar]!! + 1
            left++
        }
    }

    return minLength
}

fun main() {
    val gene = "GAAATAAA" // Example input
    println("Minimum length of substring to replace: ${steadyGene(gene)}")
}