package juuxel.accesswidener.idea

enum class AccessType(val sourceCode: String) {
    ACCESSIBLE("accessible"),
    EXTENDABLE("extendable"),
    MUTABLE("mutable");

    companion object {
        val FOR_CLASS: Set<AccessType> = setOf(ACCESSIBLE, EXTENDABLE)
        val FOR_METHOD: Set<AccessType> = setOf(ACCESSIBLE, EXTENDABLE)
        val FOR_FIELD: Set<AccessType> = setOf(ACCESSIBLE, MUTABLE)
    }
}
