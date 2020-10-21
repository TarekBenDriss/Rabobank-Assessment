package com.bendrisstarek.rabobankassessment.util

/**
 * this class represents the collections utils
 */
object CollectionUtils {
    /**
     * this function checks if map is empty
     * @param collection
     * @return
     */
    @JvmStatic
    fun isNotEmpty(collection: Map<*, *>?): Boolean {
        return collection != null && collection.isNotEmpty()
    }

    /**
     * this function returns the size of a map
     * @param collection
     * @return
     */
    @JvmStatic
    fun size(collection: Map<*, *>): Int {
        return if (isNotEmpty(collection)) collection.size else 0
    }

    /**
     * this function checks if a collection is not empty
     * @param collection
     * @return
     */
    @JvmStatic
    fun isNotEmpty(collection: Collection<*>?): Boolean {
        return collection != null && !collection.isEmpty()
    }

    /**
     * this function returns the size of a collection
     * @param collection
     * @return
     */
    @JvmStatic
    fun size(collection: Collection<*>): Int {
        return if (isNotEmpty(collection)) collection.size else 0
    }

    /**
     * this function returns the size of many collections
     * @param collections
     * @return
     */
    @JvmStatic
    fun sizes(vararg collections: Collection<*>): Int {
        var sizes = 0
        for (collection in collections) {
            sizes += size(collection)
        }
        return sizes
    }

    /**
     * this function checks if an array is not empty
     * @param array
     * @param <T>
     * @return
    </T> */
    @JvmStatic
    fun <T> isNotEmpty(array: Array<T>?): Boolean {
        return array != null && array.isNotEmpty()
    }

    /**
     * this function returns the array's size
     * @param array
     * @param <T>
     * @return
    </T> */
    @JvmStatic
    fun <T> size(array: Array<T>): Int {
        return if (isNotEmpty(array)) array.size else 0
    }

    /**
     * this function returns a string from char sequence
     * @param charSequence
     * @return
     */
    @JvmStatic
    fun charSequenceToString(charSequence: CharSequence?): String {
        return charSequence?.toString() ?: ""
    }

    /**
     * this function returns a string from char sequence
     * @param array
     * @return
     */
    @JvmStatic
    fun charSequenceToString(array: Array<CharSequence?>): Array<String?> {
        val strings = arrayOfNulls<String>(array.size)
        for (i in array.indices) {
            strings[i] = charSequenceToString(array[i])
        }
        return strings
    }
}