package com.shuvaev

import kotlin.test.Test
import kotlin.test.assertEquals

class FilterTest {
    @Test
    fun testFilter() {
        val unfiltered: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
            intArrayOf(0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2))
        val filteredActual: Hierarchy = unfiltered.filter { nodeId -> nodeId % 3 != 0 }
        val filteredExpected: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 2, 5, 8, 10, 11),
            intArrayOf(0, 1, 1, 0, 1, 2))
        assertEquals(filteredExpected.formatString(), filteredActual.formatString())
    }

    @Test
    fun testFilterEvenNodes() {
        val unfiltered: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(2, 3, 4, 8, 6),
            intArrayOf(0, 1, 2, 1, 0))

        val filteredActual: Hierarchy = unfiltered.filter { nodeId -> nodeId % 2 == 0 }

        val filteredExpected: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(2, 8, 6),
            intArrayOf(0, 1, 0))

        assertEquals(filteredExpected.formatString(), filteredActual.formatString())
    }

    @Test
    fun testEmptyHierarchy() {
        val unfiltered: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(),
            intArrayOf())

        val filteredActual = unfiltered.filter { true }

        assertEquals("[]", filteredActual.formatString())
    }

    @Test
    fun testFilterRoot() {
        val unfiltered: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(0, 1, 2, 0, 1))

        val filteredActual = unfiltered.filter { nodeId -> nodeId != 1 }

        val filteredExpected: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(4, 5),
            intArrayOf(0, 1))

        assertEquals(filteredExpected.formatString(), filteredActual.formatString())
    }

    @Test
    fun testFilterParent() {
        val unfiltered: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(0, 1, 2, 3, 1))

        val filteredActual = unfiltered.filter { nodeId -> nodeId != 2 }

        val filteredExpected: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 5),
            intArrayOf(0, 1))

        assertEquals(filteredExpected.formatString(), filteredActual.formatString())
    }

    @Test
    fun testFilterLeaf() {
        val unfiltered: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(0, 1, 1, 1))

        val filteredActual = unfiltered.filter { nodeId -> nodeId != 3 }

        val filteredExpected: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 2, 4),
            intArrayOf(0, 1, 1))

        assertEquals(filteredExpected.formatString(), filteredActual.formatString())
    }

    @Test
    fun testNoNodesFiltered() {
        val unfiltered: Hierarchy = ArrayBasedHierarchy(
            intArrayOf(1, 2, 3),
            intArrayOf(0, 1, 2))

        val filteredActual = unfiltered.filter { true }

        assertEquals(unfiltered.formatString(), filteredActual.formatString())
    }
}