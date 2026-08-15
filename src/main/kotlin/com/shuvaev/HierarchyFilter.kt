package com.shuvaev

/**
 * A node is present in the filtered hierarchy iff its node ID passes the predicate and all of its ancestors pass it as well.
 */
fun Hierarchy.filter(nodeIdPredicate: (Int) -> Boolean): Hierarchy {
    val filteredNodeIds = IntArray(size)
    val filteredDepths = IntArray(size)

    var filteredSize = 0
    var excludedDepth = -1

    for (i in 0 until size) {
        val currentDepth = depth(i)

        if (excludedDepth >= 0) {
            if (currentDepth > excludedDepth) {
                continue
            }
            excludedDepth = -1
        }

        val currentNodeId = nodeId(i)

        if (!nodeIdPredicate(currentNodeId)) {
            excludedDepth = currentDepth
            continue
        }

        filteredNodeIds[filteredSize] = currentNodeId
        filteredDepths[filteredSize] = currentDepth
        filteredSize++
    }

    return ArrayBasedHierarchy(
        filteredNodeIds.copyOf(filteredSize),
        filteredDepths.copyOf(filteredSize)
    )
}