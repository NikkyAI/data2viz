package io.data2viz.hierarchy

import io.data2viz.test.TestBase
import kotlin.test.Test

class HierarchyTests : TestBase() {

    // DO NOT CHANGE VALUES
    val width = 500.0
    val height = 400.0

    data class Hierarchical(
        val value: Int,
        val x0: Double = .0,
        val y0: Double = .0,
        val x1: Double = .0,
        val y1: Double = .0,
        val subElements: List<Hierarchical>? = null
    )

    val testTreeLight = Hierarchical(
        1, .0, .0, .0, .0, listOf(
            Hierarchical(11, .0, .0, .0, .0),
            Hierarchical(12, .0, .0, .0, .0)
        )
    )

    val testTreeMid =
        Hierarchical(
            1, 0.0, 0.0, 500.0, 100.0, subElements = listOf(
                Hierarchical(
                    11, 0.0, 100.0, 313.0, 200.0, subElements = listOf(
                        Hierarchical(111, 125.0, 200.0, 188.0, 300.0),
                        Hierarchical(112, 188.0, 200.0, 250.0, 300.0),
                        Hierarchical(113, 250.0, 200.0, 313.0, 300.0),
                        Hierarchical(
                            114, 0.0, 200.0, 125.0, 300.0, subElements = listOf(
                                Hierarchical(1141, 0.0, 300.0, 63.0, 400.0),
                                Hierarchical(1142, 63.0, 300.0, 125.0, 400.0)
                            )
                        )
                    )
                ),
                Hierarchical(
                    12, 313.0, 100.0, 500.0, 200.0, subElements = listOf(
                        Hierarchical(
                            121, 313.0, 200.0, 438.0, 300.0, subElements = listOf(
                                Hierarchical(1211, 313.0, 300.0, 375.0, 400.0),
                                Hierarchical(1212, 375.0, 300.0, 438.0, 400.0)
                            )
                        ),
                        Hierarchical(122, 438.0, 200.0, 500.0, 300.0)
                    )
                )
            )
        )

    @Test
    fun buildHierarchy() {
        val hierarchy = hierarchy(Hierarchical(0, .0, .0, .0, .0), { it.subElements })

        hierarchy.descendants().size shouldBe 1
        hierarchy.leaves().size shouldBe 1
    }

    @Test
    fun buildHierarchyFull() {
        val hierarchy = hierarchy(testTreeMid, { it.subElements })

        hierarchy.descendants().size shouldBe 3
        hierarchy.leaves().size shouldBe 8
    }
}