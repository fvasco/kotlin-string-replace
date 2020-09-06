package org.sample

fun String.replace(oldChar: Char, newChar: Char, ignoreCase: Boolean = false): String {
    // prefer case-insensitive platform implementation
    if (!ignoreCase) return (this as java.lang.String).replace(oldChar, newChar)

    return buildString(length) {
        this@replace.forEach { c ->
            append(if (c.equals(oldChar, ignoreCase)) newChar else c)
        }
    }
}

fun String.replace(oldValue: String, newValue: String, ignoreCase: Boolean = false): String? {
    // prefer case-insensitive platform implementation
    if (!ignoreCase) return (this as java.lang.String).replace(oldValue, newValue)

    var occurrenceIndex: Int = indexOf(oldValue, 0, ignoreCase)
    // FAST PATH: no match
    if (occurrenceIndex < 0) return this

    val oldValueLength = oldValue.length
    val searchStep = oldValueLength.coerceAtLeast(1)
    val newLengthHint = length - oldValueLength + newValue.length
    if (newLengthHint < 0) throw OutOfMemoryError()
    val stringBuilder = StringBuilder(newLengthHint)

    var i = 0
    do {
        stringBuilder.append(this, i, occurrenceIndex).append(newValue)
        i = occurrenceIndex + oldValueLength
        if (occurrenceIndex >= length) break
        occurrenceIndex = indexOf(oldValue, occurrenceIndex + searchStep, ignoreCase)
    } while (occurrenceIndex > 0)
    return stringBuilder.append(this, i, length).toString()
}
