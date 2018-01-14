package io.data2viz.viz

import io.data2viz.color.Color
import io.data2viz.path.PathAdapter

/**
 * Common interface to bootstrap visualization into different platform contexts.
 */
interface VizContext : ParentItem {
    fun applyStyleSheet(styleSheet: StyleSheet)
}

interface VizItem


interface ParentItem: Transformable {
    fun group(init: ParentItem.() -> Unit): ParentItem
    fun circle(init: CircleVizItem.() -> Unit): CircleVizItem
    fun rect(init: RectVizItem.() -> Unit): RectVizItem
    fun line(init: LineVizItem.() -> Unit): LineVizItem
    fun text(init: TextVizItem.() -> Unit): TextVizItem
    fun path(init: PathVizItem.() -> Unit): PathVizItem
    fun setStyle(style:String)
    fun style(init: Style.() -> Unit)
}

/**
 * Indicate an element on which we can apply a Transformation.
 * todo implement other transformation (rotate, ...)
 */
interface Transformable {
    fun transform(init: Transform.() -> Unit)
}

interface Transform {
    fun translate(x: Double = 0.0, y: Double = 0.0)
}

interface PathVizItem : VizItem, Shape, PathAdapter

interface CircleVizItem : VizItem, Shape, Transformable {
    var cx: Double
    var cy: Double
    var radius: Double
}

interface LineVizItem : VizItem, Shape, Transformable {
    var x1: Double
    var y1: Double
    var x2: Double
    var y2: Double
}
interface RectVizItem : VizItem, Shape, Transformable {
    var x: Double
    var y: Double
    var width: Double
    var height: Double
    var rx: Double
    var ry: Double
}

interface TextVizItem : VizItem, Transformable, HasFill {
    var x: Double
    var y: Double
    var textContent: String
    var anchor: TextAnchor
    var baseline: TextAlignmentBaseline
}

/**
 * The text-anchor attribute is used to horizontally align ([START], [MIDDLE] or [END]-alignment) a string of 
 * text relative to a given point.
 * See [CSS text-anchor][https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/text-anchor]
 */
enum class TextAnchor {
    START, 
    MIDDLE, 
    END}


/**
 * Vertical alignment of a text 
 */
enum class TextAlignmentBaseline {
    HANGING,
    MIDDLE,
    BASELINE
}

interface Shape : HasFill, HasStroke


/**
 * All properties of stroke
 * Todo add remaining common properties
 */
interface HasStroke {
    var stroke: Color?
    var strokeWidth: Double?
}

interface HasFill {
    var fill: Color?
}

interface VizFactory<V : VizItem> {
    fun createVizItem(): V
}

data class Margins(val top: Double, val right: Double = top, val bottom: Double = top, val left: Double = right) {
    val hMargins = right + left
    val vMargins = top + bottom
}
