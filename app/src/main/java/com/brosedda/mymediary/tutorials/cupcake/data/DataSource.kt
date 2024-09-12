package com.brosedda.mymediary.tutorials.cupcake.data

import com.brosedda.mymediary.R

/**
 * @param first value is the source ID for the string to display on each button
 * @param second actual quantity of cupcakes
 */
typealias QuantityOptions = Pair<Int, Int>

object DataSource {
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    val quantityOptions = listOf(
        QuantityOptions(R.string.one_cupcake, 1),
        QuantityOptions(R.string.six_cupcakes, 6),
        QuantityOptions(R.string.twelve_cupcakes, 12)
    )
}