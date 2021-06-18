package juuxel.accesswidener.idea.util

object Types {
    // If you have a manual $ in your class names, you are evil.
    // TODO: Should I actually generate the full set of $->. permutations?
    fun toJavaName(binaryName: String): String =
        binaryName.replace('/', '.').replace('$', '.')
}
